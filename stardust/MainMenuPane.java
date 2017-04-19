package stardust;

import javax.swing.*;
import slythr.*;
import levels.*;
import sprites.*;
import sun.plugin.JavaRunTime;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainMenuPane extends JPanel{


    public static Graphics g;
    Stack rendStack = new Stack();
    Stack activeStack = rendStack;
    Stack loadStack = new Stack();
    Stack standardStack = new Stack();
    Stack spriteStack = new Stack();
    Stack pauseBuffer = new Stack();
    public Stack sendStack = new Stack();
    int menubulge = 0;
    int cvar_menubulgesize = 15;
    int cvar_bulgespeed = 2;
    boolean bulgestate = true;
    int cvar_buttonsize_selection = 24;
    boolean newframe = false;
    boolean framevar_runme = true;
    int cvar_gamestate = -2;
    boolean evar_loading = false;
    EnemySprite enemysprite;
    GlobalGamestate globalGamestate;
    boolean statevar_gameover = false;
    BulletSprite bulletSprite;
    Frame globalFrame;

    mainMenu menulvl;

    Primitive ship;
    Primitive pause_text = new Text("Game Paused", 36, g);
    Primitive focusguage = new Rect(globalGamestate);

    boolean statevar_firing = false;
    int gamevar_firecooldown = 10;
    int statevar_firetemp = 0;
    boolean evar_spacekey = false;
    boolean statevar_weaponsArmed = true;

    int gamevar_shipMoveSpeed = 7;
    int gamevar_enemySpawnCooldown = 100;

    int statevar_enemySpawnTemp = 0;
    boolean statevar_spawnEnemies = false;
    boolean statevar_allowMovement = true;

    boolean statevar_paused = false;
    boolean statevar_canpause = false;

    int gamevar_focuscap = 600;
    int statevar_focuscharge = 0;
    int gamevar_focusrechargerate = 2;
    int gamevar_focusdrain = 3;
    int gamevar_minimumtofocus = 100;
    boolean statevar_focusiscool = true;
    int gamevar_focusfactor = 2;


    Text title;
    Text start;
    Rect start_rect;



    Action pause;
    Action unpause;

    //levels
    Level testLevel;


    //other
    int delay;
    int evar_tickspeed;
    Timer tmr;



    //keybinds
    char keybind_up = 'w';
    char keybind_down = 's';
    char keybind_right = 'd';
    char keybind_left = 'a';
    char keybind_fire = ' ';

    //keys
    boolean evar_akey = false;
    boolean evar_skey = false;
    boolean evar_dkey = false;
    boolean evar_wkey = false;
    boolean evar_esckey = false;
    boolean evar_lshiftkey = false;

    public JRootPane rootPane;


    //Render renderThread;



    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    Action testaction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action performed");
        }
    };

    public void forceDraw(){
        repaint();
    }

    public MainMenuPane(JFrame frame, GlobalGamestate gamestate) throws FileNotFoundException {
        super();

        menulvl = new mainMenu(gamestate, g);

        globalGamestate = gamestate;
        //renderThread = new Render("renderThread", g, sendStack);
        System.out.println("Entering Main Pane...");
        System.out.print("Creating some resources...");
        globalFrame = frame;
        ship = new Rect(gamestate);


        ship.setAttributes(frame.getWidth() / 2, frame.getHeight() / 2, 20, 20, 0, 0, 255);
        pause_text.setpos(100, 100);

        globalGamestate.physics_enable(ship);

        focusguage.setAttributes(0, frame.getHeight() - 30, 15, 56, 0, 255, 0);

        System.out.println("done");

        Physics phys = new Physics(globalGamestate);

        System.out.print("adding game resources to standard game stack...");
        standardStack.add(ship);
        standardStack.add(focusguage);
        globalGamestate = gamestate;
        System.out.println("done");

        rootPane = frame.getRootPane();

        //initialize levels
        System.out.println("initializing levels...");
        testLevel = new Level("/Users/teddy/Desktop/ij/stardust/src/levels/testlevel.lvl", frame, globalGamestate);
        System.out.println("...done");

        //actions for keybinds
        System.out.print("Setting up actions...");
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

        Action lshiftpressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_lshiftkey = true;
            }
        };

        Action lshiftreleased = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evar_lshiftkey = false;
            }
        };

        //pausing and unpausing stuff



        pause = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statevar_canpause) {
                    System.out.println("Trying to pause");
                    statevar_paused = true;
                    pauseBuffer.add(rendStack);
                    rendStack.flush();
                    rendStack.add(pause_text);
                    unbind_pause_bind_pause();
                }
            }
        };

        unpause = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("trying to unpause");
                statevar_paused = false;
                rendStack.flush();
                rendStack.add(pauseBuffer);
                pauseBuffer.flush();

                unbind_unpause_bind_pause();
            }
        };


        System.out.println("done");

        //todo - make system ignore normal shift input

        //put keybinds
        System.out.print("Setting up keybinds and binding actions...");

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

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed Q"), "lshiftpressed");
        rootPane.getActionMap().put("lshiftpressed", lshiftpressed);

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Q"), "lshiftreleased");
        rootPane.getActionMap().put("lshiftreleased", lshiftreleased);





        //setup renderer





        //frame listener





        System.out.println("done");
        System.out.println("inital setup done. Entering Main Menu");





        // put all the objects into the rendStack

        delay = 10;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                delay = 10;
                //System.out.println(phys.pointInObj((int) frame.getMousePosition().getX(), (int) frame.getMousePosition().getY(), start_bounding_box));
  //              if (phys.pointInObj((int) frame.getMousePosition().getX(), (int) frame.getMousePosition().getY(), start_bounding_box)){
