package sprites;

import slythr.Primitive;
import slythr.Rect;
import slythr.Stack;
import slythr.Physics;
import stardust.GlobalGamestate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by teddy on 3/5/17.
 */
public class EnemySprite{

    static public Stack spriteList = new Stack();
    Physics phys = new Physics();
    GlobalGamestate globalGamestate;


    public EnemySprite(){



    }

    public void instantiate(Frame frame, GlobalGamestate globalDatabase) {
        System.out.println("trying to instantiate");
        Primitive self_primitive = new Rect();
        self_primitive.setpos(frame.getWidth() / 2, 50);
        self_primitive.setColor(255, 0, 0);
        self_primitive.setHeight(10);
        self_primitive.setWidth(10);
        spriteList.add(self_primitive);
        globalGamestate = globalDatabase;
    }




    public void behave(Primitive ship){
        for (Primitive me : spriteList.makeArrayList()){
            me.setpos(me.getpos()[0],me.getpos()[1] + 3);
            if (phys.doObjectsCollide(me, ship)) {
                System.out.println("impact, dying");
                kill(me);
                globalGamestate.dealDamage_player(1);
            }
        }
    }
    public void draw(Graphics g){
        spriteList.draw(g);
    }
    public Stack getStack(){
        return spriteList;
    }

    public void kill(Primitive tokill){
        spriteList.remove(tokill);
    }



}
