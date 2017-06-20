package slythr;

import stardust.GlobalGamestate;
import stardust.MainPane;

import java.awt.*;

public class Title {

    String self_content;
    Primitive self_primitive;
    static Graphics self_g;
    static GlobalGamestate globalGamestate;
    Stack title_buffer = new Stack();
    Complex_Stack complex_title_buffer = new Complex_Stack();
    String self_text = "empty";
    int self_time;

    public static void bind_graphics(Graphics g){
        self_g = g;
    }

    public static void bind_globalGamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public Title(String text, int time){
        self_time = time;

//        thread = new Thread(this, "title text thread");




    }

    public void show(String text, int time, Graphics g) throws InterruptedException {
        MainPane.title_text.setText(text);
        MainPane.title_text.enable();

   //     self_primitive.update(MainPane.global_g);
        MainPane.title_text.setpos(MainPane.host_frame.getWidth() - (MainPane.title_text.getBounding_box().getWidth()  / 2), MainPane.host_frame.getHeight() + (MainPane.title_text.getBounding_box().getHeight() / 2));

        //swap stacks
//        title_buffer.add(MainPane.rendStack);
//        complex_title_buffer.add(MainPane.main_complex_stack);
//        MainPane.rendStack.flush();
//        MainPane.main_complex_stack.flush();

        //add my stuff to stack

//
//        for (int a = 0; a <= self_time; a++) {
//            self_primitive.draw(MainPane.global_g);
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Thread.sleep(time);
        MainPane.title_text.disable();

//        MainPane.rendStack.flush();
//        MainPane.rendStack.add(title_buffer);
//        MainPane.main_complex_stack.flush();
//        MainPane.main_complex_stack.add(complex_title_buffer);

    }

//    public static void show(String text, int time) throws InterruptedException {
//        outer_thread = new Thread(new Title(text, time), "Slythr title thread");
//        System.out.print("starting title thread...");
//        outer_thread.start();
//        System.out.print("joining...");
//        outer_thread.join();
//        System.out.println("done");
//    }

}
