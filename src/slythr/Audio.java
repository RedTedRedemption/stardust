package slythr;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import stardust.GlobalGamestate;
import sun.audio.AudioStream;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
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
    String self_path;
    MediaPlayer mediaPlayer;

    Media snd;


    public Audio(String path) throws IOException, LineUnavailableException {
        String bip = path;
        self_path = path;
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
    }
    public void run(){
        playing = true;
        mediaPlayer.play();
    }

    public void play(){
     //   try {
            audiothread = new Thread(this, "slythr audio thread playing " + GlobalGamestate.localizePath(self_path));
            threads.add(audiothread);
            audiothread.start();
      //  } catch (IOException e){
      //      System.out.println("Error starting audio file " + GlobalGamestate.localizePath(self_path));
      //  }
    }

    public void stop(){
       mediaPlayer.stop();
    }



}
