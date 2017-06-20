package stardust;

import javafx.embed.swing.JFXPanel;
import levels.Level;
import levels.mainMenu;
import particles.AsteroidExplodeParticle;
import slythr.*;
import slythr.Image;
import slythr.SplashScreen;
import sprites.*;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;


public class MainPane extends JPanel{


    public static Graphics g;
    public static Graphics global_g;

    public static JFXPanel audiopanel = new JFXPanel();

    //public BufferStrategy buffer_strat;
    public static Stack rendStack = new Stack();
    static Stack standardStack = new Stack();
    public static Stack pauseBuffer = new Stack();
    public Complex_Stack complex_pauseBuffer = new Complex_Stack();
    public static Stack cutsceneBuffer = new Stack();
    public static Complex_Stack cutsceneComplexBuffer = new Complex_Stack();
    boolean newframe = false;
    static boolean framevar_runme = true;
    public static int cvar_gamestate = -2;
    static boolean evar_loading = false;
    public static GlobalGamestate globalGamestate;
    static boolean statevar_gameover = false;
    static BulletSprite bulletSprite;
    static Frame globalFrame;
    static boolean statevar_showingtextbox = false;
    static Animation invuln;
    static Animation fadein = new Animation(null, Animation.OPACITY, new ArrayList<int[]>());
    static Animation fadein2;
    static Animation fadein3;


    static int evar_fpslock = 6;

    public static int drawcount = 0;

    long drawstart;
    int drawwait;

    //AUDIO
    static Audio test_sound;
    static Audio moonlight_sonata;
    static Audio white_dove;


    public static int cvar_saveslot;


//dough
    static ArrayList<int[]> invuln_dough = new ArrayList<>();
    static ArrayList<int[]> blink_dough = new ArrayList<>();


    public static int[] evar_mousepos = {0, 0};

    public static String statevar_current_level_id;

    mainMenu menulvl;


    public static Primitive ship;
    public Primitive pause_text;
    public static Primitive focusguage = new Rect();
    public static Primitive back_to_menu_text;
    public static Primitive resume_text;
    static Primitive health_text;


    public static Primitive continue_text;

    public static Primitive quit_text;

    public static Primitive cutscene_background;

    static Primitive healthblit1;
    static Primitive healthblit2;
    static Primitive healthblit3;

    public static Primitive go_back_text;

    public static Primitive title_text;



    static Primitive cursor;

    public static boolean statevar_timestop = false;

    static boolean statevar_firing = false;
    static int gamevar_firecooldown = 20;
    static int statevar_firetemp = 0;
    static boolean evar_spacekey = false;


    static int gamevar_shipMoveSpeed = 7;
    int gamevar_enemySpawnCooldown = 100;

    static int statevar_enemySpawnTemp = 0;
    static boolean statevar_spawnEnemies = false;
    static boolean statevar_allowMovement = true;

    static boolean statevar_paused = false;
    static boolean statevar_canpause = false;

    static int gamevar_focuscap = 600;
    static int statevar_focuscharge = 0;
    static int gamevar_focusrechargerate = 2;
    static int gamevar_focusdrain = 3;
    int gamevar_minimumtofocus = 100;
    static boolean statevar_focusiscool = true;
    static int gamevar_focusfactor = 2;

    public static Complex dialoguebox;


    public static Frame host_frame;

    static Primitive score;
    Primitive healthbar;

    Animation ship_animation;
    public static Animation blink_animation;
    public static Animation_Buffer general_animation_buffer = new Animation_Buffer();

    public static Complex_Stack dialoguebox_stack = new Complex_Stack();
    public static Complex_Stack main_complex_stack = new Complex_Stack();

    public Complex_Stack getMain_complex_stack(){
        return main_complex_stack;
    }

    public static void update_all_text(Stack stack){
        try {
            for (Primitive obj : stack.makeArrayList()) {
                obj.update(global_g);
            }
        } catch (java.lang.NullPointerException exeption){
            //pass
        }
    }

