package slythr;

import stardust.GameLoop;
import stardust.MainPane;

import java.time.Instant;

/**
 * Created by teddy on 5/19/17.
 */
public class FPScounter implements Runnable {

    Thread fpsthread;

    public static Primitive fps;
    public static Primitive cps;

    static Instant drawcount_start = Instant.now();

    public FPScounter(){
        System.out.println("starting fps counter thread");
        fpsthread = new Thread(this, "FPS counter thread");
        fps = new Text("", 24, MainPane.global_g, MainPane.globalGamestate);
        cps = new Text("", 24, MainPane.global_g, MainPane.globalGamestate);
        fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
    }

    public void run() {
        while (true) {
            fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
            if (Instant.now().getEpochSecond() - drawcount_start.getEpochSecond() >= 1) {
                fps.setText("FPS: " + Integer.toString(MainPane.drawcount));
                fps.update(MainPane.global_g);
                fps.setpos(MainPane.host_frame.getWidth() - fps.getBounding_box().getWidth(), 24);
                cps.setText("CPS: " + Integer.toString(GameLoop.game_loop_count));
                cps.update(MainPane.global_g);
                cps.setpos(MainPane.host_frame.getWidth() - cps.getBounding_box().getWidth(), fps.getpos()[1] + cps.getBounding_box().getHeight());
                drawcount_start = Instant.now();
                MainPane.drawcount = 0;
                GameLoop.game_loop_count = 0;

            }

        }
    }

}
