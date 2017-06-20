package slythr;

import stardust.GlobalGamestate;
import stardust.MainPane;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

/**
 * A primitive that renders as text.
 */

//todo implement physics for text

public class Text extends Primitive{

    public String self_content;
	public Font use_font;
	public Resource self_font;// = new slythr.SFont(Font.BOLD, "Serif");
	public Color self_color = Color.white;
	public int origin_x = 0;
	public int origin_y = 0;
	public int self_size;
	public Graphics graph;
	public boolean enabled = true;
	FontMetrics fontmet;
	GlobalGamestate globalGamestate;
	public Primitive bounding_box;
	public String type = "text";
	public String label = "a text object";
	public Rectangle bounding_rect;






	public int height;
	public int width;

	public Text(String content, int size, Graphics g) {
		self_font = new SFont(Font.ITALIC, "Serif");
		self_content = content;
		self_size = size;
		bounding_box = new Rect();
		bounding_box.setAttributes(this.getpos()[0], this.getpos()[1],20, 20, 0,0,0);
		bounding_box.setpos(origin_x, origin_y);
		setLabel(content);
        if (g != null){
			fontmet = g.getFontMetrics(self_font.getFont(self_size));
			width = fontmet.stringWidth(self_content);
			height = fontmet.getHeight();


        } else {
        	//pass;
        }
        GlobalGamestate.mouse_pos_list.add(this);







	}


	public void setOpacity(int opacity){
		self_color = new Color(self_color.getRed(), self_color.getGreen(), self_color.getBlue(), opacity);
	}




	public void update(Graphics g){


		fontmet = g.getFontMetrics(self_font.getFont(self_size));

		Graphics2D g2 = (Graphics2D) g;
		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = g2.getFont().createGlyphVector(frc, self_content);
		bounding_rect = gv.getPixelBounds(frc, (float) origin_x, (float) origin_y);



		bounding_box.setWidth(fontmet.stringWidth(self_content));
		bounding_box.setHeight(fontmet.getHeight());
		bounding_box.setpos(this.getpos()[0], this.getpos()[1] - bounding_box.getHeight());
	}

	public void centerx(int x){
		origin_x = x - (this.getBounding_box().getWidth() / 2);
	}

	public void centery(int y){
		origin_y = y - (this.getBounding_box().getHeight() / 2);

	}



	public String getSelf_content(){
	    return self_content;
    }

     //todo -- make these methods work
	public int getHeight() {

		return getBounding_box().getHeight();
	}
    public int getWidth(){

		return getBounding_box().getWidth();
    }

    public int[] getPos(){
        int coords[] = {origin_x, origin_y};
        return coords;
    }

    public boolean isClicked(){
    	try {
			if (Physics.pointInObj(MainPane.evar_mousepos[0], MainPane.evar_mousepos[1], this)) {
				return true;
			}
		} catch (java.lang.NullPointerException e){
    		return false;
		}
			return false;
	}

	public void textAttributes(String content, SFont font, Color color, int X, int Y) {

        self_content = content;
        self_font = font;
        self_color = color;
        origin_x = X;
        origin_y = Y;
    }

    public void emptyOnClick(){
    	//pass
	}

	public void setColor(int r, int g, int b){

    	self_color = new Color(r, g, b);
	}

	public void draw(Graphics g) {
		if (enabled) {
			g.setColor(self_color);
			g.setFont(self_font.getFont(self_size));
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
		enabled = !enabled;
	}

	public void setContent(String content){
		self_content = content;
	}

	public Primitive getBounding_box(){
		return bounding_box;
	}

	public void draw_bounding_box(Graphics g){
		g.setColor(new Color(0, 255, 0));
		g.drawRect(this.getBounding_box().getpos()[0], this.getBounding_box().getpos()[1], this.getBounding_box().getWidth(), this.getBounding_box().getHeight());
	}
}


