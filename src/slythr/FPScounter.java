package slythr;

import stardust.GameLoop;
import stardust.MainPane;

/**
 * Thread used to count how many frames are rendered in a second.
 */
public class FPScounter implements Runnable {

    Thread fpsthread;

    public static Primitive fps;
    public static Primitive cps;

    //static Instant drawcount_start = Instant.now();

    public FPScounter() {
        boolean retry = true;
        while (retry) {
            try {
                System.out.print("creating fps counter thread...");
                fpsthread = new Thread(this, "FPS counter thread");
                fps = new Text("", 24, MainPane.global_g);
                cps = new Text("", 24, MainPane.global_g);
                fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);

                System.out.println("done");
                retry = false;
            } catch (java.lang.NullPointerException e) {
                System.out.print("retrying...");
            }
        }

    }

    public void run() {
        System.out.print("starting fps counter thread...");
//        System.out.println("waiting for Graphics source to not be null");
//        while (MainPane.global_g == null){
//           //pass;
//        }
        System.out.println("fps counter setup");
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
            fps.setText("FPS: " + Integer.toString(MainPane.drawcount));
            fps.update(MainPane.global_g);
            fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
            cps.setText("CPS: " + Integer.toString(GameLoop.game_loop_count));
            cps.update(MainPane.global_g);
            cps.setpos(MainPane.host_frame.getWidth() - cps.getBounding_box().getWidth(), fps.getpos()[1] + cps.getBounding_box().getHeight());
           // drawcount_start = Instant.now();
            MainPane.drawcount = 0;
            GameLoop.game_loop_count = 0;


        }
    }

}
