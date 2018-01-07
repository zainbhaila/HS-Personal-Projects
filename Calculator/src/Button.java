//Name:Connor Hunt, Zain Bhaila
//Date: Created: 9/7/15
//  Modified: 1/29/16
//Purpose: Create clickable named button

import objectdraw.*;
import java.awt.*;

public class Button{
    private FramedRect outline;
    private FilledRect button;
    private Text text;
    
    public Button(double x, double y, double width, double height, String name, DrawingCanvas canvas) {
        outline = new FramedRect(x,y,width,height,canvas);
        button = new FilledRect(x,y,width,height,canvas);
        button.setColor(Color.WHITE);
        button.sendToBack();
        text = new Text(" " + name, 0, 0, canvas);
        resetTextLocation();
    }
    
    public Button(double x, double y, String name, DrawingCanvas canvas) {
        outline = new FramedRect(x,y,0,0,canvas);
        button = new FilledRect(x,y,0,0,canvas);
        button.setColor(Color.WHITE);
        button.sendToBack();
        text = new Text(" " + name, 0, 0, canvas);
        resetTextLocation();
        this.setToTextSize();
    }
    
    private void resetTextLocation() {
        text.moveTo(button.getX()+button.getWidth()/2-text.getWidth()/2, button.getY()+button.getHeight()/2-text.getHeight()/2); 
    }
    
    public boolean contains(Location point) {
        return button.contains(point);
    }
    
    public double getTextWidth() {
        return text.getWidth();
    }
    
    public double getTextHeight() {
        return text.getHeight();
    }
    
    public double getWidth() {
        return button.getWidth();
    }
    
    public double getHeight() {
        return button.getHeight();
    }
    
    public void setWidth(double width) {
        button.setWidth(width);
        outline.setWidth(width);
        resetTextLocation();
    }
    
    public void setHeight(double height) {
        button.setHeight(height);
        outline.setHeight(height);
        resetTextLocation();
    }
    
    public void setToTextSize() {
        this.setHeight(text.getHeight()+10);
        this.setWidth(text.getWidth()+10);
        resetTextLocation();
    }
}