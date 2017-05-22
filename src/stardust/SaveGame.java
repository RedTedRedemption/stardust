package stardust;

/**
 * Created by teddy on 4/24/17.
 */
public class SaveGame {

    static String save_String;
    static String level_id;

    public static void load(String target_file, MainPane mainPane){

    }

    public static int save(String target_file){
        level_id = MainPane.statevar_current_level_id;

        save_String = save_String + level_id;

        //todo -- save save_String to file
        return 1;
    }

}
