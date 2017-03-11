package stardust;

/**
 * Created by teddy on 3/5/17.
 */
public class GlobalGamestate {

    public int gamevar_playerHealth = 10;

    public int statevar_playerHealth;



    public GlobalGamestate(){

        statevar_playerHealth = gamevar_playerHealth;

    }

    public void dealDamage_player(int damage){
        statevar_playerHealth = statevar_playerHealth - damage;
    }


}
