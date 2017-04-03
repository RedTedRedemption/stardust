package sprites;
import slythr.*;
import stardust.GlobalGamestate;

import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class BulletSprite {

    Primitive emitter;

    static Stack spritelist = new Stack();

    public BulletSprite(){

    }

    public void instantiate(Primitive emitter){

        System.out.println("instantiating bullet");
        Primitive self_primitive = new Rect();
        //self_primitive.centerx(emitter.centerx());
        //self_primitive.centery(emitter.centery());
        //self_primitive.setpos(emitter.getpos()[0], emitter.getpos()[1]);
        //self_primitive.setpos(emitter.centerx(), emitter.centery());
        self_primitive.centerx(emitter.centerx());
        self_primitive.centery(emitter.centery());
        self_primitive.setColor(255, 0, 0);
        self_primitive.setHeight(10);
        self_primitive .setWidth(5);
        spritelist.add(self_primitive);


    }

    public void kill(Primitive instance){
        spritelist.remove(instance);
        System.out.println("brutally murdering bullet");
    }

    public static void behave(){
        for (Primitive obj : spritelist.makeArrayList()){
            obj.setpos(obj.getpos()[0], obj.getpos()[1] - GlobalGamestate.gamevar_bulletspeed);
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
