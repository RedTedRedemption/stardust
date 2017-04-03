package stardust;

/**
 * Created by teddy on 3/5/17.
 */
public class GlobalGamestate {

    public static int gamevar_playerHealth = 10;

    public static int statevar_playerHealth;

    public static int gamevar_bulletspeed = 10;




    public GlobalGamestate(){

        statevar_playerHealth = gamevar_playerHealth;

    }

    public void dealDamage_player(int damage){
        statevar_playerHealth = statevar_playerHealth - damage;
    }


}
