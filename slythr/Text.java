package slythr;

import slythr.Resource;

import java.awt.*;

public class Text extends Primitive {

    public String self_content;
	Font use_font;
	Resource self_font;// = new slythr.SFont(Font.BOLD, "Serif");
	Color self_color = Color.black;
	int origin_x = 0;
	int origin_y = 0;
	int self_size;
	public Graphics graph;

	public Text(String content, int size) {
		self_font = new SFont(Font.ITALIC, "Serif");
		self_content = content;
		self_size = size;
	}

	public String getSelf_content(){
	    return self_content;
    }

	public void textAttributes(String content, SFont font, Color color, int X, int Y) {

		self_content = content;
		self_font = font;
		self_color = color;
		origin_x = X;
		origin_y = Y;
	}

	public void draw(Graphics g) {
		g.setFont(self_font.getFont(self_size));
		g.setColor(self_color);
		g.drawString(self_content, origin_x, origin_y);

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
}


