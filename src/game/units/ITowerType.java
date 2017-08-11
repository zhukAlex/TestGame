/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.units;

import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public interface ITowerType {
    public Image getImage();
    public int getWidth();
    public int getHeight();
    public int getPower();
    public int getCost();
    public void shoot(Enemy e);
    public Image getImageBullet();
    public long getLastShoot();
}
