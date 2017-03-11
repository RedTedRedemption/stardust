package stardust;

import javax.swing.*;
import slythr.*;
import levels.*;
import sprites.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MainMenuPane extends JPanel implements KeyListener{


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
    Component globalListener = new Canvas();



    //keybinds
    char keybind_up = 'w';
    char keybind_down = 's';
    char keybind_right = 'd';
    char keybind_left = 'a';
    char keybind_fire = ' ';


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

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ship.setColor(0,0,255);
                System.out.println("performing action");
            }
        };

        //frame listener






        System.out.println("Done!");
        System.out.println("Adding keylistener");
        addKeyListener(this);




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
                    }

                    if (cvar_gamestate == 1 && evar_loading){ //loads all necessary resources for the playground level
                        System.out.println("setting up the playground");
                        makeIngame(standardStack.makeArrayList()); //makes the stack contain stuff that is needed "ingame"
                        // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                        //keep menu items and stuff after we're done with them.
                        evar_loading = false; //keeps this from happening every time we call the frame
                       //enemysprite.instantiate(frame, globalGamestate);
                        bulletSprite.instantiate(ship);




                    }
                    if (cvar_gamestate == 1 && !evar_loading){

                        enemysprite.behave(ship);
                        bulletSprite.behave();
                        System.out.println(globalGamestate.statevar_playerHealth);
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        cvar_gamestate = 1;
        evar_loading = true;
        if (cvar_gamestate == 1){
            //bulletSprite.instantiate(ship);
            //enemysprite.instantiate(globalFrame, globalGamestate);
        }
        //enemysprite.instantiate(globalFrame, globalGamestate);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }


    void makeIngame(ArrayList<Primitive> standards){
        rendStack.flush();
        for (Primitive obj : standards){
            rendStack.add(obj);
        }
    }




}
