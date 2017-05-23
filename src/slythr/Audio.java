package slythr;


import javafx.scene.media.Media;
import stardust.GlobalGamestate;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by teddy on 5/18/17.
 */
public class Audio implements Runnable {

    static ArrayList<Thread> threads = new ArrayList<>();



    InputStream in;
    AudioStream audioStream;
    public boolean playing = false;
    Thread audiothread;

    Media snd;


    public Audio(String path) throws IOException {
        audiothread = new Thread(this, "slythr audio thread playing " + GlobalGamestate.localizePath(path));
                // get the sound file as a resource out of my jar file;
                // the sound file must be in the same directory as this class file.
                // the input stream portion of this recipe comes from a javaworld.com article.
            InputStream inputStream = getClass().getResourceAsStream("/src/sounds/Troll Song.mp3");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
    }

    public void run(){
        playing = true;
        AudioPlayer.player.start(audioStream);
    }

    public static void play(String path) throws IOException {
        Thread new_thread = new Thread(new Audio(GlobalGamestate.localizePath(path)), "slythr audio thread playing " + GlobalGamestate.localizePath(path));
        threads.add(new_thread);
        new_thread.start();
    }

}
