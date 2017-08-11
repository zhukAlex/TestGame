/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics;

import game.MyLevel;
import game.units.Enemy;
import game.units.IUnits;
import game.Player;
import game.units.Tower;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public class Renderer {
    private int width, height;
    
    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void clear(Graphics g2) {
       g2.setColor(Color.gray);
       g2.fillRect(0, 0, width, height);
       g2.setColor(Color.black);
    }
    
    private void drawPlayer(Graphics g, Player p, MyLevel l, int count) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 20) );
        g.drawString(p.getMoney() + "", 10, 30);
        g.drawImage(p.getImgMoney(), 40, 10, 30, 30, null);
        
        g.drawString(p.getScore() + "", 100, 30);
        g.drawImage(p.getImgScore(), 130, 10, 30, 30, null);
        
        g.drawString(p.getLives()+ "", 190, 30);
        g.drawImage(p.getImgLive(), 220, 10, 30, 30, null);
        
        g.drawImage(p.getImgTime(), width - 50, 10, 30, 30, null);
        g.drawString(30 - (-1 * (l.getTimeLast() - System.currentTimeMillis()) / 1000 ) + "", width - 80, 30);
        
        g.drawImage(p.getImgVawe(), width - 140, 10, 30, 30, null);
        g.drawString(l.getLevel() + "", width - 160, 30);
        
        g.drawImage(p.getImgCount(), width - 220, 10, 30, 30, null);
        g.drawString(count + "", width - 235, 30);
    }
    
    public void render(ArrayList<Enemy> enemies, ArrayList<Tower> towers, Player p, MyLevel level, Graphics g2) {
        clear(g2);
        try {
            
            Image img = ImageIO.read(new File("t.png"));
            drawPlayer(g2, p, level, enemies.size());
            g2.drawImage(img, 0, 50, width, height, null);
            for(Enemy e : enemies) {
                g2.drawImage(e.getImg(), e.getX(), e.getY(), e.getWidth(), e.getHeight(), null); 
            }
            for(Tower t : towers) {
                g2.setColor(Color.GREEN);
                g2.drawOval(t.getCenterX(), t.getCenterY(),
                        t.getRadius(), t.getRadius());
                g2.drawImage(t.getImg(), t.getX(), t.getY(), t.getWidth(), t.getImg().getHeight(null), null); 
               
            }
        } catch (IOException ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g2.dispose();
    }
}