    public static void stoptime(){
        statevar_timestop = true;
        evar_spacekey = false;
        statevar_canpause = false;
    }
    public static void starttime(){
        statevar_timestop = false;
        evar_spacekey = false;
        dialoguebox_stack.remove(dialoguebox);
        statevar_canpause = true;
    }

//MISC PRIMITIVE DECLARATION

    static Primitive main_menu_title;
    static Primitive new_game_text;
    static Primitive exit_text;
    static Primitive load_text;
    static Primitive options_title;
    static Primitive master_volume_text;
    static Primitive options_text;
    static Primitive slot_1_text;
    static Primitive slot_2_text;
    static Primitive slot_3_text;
    static Primitive master_volume_slider;
    static Primitive master_volume_slider_border;
    static Primitive delete_saves_text;
    static Primitive confirm_text;
    static Primitive game_over_text;

    Primitive qexit_text;

    Primitive testimage;

    public static Complex cutscene_text_box;






    static Action pause;
    static Action unpause;

    //levels
    Level testLevel;
    static Level currentLevel;


    //other
    static int delay;
    int evar_tickspeed;
    Timer repaint_timer;
    Timer animation_cleaner_timer;



    //keybinds
    char keybind_up = 'w';
    char keybind_down = 's';
    char keybind_right = 'd';
    char keybind_left = 'a';
    char keybind_fire = ' ';

    //keys
    public static boolean evar_akey = false;
    public static boolean evar_skey = false;
    public static boolean evar_dkey = false;
    public static boolean evar_wkey = false;
    public boolean evar_esckey = false;
    public static boolean evar_qkey = false;
    public static boolean evar_mouseLeft = false;


    public static boolean evar_detectmousepos = true;







    public static JRootPane rootPane;

