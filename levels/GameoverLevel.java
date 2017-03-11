package levels;

import slythr.*;

/**
 * Created by teddy on 3/6/17.
 */
public class GameoverLevel {

    Stack selfstack = new Stack();

    public GameoverLevel(){
        Primitive gameover = new Text("Game Over!", 48);
        gameover.setpos(300, 300);
        selfstack.add(gameover);
    }

    public Stack getMe(){
        return selfstack;
    }

}
