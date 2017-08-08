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
public class Player implements IUnits {
    private int x;
    private int y;
    private int speed;
    private int color;
    private int height, width;
    
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        height = 1;
        width = 1;
        speed = 2;
        color = 0;
    }
    
    public int getColor() {
        return color;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
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
