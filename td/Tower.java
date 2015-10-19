/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Tower extends GameObject {

    private Blob target;
    private int cooldown;
    private int heat;
    private int range;

    public Tower(Track track, int x, int y) {
        super(track);
        target = null;
        range = (int) (80 + (Math.random() * 80.0));
        setPosition(new int[]{x, y});
        cooldown = 450;
        heat = 0;
    }

    @Override
    public void draw(Graphics g) {
        int[] pos = getPosition();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(pos[0] - getSize() / 2, pos[1] - getSize() / 2, getSize(), getSize());
        g.setColor(Color.BLACK);
        g.drawRect(pos[0] - getSize() / 2, pos[1] - getSize() / 2, getSize(), getSize());
//        if (target != null) {
//            g.setColor(Color.RED);
//            g.drawLine(getX(), getY(), target.getX(), target.getY());
//        }
    }

    public void drawRange(Graphics g) {
        int[] pos = getPosition();
        g.setColor(new Color(0, 0, 155, 25));
        g.fillOval(pos[0] - range, pos[1] - range, range * 2, range * 2);
        g.setColor(new Color(0, 0, 155, 255));
        g.drawOval(pos[0] - range, pos[1] - range, range * 2, range * 2);

    }

    public void lockTarget(ArrayList<Blob> blobs) {
        if (target != null && !target.isActive()) {
            target = null;
        }
        if (target == null || distance(target) > range) {
            double dist = range;
            for (int i = 0; i < blobs.size(); i++) {
                Blob b = blobs.get(i);
                if (distance(b) < dist) {
                    dist = distance(b);
                    target = b;
                }
            }
        }
    }

    public void shoot(int millis) {
        if (heat <= 0 && target != null) {
            double dist = Math.sqrt((target.getX() - getX()) * (target.getX() - getX()) + (target.getY() - getY()) * (target.getY() - getY()));
            double dx = 5.0 * (target.getX() - getX()) / dist;
            double dy = 5.0 * (target.getY() - getY()) / dist;
            getTrack().addShot(new Shot(new double[]{dx, dy}, new int[]{getX(), getY()}, getTrack()));
            heat = cooldown;
        } else {
            heat = heat - millis;
        }
    }
}