//
//                  if (menubulge < cvar_menubulgesize && bulgestate) {
//                      menubulge = menubulge + cvar_bulgespeed;
//                      start.setSize(cvar_buttonsize_selection + menubulge);
//                  }
//                  if (menubulge >= cvar_buttonsize_selection && !bulgestate){
//                      menubulge = menubulge - cvar_bulgespeed[ + menubulge);
//                  }

                bulletSprite = new BulletSprite(globalGamestate);
                enemysprite = new EnemySprite(frame, globalGamestate);
                if (framevar_runme){

                   // System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, false));
                    //System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, true));


                    if (cvar_gamestate == -2){
                        System.out.println("initializing main menu...");
                        //rendStack = menulvl.getMe();
                        title = new Text("Main Menu", 36, g);
                        start = new Text("start game", 24, g);
                        title.setpos(60, 150);
                        start.setpos(60, 210);

                        rendStack.add(title);
                        rendStack.add(start);
                        cvar_gamestate = 0;
                        System.out.println("...done");

                    }
                    if (cvar_gamestate == 0){
                        try {

                            if (phys.pointInObj((int)frame.getMousePosition().getX(), (int)frame.getMousePosition().getY(), start)) {
                                System.out.println("mouse overlapping");
                            }
                        } catch (java.lang.NullPointerException e){
                                //pass
                        }

                        if (evar_spacekey){
                            //if (phys.pointInObj(getMousePosition()[0], getMousePosition()[1], ))
                            cvar_gamestate = 1;
                            rendStack.flush();
                            try {
                                makeIngame(standardStack.makeArrayList());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (cvar_gamestate == 1 && evar_loading){ //loads all necessary resources for the level
                        System.out.print("setting up the level...");

                        try {
                            makeIngame(standardStack.makeArrayList()); //makes the stack contain stuff that is needed "ingame"
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                        //keep menu items and stuff after we're done with them.
                        evar_loading = false; //keeps this from happening every time we call the frame
                       //enemysprite.instantiate(frame, globalGamestate);
                        //bulletSprite.instantiate(ship);
                        System.out.println("done");




                    }

                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //---------------------------------------------------------
                    //RUN WHILE GAME IS NOT PAUSED AND NOT LOADING
                    if (cvar_gamestate == 1 && !evar_loading && !statevar_paused) {
//                        if (phys.pointInObj((int) frame.getMousePosition().getX(), (int) frame.getMousePosition().getY(), ship)){
//                            System.out.println("mouse overlapping ship");
//                        }
                        focusguage.setWidth(statevar_focuscharge);
                        if (statevar_focuscharge < gamevar_focuscap / 2) {
                            statevar_focuscharge = statevar_focuscharge + gamevar_focusrechargerate;
                        }
                        globalGamestate.physics_stack.flush();
                        globalGamestate.physics_stack.add(ship);

                        enemysprite.behave(ship, bulletSprite);
                        globalGamestate.physics_stack.add(enemysprite.getStack());
                        globalGamestate.physics_stack.add(bulletSprite.getStack());
                        bulletSprite.behave();
                        //System.out.println(globalGamestate.statevar_playerHealth);
                        if (globalGamestate.statevar_playerHealth <= 0) {
                            System.out.println("player is dead");
                            rendStack.remove(ship);
                            cvar_gamestate = -1;
                        }
                        if (statevar_spawnEnemies) {
                            if (!testLevel.step(enemysprite)) {
                                cvar_gamestate = -1;
                            }

                        }

                        if (statevar_firing && statevar_firetemp <= gamevar_firecooldown * globalGamestate.time){
                            bulletSprite.instantiate(ship);

                            statevar_firetemp = gamevar_firecooldown * 2;
                        }
                        if (statevar_allowMovement) {
                            ship.setPhysics_velocity(0, 0);
                            if (evar_dkey) {
                                if (ship.getpos()[0] <= frame.getWidth() - ship.getWidth()) {
                                    ship.setPhysics_velocity_x(ship.physics_velocity_x + gamevar_shipMoveSpeed);
                                }
                            }
                            if (evar_akey) {
                                if (ship.getpos()[0] >= 0) {
                                    ship.setPhysics_velocity_x(ship.physics_velocity_x - gamevar_shipMoveSpeed);
                                }
                            }
                            if (evar_wkey) {
                                if (ship.getpos()[1] >= 0 + ship.height) {
                                    ship.setPhysics_velocity_y(ship.physics_velocity_y - gamevar_shipMoveSpeed);
                                }
                            }
                            if (evar_skey) {
                                if (ship.getpos()[1] <= frame.getHeight() - ship.height) {
                                    ship.setPhysics_velocity_y(ship.physics_velocity_y + gamevar_shipMoveSpeed);
                                }
                            }
                        }

                        //todo change movement to physics simulation implementation -- mostly done?
                        phys.simulate();

                        if (evar_lshiftkey && statevar_focusiscool) {
                            globalGamestate.set_time(1.0 / gamevar_focusfactor);
                            statevar_focuscharge = statevar_focuscharge - gamevar_focusdrain;
                        } else {
                            globalGamestate.set_time(1);
                        }
                        if (statevar_focuscharge == 0) {
                            statevar_focusiscool = false;
                        }
                        if (!statevar_focusiscool) {
                            if (statevar_focuscharge == gamevar_focuscap / 2) {
                                statevar_focusiscool = true;
                            }
                        }
                        if (cvar_gamestate == -1) {
                            rendStack.flush();
                            GameoverLevel gameover_level = new GameoverLevel(g);
                            rendStack.add(gameover_level.getMe());
                        }

                        if (statevar_weaponsArmed) {
                            statevar_firing = evar_spacekey;
                        }
                    }



                    //cool cooldowns

                    //todo change step() behavior to use time variable


                    statevar_firetemp = statevar_firetemp - 2;

                    statevar_enemySpawnTemp = statevar_enemySpawnTemp - (int)(2.0 / globalGamestate.time);



                            //////////////////////
                            //                  //
                            //   This frame's   //
                            //  code goes here  //
                            //                  //
                            //////////////////////

//                    System.out.println(newframe);
//                    System.out.println("menuframe is active");

                }

//                if (newframe){
//                    System.out.println("setting new frame");
//                    frame.setVisible(false);
//                    frame.setContentPane(new GameFrame(frame));
//                    frame.setVisible(true);
//                    framevar_runme = false;
//                    newframe = false;
//                }
                repaint();

            }
        };

        tmr = new Timer(delay, taskPerformer);
        tmr.start();
    }



    public void paintComponent(Graphics g) {
        //spriteStack.flush();
        //spriteStack.add(enemysprite.getStack());
        //spriteStack.add(bulletSprite.getStack());
//        if (cvar_gamestate == -1){
//            spriteStack.flush();
//        }
//        for (Primitive obj : rendStack.makeArrayList()){
//            System.out.println(obj);
//        }
//        for (Primitive obj : spriteStack.makeArrayList()){
//            System.out.println(obj);
//        }
        //sendStack.add(bulletSprite.getStack());
        //sendStack.add(rendStack.makeArrayList());
        //renderThread.run();



//        if (!rend.graphics_bound){
//            rend.bindGraphics(g);

//        }
        //System.out.println("making render instance");
        //Render rnd = new Render("stardust render thread", g, rendStack);
        //rnd.start();
//        try {
//            rnd.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //rendStack.draw(g);


//       if (!statevar_paused && cvar_gamestate != -1) {
//           bulletSprite.draw(g);
//           enemysprite.draw(g);
//       }
//        spriteStack.draw(g);
//        rendStack.draw(g);

        enemysprite.draw(g);
        bulletSprite.draw(g);
        rendStack.draw(g);



    }



    private void unbind_pause_bind_pause(){
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), null);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), "unpause");
        rootPane.getActionMap().put("unpause", unpause);

    }

    private void unbind_unpause_bind_pause(){
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), null);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed ESCAPE"), "pause");
        rootPane.getActionMap().put("pause", pause);
    }

    void makeIngame(ArrayList<Primitive> standards) throws FileNotFoundException {
        System.out.print("entering game...");
        testLevel.load();
        rendStack.flush();
        statevar_spawnEnemies = true;
        statevar_canpause = true;
        System.out.print("adding standard stack items to render stack...");
        for (Primitive obj : standards){
            rendStack.add(obj);
        }
        System.out.println("done");
    }




}
