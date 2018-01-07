import objectdraw.*;

public class Calculator extends FrameWindowController{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button resetButton;
    Button[][] calculator = new Button[4][4];
    double width = canvas.getWidth(), height = canvas.getHeight();
    double vSpacing = height/50, hSpacing = width/50;
    String output = "";
    Text outText;
    double currentVal = 0;
    String func = "";
    double spec;
    
    public void begin() {//call this method to run UI
        createUI();
    }

    public void createUI() {//creates the user interface for the calculator
        double width = canvas.getWidth(), height = canvas.getHeight();
        double vSpacing = height/50, hSpacing = width/50;

        resetButton = new Button(hSpacing, vSpacing, "Reset UI", canvas);

        int i = 9;

        for(int y = 0; y < 3; y++) {
            for(int x = 2; x > -1; x--) {
                calculator[x][y] = new Button(x*width/4+hSpacing, (y+1)*height/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, i+"", canvas);
                i--;
            }
        }
        calculator[3][0] = new Button(width*3/4+hSpacing, height/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "/", canvas);
        calculator[3][1] = new Button(width*3/4+hSpacing, height*2/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "x", canvas);
        calculator[3][2] = new Button(width*3/4+hSpacing, height*3/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "-", canvas);
        calculator[3][3] = new Button(width*3/4+hSpacing, height*4/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "+", canvas);
        calculator[0][3] = new Button(hSpacing, height*4/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "0", canvas);
        calculator[1][3] = new Button(width/4+hSpacing, height*4/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, ".", canvas);
        calculator[2][3] = new Button(width/2+hSpacing, height*4/5+vSpacing, width/4-2*hSpacing, height/5-2*vSpacing, "=", canvas);
        outText = new Text(output, hSpacing+resetButton.getWidth()*2, vSpacing, canvas);
    }

    public void onMouseClick(Location point) {//displays the output and does mathematical calculations
        if(output.length() > 0 && output.charAt(0)=='A')//after an answer is used, clear the answer
        {
            output = "";
        }
        if(resetButton.contains(point)) {//reset UI
            canvas.clear();
            createUI();
        }
        else if((calculator[0][0]).contains(point)) {//follow else-ifs output numbers upto and including comment "end numbers"
            output = output.concat("7");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[1][0].contains(point)) {
            output = output.concat("8");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[2][0].contains(point)) {
            output = output.concat("9");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[0][1].contains(point)) {
            output = output.concat("4");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[1][1].contains(point)) {
            output = output.concat("5");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[2][1].contains(point)) {
            output = output.concat("6");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[0][2].contains(point)) {
            output = output.concat("1");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[1][2].contains(point)) {
            output = output.concat("2");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[2][2].contains(point)) {
            output = output.concat("3");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[0][3].contains(point)) {//end numbers
            output = output.concat("0");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[1][3].contains(point)) {//decimal
            if (output == "")
            {
                output = "0";
            }
            output = output.concat(".");
            currentVal = Double.parseDouble(output);
        }
        else if(calculator[3][0].contains(point)){//division
            output = "";
            func = "/";
            spec = currentVal;
        }
        else if(calculator[3][1].contains(point)){//multiplication
            output = "";
            func = "x";
            spec = currentVal;
        }
        else if(calculator[3][2].contains(point)){//subtraction
            output = "";
            func = "-";
            spec = currentVal;
        }
        else if(calculator[3][3].contains(point)){//addition
            output = "";
            func = "+";
            spec = currentVal;
        }
        else if(calculator[2][3].contains(point)){//equals sign
            if (func.equals("/"))
            {
                output = "Answer: " + (spec/currentVal);
                currentVal = spec/currentVal;
            }
            else if (func.equals("x"))
            {
                output = "Answer: " + (spec*currentVal);
                currentVal = spec*currentVal;
            }
            else if (func.equals("-"))
            {
                output = "Answer: " + (spec-currentVal);
                currentVal = spec-currentVal;
            }
            else if (func.equals("+"))
            {
                output = "Answer: " + (spec+currentVal);
                currentVal = spec+currentVal;
            }
            spec = 0;
        }
        canvas.clear();
        createUI();
    }
}
