/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Shot extends MovingGameObject {
    private int flyTime;
    private double[] direction;

    public Shot(double[] direction, int[] position, Track track) {
        super(1, track);
        this.direction = direction;
        flyTime = 1000;
        setPosition(position);
        setSize(4);
        //System.out.println("Shot: "+direction[0]+","+direction[1]);
    }

    @Override
    public void move(int millis) {
        int x = (int) (getPosition()[0]+Math.round(direction[0]*millis*0.05));
        int y = (int) (getPosition()[1]+Math.round(direction[1]*millis*0.05));
        setPosition(new int[]{x,y});
        flyTime = flyTime - millis;
    }
    
    @Override
    public void draw(Graphics g) {
        int[] pos = getPosition();
        g.setColor(Color.black);
        g.fillOval(pos[0] - getSize() / 2, pos[1] - getSize() / 2, getSize(), getSize());
        //g.drawLine(pos[0] - getSize() / 2, pos[1] - getSize() / 2,pos[0] - getSize() / 2 + (int) (direction[0]*25), pos[1] - getSize() / 2 + (int) (direction[1]*25));
    }

    public double[] getDirection() {
        return direction;
    }

    public void setDirection(double[] direction) {
        this.direction = direction;
    }

    public int getFlyTime() {
        return flyTime;
    }

    public void setFlyTime(int flyTime) {
        this.flyTime = flyTime;
    }
    
    
}
