/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.glass.events.KeyEvent;
import game.graphics.Renderer;
import game.input.InputHandler;
import game.units.Enemy;
import game.units.FireTowerType;
import game.units.IUnits;
import game.units.Tower;
import game.units.ZombiType;
import game.MyLevel;
import game.units.IEnemyType;
import game.units.ITowerType;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Алексей
 */
public class Game extends Canvas{
    private static final long serialVersionUID = 1L;
    public static int width = 900;
    public static int height = 600;
    public static int scale = 3;
   
    private Renderer renderer;
    private BufferStrategy bs = null;
    private Thread thread;
    private boolean running = false;
    private String title = "Game";
    private JFrame jFrame;
    private Player player;
    private ArrayList<Enemy> enemies;
    private long lastTimeGenerate;
    ArrayList<Enemy> killedEnemies;
    private MyLevel level;
    private ITowerType fireTowerType;
    private IEnemyType zombiType;
    public Game() {
        
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        jFrame = new JFrame(title);
        renderer = new Renderer(width, height);
        jFrame.addKeyListener(new InputHandler());
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                if(e.getY() < 310|| e.getY() > 400)
                    player.buyTower(e.getX(), e.getY(), fireTowerType);
            }

            @Override
            public void mousePressed(MouseEvent e) {
              
            }

            @Override
            public void mouseReleased(MouseEvent e) {
             
            }

            @Override
            public void mouseEntered(MouseEvent e) {
             
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
            } 
        });
        fireTowerType = new FireTowerType();
        zombiType = new ZombiType(); 
        player = new Player();
        level = new MyLevel();
        killedEnemies = new ArrayList();
        enemies = new ArrayList(); 
    }
    
    public synchronized void start() {
        init();
        running = true;
        thread = new Thread(()-> {
            long lastTime = System.nanoTime();
            long time = System.currentTimeMillis();
            lastTimeGenerate = System.currentTimeMillis();
            double partTime = 1_000_000_000.0 / 60.0;
            double delta = 0;
            int updates = 0;
            int frames = 0;
            while(running) {
                long nowTime = System.nanoTime();
                delta += (nowTime - lastTime);
                lastTime = nowTime;
                if (delta >= partTime) {
                   update();
                   updates++;
                   delta = 0;
                }
                render();
                frames++;
                if (System.currentTimeMillis() - time > 1000) {
                    time += 1000;
                    jFrame.setTitle(title + " | Frames: " + frames + ", Updates: " + updates);
                    updates = 0;
                    frames = 0;
                }
            }
        });
        thread.start();
    }
    
    public synchronized void stop() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void render() {
        if (bs == null) {
            createBufferStrategy(3);
            bs = getBufferStrategy();
            return;
        }

        Graphics g2 = bs.getDrawGraphics();
        renderer.render(enemies, player.getTowers(), player, level, g2);
        
        bs.show();
    }
    
    private void generateEnemies() {
        if(System.currentTimeMillis() - level.getTimeLast() > level.getTimeBetween()){
            for(int i = 0; i < level.getCount(); i++) {
                Enemy e = new Enemy(0, 270 + new Random().nextInt(70), zombiType);
                enemies.add(e);
            }
            level.nextLevel();
            level.setTimeLast(System.currentTimeMillis());
        }
    }

    private void update() {
        generateEnemies();
        
        if(enemies.isEmpty())
            return;
        for(Enemy e : enemies) {
            e.doNextStep();
            if( e.isFinished(width) ) {
               player.setLives(player.getLives() - 1);
               killedEnemies.add(e);
               if(player.getLives() == 0)
                   running = false;
           }
        }
        killEnemy();
    }
    
    private void killEnemy() {
        boolean isAdded = false;
        deleteEnemy(killedEnemies);
        killedEnemies.clear();
        for(Enemy e : enemies) {
            for(Tower t : player.getTowers()) {
                if(e.isAlive()) {
                    // переписать самодокументированно !
                    isAdded = false;
                    if (Mathemat.GET_LENGTH(e.getX() - e.getWidth(), e.getY() - e.getHeight(), 
                            t.getCenterX() + t.getRadius() / 2, 
                            t.getCenterY() + t.getRadius() / 2) <= t.getRadius()) {
                        t.shoot(e);
                    }
                } else if (!isAdded) {
                    player.setMoney(player.getMoney() + e.getCost());
                    player.setScore(player.getScore() + 1);
                    isAdded = true;
                    killedEnemies.add(e);
                }
            }
        }  
    }
    
    private void deleteEnemy(ArrayList<Enemy> killedEnemies) {
        for(Enemy e : killedEnemies) {
            enemies.remove(e);
        }
    }
    
    private void init() {
        jFrame.setResizable(false);
        jFrame.setTitle("title");
        jFrame.add(this);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
    }
    
    public static void main(String[] args) {
        new Game().start(); 
    }
    
}
