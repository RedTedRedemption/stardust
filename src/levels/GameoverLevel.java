package levels;

import slythr.*;
import stardust.GlobalGamestate;
import stardust.MainPane;

import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class GameoverLevel {

    Graphics g;
    GlobalGamestate globalGamestate;

    Stack selfstack = new Stack();

    public GameoverLevel(Graphics g, GlobalGamestate gamestate){
        globalGamestate = gamestate;
        Primitive gameover = new Text("Game Over!", 48, g);
        gameover.update(MainPane.global_g);
        gameover.setpos(MainPane.host_frame.getWidth() - gameover.getBounding_box().getWidth(), MainPane.host_frame.getHeight() + gameover.getBounding_box().getHeight());
        selfstack.add(gameover);
    }

    public Stack getMe(){
        return selfstack;
    }

}
