/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.units.ITowerType;
import game.units.Tower;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Алексей
 */
public class Player {
    private int money;
    private int lives;
    private int score;
    private Image imgScore;
    private Image imgMoney;
    private Image imgLive;
    private Image imgTime;
    private Image imgVawe;
    private Image imgCount;
    private ArrayList<Tower> towers;
    
    public Player() {
        try {
            imgVawe = ImageIO.read(new File("vawe.png"));
            imgCount = ImageIO.read(new File("zombi.png"));
            imgTime = ImageIO.read(new File("time.png"));
            imgLive = ImageIO.read(new File("live.png"));
            imgMoney = ImageIO.read(new File("money.png"));
            imgScore = ImageIO.read(new File("score.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        towers = new ArrayList();
        lives = 20;
        score = 0;
        money = 20;
    }
    
    public Image getImgCount() {
        return imgCount;
    }
    
    public Image getImgVawe() {
        return imgVawe;
    }
    
    public Image getImgTime() {
        return imgTime;
    }
    
    public boolean buyTower(int x, int y, ITowerType type) {
        boolean result = false;
        if (type.getCost() <= money) {
            result = true;
            money -= type.getCost();
            towers.add(new Tower(x,y, type));
        }
        return result;
    }
    
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    
    public Image getImgLive() {
        return imgLive;
    }
    
    public Image getImgScore() {
        return imgScore;
    }
    
    public Image getImgMoney() {
        return imgMoney;
    }
    
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
   
}
