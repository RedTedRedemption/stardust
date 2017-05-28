package stardust;

import Particles.AsteroidExplodeParticle;
import levels.GameoverLevel;
import levels.Level;
import slythr.Physics;
import slythr.Primitive;
import slythr.Text;
import sprites.AsteroidSprite;
import sprites.BulletSprite;
import sprites.EnemySprite;
import sprites.LittleStar;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.time.Instant;

import static stardust.MainPane.*;

/**
 * Created by teddy on 5/17/17.
 */
public class GameLoop implements Runnable{

    Thread gameThread;
    String statevar_menu = "main";
    String slot_1_content = new String(Files.readAllBytes(FileSystems.getDefault().getPath(SaveGame.get_savedat_path("slot_1.sav"))), StandardCharsets.UTF_8);
    String slot_2_content = new String(Files.readAllBytes(FileSystems.getDefault().getPath(SaveGame.get_savedat_path("slot_2.sav"))), StandardCharsets.UTF_8);
    String slot_3_content = new String(Files.readAllBytes(FileSystems.getDefault().getPath(SaveGame.get_savedat_path("slot_3.sav"))), StandardCharsets.UTF_8);
    public static int game_loop_count;
    public static Instant dialogue_start;



    public GameLoop() throws IOException {
        gameThread = new Thread(this, "Stardust game loop thread");

        System.out.println(slot_1_content);
        System.out.println(slot_2_content);
        System.out.println(slot_3_content);

    }

