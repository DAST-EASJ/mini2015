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
public class Blob extends MovingGameObject {
    
    public Blob(double speed, Track t) {
        super(speed, t);
        setPosition(getTrack().function(0));
    }
    
    @Override
     public void draw(Graphics g) {
        int[] pos = getPosition();
        g.setColor(Color.WHITE);
        g.fillOval(pos[0] - getSize()/2, pos[1]- getSize()/2, getSize(), getSize());
        g.setColor(Color.BLACK);
        g.drawOval(pos[0] - getSize()/2, pos[1]- getSize()/2, getSize(), getSize());
    }

    public boolean hit() {
        if(getSize()<14){
            setActive(false);
        } else {
            setSize(getSize()-2);
        }
        return isActive();
    }

    
}
