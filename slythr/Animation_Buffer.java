package slythr;

import java.util.ArrayList;

/**
 * Created by teddy on 4/20/17.
 */
public class Animation_Buffer {

    public ArrayList<Animation> buffer = new ArrayList<>();


    public Animation_Buffer(){

    }

    public void add(Animation append){
        buffer.add(append);
    }

    public void step(){
        for (Animation a : buffer){
            a.Step();
        }
    }

}
