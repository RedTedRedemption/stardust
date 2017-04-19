package levels;

import slythr.*;

import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class GameoverLevel {

    Graphics g;

    Stack selfstack = new Stack();

    public GameoverLevel(Graphics g){
        Primitive gameover = new Text("Game Over!", 48, g);
        gameover.setpos(300, 300);
        selfstack.add(gameover);
    }

    public Stack getMe(){
        return selfstack;
    }

}
