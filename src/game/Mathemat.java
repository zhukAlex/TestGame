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
public class Mathemat {
    public static double GET_LENGTH(int x1, int y1, int x2, int y2) {
        int x = x2 - x1;
        int y = y2 - y1;
        double r = Math.sqrt(x*x + y*y);
        return r;
    }
}
