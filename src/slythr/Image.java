package slythr;

import stardust.GlobalGamestate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A Primitive that renders as an image on the screen.
 */

public class Image extends Primitive {

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
    public Animation self_animation;
    BufferedImage self_image;
    public boolean sprite = false;
    public int sprite_step;
    public String label = "an image object";


    /**
     * Create a new image Primitive
     * @param path file path to the image to load.
     * @throws IOException
     */
    public Image(String path) throws IOException {
        SplashScreen.status.setText("loading image " + path);

        self_image = ImageIO.read(new File(GlobalGamestate.localizePath(path)));


    }

    /**
     * Set the object's image.
     * @param path path to the image
     * @throws IOException
     */
    public void setImage(String path) throws IOException {
        self_image = ImageIO.read(new File(GlobalGamestate.localizePath(path)));
    }


    /**
     * Draw the object.
     * @param g Graphics instance to draw the object to
     */
    public void draw(Graphics g) {

        // System.out.println("drawing from rect");
        if (enabled) {
            g.drawImage(self_image, origin_x, origin_y, null);
        }
    }




    public void setPhysics_velocity_x(int magnitude){
        physics_velocity_x = magnitude;
    }

    public void setPhysics_velocity_y(int magnitude){
        physics_velocity_y = magnitude;
    }

    public void setPhysics_velocity(int x, int y){
        this.physics_velocity_x = x;
        this.physics_velocity_y = y;
    }

    public int[] getPhysics_Velocity(){
        return new int[] {this.physics_velocity_x, this.physics_velocity_y};
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
        return this.height;
    }

    public int getWidth() {
        return this.width;
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
        this.enabled = true;
    }

    public void disable(){
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !enabled;
    }

    public int bind_Animation(Animation anim){
        self_animation = anim;
        self_animation.setTarget(this);
        return 1;
    }

    public void make_Sprite(){
        sprite = true;
    }

    public int sprite_Step(int stepby){
        sprite_step = sprite_step + 1;
        return sprite_step;
    }

    public void reset_sprite_animation(){
        sprite_step = 0;
    }

    public int get_step(){
        return sprite_step;
    }

    public void setLabel(String identifier){
        label = identifier;
    }


}
