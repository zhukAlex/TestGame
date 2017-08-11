/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public class ZombiType extends AbstractEnemyType implements IEnemyType{    
    public ZombiType() {
        cost = 1;
        i = 0;
        speed = 2;
        maxHealth = 10;
        lastUpdate = System.currentTimeMillis();
        count = 3;
        height = width = 128;
        try {
            File f = new File("enemy.png");
            img = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(ZombiType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void nextStep() {
        if(System.currentTimeMillis() - lastUpdate >= 100 ){
            i = i >= count ? 0 : i+1;
            lastUpdate = System.currentTimeMillis();
        }
    }
    
    public double getHealth() {
        return maxHealth;
    }
    
    @Override
    public Image getImage() {
        nextStep();
        Image tempImg = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tempImg.getGraphics();
        g.drawImage(img, 0, 0, 128, 128, 128*i, 256, 128*i + 128, 384, null);
        
        return tempImg;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
    
}
