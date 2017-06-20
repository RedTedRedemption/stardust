package sprites;

import slythr.*;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by teddy on 3/5/17.
 */
public class EnemySprite{

    static public ArrayList<EnemySprite> spritelist = new ArrayList<>();
    public static GlobalGamestate globalGamestate;
    Physics phys = new Physics(globalGamestate);
    int spawn_location;
    public static Frame hostFrame;
    public boolean animated = false;
    public Animation self_animation;
    static int gamevar_killreward = 3;
    public static Animation_Buffer animation_buffer;

    public Primitive self_primitive;


    public static Random rand = new Random();

    static ArrayList<int[]> animation_points = new ArrayList<>();

    public static void bind_host_frame(Frame frame){
        hostFrame = frame;
    }
    public static void bind_gamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public static void bind_animation_buffer(Animation_Buffer buffer){
        animation_buffer = buffer;
    }



    public EnemySprite(){

        self_primitive = new Rect();

        for (int i = 0; i <= 100; i++){
            animation_points.add(new int[] {1, 1});
        }
        for (int j = 0; j <= 100; j++){
            animation_points.add(new int[] {-1, 1});
        }

    }

    public static void instantiate() {


        EnemySprite new_instance = new EnemySprite();
        int spawn_location = rand.nextInt(hostFrame.getWidth() - 20) + 20;
        new_instance.self_primitive.setpos(spawn_location, -30);
        new_instance.self_primitive.setColor(255, 0, 0);
        new_instance.self_primitive.setHeight(20);
        new_instance.self_primitive.setWidth(20);
        new_instance.self_primitive.setPhysics_velocity_y(1);
        new_instance.self_animation = new Animation(new_instance.self_primitive, "offset", animation_points);
        animation_buffer.add(new_instance.self_animation);
        new_instance.self_animation.start();
        new_instance.self_animation.loopme(true);


        spritelist.add(new_instance);
    }



    public static void behave(Primitive ship) {

        try {
            for (EnemySprite instance : spritelist) {
                if (Physics.doObjectsCollide(instance.self_primitive, ship)) {
                    System.out.println("impact, dying");
                    kill(instance);
                    GlobalGamestate.dealDamage_player(1);
                }

                if (instance.self_primitive.getpos()[1] > hostFrame.getHeight()) {
                    kill(instance);
                }
                try {
                    for (BulletSprite bullet : BulletSprite.spritelist) {
                        if (Physics.doObjectsCollide(instance.self_primitive, bullet.self_primitive)) {
                            kill(instance);
                            BulletSprite.kill(bullet);
                            GlobalGamestate.award(gamevar_killreward);
                        }
                    }
                } catch (java.util.ConcurrentModificationException e) {
                    //pass
                }

                //me.self_animation.Step();
            }
        } catch (java.util.ConcurrentModificationException e) {
            //pass
        }
    }
    public static void draw(Graphics g){
        for (EnemySprite instance : spritelist){
            instance.self_primitive.draw(g);
        }
    }

    public static Stack getStack(){
        Stack tout = new Stack();
        for (EnemySprite instance : spritelist){
            tout.add(instance.self_primitive);
        }
        return tout;
    }

    public static void kill(EnemySprite tokill){
        spritelist.remove(tokill);
    }

    public static void rephysic(){
        for (EnemySprite me : spritelist) {
            if (!GlobalGamestate.physics_stack.makeArrayList().contains(me.self_primitive)) {
                GlobalGamestate.physics_stack.add(me.self_primitive);
            }
        }
    }

//    public void bind_animation(Animation anim){
//        anime = anim;
//        animated = true;
//    }

//    public Animation get_animation(){
//        return anime;
//    }

    public void set_animated(){
        animated = true;
    }

    public static void flush(){
        spritelist.clear();
    }

}
