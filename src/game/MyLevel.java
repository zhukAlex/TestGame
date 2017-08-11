/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Алексей
 */
public class MyLevel {
    private int level;
    private int count;
    private long timeLastUpdate;
    private long timeBetweenLevel;
    
    public MyLevel() {
        count = 5;
        level = 0;
        timeLastUpdate = System.currentTimeMillis();
        timeBetweenLevel = 0;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getCount() {
        return count;
    }
    
    public long getTimeBetween() {
        return timeBetweenLevel;
    }
    
    public long getTimeLast() {
        return timeLastUpdate;
    }
    
    public void setTimeLast(long timeLast) {
        this.timeLastUpdate = timeLast;
    }
    
    public void nextLevel() {
        level++;
        timeBetweenLevel = 30000;
        count += count / 3;
    }
    
    
}
