package slythr;

import slythr.Resource;
import stardust.MainMenuPane;

import java.awt.*;
import java.awt.geom.Rectangle2D;

//todo implement physics for text

public class Text extends Primitive {

    public String self_content;
	public Font use_font;
	Resource self_font;// = new slythr.SFont(Font.BOLD, "Serif");
	Color self_color = Color.black;
	public int origin_x = 0;
	public int origin_y = 0;
	public int self_size;
	public Graphics graph;
	public boolean enabled = true;
	FontMetrics fontmet;
	Rectangle2D bounding_box;

	public int height;
	public int width;

	public Text(String content, int size, Graphics g) {
		self_font = new SFont(Font.ITALIC, "Serif");
		self_content = content;
		self_size = size;
        if (g != null){
            fontmet = MainMenuPane.g.getFontMetrics();
            bounding_box = fontmet.getStringBounds(self_content, g);
            height = (int) bounding_box.getHeight();
            width = (int) bounding_box.getWidth();

        } else {
            System.out.println("WARNING! g has not been initialized yet");
        }




	}

	public String getSelf_content(){
	    return self_content;
    }

    public int getHeight(){
	    return height;
    }

    public int getWidth(){
        return width;
    }

    public int[] getPos(){
        int coords[] = {origin_x, origin_y};
        return coords;
    }

	public void textAttributes(String content, SFont font, Color color, int X, int Y) {

        self_content = content;
        self_font = font;
        self_color = color;
        origin_x = X;
        origin_y = Y;
    }

	public void draw(Graphics g) {
		if (enabled) {
			g.setFont(self_font.getFont(self_size));
			g.setColor(self_color);
			g.drawString(self_content, origin_x, origin_y);
		}
	}

	public int getSize(){
	    return self_size;
    }

	public void setSFont(SFont font) {
		self_font = font;
	}

	public void setpos(int X, int Y) {
		origin_x = X;
		origin_y = Y;
	}

	public int[] getpos() {
		return new int[] {origin_x, origin_y};

	}

	public void setSize(int size){
	    self_size = size;
    }

	public void setText(String content) {
		self_content = content;
	}
	public void enable(){
		enabled = true;
	}
	public void disable(){
		enabled = false;
	}
	public void toggle() {
		if (enabled) {
			enabled = false;
		} else {
			enabled = true;
		}
	}
}


