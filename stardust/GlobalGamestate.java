package stardust;

import slythr.Primitive;
import slythr.Stack;

/**
 * Created by teddy on 3/5/17.
 */
public class GlobalGamestate {

    public static int gamevar_playerHealth = 10;

    public static int statevar_playerHealth;

    public static int gamevar_bulletspeed = 25;

    public static Stack physics_stack = new Stack();

    public static double time = 1;



    public int statevar_score = 0;



    public GlobalGamestate(){

        statevar_playerHealth = gamevar_playerHealth;

    }

    public void dealDamage_player(int damage){
        statevar_playerHealth = statevar_playerHealth - damage;
    }

    public void award(int reward){
        statevar_score = statevar_score + reward;
    }

    public void penalize(int penalty){
        statevar_score = statevar_score - penalty;
    }

    public void physics_enable(Primitive tadd){
        physics_stack.add(tadd);
    }

    public void physics_enable(Stack tadd){
        physics_stack.add(tadd);
    }

    public void set_time(double Time){
        time = Time;
    }
}
