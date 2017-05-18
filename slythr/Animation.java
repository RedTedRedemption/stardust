package slythr;

import java.util.ArrayList;

/**
 * Created by teddy on 4/20/17.
 */
public class Animation {


    public ArrayList<int[]> bread = new ArrayList<>();
    public Primitive target;
    int step = 0;
    public String mode;
    public boolean enabled = false;
    public SlythrAction action;

    public Animation(Primitive Target, String Mode, ArrayList<int[]> point_array){
        for (int[] a : point_array){
            bread.add(a);
        }
        target = Target;
        mode = Mode;
    }

    public void bind_Action(SlythrAction slaction){
        action = slaction;
    }

    public boolean Step() {
        if (enabled) {
            try {
                step = step + 1;
                if (mode.equals("keyframe")) {
                    target.setpos(bread.get(step)[0], bread.get(step)[1]);
                    return true;
                }
                if (mode.equals("offset")) {
                    target.move(bread.get(step)[0], bread.get(step)[1]);
                    return true;
                }
                if (mode.equals("enabled")) {
                    if (bread.get(step)[0] == 1) {
                        target.enable();
                    }
                    if (bread.get(step)[0] == 0) {
                        target.disable();
                    }
                }
                if (mode.equals("action")) {
                    if (bread.get(step)[0] == 1) {
                        action.execute();
                    }
                    if (bread.get(step)[1] == 1) {
                        action.execute2();
                    }
                }



                if (step >= bread.size() - 1) {
                    enabled = false;
                }
            } catch (java.lang.NullPointerException e) {
                System.out.println("WARNING! animation target has not been initialized yet");
                return false;
            }
        }
        return false;
    }

    public void setStep(int frame){
        step = frame;
    }

    public void bake(ArrayList<int[]> dough){
        bread.clear();
        for (int[] a : dough){
            bread.add(a);
        }
    }

    public void add(int[] point){
        bread.add(point);
    }

    public void setTarget(Primitive targ){
        target = targ;
    }

    public void start(){
        step = 0;
        enabled = true;
    }
    public void pause(){
        enabled = false;
    }
    public void resume(){
        enabled = true;
    }
}

