package slythr;

import slythr.Resource;

import java.awt.*;

public class Primitive {

	public int[] physics_velocity;
	public int height;
	public int width;
	public int origin_x;
	public int origin_y;
	public int[] origin;

	// primitives
	public void draw(Graphics g) {
		System.out.println("drawing from primitive");
	}

	public void setpos(int x, int y) {
		System.out.println("moving from primitive");
	}

	public int[] getpos() {
		int[] placeholder = { 0, 0 };
		return placeholder;

	}

	public void setAttributes(int x, int y, int Height, int Width, int r, int g, int b) {
		// sets attributes of primitive object
	}

	public void centerx(int x) {
		// sets origin to align center x to this coordinate

	}

	public void centery(int y) {
		// sets origin to align center y to this coordinate
	}

	public void setSFont(Resource hellofont) {

	}

	public void move(int x, int y) {
		// moves the object by its physics_velocity
	}

	public void setVelocity(int[] vel) {
		// sets an object's velocity in its attribute physics_velocity
	}

	public void updatePoints() {

	}

	public void setWidth(int i) {
		// TODO Auto-generated method stub
	}

	public void setHeight(int i) {

	}

	public void setRotation(double i) {

	}

	public void setText(String content) {

	}

	public void getSizes() {

	}

	public int getHeight() {
		return -1;
	}

	public int getWidth() {
		return -1;
	}

	public void triAttributes(int h, int w, int x, int y) {

	}

	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub
		
	}

	public void setSize(int size){

	}

	public int getSize(){
		return 1;
	}

	public String getSelfContent() {
		return "error, wrong scope for getSelfContent call";
	}
}
