package particles;



import slythr.Primitive;
import slythr.Rect;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by teddy on 5/15/17.
 */
public class AsteroidExplodeParticle extends Particle {

    public static ArrayList<AsteroidExplodeParticle> particlearray = new ArrayList<>();
    static Random rand = new Random();
    public Primitive self_primitive;
    static GlobalGamestate globalGamestate;
    int lifetime = 0;

    public static void bind_globalGamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public AsteroidExplodeParticle(){

    }

    public static void single_instantiate(int[] origin){
        AsteroidExplodeParticle new_instance = new AsteroidExplodeParticle();
        new_instance.self_primitive = new Rect();
        new_instance.self_primitive.setpos(origin[0], origin[1]);
        if (rand.nextBoolean()) {
            new_instance.self_primitive.setPhysics_velocity(rand.nextInt(GlobalGamestate.gamevar_asteroidexplodeparticle_maxspeed) + GlobalGamestate.gamevar_asteroidexplodeparticle_minspeed, rand.nextInt(GlobalGamestate
                    .gamevar_asteroidexplodeparticle_maxspeed) + GlobalGamestate.gamevar_asteroidexplodeparticle_minspeed);
        } else {
            new_instance.self_primitive.setPhysics_velocity(-rand.nextInt(GlobalGamestate.gamevar_asteroidexplodeparticle_maxspeed) + GlobalGamestate.gamevar_asteroidexplodeparticle_minspeed, -rand.nextInt(GlobalGamestate
                    .gamevar_asteroidexplodeparticle_maxspeed) + GlobalGamestate.gamevar_asteroidexplodeparticle_minspeed);
        }
        new_instance.self_primitive.setHeight(6);
        new_instance.self_primitive.setWidth(6);
        new_instance.self_primitive.setColor(255, 0, 0);
        globalGamestate.physics_enable(new_instance.self_primitive);
        particlearray.add(new_instance);
        new_instance.lifetime = rand.nextInt(GlobalGamestate.gamevar_asteroidexplodeparticle_lifetime) + GlobalGamestate.gamevar_asteroidexplodeparticle_lifetime - GlobalGamestate.gamevar_asteroidexplodeparticle_lifetime_variance;

    }

    public static void instantiate(int[] origin){
        for (int i = 0; i < rand.nextInt(7) + 6; i++){
            single_instantiate(origin);
        }
    }

    public static void draw(Graphics g){
        for (AsteroidExplodeParticle instance : particlearray){
            try {
                instance.self_primitive.draw(g);
            } catch (java.util.ConcurrentModificationException e){
                System.out.println("WARNING!!! concurrent modification exception handled successfully");
            }
        }
    }

    public static void kill(AsteroidExplodeParticle instance){
        particlearray.remove(instance);
    }

    public static void behave(){
        try {
            for (AsteroidExplodeParticle instance : particlearray) {
                instance.lifetime = instance.lifetime - 1;
                if (instance.lifetime <= 0) {
                    kill(instance);
                }
            }
        } catch (java.util.ConcurrentModificationException e){
            //pass;
        }
    }

    public static void flush(){
        particlearray.clear();
    }

}
