package sprites;

import slythr.Primitive;
import slythr.Rect;
import slythr.Stack;
import slythr.Physics;
import stardust.GlobalGamestate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by teddy on 3/5/17.
 */
public class EnemySprite extends Sprite{

    static public Stack spriteList = new Stack();
    public GlobalGamestate globalGamestate;
    Physics phys = new Physics(globalGamestate);
    int spawn_location;
    public Frame hostFrame;


    Random rand = new Random();



    public EnemySprite(Frame frame, GlobalGamestate gamestate){

        hostFrame = frame;
        globalGamestate = gamestate;
    }

    public void instantiate(Frame frame, GlobalGamestate gamestate) {


        Primitive self_primitive = new Rect(gamestate);
        spawn_location = rand.nextInt(frame.getWidth() - 20) + 20;
        self_primitive.setpos(spawn_location, -30);
        self_primitive.setColor(255, 0, 0);
        self_primitive.setHeight(20);
        self_primitive.setWidth(20);
        spriteList.add(self_primitive);
        globalGamestate = gamestate;
        self_primitive.setPhysics_velocity_y(5);
    }




    public void behave(Primitive ship, BulletSprite bullets){
        for (Primitive me : spriteList.makeArrayList()) {
            if (phys.doObjectsCollide(me, ship)) {
                System.out.println("impact, dying");
                kill(me);
                globalGamestate.dealDamage_player(1);
            }
            if (me.getpos()[1] > hostFrame.getHeight()) {
                kill(me);
            }
            for (Primitive bullet : bullets.getStack().makeArrayList()) {
                if (phys.doObjectsCollide(me, bullet)) {
                    kill(me);
                    bullets.kill(bullet);
                    globalGamestate.award(3);
                }
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
