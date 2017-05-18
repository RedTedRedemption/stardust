package sprites;

import Particles.AsteroidExplodeParticle;
import slythr.Physics;
import slythr.Primitive;
import slythr.Rect;
import slythr.Stack;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by teddy on 3/6/17.
 */
public class AsteroidSprite {



    private static GlobalGamestate globalGamestate;
    private static Frame host_frame;
    private static int spawn_location;
    private static Random rand = new Random();
    public Primitive self_primitive;
    static int spares = 0;

    static ArrayList<AsteroidSprite> spritelist = new ArrayList<>();


    public static void bind_gamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public static void bind_host_frame(Frame frame){
        host_frame = frame;
    }

    public AsteroidSprite(){

    }

    public static void instantiate(){
        //System.out.println("instantiating bullet");
        AsteroidSprite new_instance = new AsteroidSprite();
        new_instance.self_primitive = new Rect(globalGamestate);
        spawn_location = rand.nextInt(host_frame.getWidth() - 20) + 20;
        new_instance.self_primitive.setpos(spawn_location, -30);
        new_instance.self_primitive.setColor(255, 0, 0);
        new_instance.self_primitive.setHeight(20);
        new_instance.self_primitive.setWidth(20);
        if (rand.nextBoolean()){
            new_instance.self_primitive.setPhysics_velocity(rand.nextInt(2) + 1, rand.nextInt(4) + 2);

        }
        else {
            new_instance.self_primitive.setPhysics_velocity((rand.nextInt(2) + 2) * -1, rand.nextInt(4) + 2);
        }

//        self_primitive.setPhysics_velocity_y(rand.nextInt(5));
//        self_primitive.setPhysics_velocity_x(rand.nextInt(5));

        spritelist.add(new_instance);

    }

    public static void kill(AsteroidSprite instance){
        spritelist.remove(instance);
        //System.out.println("brutally murdering bullet");
    }

    public static void rephysic(){
        for (AsteroidSprite me : spritelist) {
            if (!GlobalGamestate.physics_stack.makeArrayList().contains(me.self_primitive)) {
                GlobalGamestate.physics_stack.add(me.self_primitive);
            }
        }
    }

    public static void behave(Primitive player_ship, BulletSprite bullets){
        try {
            for (AsteroidSprite obj : spritelist) {

                if (obj.self_primitive.centerx() > host_frame.getHeight()) {
                    spritelist.remove(obj);
                }
                if (Physics.doObjectsCollide(obj.self_primitive, player_ship)) {
                    GlobalGamestate.dealDamage_player(1);
                    kill(obj);
                    AsteroidExplodeParticle.instantiate(obj.self_primitive.getpos());
                }
                for (BulletSprite bullet : bullets.spritelist) {
                    if (Physics.doObjectsCollide(obj.self_primitive, bullet.self_primitive)) {
                        kill(obj);
                        bullets.kill(bullet);
                        AsteroidExplodeParticle.instantiate(obj.self_primitive.getpos());

                    }
                }
            }
        } catch (java.util.ConcurrentModificationException e){
            //pass
        }
    }

    public static void draw(Graphics g){
        for (AsteroidSprite obj : spritelist){
            obj.self_primitive.draw(g);
        }
    }



    public Stack getStack(){
        Stack tout = new Stack();
        for (AsteroidSprite instance : spritelist){
            tout.add(instance.self_primitive);
        }
        return tout;
    }

    public static void flush(){
        spritelist.clear();
    }

}
