/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

/**
 *
 * @author Алексей
 */
public class Enemy implements IUnits{

    private int x, y, color, speed, width, height;
    
    
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 4;
        width = 10; 
        height = 10;
        color = 0xE13005;
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
    
}