    public void run() {
        System.out.println("gamethread has started");
        while (framevar_runme) {
            long gloopstart = System.currentTimeMillis();
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
//                      new_game_text.setSize(cvar_buttonsize_selection + menubulge);
//                  }
//                  if (menubulge >= cvar_buttonsize_selection && !bulgestate){
//                      menubulge = menubulge - cvar_bulgespeed[ + menubulge);
//                  }


                if (MainPane.framevar_runme) {

                    // System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, false));
                    //System.out.println(KeyStroke.getKeyStroke(keybind_up, 0, true));

                    //MAIN MENU INITIALIZATION CONTROLLER
                    if (cvar_gamestate == -2 && statevar_menu.equals("main")) {
                        System.out.print("initializing main menu...");
                        MainPane.white_dove.play();
                        MainPane.go_back_text.disable();
                        //rendStack = menulvl.getMe();
                        MainPane.main_menu_title = new Text("Main Menu", 48, g, globalGamestate);
                        MainPane.new_game_text = new Text("New Game", 24, g, globalGamestate);
                        MainPane.continue_text = new Text("Continue Game", 24, g, globalGamestate);
                        MainPane.exit_text = new Text("Exit Game", 24, g, globalGamestate);
                        MainPane.load_text = new Text("Load Game", 24, g, globalGamestate);


                        if (slot_1_content.equals("")) {
                            MainPane.slot_1_text = new Text("Slot 1 <EMPTY>", 36, g, globalGamestate);
                        } else {
                            MainPane.slot_1_text = new Text("Slot 1", 36, g, globalGamestate);
                        }

                        if (slot_2_content.equals("")) {
                            MainPane.slot_2_text = new Text("Slot 2 <EMPTY>", 36, g, globalGamestate);
                        } else {
                            MainPane.slot_2_text = new Text("Slot 2", 36, g, globalGamestate);
                        }

                        if (slot_3_content.equals("")) {
                            MainPane.slot_3_text = new Text("Slot 3 <EMPTY>", 36, g, globalGamestate);
                        } else {
                            MainPane.slot_3_text = new Text("Slot 3", 36, g, globalGamestate);
                        }



                            MainPane.main_menu_title.setpos(60, 150);
                        continue_text.setpos(60, 200);
                        new_game_text.setpos(60, 250);
                        load_text.setpos(60, 300);
                        exit_text.setpos(60, 350);
                        slot_1_text.enable();
                        slot_1_text.setLabel("save slot 1 text object");
                        slot_2_text.setLabel("save slot 2 text object");
                        slot_3_text.setLabel("save slot 3 text object");
                        slot_2_text.disable();
                        slot_1_text.disable();
                        slot_3_text.disable();


                        slot_1_text.update(global_g);
                        slot_2_text.update(global_g);
                        slot_3_text.update(global_g);

                        slot_2_text.setpos(host_frame.getWidth() / 2 - slot_2_text.getBounding_box().getWidth() / 2, host_frame.getHeight() / 2);
                        slot_1_text.setpos(host_frame.getWidth() / 2 - slot_1_text.getBounding_box().getWidth() / 2, host_frame.getHeight() / 2 - 100);
                        slot_3_text.setpos(host_frame.getWidth() / 2 - slot_3_text.getBounding_box().getWidth() / 2, host_frame.getHeight() / 2 + 100);

                        rendStack.add(MainPane.main_menu_title);
                        rendStack.add(new_game_text);
                        rendStack.add(exit_text);
                        rendStack.add(continue_text);
                        rendStack.add(load_text);
                        rendStack.add(slot_2_text);
                        rendStack.add(slot_3_text);
                        rendStack.add(slot_1_text);


                        cvar_gamestate = 0;
                        System.out.println("done");


                    }

                    //MAIN MENU CONTROLLER
                    if (cvar_gamestate == 0) {


//                        try {
//
//                            if (phys.pointInObj((int)frame.getMousePosition().getX(), (int)frame.getMousePosition().getY(), new_game_text)) {
//                                System.out.println("mouse overlapping");
//                            }
//                        } catch (java.lang.NullPointerException e){
//                                //pass
//                        }


//                            new_game_text.bounding_box.setpos(new_game_text.getpos()[0], new_game_text.getpos()[1] - 20);
//                            new_game_text.bounding_box.setWidth(100);
                        if (statevar_menu.equals("main")) {

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], new_game_text.getBounding_box())) {
                                if (new_game_text.getSize() < 30) {
                                    new_game_text.setSize(new_game_text.getSize() + 1);
                                }
                            } else {
                                if (new_game_text.getSize() > 24) {
                                    new_game_text.setSize(new_game_text.getSize() - 1);
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

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], continue_text.getBounding_box())) {
                                if (continue_text.getSize() < 30) {
                                    continue_text.setSize(continue_text.getSize() + 1);
                                }
                            } else {
                                if (continue_text.getSize() > 24) {
                                    continue_text.setSize(continue_text.getSize() - 1);
                                }
                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], load_text.getBounding_box())) {
                                if (load_text.getSize() < 30) {
                                    load_text.setSize(continue_text.getSize() + 1);
                                }
                            } else {
                                if (load_text.getSize() > 24) {
                                    load_text.setSize(load_text.getSize() - 1);
                                }
                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, new_game_text.getBounding_box())) {

                                evar_mouseLeft = false;
                                go_back_text.setLabel("previous menu button");
                                go_back_text.enable();
                                rendStack.add(go_back_text);
                                go_back_text.setpos(0, 875);
//

                                statevar_menu = "newgame";
                                slot_1_text.enable();
                                slot_2_text.enable();
                                slot_3_text.enable();
                                main_menu_title.disable();
                                continue_text.disable();
                                new_game_text.disable();
                                load_text.disable();
                                exit_text.disable();
                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, load_text.getBounding_box())) {

                                evar_mouseLeft = false;
                                go_back_text.setLabel("previous menu button");
                                go_back_text.enable();
                                rendStack.add(go_back_text);
                                go_back_text.setpos(0, 875);
//

                                statevar_menu = "newgame";
                                slot_1_text.enable();
                                slot_2_text.enable();
                                slot_3_text.enable();
                                main_menu_title.disable();
                                continue_text.disable();
                                new_game_text.disable();
                                load_text.disable();
                                exit_text.disable();
                            }

                            if (MainPane.evar_mouseLeft && Physics.doObjectsCollide(MainPane.cursor, exit_text.getBounding_box())) {
                                System.out.println("Exiting by request");
                                System.exit(0);
                            }

                            if (MainPane.evar_mouseLeft && Physics.doObjectsCollide(MainPane.cursor, continue_text.getBounding_box())) {
                                cvar_gamestate = 1;
                                rendStack.flush();
                                try {
                                    makeIngame(standardStack.makeArrayList(), "quicksave.sav");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (statevar_menu.equals("newgame")) {



                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_1_text.getBounding_box())) {
                                if (slot_1_text.getSize() < 42) {
                                    slot_1_text.setSize(slot_1_text.getSize() + 1);

                                }
                            } else if (slot_1_text.getSize() > 36) {
                                slot_1_text.setSize(slot_1_text.getSize() - 1);

                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_2_text.getBounding_box())) {
                                if (slot_2_text.getSize() < 42) {
                                    slot_2_text.setSize(slot_2_text.getSize() + 1);
                                }
                            } else if (slot_2_text.getSize() > 36) {
                                slot_2_text.setSize(slot_2_text.getSize() - 1);
                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_3_text.getBounding_box())) {
                                if (slot_3_text.getSize() < 42) {
                                    slot_3_text.setSize(slot_3_text.getSize() + 1);
                                }
                            } else if (slot_3_text.getSize() > 36) {
                                slot_3_text.setSize(slot_3_text.getSize() - 1);
                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], go_back_text.getBounding_box())) {
                                if (go_back_text.getSize() < 30) {
                                    go_back_text.setSize(go_back_text.getSize() + 1);
                                }
                            } else if(go_back_text.getSize() > 24) {
                                go_back_text.setSize(go_back_text.getSize() - 1);
                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, slot_1_text.getBounding_box())) {
                                MainPane.cvar_saveslot = 1;
                                cvar_gamestate = 1;
                                try {
                                    SaveGame.save("quicksave.sav", Level.get_level_path("testlevel.lvl"), 00);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("starting a new game in save slot " + Integer.toString(cvar_saveslot));
                                System.out.print("setting up the level...");
                                try {
                                    makeIngame(MainPane.standardStack.makeArrayList(), "quicksave.sav"); //makes the stack contain stuff that is needed "ingame"
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    e.printStackTrace();
                                }


                                // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                                //keep menu items and stuff after we're done with them.
                                evar_loading = false; //keeps this from happening every time we call the frame
                                //enemysprite.instantiate(frame, globalGamestate);
                                //bulletSprite.instantiate(ship);
                                System.out.println("done");

                            }
                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, slot_2_text.getBounding_box())) {
                                MainPane.cvar_saveslot = 2;
                                cvar_gamestate = 1;
                                try {
                                    SaveGame.save("quicksave.sav", Level.get_level_path("testlevel.lvl"), 00);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("starting a new game in save slot " + Integer.toString(cvar_saveslot));
                                System.out.print("setting up the level...");
                                try {
                                    makeIngame(MainPane.standardStack.makeArrayList(), "quicksave.sav"); //makes the stack contain stuff that is needed "ingame"
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    e.printStackTrace();
                                }


                                // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                                //keep menu items and stuff after we're done with them.
                                evar_loading = false; //keeps this from happening every time we call the frame
                                //enemysprite.instantiate(frame, globalGamestate);
                                //bulletSprite.instantiate(ship);
                                System.out.println("done");

                            }
                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, slot_3_text.getBounding_box())) {
                                MainPane.cvar_saveslot = 3;
                                cvar_gamestate = 1;
                                try {
                                    SaveGame.save("quicksave.sav", Level.get_level_path("testlevel.lvl"), 00);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("starting a new game in save slot " + Integer.toString(cvar_saveslot));
                                System.out.print("setting up the level...");
                                try {
                                    makeIngame(MainPane.standardStack.makeArrayList(), "quicksave.sav"); //makes the stack contain stuff that is needed "ingame"
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    e.printStackTrace();
                                }


                                // i.e. the ship, enemies, etc.  also clears the stack of the previous resources, so we don't
                                //keep menu items and stuff after we're done with them.
                                evar_loading = false; //keeps this from happening every time we call the frame
                                //enemysprite.instantiate(frame, globalGamestate);
                                //bulletSprite.instantiate(ship);
                                System.out.println("done");

                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, go_back_text.getBounding_box())) {
                                cvar_gamestate = -2;
                                evar_mouseLeft = false;
                                rendStack.flush();
                                go_back_text.setSize(24);
                                statevar_menu = "main";
                                slot_1_text.disable();
                                slot_2_text.disable();
                                slot_3_text.disable();
                                main_menu_title.enable();
                                continue_text.enable();
                                new_game_text.enable();
                                load_text.enable();
                                exit_text.enable();

                            }
                        }

                        if (statevar_menu.equals("load")){
                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_1_text.getBounding_box())) {
                                if (slot_1_text.getSize() < 42) {
                                    slot_1_text.setSize(slot_1_text.getSize() + 1);

                                }
                            } else if (slot_1_text.getSize() > 36) {
                                slot_1_text.setSize(slot_1_text.getSize() - 1);

                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_2_text.getBounding_box())) {
                                if (slot_2_text.getSize() < 42) {
                                    slot_2_text.setSize(slot_2_text.getSize() + 1);
                                }
                            } else if (slot_2_text.getSize() > 36) {
                                slot_2_text.setSize(slot_2_text.getSize() - 1);
                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], slot_3_text.getBounding_box())) {
                                if (slot_3_text.getSize() < 42) {
                                    slot_3_text.setSize(slot_3_text.getSize() + 1);
                                }
                            } else if (slot_3_text.getSize() > 36) {
                                slot_3_text.setSize(slot_3_text.getSize() - 1);
                            }

                            if (Physics.pointInObj(evar_mousepos[0], evar_mousepos[1], go_back_text.getBounding_box())) {
                                if (go_back_text.getSize() < 30) {
                                    go_back_text.setSize(go_back_text.getSize() + 1);
                                }
                            } else if(go_back_text.getSize() > 24) {
                                go_back_text.setSize(go_back_text.getSize() - 1);
                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, slot_1_text.getBounding_box())) {
                                cvar_gamestate = 1;
                                rendStack.flush();
                                System.out.println("loading game from save slot " + Integer.toString(cvar_saveslot));
                                System.out.print("setting up the level...");
                                try {
                                    makeIngame(standardStack.makeArrayList(), "slot_1.sav");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    e.printStackTrace();
                                }

                            }

                            if (evar_mouseLeft && Physics.doObjectsCollide(cursor, go_back_text.getBounding_box())) {
                                cvar_gamestate = -2;
                                evar_mouseLeft = false;
                                go_back_text.enable();
                                rendStack.add(go_back_text);
                                statevar_menu = "main";
                                slot_1_text.disable();
                                slot_2_text.disable();
                                slot_3_text.disable();
                                main_menu_title.enable();
                                continue_text.enable();
                                new_game_text.enable();
                                load_text.enable();
                                exit_text.enable();

                            }


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
                        } catch (LineUnavailableException e) {
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
                            AsteroidExplodeParticle.flush();
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

                            //update text
                            update_all_text(rendStack);

                            if (statevar_spawnEnemies) {
/*steps the level*/
                                try {
                                    if (!currentLevel.step(global_g, dialoguebox_stack)) {
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
            delay = 15 - (int) (System.currentTimeMillis() - gloopstart);

            if (delay < 0){
                delay = 16;
            }
            try {
                gameThread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game_loop_count = game_loop_count + 1;








        }
    }



}

