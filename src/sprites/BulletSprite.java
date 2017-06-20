package sprites;
import slythr.*;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by teddy on 3/6/17.
 */
public class BulletSprite {

    Primitive emitter;

    private static GlobalGamestate globalGamestate;

    Primitive self_primitive;

    public int gamevar_damage_dealt= 1;


    public static ArrayList<BulletSprite> spritelist = new ArrayList<>();

    public BulletSprite(){
       self_primitive = new Rect();
    }

    public static void instantiate(Primitive emitter){

        //System.out.println("instantiating bullet");
        BulletSprite new_instance = new BulletSprite();
        new_instance.self_primitive.setpos(emitter.centerx(), emitter.centery());
        new_instance.self_primitive.centerx(emitter.centerx());
        new_instance.self_primitive.centery(emitter.centery());
        new_instance.self_primitive.setColor(255, 0, 0);
        new_instance.self_primitive.setHeight(10);
        new_instance.self_primitive.setWidth(5);
        spritelist.add(new_instance);
        new_instance.self_primitive.setPhysics_velocity_y(-GlobalGamestate.gamevar_bulletspeed);



    }

    public static void kill(BulletSprite instance){
        spritelist.remove(instance);
        GlobalGamestate.physics_stack.remove(instance.self_primitive);
        //System.out.println("brutally murdering bullet");
    }

    public static void behave(){
        for (BulletSprite obj : spritelist){
            if (obj.self_primitive.centerx() < 0){
                spritelist.remove(obj);
            }

        }
    }

    public static void draw(Graphics g){
        for (BulletSprite instance : spritelist){
            instance.self_primitive.draw(g);
        }
    }

    public static void rephysic(){
        for (BulletSprite me : spritelist) {
            if (!GlobalGamestate.physics_stack.makeArrayList().contains(me.self_primitive)) {
                GlobalGamestate.physics_stack.add(me.self_primitive);
            }
        }
    }

    public static void bind_gamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }



    public Stack getStack(){
        Stack tout = new Stack();
        for (BulletSprite instance : spritelist){
            tout.add(instance.self_primitive);
        }
        return tout;
    }

    public static void flush(){
        spritelist.clear();
    }

}
