package levels;

import slythr.*;
import sprites.EnemySprite;
import sprites.Sprite;

import java.awt.*;

/**
 * Created by teddy on 3/5/17.
 */
public class levelPlayground{

    Stack myStack = new Stack();

   // public EnemySprite enemysprite = new EnemySprite();

   // public levelPlayground(Frame frame){



   // }

    public Stack getMe(){
        return myStack;
    }

    //public Sprite enemySprite(){
        //return enemysprite;
   // }

    public void draw(Graphics g){
        myStack.draw(g);
    }

}
