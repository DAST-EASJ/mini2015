/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

/**
 *
 * @author David
 */
public abstract class MovingGameObject extends GameObject{
    private double speed;
    private double progress;
    private boolean active;
    
    public MovingGameObject(double speed, Track track) {
        super(track);
        active = true;
        this.speed = speed;
    }

    public void move(int millis) {
        progress = progress + speed * millis * 0.5;
        setPosition(getTrack().function(progress));
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
        
}
