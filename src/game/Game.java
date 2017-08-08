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
import game.units.IUnits;
import game.units.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
   
    private Renderer renderer;
    private BufferStrategy bs = null;
    private Thread thread;
    private boolean running = false;
    private String title = "Game";
    private JFrame jFrame;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<IUnits> units;
    private long lastGenerate = 0;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        jFrame = new JFrame(title);
        renderer = new Renderer(width, height, pixels);
        jFrame.addKeyListener(new InputHandler());
        player = new Player(100, 0);
        units = new ArrayList();
        units.add(player);
        
        enemies = new ArrayList();
        for(int i = 0; i < 10; i++) {
            Enemy enemy = new Enemy(25 * i, 10 * i);
            units.add(enemy);
            enemies.add(enemy);
        }
        
    }
    
    public synchronized void start() {
        init();
        running = true;
        thread = new Thread(()-> {
            long lastTime = System.nanoTime();
            long time = System.currentTimeMillis();
            double partTime = 1_000_000_000.0 / 60.0;
            double delta = 0;
            int updates = 0;
            int frames = 0;
            lastGenerate = System.currentTimeMillis();
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

        Graphics graphics = bs.getDrawGraphics();
        renderer.clear();
        graphics.drawImage(image, 0, 0, width * scale, height * scale, null);
        renderer.render(units);
        graphics.drawImage(image, 0, 0, width * scale, height * scale, null);
        graphics.dispose();
        bs.show();
    }
    
    private void randomEnemy() {
        int x, y;
        
        if (System.currentTimeMillis() - lastGenerate > 5000) {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
              Enemy e = new Enemy(random.nextInt(200), random.nextInt(2));
              enemies.add(e);
              units.add(e);
            }  
            lastGenerate = System.currentTimeMillis();
        }
        for(Enemy e : enemies) {
            x = e.getX();
            y = e.getY() + 1;
            if(y <= 0)
                y = height;
            else if(y >= height)
                y = 0;
            e.setY(y);
            
            if (player.getX() >= e.getX() && 
                player.getX() + player.getWidth() <= e.getX() + e.getWidth() ) {
                System.out.println("xxx");
                if (
                    player.getY() >= e.getY() && 
                    player.getY() + player.getHeight() < e.getY() + e.getHeight()
                    ) {
                    System.out.println("yyy");
                    units.remove(player);
                    jFrame.setTitle(title + " | Сори, ты проиграл, браааааааа!");
                    running = false;
                }
            }
            
        }
    }

    private void update() {
        int x, y;
        if (InputHandler.isKeyPressed(KeyEvent.VK_LEFT)){
            x = player.getX() - player.getSpeed();
            if(x < 0) x = width - 1;
            if(x > width) x = 0;
            player.setX(x);
        }
        if (InputHandler.isKeyPressed(KeyEvent.VK_RIGHT)){
            x = player.getX() + player.getSpeed();
            if(x < 0) x = width - 1;
            if(x > width) x = 0;
            player.setX(x);
        }
        if (InputHandler.isKeyPressed(KeyEvent.VK_UP)){
            y = player.getY() - player.getSpeed();
            if(y <= 0) 
                y = height - 1;
            if(y >= height) 
                y = 0;
            player.setY(y);
        }
        if (InputHandler.isKeyPressed(KeyEvent.VK_DOWN)) {
            y = player.getY() + player.getSpeed();
            if(y <= 0) 
                y = height - 1;
            if(y >= height) 
                y = 0;
            player.setY(y);
        }
        randomEnemy();
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
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Game().start(); 
    }
    
}
