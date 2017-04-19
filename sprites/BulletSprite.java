package sprites;
import slythr.*;
import stardust.GlobalGamestate;

import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class BulletSprite {

    Primitive emitter;

    private static GlobalGamestate globalGamestate;

    static Stack spritelist = new Stack();

    public BulletSprite(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public void instantiate(Primitive emitter){

        //System.out.println("instantiating bullet");
        Primitive self_primitive = new Rect(globalGamestate);
        self_primitive.setpos(emitter.centerx(), emitter.centery());
        self_primitive.centerx(emitter.centerx());
        self_primitive.centery(emitter.centery());
        self_primitive.setColor(255, 0, 0);
        self_primitive.setHeight(10);
        self_primitive .setWidth(5);
        spritelist.add(self_primitive);
        self_primitive.setPhysics_velocity_y(-10);


    }

    public void kill(Primitive instance){
        spritelist.remove(instance);
        //System.out.println("brutally murdering bullet");
    }

    public static void behave(){
        for (Primitive obj : spritelist.makeArrayList()){
            if (obj.centerx() < 0){
                spritelist.remove(obj);
            }

        }
    }

    public void draw(Graphics g){
        for (Primitive obj : spritelist.makeArrayList()){
            obj.draw(g);
        }
    }



    public Stack getStack(){
        return spritelist;
    }

}
