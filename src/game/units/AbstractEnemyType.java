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
public class AbstractEnemyType {
    protected int count;
    protected int i;
    protected Image img;
    protected long lastUpdate;
    protected int width, height;
    protected double maxHealth;
    protected int cost;
    protected int speed;
}
