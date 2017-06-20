package slythr;

import stardust.GlobalGamestate;

/**
 * Created by teddy on 5/23/17.
 */
public class SplashThread implements Runnable {

    Thread thread;
    public SplashScreen splash;

    public SplashThread(){
        thread = new Thread(this, "splash thread");
    }

    public void run(){
        System.out.println("showing splash...");
        splash = new slythr.SplashScreen(3000);
        splash.showSplash();
        GlobalGamestate.runtime.gc();
        System.out.println("splash done");
    }

}
