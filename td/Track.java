/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Track extends javax.swing.JPanel {

    private ArrayList<Blob> blobs;
    private ArrayList<Tower> towers;
    private ArrayList<Shot> shots;
    private char[][] fields;
    private ArrayList<int[]> wayPoints;
    private int lives;
    private int length;
    public final static int FIELD_SIZE = 50;

    /**
     * Creates new form Track
     */
    public Track() {
        blobs = new ArrayList<>();
        towers = new ArrayList<>();
        shots = new ArrayList<>();
        wayPoints = new ArrayList<>();
        fields = new char[16][12];

        this.lives = 100;

        setupFields();
        setupWayPoints();
        initComponents();

    }

    private void setupFields() {
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 16; x++) {
                if ((y == 6 && x < 6) || (y == 6 && x > 10) || (x == 5 && y > 6 && y <= 9) || (x == 11 && y > 6 && y <= 9) || (y == 9 && x > 5 && x < 11)) {
                    fields[x][y] = 'r';
                } else if (y >= 7 && y <= 10 && x >= 6 && x <= 10) {
                    fields[x][y] = 'w';
                } else {
                    fields[x][y] = 'g';
                }
            }
        }
    }

    private void setupWayPoints() {
        int[] waypoint;
        waypoint = new int[]{0, -1, 6};
        wayPoints.add(waypoint);
        waypoint = new int[]{0, 5, 6};
        wayPoints.add(waypoint);
        waypoint = new int[]{0, 5, 9};
        wayPoints.add(waypoint);
        waypoint = new int[]{0, 11, 9};
        wayPoints.add(waypoint);
        waypoint = new int[]{0, 11, 6};
        wayPoints.add(waypoint);
        waypoint = new int[]{0, 17, 6};
        wayPoints.add(waypoint);
        for (int i = 0; i < wayPoints.size(); i++) {
            int[] point = wayPoints.get(i);
            point[1] = point[1] * FIELD_SIZE;
            point[2] = point[2] * FIELD_SIZE;
        }
        for (int i = 1; i < wayPoints.size(); i++) {
            int[] point0 = wayPoints.get(i - 1);
            int[] point1 = wayPoints.get(i);
            int dist = (int) Math.round(Math.sqrt((point0[1] - point1[1]) * (point0[1] - point1[1]) + (point0[2] - point1[2]) * (point0[2] - point1[2])));
            point1[0] = 50 * dist + point0[0];
        }
        length = wayPoints.get(wayPoints.size() - 1)[0];
    }

    @Override
    public void paint(Graphics g1) {
        Color darkness = new Color(0, 0, 0, 100);
        Color light = new Color(255, 255, 255, 100);
        super.paint(g1);
        Graphics2D g = (Graphics2D) g1;
        Key key = RenderingHints.KEY_ANTIALIASING;
        Object value = RenderingHints.VALUE_ANTIALIAS_ON;
        g.setRenderingHint(key, value);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, fields.length * FIELD_SIZE, fields[0].length * FIELD_SIZE);
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                char field = fields[i][j];

                if (field == 'r') {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i * FIELD_SIZE + 12, j * FIELD_SIZE, FIELD_SIZE - 24, FIELD_SIZE);
                    g.fillRect(i * FIELD_SIZE, j * FIELD_SIZE + 12, FIELD_SIZE, FIELD_SIZE - 24);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(i * FIELD_SIZE + 12, j * FIELD_SIZE, FIELD_SIZE - 24, FIELD_SIZE);
                    g.drawRect(i * FIELD_SIZE, j * FIELD_SIZE + 12, FIELD_SIZE, FIELD_SIZE - 24);

                    g.setColor(Color.WHITE);
                    g.fillRect(i * FIELD_SIZE + 4, j * FIELD_SIZE + 4, FIELD_SIZE - 8, FIELD_SIZE - 8);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(i * FIELD_SIZE + 4, j * FIELD_SIZE + 4, FIELD_SIZE - 8, FIELD_SIZE - 8);
                }

                if (field == 'g') {
                    g.setColor(Color.DARK_GRAY);
                    for (int k = 0; k < 2; k++) {
                        for (int m = 0; m < 2; m++) {
                            for (int n = 0; n < 4; n++) {
                                int xoff = 0;
                                int yoff = 0;
                                if ((k + m) % 2 == 0) {
                                    g.fillRect(i * FIELD_SIZE + k * FIELD_SIZE / 2 + n*6 + 2, j * FIELD_SIZE + m * FIELD_SIZE / 2 , FIELD_SIZE / 16, FIELD_SIZE / 2);
                                } else {
                                    g.fillRect(i * FIELD_SIZE + k * FIELD_SIZE / 2  , j * FIELD_SIZE + m * FIELD_SIZE / 2 + n*6 + 2, FIELD_SIZE / 2, FIELD_SIZE / 16);
                                }
                                
                            }
                        }
                    }
                }
                
                if (field == 'w') {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i * FIELD_SIZE, j * FIELD_SIZE , FIELD_SIZE , FIELD_SIZE );
                    g.setColor(Color.BLACK);
                    for (int k = 0; k < 2; k++) {
                        for (int m = 0; m < 2; m++) {
                            for (int n = 0; n < 4; n++) {
                                int xoff = 0;
                                int yoff = 0;
                                if ((k + m) % 2 == 0) {
                                    g.fillRect(i * FIELD_SIZE + k * FIELD_SIZE / 2 + n*6 + 2, j * FIELD_SIZE + m * FIELD_SIZE / 2 , FIELD_SIZE / 16, FIELD_SIZE / 2);
                                } else {
                                    g.fillRect(i * FIELD_SIZE + k * FIELD_SIZE / 2  , j * FIELD_SIZE + m * FIELD_SIZE / 2 + n*6 + 2, FIELD_SIZE / 2, FIELD_SIZE / 16);
                                }
                                
                            }
                        }
                    }
                }

            }
        }

        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).drawRange(g);
        }
        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).draw(g);
        }
        
        
        
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).draw(g);
        }
        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).draw(g);
        }

        drawString(g, "Lives: " + lives, 5, 30);
    }

    public void drawString(Graphics g1, String text, int x, int y) {
        Graphics2D g = (Graphics2D) g1;
        RenderingHints.Key key = RenderingHints.KEY_TEXT_ANTIALIASING;
        Object value = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
        g.setRenderingHint(key, value);

        g.setColor(Color.BLACK);
        g.drawString(text, x - 1, y);
        g.drawString(text, x + 1, y);
        g.drawString(text, x - 1, y - 1);
        g.drawString(text, x, y - 1);
        g.drawString(text, x + 1, y - 1);
        g.drawString(text, x - 1, y + 1);
        g.drawString(text, x, y + 1);
        g.drawString(text, x + 1, y + 1);
        g.setColor(getForeground());
        g.drawString(text, x, y);
    }

    public void addBlob(Blob b) {
        blobs.add(b);
    }

    public void addTower(Tower t) {
        towers.add(t);
    }

    public void addShot(Shot s) {
        shots.add(s);
    }

    public void move(int millis) {
        for (int i = 0; i < blobs.size(); i++) {
            blobs.get(i).move(millis);
        }
        for (int i = 0; i < towers.size(); i++) {
            Tower t = towers.get(i);
            t.lockTarget(blobs);
            t.shoot(millis);
        }
        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).move(millis);
        }
    }

    public void collision() {
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            for (int j = 0; j < blobs.size(); j++) {
                Blob blob = blobs.get(j);
                if (shot != null && blob != null && blob.distance(shot) < blob.getSize() / 2 + shot.getSize() / 2) {
                    if (!blob.hit()) {
                        blobs.remove(blob);
                        blob = null;
                    }
                    shots.remove(shot);
                    shot = null;
                }
            }
        }
    }

    public void remove() {
        for (int i = 0; i < blobs.size(); i++) {
            if (blobs.get(i).getProgress() > length) {
                blobs.remove(i).setActive(false);
                lives--;
            }
        }
        for (int i = 0; i < shots.size(); i++) {
            if (shots.get(i).getFlyTime() <= 0) {
                shots.remove(i);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public int getLives() {
        return lives;
    }

    public int[] function1(double time) {
        int x = (int) Math.round(time * 0.02) - 40;
        int y = (int) 9 * 40 - 50;
        int[] point = {x, y};
        return point;
    }

    public int[] function(double time) {
        int x = -50;
        int y = -50;
        int index = 1;
        while (index < wayPoints.size() - 1 && wayPoints.get(index)[0] < time) {
            index++;
        }
        int[] point0 = wayPoints.get(index - 1);
        int[] point1 = wayPoints.get(index);
        double dt = point1[0] - point0[0];
        double p1 = (time - point0[0]) / dt;
        double p0 = (1.0 - p1);
        x = (int) Math.round(p0 * point0[1] + p1 * point1[1]) + 25;
        y = (int) Math.round(p0 * point0[2] + p1 * point1[2]) + 25;
        int[] point = {x, y};
        //System.out.println(time + ":\t ("+x+","+y+") p0/p1:"+p0+"/"+p1);
        return point;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setForeground(new java.awt.Color(255, 0, 51));
        setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        setPreferredSize(new java.awt.Dimension(816, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
