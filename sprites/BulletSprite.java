package sprites;
import slythr.*;

import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class BulletSprite {

    Primitive emitter;

    Stack spritelist = new Stack();

    public BulletSprite(){

    }

    public void instantiate(Primitive emitter){

        System.out.println("instantiating bullet");
        Primitive self_primitive = new Rect();
        self_primitive.setpos(emitter.getpos()[0], emitter.getpos()[1]);
        self_primitive.setColor(255, 0, 0);
        self_primitive.setHeight(10);
        self_primitive .setWidth(5);
        spritelist.add(self_primitive);


    }

    public void behave(){
        for (Primitive obj : spritelist.makeArrayList()){
            obj.setpos(obj.getpos()[0], obj.getpos()[1] - 1);
        }
    }

    public void draw(Graphics g){
        for (Primitive obj : spritelist.makeArrayList()){
            obj.draw(g);
        }
    }

    public Stack getStack(){
        return spritelist;
    }

}
