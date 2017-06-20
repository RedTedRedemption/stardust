package levels;


import slythr.*;
import stardust.GlobalGamestate;

import java.awt.*;

/**
 * Created by teddy on 3/5/17.
 */
public class mainMenu {

    Stack myStack = new Stack();
    public Primitive start;
    public Primitive start_bounding_box;
    public Primitive title;
    private GlobalGamestate globalGamestate;


    public mainMenu(GlobalGamestate gamestate, Graphics g){
        title = new Text("Main Menu", 36, g);
        start = new Text("New Game", 24, g);
        globalGamestate = gamestate;
        start_bounding_box = new Rect();
        start_bounding_box.setHeight(20);
        start_bounding_box.setWidth(130);

        title.setColor(0,0,0);
        start.setColor(0,0,0);




//        int titlewidth = g.getFontMetrics().stringWidth(title.getSelfContent());

        title.setpos(60, 150);
        start.setpos(60, 210);
        start_bounding_box.setpos(start.getpos()[0], start.getpos()[1] - start_bounding_box.getHeight());
        myStack.add(title);
        myStack.add(start);

    }

    public Stack getMe(){
        return myStack;
    }

}
