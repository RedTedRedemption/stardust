package slythr;

import java.util.ArrayList;

/**
 * Created by teddy on 4/20/17.
 *
 * Animation_buffers are sets of animations. Animations should be added to a buffer, and the buffer should be stepped
 * once for each cycle of the main game loop.
 */
public class Animation_Buffer {

    public ArrayList<Animation> buffer = new ArrayList<>();


    public Animation_Buffer(){

    }

    /**
     * Adds an animation to the buffer.
     * @param append Animation to be appended to the buffer
     */
    public void add(Animation append){
        buffer.add(append);
    }

    /**
     * Steps every animation in the buffer.
     */
    public void step(){
        for (Animation a : buffer){
            a.Step();
        }
    }

}
