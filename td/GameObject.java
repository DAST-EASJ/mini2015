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
public abstract class GameObject {

    private Track track;
    private int size;
    private int[] position;

    public GameObject(Track track) {
        this.track = track;
        size = 24;
    }

    public abstract void draw(Graphics g);

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public int[] getPosition() {
        return position;
    }
    
    public int getX(){
        return position[0];
    }
    
    public int getY(){
        return position[1];
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
    
    public double distance(GameObject obj){
        return Math.sqrt((obj.getX()-getX())*(obj.getX()-getX())+(obj.getY()-getY())*(obj.getY()-getY()));
    }
    
}
