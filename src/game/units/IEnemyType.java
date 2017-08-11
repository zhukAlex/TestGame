/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Image;

/**
 *
 * @author Алексей
 */
public interface IEnemyType {
    public Image getImage();
    public int getWidth();
    public int getHeight();
    public double getHealth();
    public int getCost();
    public int getSpeed();
}