    //Render renderThread;



    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public MainPane(JFrame frame, GlobalGamestate gamestate) throws IOException, InterruptedException, LineUnavailableException {
        super();
        //buffer_strat = bufferStrategy;


        host_frame = frame;






        globalGamestate = gamestate;
        rootPane = frame.getRootPane();





        cursor = new Rect();
        cursor.setAttributes(0,0,1,1,0,0,0);

        menulvl = new mainMenu(gamestate, g);



        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                evar_mouseLeft = true;
                evar_mousepos[0] = e.getX();
                evar_mousepos[1] = e.getY();
                cursor.setpos(evar_mousepos[0], evar_mousepos[1]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                evar_mouseLeft = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //pass;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //pass;
            }
        });













        System.out.println("Entering Main Pane...");
        SplashScreen.status.setText("entering main pane");
        System.out.print("Creating some resources...");
        SplashScreen.status.setText("creating some resources");
        Animation.bind_default_animation_buffer(general_animation_buffer);
        globalFrame = frame;
        ship = new Rect();
        health_text = new Text(Integer.toString(GlobalGamestate.statevar_playerHealth), 24, global_g);


        ship.setAttributes(frame.getWidth() / 2, frame.getHeight() / 2, 20, 20, 0, 0, 255);


        cutscene_background = new Image(GlobalGamestate.localizePath("src/images/cutscene_back_1.png"));
        cutsceneBuffer.add(cutscene_background);


        globalGamestate.physics_enable(ship);
        healthbar = new Rect();
        healthbar.setWidth(GlobalGamestate.statevar_playerHealth);
        healthbar.setHeight(15);
        healthbar.setColor(0, 0,255);
        healthbar.setLabel("player's healthbar");

        //INITIALIZE AUDIO
        test_sound = new Audio("src/sounds/Troll Song.mp3");
        moonlight_sonata = new Audio("src/sounds/moonlight_sonata.mp3");
        white_dove = new Audio ("src/sounds/Troll Song.mp3");

        System.out.print("generating animations...");
        SplashScreen.status.setText("generating animations");
        for (int i = 0; i <= 255; i = i + 10) {
            fadein.add(new int[] {i, 0});
        }
        fadein2 = fadein.duplicate_onto();
        fadein3 = fadein.duplicate_onto();



        for (int i = 0; i <= 2; i++){
            for (int j = 0; j <= 15; j++){
                blink_dough.add(new int[] {0, 0});
            }
            for (int k = 0; k <= 15; k++){
                blink_dough.add(new int[] {1, 0});
            }

        }

        for (int i = 0; i < 50; i++){
            invuln_dough.add(new int[] {1, 0});
        }
        invuln_dough.add(new int[] {0, 1});
        System.out.println("done");









        focusguage.setAttributes(0, frame.getHeight() - 30, 15, 56, 0, 255, 0);
        score = new Text(Integer.toString(GlobalGamestate.statevar_score), 24, global_g);
        System.out.println("done");
        score.setpos(0, 15);

        title_text = new Text("empty", 36, global_g);
        title_text.disable();


        healthblit1 = new Rect();
        healthblit1.setAttributes(0,0,15, 20, 0, 255, 0);
        healthblit2 = new Rect();
        healthblit2.setAttributes(0,0,15,20,0,255,0);
        healthblit3 = new Rect();
        healthblit3.setAttributes(0,0,15,20,0,255,0);
        health_text.setText("Health: ");
        health_text.getBounding_box();

        go_back_text = new Text("Back", 24, g);
        go_back_text.setLabel("previous menu");
        go_back_text.setpos(0, 900);

        game_over_text = new Text("Game Over", 48, g);








        System.out.println("done");


        System.out.print("adding game resources to standard game stack...");
        SplashScreen.status.setText("adding game resources to standard game stack");
        standardStack.add(ship);
        standardStack.add(focusguage);
        standardStack.add(score);
        standardStack.add(healthbar);
        standardStack.add(health_text);
        standardStack.add(healthblit1);
        standardStack.add(healthblit2);
        standardStack.add(healthblit3);
        standardStack.add(title_text);
        standardStack.add(game_over_text);

        globalGamestate = gamestate;
        System.out.println("done");

        System.out.print("creating cutscene resources and adding them to buffer");
        SplashScreen.status.setText("creating cutscene resources and adding them to buffer");

        cutsceneBuffer.add(cutscene_background);
        cutscene_text_box = new Text_Box(globalGamestate, global_g, "empty", "empty", "empty", host_frame);
        cutsceneComplexBuffer.add(cutscene_text_box);




        statevar_current_level_id = "testlevel";

        LittleStar.bind_gamestate(globalGamestate);
        LittleStar.bind_host_frame(frame);
        AsteroidExplodeParticle.bind_globalGamestate(globalGamestate);
        Title.bind_globalGamestate(globalGamestate);
        Title.bind_graphics(global_g);

        //initialize levels

        //actions for keybinds
        System.out.print("Setting up actions...");
        SplashScreen.status.setText("setting up actions");
        Action testAction = new AbstractAction("firingOn"){
            public void actionPerformed(ActionEvent e){
                System.out.println("performing test action");
            }
        };

        Action spacePressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_spacekey = true;
            }
        };

        Action spaceReleased = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                evar_spacekey = false;
            }
        };

        Action aPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_akey = true;
            }
        };

        Action aReleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_akey = false;
            }
        };


        Action fireOn = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statevar_firing = true;
            }
        };

        Action fireOff = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statevar_firing = false;
            }
        };


        Action sPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_skey = true;
            }
        };

        Action sReleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_skey = false;
            }
        };

        Action dPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_dkey = true;
            }
        };

        Action dReleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_dkey = false;
            }
        };

        Action wPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_wkey = true;
            }
        };

        Action wReleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_wkey = false;
            }
        };

        Action escPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_esckey = true;
            }
        };

        Action escReleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_esckey = false;
            }
        };

        Action qpressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_qkey = true;
            }
        };

        Action qreleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_qkey = false;
            }
        };

        //pausing and unpausing stuff



        pause = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {




                if (statevar_canpause) {
                    System.out.println("Pausing game");
                    Audio.pauseAll();
                    evar_detectmousepos = true;
                    pause_text = new Text("Game Paused", 40, global_g);
                    back_to_menu_text = new Text("Back to Menu", 24, global_g);
                    quit_text = new Text("Quit Game", 24, global_g);
                    resume_text = new Text("Resume Game", 24, global_g);
                    pause_text.setpos(100, 100);
                    quit_text.setpos(100, 300);
                    back_to_menu_text.setpos(100, 250);
                    resume_text.setpos(100, 200);



                    statevar_paused = true;
                    pauseBuffer.add(rendStack);
                    //todo -- use same pause buffer system for complexes
                    rendStack.flush();
                    rendStack.add(pause_text);
                    rendStack.add(quit_text);
                    rendStack.add(back_to_menu_text);
                    rendStack.add(resume_text);



                    unbind_pause_bind_pause();
                }
            }
        };

        unpause = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("unpausing");
                Audio.resumeAll();
                statevar_paused = false;
                rendStack.flush();
                rendStack.add(pauseBuffer);
                pauseBuffer.flush();
                evar_detectmousepos = false;
                unbind_unpause_bind_pause();
            }
        };


        System.out.println("done");

        //todo - make system ignore normal shift input

        //put keybinds
        System.out.print("Setting up keybinds and binding actions...");
        SplashScreen.status.setText("setting up keybinds and binding actions");

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), "pause");
        rootPane.getActionMap().put("pause", pause);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spacePressed");
        rootPane.getActionMap().put("spacePressed", spacePressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SPACE"), "spaceReleased");
        rootPane.getActionMap().put("spaceReleased", spaceReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "aPressed");
        rootPane.getActionMap().put("aPressed", aPressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "aReleased");
        rootPane.getActionMap().put("aReleased", aReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "sPressed");
        rootPane.getActionMap().put("sPressed", sPressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "sReleased");
        rootPane.getActionMap().put("sReleased", sReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"), "dPressed");
        rootPane.getActionMap().put("dPressed", dPressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "dReleased");
        rootPane.getActionMap().put("dReleased", dReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "wPressed");
        rootPane.getActionMap().put("wPressed", wPressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "wReleased");
        rootPane.getActionMap().put("wReleased", wReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed R"), "escPressed");
        rootPane.getActionMap().put("escPressed", escPressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released R"), "escReleased");
        rootPane.getActionMap().put("escReleased", escReleased);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed Q"), "qpressed");
        rootPane.getActionMap().put("qpressed", qpressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Q"), "qreleased");
        rootPane.getActionMap().put("qreleased", qreleased);





        //setup renderer





        //frame listener






        SplashScreen.status.setText("binding gamestates, host frames, and buffers");
        BulletSprite.bind_gamestate(globalGamestate);
        EnemySprite.bind_gamestate(globalGamestate);
        EnemySprite.bind_host_frame(frame);
        EnemySprite.bind_animation_buffer(general_animation_buffer);

        AsteroidSprite.bind_gamestate(globalGamestate);
        AsteroidSprite.bind_host_frame(frame);






        long startmili = System.currentTimeMillis();
        Instant startsecond = Instant.now();

        Testsprite.bind_graphics(global_g);
        Testsprite.bind_host_frame(frame);
        System.out.println("done");

        System.out.print("loading preferences...");
        SplashScreen.status.setText("loading preferences");
        Settings.load();
        System.out.println("done");

        System.out.println("initial setup done. Entering Main Menu");
        SplashScreen.status.setText("initial setup done; entering main menu");

        //join splash thread
        if (!Main.evar_nosplash) {
            System.out.print("Waiting on splash...");
            Main.splashthread.join();
            Main.splash_direct_access.splash.setInvisible();
            System.out.println("Splash has joined, continuing");
        }

        // spawn threads
        Thread gameThread = new Thread(new GameLoop(), "gamethread");
        Thread fpsThread = new Thread(new FPScounter(), "FPS counter");



        //start threads
        gameThread.start();
        if (Main.evar_drawfps) {
            fpsThread.start();
        }
        repaint();

        ActionListener repainter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };

        ActionListener AnimationCleaner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Animation animation : Animation.default_buffer.buffer) {
                    if (animation.generic && !animation.enabled){
                        Animation.default_buffer.remove(animation);
                    }
                }
            }
        };
        animation_cleaner_timer = new Timer(5000, AnimationCleaner);
        animation_cleaner_timer.start();
        repaint_timer = new Timer(1, repainter);
        repaint_timer.start();
        delay = 16;



    }

    public void force_paint(){
        repaint();
    }


    public void paintComponent(Graphics g) {
        drawcount = drawcount + 1;
        drawstart = System.currentTimeMillis();
        global_g = g;
        g.setColor(Color.black);
        g.fillRect(0, 0, 900, 900);
       // g.setColor(new Color(0, 0, 0));
       // g.fillRect(0, 0, globalFrame.getHeight(), globalFrame.getWidth());



        if (g == null) {
            //pass
        } else {
            if (!statevar_paused) {
                try {
                    EnemySprite.draw(g);
                    BulletSprite.draw(g);
                    AsteroidSprite.draw(g);
                    LittleStar.draw(g);
                    AsteroidExplodeParticle.draw(g);
                    rendStack.draw(g);
                    dialoguebox_stack.draw(g); //temporary solution to complexes rendering during pausetime
                } catch (java.lang.NullPointerException except){
                    //pass
                }
            } else {
                rendStack.draw(g);
            }
            if (cvar_gamestate == 4) {
                cutsceneBuffer.draw(g);
                dialoguebox_stack.draw(g);
            }

            FPScounter.fps.draw(g);
            FPScounter.cps.draw(g);




        }

        drawwait = evar_fpslock - (int) (System.currentTimeMillis() - drawstart);

        if (drawwait < 0){
            drawwait = evar_fpslock;
        }
        try {
            Thread.sleep(drawwait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//


    }

    public static Complex make_text_box(GlobalGamestate gamestate, Graphics graph, String line1, String line2, String line3, Frame frame){
        try {
            statevar_showingtextbox = true;
            dialoguebox = new Text_Box(globalGamestate, global_g, line1, line2, line3, globalFrame);
            dialoguebox_stack.add(dialoguebox);
            return dialoguebox;
        } catch (Exception e){
                JOptionPane.showMessageDialog(new Frame(), e.getStackTrace());
        }
        return null;
    }



    private void unbind_pause_bind_pause(){
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), null);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), "unpause");
        rootPane.getActionMap().put("unpause", unpause);

    }

    public static void unbind_unpause_bind_pause(){
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), null);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), "pause");
        rootPane.getActionMap().put("pause", MainPane.pause);
    }


        //NEWGAME



    static void makeIngame(ArrayList<Primitive> standards) throws IOException, LineUnavailableException {
        white_dove.stop();
        System.out.println("entering game...");
        System.out.print("loading level...");
        set_level(Level.get_level_path("testlevel.lvl"));
        System.out.println("done");
        System.out.print("flushing stacks...");
        rendStack.flush();
        dialoguebox_stack.flush();
        System.out.println("done");
        statevar_spawnEnemies = true;
        statevar_canpause = true;
        System.out.print("adding standard stack items to render stack...");
        for (Primitive obj : standards){
            rendStack.add(obj);
        }
        System.out.println("done");
        System.out.print("setting up animations...");
        invuln = new Animation(ship, Animation.ACTION, invuln_dough);
        invuln.bind_Action(new SlythrAction() {
            @Override
            public void execute() {
                GlobalGamestate.statevar_god = true;
            }

            @Override
            public void execute2() {
                GlobalGamestate.statevar_god = false;
            }
        });
        blink_animation = new Animation(ship, Animation.ENABLED,  blink_dough);
        general_animation_buffer.add(blink_animation);
        general_animation_buffer.add(invuln);
        System.out.println("done");
        System.out.print("doing other misc setup...");




        general_animation_buffer.add(fadein);
        general_animation_buffer.add(fadein2);
        general_animation_buffer.add(fadein3);




        healthblit1.setpos(globalFrame.getWidth() - (healthblit1.getWidth() + 5), globalFrame.getHeight() - 40);
        healthblit2.setpos(globalFrame.getWidth() - (healthblit2.getWidth() + healthblit1.getWidth() + 10), globalFrame.getHeight() - 40);
        healthblit3.setpos(globalFrame.getWidth() - (healthblit3.getWidth() + healthblit2.getWidth() + healthblit1.getWidth() + 15), globalFrame.getHeight() - 40);


        health_text.setpos(globalFrame.getWidth() - health_text.getBounding_box().getWidth() - ((healthblit1.getWidth() * 3) + 20), globalFrame.getHeight());

        LittleStar.initialize();


        evar_detectmousepos = false;


        moonlight_sonata.play();
        System.out.println("done");


     //CONTINUE GAME



    }static void makeIngame(ArrayList<Primitive> standards, String savefile) throws IOException, LineUnavailableException {
        white_dove.stop();
        System.out.println("entering game...");
        System.out.print("loading level...");
        SaveGame.load(savefile);
        System.out.println("done");
        System.out.print("flushing stacks...");
        rendStack.flush();
        dialoguebox_stack.flush();
        System.out.println("done");
        statevar_spawnEnemies = true;
        statevar_canpause = true;
        System.out.print("adding standard stack items to render stack...");
        for (Primitive obj : standards){
            rendStack.add(obj);
        }
        System.out.println("done");
        System.out.print("setting up animations...");

        invuln = new Animation(ship, Animation.ACTION, invuln_dough);
        invuln.bind_Action(new SlythrAction() {
            @Override
            public void execute() {
                GlobalGamestate.statevar_god = true;
            }

            @Override
            public void execute2() {
                GlobalGamestate.statevar_god = false;
            }
        });
        blink_animation = new Animation(ship, Animation.ENABLED,  blink_dough);
        general_animation_buffer.add(blink_animation);
        general_animation_buffer.add(invuln);
        System.out.println("done");
        System.out.print("doing other misc setup...");




        healthblit1.setpos(globalFrame.getWidth() - (healthblit1.getWidth() + 5), globalFrame.getHeight() - 40);
        healthblit2.setpos(globalFrame.getWidth() - (healthblit2.getWidth() + healthblit1.getWidth() + 10), globalFrame.getHeight() - 40);
        healthblit3.setpos(globalFrame.getWidth() - (healthblit3.getWidth() + healthblit2.getWidth() + healthblit1.getWidth() + 15), globalFrame.getHeight() - 40);


        health_text.setpos(globalFrame.getWidth() - health_text.getBounding_box().getWidth() - ((healthblit1.getWidth() * 3) + 20), globalFrame.getHeight());

        LittleStar.initialize();


        evar_detectmousepos = false;


        moonlight_sonata.play();
        System.out.println("done");
    }

    public double double_clock(){
        return Double.parseDouble(Instant.now().toString().split(":")[2].replace("Z", ""));
    }

    public static void set_level(String level) throws IOException {
        currentLevel = new Level(level, globalFrame, globalGamestate);
        currentLevel.load();
    }

    public long get_nano(){
        return Instant.now().getNano();
    }

    public static void gameover(){
        cvar_gamestate = -3;
    }

    public void setCvar_gamestate(int id){
        cvar_gamestate = id;
    }

    public static void populate_cutscene(String background_path, String line1, String line2, String line3) throws IOException {
        cvar_gamestate = 4;
        //cutsceneBuffer.add(new Image(globalGamestate, "cutscene_back_1.png"));
        make_text_box(globalGamestate, global_g, line1, line2, line3, host_frame);

    }

}
