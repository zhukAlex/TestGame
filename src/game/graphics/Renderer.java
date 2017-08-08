/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics;

import game.units.IUnits;
import game.units.Player;
import java.util.ArrayList;

/**
 *
 * @author Алексей
 */
public class Renderer {
    private int width, height;
    private int[] pixels = null;
    
    public Renderer(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
    
    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFFFFFF;
        }
    }
    
    public void render(ArrayList<IUnits> units) {
        int tWidth = 1;
        int tHeight = 1;
        for(IUnits u : units) {
            tWidth = u.getWidth() + u.getX();
            tHeight = u.getHeight() + u.getY();
            for(int y = u.getY(); y < tHeight; y++) {
                for(int x = u.getX(); x < tWidth; x++) {
                    if(x + y * width >= pixels.length)
                        continue;
                    pixels[x + y * width] = u.getColor();
                }
            }
            
        }    
    }
}
