package stardust;

import Particles.AsteroidExplodeParticle;
import levels.GameoverLevel;
import slythr.Physics;
import slythr.Primitive;
import slythr.Text;
import sprites.AsteroidSprite;
import sprites.BulletSprite;
import sprites.EnemySprite;
import sprites.LittleStar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static stardust.MainPane.*;

/**
 * Created by teddy on 5/17/17.
 */
public class GameLoop implements Runnable{

    Thread gameThread;

    public GameLoop(){
        gameThread = new Thread(this, "Stardust game loop thread");

    }

    public void run() {
        System.out.println("gamethread has started");
        while (framevar_runme) {
            try {
                if (MainPane.evar_detectmousepos) {
                    MainPane.evar_mousepos[0] = (int) (MouseInfo.getPointerInfo().getLocation().getX() - host_frame.getLocationOnScreen().getX());
                    MainPane.evar_mousepos[1] = (int) (MouseInfo.getPointerInfo().getLocation().getY() - host_frame.getLocationOnScreen().getY()) - 30;
                }
            } catch (java.awt.IllegalComponentStateException e) {

            }


//                Testsprite.instantiate();

            if (MainPane.global_g != null) {
                MainPane.update_all_text(MainPane.rendStack);
                MainPane.score.setText(Integer.toString(GlobalGamestate.statevar_score));

//                evar_mousepos[0] = mouseLocator.mousePos[0];
//                evar_mousepos[1] = mouseLocator.mousePos[1];

//                PointerInfo mouseloc = MouseInfo.getPointerInfo();
//                evar_mousepos[0] = (int) mouseloc.getLocation().getX();
//                evar_mousepos[1] = (int) mouseloc.getLocation().getY();

                MainPane.delay = 16;
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


                if (MainPane.framevar_runme) {

                    // System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, false));
                    //System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, true));

                    //MAIN MENU INITIALIZATION CONTROLLER
                    if (cvar_gamestate == -2) {
                        System.out.println("initializing main menu...");
                        //rendStack = menulvl.getMe();
                        MainPane.title = new Text("Main Menu", 48, g, globalGamestate);
                        MainPane.start = new Text("Start Game", 24, g, globalGamestate);
                        MainPane.exit_text = new Text("Exit Game", 24, g, globalGamestate);


                        MainPane.title.setpos(60, 150);
                        start.setpos(60, 210);
                        exit_text.setpos(60, 250);


                        rendStack.add(MainPane.title);
                        rendStack.add(start);
                        rendStack.add(exit_text);


                        cvar_gamestate = 0;
                        System.out.println("...done");


                    }

                    //MAIN MENU CONTROLLER
                    if (cvar_gamestate == 0) {


//                        try {
//
//                            if (phys.pointInObj((int)frame.getMousePosition().getX(), (int)frame.getMousePosition().getY(), start)) {
//                                System.out.println("mouse overlapping");
//                            }
//                        } catch (java.lang.NullPointerException e){
//                                //pass
//                        }


//                            start.bounding_box.setpos(start.getpos()[0], start.getpos()[1] - 20);
//                            start.bounding_box.setWidth(100);


                        if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], start.getBounding_box())) {
                            if (start.getSize() < 30) {
                                start.setSize(start.getSize() + 1);
                            }
                        } else {
                            if (start.getSize() > 24) {
                                start.setSize(start.getSize() - 1);
                            }
                        }

                        if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], exit_text.getBounding_box())) {
                            if (exit_text.getSize() < 30) {
                                exit_text.setSize(exit_text.getSize() + 1);
                            }
                        } else {
                            if (exit_text.getSize() > 24) {
                                exit_text.setSize(exit_text.getSize() - 1);
                            }
                        }

                        if (evar_mouseLeft && Physics.doObjectsCollide(cursor, start.getBounding_box())) {
                            //if (phys.pointInObj(getMousePosition()[0], getMousePosition()[1], ))
                            cvar_gamestate = 1;
                            rendStack.flush();
                            try {
                                makeIngame(standardStack.makeArrayList());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (MainPane.evar_mouseLeft && Physics.doObjectsCollide(MainPane.cursor, exit_text.getBounding_box())) {
                            System.out.println("Exiting by request");
                            System.exit(0);
                        }
                    }

                    //PAUSE CONTROLLER
                    if (MainPane.statevar_paused) {

                        if (evar_mouseLeft && Physics.doObjectsCollide(cursor, MainPane.quit_text.getBounding_box())) {
                            System.out.println("Quitting by request");
                            System.exit(0);
                        }
                        if (evar_mouseLeft && Physics.doObjectsCollide(cursor, MainPane.back_to_menu_text.getBounding_box())) {
                            evar_mouseLeft = false;
                            cvar_gamestate = -2;
                            statevar_paused = false;
                            MainPane.statevar_gameover = false;
                            rendStack.flush();
                            MainPane.unbind_unpause_bind_pause();
                            BulletSprite.flush();
                            AsteroidSprite.flush();
                            EnemySprite.flush();
                            LittleStar.flush();
                            AsteroidExplodeParticle.flush();
                        }

                        if (evar_mouseLeft && Physics.doObjectsCollide(cursor, MainPane.resume_text.getBounding_box())) {
                            MainPane.unpause.actionPerformed(new ActionEvent(MainPane.host_frame, 2, "nothing"));
                        }

                        if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], quit_text.getBounding_box())) {
                            if (quit_text.getSize() < 30) {
                                quit_text.setSize(quit_text.getSize() + 1);
                            }
                        } else if (quit_text.getSize() > 24) {
                            quit_text.setSize(quit_text.getSize() - 1);
                        }

                        if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], back_to_menu_text.getBounding_box())) {
                            if (back_to_menu_text.getSize() < 30) {
                                back_to_menu_text.setSize(back_to_menu_text.getSize() + 1);
                            }
                        } else if (back_to_menu_text.getSize() > 24) {
                            back_to_menu_text.setSize(back_to_menu_text.getSize() - 1);
                        }

                        if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], resume_text.getBounding_box())) {
                            if (resume_text.getSize() < 30) {
                                resume_text.setSize(resume_text.getSize() + 1);
                            }
                        } else if (resume_text.getSize() > 24) {
                            resume_text.setSize(resume_text.getSize() - 1);
                        }

                    }


                    //LOADING CONTROLLER

                    if (cvar_gamestate == 1 && MainPane.evar_loading) { //loads all necessary resources for the level
                        System.out.print("setting up the level...");
                        try {
                            makeIngame(MainPane.standardStack.makeArrayList()); //makes the stack contain stuff that is needed "ingame"
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
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


                        MainPane.focusguage.setWidth(MainPane.statevar_focuscharge);


                        //System.out.println(globalGamestate.statevar_playerHealth);
                        if (GlobalGamestate.statevar_playerHealth <= 0) {
                            System.out.println("player is dead");
                            rendStack.remove(ship);
                            cvar_gamestate = -1;
                        }


                        //HEALTHBLITS

                        if (globalGamestate.statevar_playerHealth < 3) {
                            healthblit3.disable();
                        } else {
                            healthblit3.enable();
                        }
                        if (globalGamestate.statevar_playerHealth < 2) {
                            healthblit2.disable();
                        } else {
                            healthblit2.enable();
                        }


                        if (MainPane.statevar_firing && MainPane.statevar_firetemp <= MainPane.gamevar_firecooldown * GlobalGamestate.time) {
                            BulletSprite.instantiate(ship);

                            statevar_firetemp = gamevar_firecooldown * 2;
                        }
                        if (MainPane.statevar_allowMovement) {
                            ship.setPhysics_velocity(0, 0);
                            if (MainPane.evar_dkey) {
                                if (ship.getpos()[0] <= MainPane.host_frame.getWidth() - ship.getWidth()) {
                                    ship.setPhysics_velocity_x(ship.physics_velocity_x + MainPane.gamevar_shipMoveSpeed);
                                }
                            }
                            if (MainPane.evar_akey) {
                                if (ship.getpos()[0] >= 0) {
                                    ship.setPhysics_velocity_x(ship.physics_velocity_x - gamevar_shipMoveSpeed);
                                }
                            }
                            if (MainPane.evar_wkey) {
                                if (ship.getpos()[1] >= 0 + ship.height) {
                                    ship.setPhysics_velocity_y(ship.physics_velocity_y - gamevar_shipMoveSpeed);
                                }
                            }
                            if (MainPane.evar_skey) {
                                if (ship.getpos()[1] <= host_frame.getHeight() - ship.height) {
                                    ship.setPhysics_velocity_y(ship.physics_velocity_y + gamevar_shipMoveSpeed);
                                }
                            }
                        }

                        //todo change movement to physics simulation implementation -- mostly done?


                        if (cvar_gamestate == -1) {
                            rendStack.flush();
                            AsteroidSprite.flush();
                            LittleStar.flush();
                            BulletSprite.flush();
                            GameoverLevel gameover_level = new GameoverLevel(g, globalGamestate);
                            rendStack.add(gameover_level.getMe());
                            if (evar_spacekey) {
                                cvar_gamestate = 0;
                                statevar_gameover = false;
                            }
                        }

                        if (GlobalGamestate.statevar_weaponsArmed) {
                            statevar_firing = evar_spacekey;
                        }

                        if (!statevar_timestop) {

                            //cool cooldowns
                            statevar_firetemp = statevar_firetemp - 2;


                            //focus system
                            if (MainPane.evar_lshiftkey && MainPane.statevar_focusiscool) {
                                globalGamestate.set_time(1.0 / MainPane.gamevar_focusfactor);
                                statevar_focuscharge = statevar_focuscharge - MainPane.gamevar_focusdrain;
                            } else {
                                globalGamestate.set_time(1);
                            }
                            if (statevar_focuscharge == 0) {
                                statevar_focusiscool = false;
                            }
                            if (!statevar_focusiscool) {
                                if (statevar_focuscharge == MainPane.gamevar_focuscap / 2) {
                                    statevar_focusiscool = true;
                                }
                            }
                            if (statevar_focuscharge < gamevar_focuscap / 2) {
                                statevar_focuscharge = statevar_focuscharge + MainPane.gamevar_focusrechargerate;
                            }


                            //sprite and particle behavior
                            EnemySprite.behave(ship);
                            BulletSprite.rephysic();
                            EnemySprite.rephysic();
                            AsteroidSprite.rephysic();
                            BulletSprite.behave();
                            AsteroidSprite.behave(ship, MainPane.bulletSprite);
                            LittleStar.behave();
                            AsteroidExplodeParticle.behave();

                            //animation steps
                            general_animation_buffer.step();

                            //physics simulation
                            Physics.simulate();

                            if (statevar_spawnEnemies) {
/*steps the level*/
                                try {
                                    if (!currentLevel.step(g, dialoguebox_stack)) {
                                        cvar_gamestate = -1;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                        } else if (evar_spacekey) {
                            evar_spacekey = false;
                            try {
                                if (!currentLevel.step(g, dialoguebox_stack)) {
                                    cvar_gamestate = -1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }




                    //todo change step() behavior to use time variable


                    MainPane.statevar_enemySpawnTemp = statevar_enemySpawnTemp - (int) (2.0 / GlobalGamestate.time);

                    //gameover displayer

                    if (cvar_gamestate == -3) {
                        rendStack.flush();
                        statevar_paused = true;
                        Primitive game_over = new Text("Game Over", 50, global_g, globalGamestate);
                        game_over.centerx(host_frame.getWidth() / 2, global_g);
                        game_over.setpos(game_over.getpos()[0], host_frame.getHeight() / 2);
                        rendStack.add(game_over);
                    }

                    //cutscene controller


                    if (cvar_gamestate == 4){
                        if (evar_spacekey){
                            try {
                                currentLevel.step(global_g, main_complex_stack);
                                evar_spacekey = false;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
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

            } else {

            }
            try {
                gameThread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



}

