package slythr;

import stardust.GlobalGamestate;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends Primitive {

	public int color_r = 255;
	public int color_g = 255;
	public int color_b = 255;
	public int origin_x = 0;
	public int origin_y = 0;
	public int height = 20;
	public int width = 20;
	public int physics_velocity_x = 0;
	public int physics_velocity_y = 0;
	public boolean enabled = true;
	private GlobalGamestate globalGamestate;

	public Rect(GlobalGamestate gamestate) {
		globalGamestate = gamestate;
	}

	public void setAttributes(int x, int y, int Height, int Width, int r, int g, int b) {

		origin_x = x;
		origin_y = y;
		height = Height;
		width = Width;
		color_r = r;
		color_g = g;
		color_b = b;
		center_x = x - (width / 2);
		center_y = y - (height / 2);


		/*
		System.out.print("the attributes of object ");
		System.out.print(this);
		System.out.print(" are ");
		System.out.print(origin_x + " ");
		System.out.print(origin_y + " ");
		System.out.print(height + " ");
		*/

	}

	public void draw(Graphics g) {

		// System.out.println("drawing from rect");
		if (enabled) {
			g.setColor(new Color(color_r, color_g, color_b));
			g.fillRect(origin_x, origin_y, width, height);
		}
	}

	public void setColor(int R, int G, int B) {
		color_r = R;
		color_g = G;
		color_b = B;
	}



	public void setPhysics_velocity_x(int magnitude){
		physics_velocity_x = magnitude;
	}

	public void setPhysics_velocity_y(int magnitude){
		physics_velocity_y = magnitude;
	}

	public void setPhysics_velocity(int x, int y){
		physics_velocity_x = x;
		physics_velocity_y = y;
	}

	public void setpos(int x, int y) {
		origin_x = x;
		origin_y = y;
	}

	public void move(int x, int y) {
		origin_x = origin_x + x;
		origin_y = origin_y + y;
	}

	public void move(double time){
		setpos(getpos()[0] + (int)(physics_velocity_x * time), getpos()[1] + (int)(physics_velocity_y * time));
	}
	public int[] getpos() {
		int[] coords = { origin_x, origin_y };
		return coords;

	}

	public void getSizes() {
		int[] tout = { origin_x, origin_y, height, width, color_r, color_g, color_b };
		//System.out.print("sizes are: ");
		//System.out.println(tout[0] + " " + tout[1] + " " + tout[2] + " " + tout[3]);

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void centerx(int x) {
		origin_x = x - (width / 2);
	}

	public int centerx(){
		return origin_x + (width / 2);
	}

	public int centery(){
		return origin_y + (height / 2);
	}

	public void centery(int y) {
		origin_y = y - (height / 2);
	}

	public void setWidth(int value) {
		width = value;
	}

	public void setHeight(int value) {
		height = value;
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
