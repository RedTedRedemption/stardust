package levels;


import slythr.*;

/**
 * Created by teddy on 3/5/17.
 */
public class levelMenu extends Level{

    Stack myStack = new Stack();


    public levelMenu(){
        Primitive title = new Text("Main Menu", 36);
        Primitive start = new Text("New Game", 24);
        Primitive start_bounding_box = new Rect();
        start_bounding_box.setHeight(20);
        start_bounding_box.setWidth(130);




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
