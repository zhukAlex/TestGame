/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Алексей
 */
public class Tower implements IUnits {
    private int x;
    private int y;
    private int height = 100;
    private int width = 75;
    private int radius;
    private ITowerType type;
    private int centerX;
    private int centerY;
    private Enemy enemy;
    
    public Tower(int x, int y, ITowerType type) {
        this.type = type;
        this.width = type.getWidth();
        this.height = type.getHeight();
        this.x = x - width / 2;
        this.y = y - height;
        this.radius = 300;
        this.centerX = this.x - this.radius / 2 + this.width / 2;
        this.centerY = this.y - this.radius / 2 + this.height;
    }
    
    public void shoot(Enemy enemy) {
        this.enemy = enemy;
        type.shoot(enemy);
    }
    
    public int getCenterX() {
        return centerX;
    }
    
    public int getCenterY() {
        return centerY;
    }
    
    public int getRadius() {
        return radius;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Image getImg() {
        return type.getImage();
    }
    
    public Image getImgBullet() {
        return type.getImageBullet();
    }
    
    public long getLastShoot() {
        return type.getLastShoot();
    }
    
    public Enemy getEnemy() {
        return enemy;
    }
}
