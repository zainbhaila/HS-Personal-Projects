import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Text;
import objectdraw.VisibleImage;

public class Ship extends ActiveObject
{
    VisibleImage shipBod;
    DrawingCanvas canvas;
    boolean gameOver = false;
    boolean inShip;
    Location lastPoint;
    final int LEVELTIME = 45, STARTINGLEVEL = 1;
    Location clickPoint;
    BufferedImage image;
    int x,y;
    
    public Ship(int startx, int starty, DrawingCanvas aCanvas)
    {
        x = startx;
        y = starty;
        canvas = aCanvas;
        
        try{image = ImageIO.read(new File("spaceship2.png"));}
        catch (IOException e){}

        start();
    }

    public void move(double x, double y)
    {
        shipBod.move(x,y);
    }

    public boolean collision(VisibleImage a)
    {
        if (shipBod.overlaps(a))
        {
            return true;
        }
        return false;
    }
    
    public boolean contains(Location point) {
        return shipBod.contains(point);
    }
    
    public void onMousePress(Location point) {
        inShip = this.contains(point);
        lastPoint = point;
    }

    public void onMouseDrag(Location point) {
        if(inShip) {
            this.move(point.getX() - lastPoint.getX(), 0);
            lastPoint = point;
        }
    }
    

    
    public double getY()
    {
        return shipBod.getY();
    }

    public DrawingCanvas getC()
    {
        return shipBod.getCanvas();
    }
    
    @Override
    public void run()
    {
        shipBod = new VisibleImage (image, x, y, canvas);
        int lives = 1;
        int level = STARTINGLEVEL;
        long startTime = System.currentTimeMillis();
        long startTime2 = System.currentTimeMillis();
        long startTime3 = System.currentTimeMillis();
        int seconds = LEVELTIME;
        Text lvl = new Text("Level: " + level, 0, 0, canvas);
        Text timeRemaining = new Text("Time until level progression: " + seconds, 0, 15, canvas);
        Text levelUp = new Text(" ", 15,25,canvas);
        levelUp.setFontSize(50);
        Random gen = new Random();
        ArrayList<DeathObject> deaths = new ArrayList<DeathObject>();
        while (lives >= 1)
        {
            if (System.currentTimeMillis() - startTime >= 1000){
                yield();
                seconds--;
                timeRemaining.setText("Time until level progression: " + seconds);
                if (seconds<=0) {
                    level++;
                    startTime = System.currentTimeMillis();
                    lvl.setText("Level: " + level);
                    seconds = LEVELTIME;
                    levelUp.setText("Level up!");
                }
                
                if(seconds==LEVELTIME-3) {
                    levelUp.setText(" ");
                }
                startTime = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - startTime2 >= 1500-level*200)
            {
                yield();
                deaths.add(new DeathObject(canvas.getWidth()-40,gen.nextInt((int)canvas.getHeight()-1),20,level,canvas));
                startTime2 = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - startTime3 >= 100){
                for (int index = deaths.size()-1; index >= 0; index--){
                    yield();
                    if (this.collision(((DeathObject)deaths.get(index)).circle()))
                    {
                        lives--;
                    }
                }
                startTime3 = System.currentTimeMillis();
            }
        }
        levelUp.setText(" ");
        Text gameOverText = new Text("GAME OVER", 10,10,canvas);
        gameOverText.setFontSize(50);
        gameOver = true;
    }
}