package stardust;

import slythr.Primitive;
import slythr.Stack;

import java.util.ArrayList;

/**
 * Created by teddy on 3/5/17.
 */
public class GlobalGamestate {

    public static Runtime runtime = Runtime.getRuntime();

    public static int gamevar_playerHealth = 3;

    public static double evar_master_volume = 1.0;

    public static int statevar_playerHealth;

    public static int gamevar_bulletspeed = 25;

    public static Stack physics_stack = new Stack();

    public static String evar_os;

    public static double time = 1;

    public static int statevar_saveslot;

    public static boolean statevar_alwaysgod = false;

    public static int gamevar_dialoguedelay = 1000;


    public static ArrayList<Object> mouse_pos_list= new ArrayList<>();

    public static int statevar_score = 0;

    public static boolean statevar_weaponsArmed = false;
    public static int gamevar_maxlittlestarspeed = 20;
    public static int gamevar_minlittlestarspeed = 10;
    public static int gamevar_asteroidexplodeparticle_maxspeed = 10;
    public static int gamevar_asteroidexplodeparticle_minspeed = 3;
    public static int gamevar_asteroidexplodeparticle_lifetime = 20;
    public static int gamevar_asteroidexplodeparticle_lifetime_variance = 7;
    public static boolean statevar_god = false;


    public GlobalGamestate(){

        statevar_playerHealth = gamevar_playerHealth;

    }

    public static void dealDamage_player(int damage){
        if (!statevar_god && !statevar_alwaysgod) {
            statevar_playerHealth = statevar_playerHealth - damage;
            MainPane.blink_animation.start();
            MainPane.invuln.start();
        }
    }

    public static void getOS(){
        if (System.getProperty("os.name").contains("win")){
            evar_os = "win";
        } else {
            evar_os = "notwin";
        }
    }

    public static void award(int reward){
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

    public void god_on(){
        statevar_god = true;
    }

    public static String localizePath(String unix_style){
        if (evar_os.equals("win")){
            return unix_style.replace("/", "\\");
        }
        return unix_style;
    }


    public static void arm_weapons(){
        statevar_weaponsArmed = true;
    }

    public static void disarm_weapons(){
        statevar_weaponsArmed = false;
    }
}

