package slythr;

import stardust.GlobalGamestate;
import stardust.MainPane;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

/**
 * Created by teddy on 4/21/17.
 */
public class Text_Box extends Complex{

    public ArrayList<Primitive> elements = new ArrayList<>();
    public Primitive continue_guage;
    public Primitive outer_rect;
    public Primitive inner_rect;
    public Primitive text_line1;
    public Primitive text_line2;
    public Primitive text_line3;
    public GlobalGamestate globalGamestate;
    public String line_1;
    public String line_2;
    public String line_3;
    Graphics g;
    Frame host_frame;




    FontRenderContext context;


    public Text_Box(GlobalGamestate gamestate, Graphics G, String line1, String line2, String line3, Frame frame){
        host_frame = frame;
        line_1 = line1;
        line_2 = line2;
        line_3 = line3;
        globalGamestate = gamestate;
        g = G;

        outer_rect = new Rect();
        inner_rect = new Rect();
        text_line1 = new Text(line_1, 24, g);
        text_line2 = new Text(line_2, 24, g);
        text_line3 = new Text(line_3, 24, g);
        continue_guage = new Rect();
        if(g != null) {
            text_line1.update(MainPane.global_g);
            text_line2.update(MainPane.global_g);
            text_line3.update(MainPane.global_g);
            text_line1.setpos((host_frame.getWidth() / 2) - (text_line1.getBounding_box().getWidth() / 2), (host_frame.getHeight() / 2) + 180);
            text_line2.setpos((host_frame.getWidth() / 2) - (text_line2.getBounding_box().getWidth() / 2), (host_frame.getHeight() / 2) + 200);
            text_line3.setpos((host_frame.getWidth() / 2) - (text_line3.getBounding_box().getWidth() / 2), (host_frame.getHeight() / 2) + 220);
            text_line1.setColor(0,0,0);
            text_line2.setColor(0,0,0);
            text_line3.setColor(0, 0, 0);

            elements.clear();
            elements.add(outer_rect);
            elements.add(inner_rect);
            elements.add(continue_guage);
            elements.add(text_line1);
            elements.add(text_line2);
            elements.add(text_line3);
        } else {
            System.out.println("WARNING! g has not been instantiated yet");
        }

        outer_rect.setAttributes(0,0, 170, 600, 0, 0,0);
        inner_rect.setAttributes(0, 0, 160, 590, 255, 255, 255);

        outer_rect.centerx(host_frame.getWidth() / 2);
        outer_rect.centery((host_frame.getHeight() / 2) + 200);

        inner_rect.centerx(host_frame.getHeight() / 2);
        inner_rect.centery((host_frame.getWidth() / 2) + 200);


        continue_guage.setHeight(5);
        continue_guage.setColor(0,255,0);
        continue_guage.setpos(inner_rect.getpos()[0], (inner_rect.getpos()[1] + inner_rect.getHeight() - continue_guage.getHeight()));








    }

    public void draw(Graphics g){
        for (Primitive element: elements){
            element.draw(g);
        }
    }

    public Primitive getContinue_guage(){
        return continue_guage;
    }

    public Primitive getInner_rect(){
        return inner_rect;
    }

    public Primitive getOuter_rect(){
        return outer_rect;
    }


}
