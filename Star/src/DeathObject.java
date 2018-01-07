import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.VisibleImage;

public class DeathObject extends ActiveObject
{
    VisibleImage obj;
    DrawingCanvas canvas;
    int pauseTime;
    double speed;
    BufferedImage image = null;
    
    
    public DeathObject(int startx, int starty, int radius, int sp, DrawingCanvas aCanvas)
    {
        canvas =  aCanvas;
        try{image = ImageIO.read(new File("asteroid.png"));}
        catch (IOException e){};
        obj = new VisibleImage (image, startx, starty, canvas);
        obj.setColor(new Color(0,255,0));
        if(sp>4) {
            pauseTime = 10;
            speed = (double)sp/4;
        }
        
        else {
            pauseTime=30-sp*4;
            speed = 1;
        }
        start();
    }
    
    public VisibleImage circle()
    {
        return obj;
    }
    
    public void move(double x, double y)
    {
        obj.move(x,y);
    }
    
    public int getX()    
    {
        return (int)obj.getX();
    }
    
    public DrawingCanvas getC()
    {
        return obj.getCanvas();
    }
    
    public void run() {
        while (this.getX() > -20) {
            this.move(-speed, 0);
            pause(pauseTime);
        }
        this.obj.removeFromCanvas();
    }
}