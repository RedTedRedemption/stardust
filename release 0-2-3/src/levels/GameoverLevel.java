package levels;

import slythr.*;
import stardust.GlobalGamestate;

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
        Primitive gameover = new Text("Game Over!", 48, g, globalGamestate);
        gameover.setpos(300, 300);
        selfstack.add(gameover);
    }

    public Stack getMe(){
        return selfstack;
    }

}
