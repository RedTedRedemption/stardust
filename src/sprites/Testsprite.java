package sprites;



import slythr.Primitive;
import slythr.Rect;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by teddy on 5/3/17.
 */
public class Testsprite {

    public static ArrayList<Testsprite> spritelist = new ArrayList<>();
    public static Graphics global_g;
    public static Frame host_frame;
    public static GlobalGamestate globalGamestate;
    public Primitive self_primitive;

    public static void bind_graphics(Graphics g){
        global_g = g;
    }

    public static void bind_host_frame(Frame frame){
        host_frame = frame;
    }

    public Testsprite(){
        self_primitive = new Rect();
        self_primitive.setpos(50, 50);

    }

    public static void instantiate(){
        spritelist.add(new Testsprite());
    }

    public static void draw(Graphics g){
        for (Testsprite instance : spritelist){
            instance.self_primitive.draw(g);
        }
    }
}
