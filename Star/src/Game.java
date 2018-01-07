
import objectdraw.FrameWindowController;
import objectdraw.Location;


public class Game extends FrameWindowController
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Ship player;
    

    public void begin()
    {
        canvas.clear();
        play();
    }

    public void play()
    {
        player = new Ship(5, canvas.getHeight() / 2 - 7, canvas);
        
    }

    public void onMouseClick(Location point)
    {
        if (point.getY() > player.getY())
        {
            for (int i = 0; i < 3; i++)
            {
                player.move(0, 2);
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                player.move(0, -2);
            }
        }
    }
    
    

}
