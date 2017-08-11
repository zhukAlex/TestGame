/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public class Enemy implements IUnits{

    private int x, y, color, width, height;
    private IEnemyType type;
    private long lastStep;
    private boolean alive;
    private double maxHealth;
    private double nowHealth;
    
    
    public Enemy(int x, int y, IEnemyType type) {
        lastStep = System.currentTimeMillis();
        this.type = type;
        this.x = x;
        this.y = y;
        this.alive = true;
        maxHealth = nowHealth = type.getHealth();
        width = 50; 
        height = 50;
        color = 0xE13005;
    }
    
    public boolean isAlive() {
        return alive;
    }
        
    public int getCost() {
        return type.getCost();
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void doNextStep() {
        if(System.currentTimeMillis() - lastStep > 200) {
            x += type.getSpeed();
            lastStep = System.currentTimeMillis();
        }
    }
    
    public void shoot(int damage) {
        nowHealth -= damage;
        if(nowHealth <= 0)
            alive = false;
    }
    
    public void drawHp(Graphics g, int height, int width) {
        double w = nowHealth * width / maxHealth;
        g.setColor(Color.black);
        g.fillRect(0, height - 10, width, 10);
        setColorHp(g);
        g.fillRect(0, height - 10, (int)w, 10);
    }    
    
    public void setColorHp(Graphics g) {
        double yellow = 60 * maxHealth / 100;
        double red =  20 * maxHealth / 100;
        if(nowHealth > yellow)
            g.setColor(Color.green);
        else if(nowHealth > red)
            g.setColor(Color.yellow);
        else if(nowHealth > 0)
            g.setColor(Color.red);
    }
    
    
    public double getHp() {
        return type.getHealth();    
    }
    
    public boolean isFinished(int width) {
        boolean result = false;
        if(x + this.width >= width)
            result = true;
        return result;
        
    }

    @Override
    public Image getImg() {
        Image img = type.getImage();
        Graphics g = img.getGraphics();
        drawHp(g, type.getHeight(), type.getWidth());
        return img;
    }
    
}
