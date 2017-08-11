/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public class FireTowerType extends AbstractTowerType implements ITowerType{
    private int power;
    private int cost;
    private long lastShoot;
    
    public FireTowerType() {
        lastShoot = System.currentTimeMillis();
        power = 2;
        cost = 10;
        width = 100;
        height = 150;
        try {
            imgBullet = ImageIO.read( new File("fireBall.png") );
            img = ImageIO.read( new File("secondTower.png") );
        } catch (IOException ex) {
            Logger.getLogger(FireTowerType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void shoot(Enemy enemy) {
        if(System.currentTimeMillis() - lastShoot >= 1000) {
            enemy.shoot(power);
            lastShoot = System.currentTimeMillis();
        }
    }
    
    public int getPower() {
        return power;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    @Override
    public Image getImage() {
        Image tempImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tempImg.getGraphics();
        g.drawImage(img, 0, 0, width, height, 0, 0, 150, 190, null);
        if(System.currentTimeMillis() - lastShoot < 1000)
            g.drawImage(imgBullet, 0, 0, -20, -20, 0, 0, imgBullet.getWidth(null), imgBullet.getHeight(null), null);
        return tempImg;
    }

    @Override
    public Image getImageBullet() {
        return imgBullet;
    }
    
    @Override
    public long getLastShoot() {
        return lastShoot;
    }
    
}
