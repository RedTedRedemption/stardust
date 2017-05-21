package slythr;

import stardust.MainPane;

import java.time.Instant;

/**
 * Created by teddy on 5/19/17.
 */
public class FPScounter implements Runnable {

    Thread fpsthread;

    public static Primitive fps;

    static Instant drawcount_start = Instant.now();

    public FPScounter(){
        System.out.println("starting fps counter thread");
        fpsthread = new Thread(this, "FPS counter thread");
        fps = new Text("", 24, MainPane.global_g, MainPane.globalGamestate);
        fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
    }

    public void run() {
        while (true) {
            fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
            if (Instant.now().getEpochSecond() - drawcount_start.getEpochSecond() >= 1) {
                fps.setText(Integer.toString(MainPane.drawcount));
                fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
                drawcount_start = Instant.now();
                MainPane.drawcount = 0;
            }

        }
    }

}
