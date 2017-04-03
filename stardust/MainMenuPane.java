package stardust;

import javax.swing.*;
import slythr.*;
import levels.*;
import sprites.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MainMenuPane extends JPanel{


    Graphics g;
    Stack rendStack = new Stack();
    Stack activeStack = rendStack;
    Stack loadStack = new Stack();
    Stack standardStack = new Stack();
    Stack spriteStack = new Stack();
    public Stack sendStack = new Stack();
    int menubulge = 0;
    int cvar_menubulgesize = 15;
    int cvar_bulgespeed = 2;
    boolean bulgestate = true;
    int cvar_buttonsize_selection = 24;
    boolean newframe = false;
    boolean framevar_runme = true;
    int cvar_gamestate = 0;
    boolean evar_loading = false;
    EnemySprite enemysprite;
    GlobalGamestate globalGamestate;
    boolean statevar_gameover = false;
    BulletSprite bulletSprite;
    Frame globalFrame;
    Primitive ship;
    KeyStroke keystroke;
    boolean statevar_firing = false;
    int gamevar_firecooldown = 20;
    int statevar_firetemp = 0;
    boolean evar_spacekey = false;
    boolean statevar_weaponsArmed = true;

    int gamevar_shipMoveSpeed = 10;



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

    public MainMenuPane(JFrame frame, GlobalGamestate gamestateDatabase) {
        super();
        //renderThread = new Render("renderThread", g, sendStack);
        System.out.println("Entering Main Pane...");
        System.out.println("Creating some resources...");
        globalFrame = frame;
        ship = new Rect();


        ship.setAttributes(frame.getWidth() / 2, frame.getHeight() / 2, 20, 20, 0, 0, 255);

        Physics phys = new Physics();
        standardStack.add(ship);
        globalGamestate = gamestateDatabase;

        JRootPane rootPane = frame.getRootPane();

        //actions for keybinds
        System.out.println("Setting up actions...");
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




        //put keybinds
        System.out.println("Setting up keybinds...");
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









        //frame listener





        System.out.println("Done!");





        // put all the objects into the rendStack
        int delay = 25;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //System.out.println(phys.pointInObj((int) frame.getMousePosition().getX(), (int) frame.getMousePosition().getY(), start_bounding_box));
                //if (phys.pointInObj((int) frame.getMousePosition().getX(), (int) frame.getMousePosition().getY(), start_bounding_box)){
//
//                  if (menubulge < cvar_menubulgesize && bulgestate) {
//                      menubulge = menubulge + cvar_bulgespeed;
//                      start.setSize(cvar_buttonsize_selection + menubulge);
//                  }
//                  if (menubulge >= cvar_buttonsize_selection && !bulgestate){
//                      menubulge = menubulge - cvar_bulgespeed[ + menubulge);
//                  }



                bulletSprite = new BulletSprite();
                if (framevar_runme){

                   // System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, false));
                    //System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, true));



                    if (cvar_gamestate == 0){
                        rendStack = new levelMenu().getMe();
                        enemysprite = new EnemySprite();
                        if (evar_spacekey){
                            cvar_gamestate = 1;
                            rendStack.flush();
                            makeIngame(standardStack.makeArrayList());
                        }
                    }

                    if (cvar_gamestate == 1 && evar_loading){ //loads all necessary resources for the playground level
                        System.out.println("setting up the playground");
                        makeIngame(standardStack.makeArrayList()); //makes the stack contain stuff that is needed "ingame"
                        // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                        //keep menu items and stuff after we're done with them.
                        evar_loading = false; //keeps this from happening every time we call the frame
                       //enemysprite.instantiate(frame, globalGamestate);
                        //bulletSprite.instantiate(ship);




                    }
                    if (cvar_gamestate == 1 && !evar_loading){

                        enemysprite.behave(ship);
                        bulletSprite.behave();
                        //System.out.println(globalGamestate.statevar_playerHealth);
                        if (globalGamestate.statevar_playerHealth <= 0){
                            System.out.println("player is dead");
                            rendStack.remove(ship);
                            cvar_gamestate = -1;
                        }
                    }
                    if (cvar_gamestate == -1){
                        rendStack.flush();
                        GameoverLevel gameover_level = new GameoverLevel();
                        rendStack.add(gameover_level.getMe());
                    }

                    if (statevar_weaponsArmed){
                        statevar_firing = evar_spacekey;
                    }

                    if (statevar_firing && statevar_firetemp <= 0){
                        bulletSprite.instantiate(ship);

                        statevar_firetemp = gamevar_firecooldown;
                    }

                    //ship movement

                    if (evar_dkey){
                        ship.setpos(ship.getpos()[0] + gamevar_shipMoveSpeed, ship.getpos()[1]);
                    }
                    if (evar_akey){
                        ship.setpos(ship.getpos()[0] - gamevar_shipMoveSpeed, ship.getpos()[1]);
                    }


                    //cool cooldowns
                    statevar_firetemp = statevar_firetemp - 1;




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

        new Timer(delay, taskPerformer).start();

    }



    public void paintComponent(Graphics g) {
        //spriteStack.flush();
        spriteStack.add(enemysprite.getStack());
        spriteStack.add(bulletSprite.getStack());
//        if (cvar_gamestate == -1){
//            spriteStack.flush();
//        }
//        for (Primitive obj : rendStack.makeArrayList()){
//            System.out.println(obj);
//        }
//        for (Primitive obj : spriteStack.makeArrayList()){
//            System.out.println(obj);
//        }
        sendStack.add(bulletSprite.getStack());
        //sendStack.add(rendStack.makeArrayList());
        //renderThread.run();
        bulletSprite.draw(g);
        rendStack.draw(g);
        spriteStack.draw(g);


    }




    void makeIngame(ArrayList<Primitive> standards){
        rendStack.flush();
        for (Primitive obj : standards){
            rendStack.add(obj);
        }
    }




}
