package slythr;

import stardust.GlobalGamestate;

import java.awt.*;

/**
 * Created by teddy on 4/21/17.
 */
public class Complex {

    public Primitive outer_rect;
    public Primitive inner_rect;
    public Primitive text_line1;
    public Primitive text_line2;
    public Primitive text_line3;
    public GlobalGamestate globalGamestate;
    public String line_1;
    public String line_2;
    public String line_3;
    public Primitive continue_guage;

    public Stack elements = new Stack();

    public void draw(Graphics g){
        elements.draw(g);
    }

    public void add(Primitive element){
        elements.add(element);
    }

    public Primitive getContinue_guage(){
        return null;
    }

    public Primitive getInner_rect(){
        return null;
    }

    public Primitive getOuter_rect(){
        return new Primitive();
    }


}
