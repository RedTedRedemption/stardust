package stardust;

import javax.swing.*;

import slythr.*;

import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JPanel implements KeyListener {

    //frame, game, and state variables
    Graphics g;

    boolean newframe = false;
    boolean framevar_runme = true;
    int evar_framedelay;
    GlobalGamestate globalGamestate;

    //generate the default stack
    Stack rendStack = new Stack();

    //create resources
    //Primitive ship = new ClosedPoly();
    //Primitive testtext = new Text("asdf", 36);
    Primitive ship = new Rect();



    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public GameFrame(JFrame frame) {
        super();

        ship.setAttributes(frame.getWidth() / 2, frame.getHeight() - 23, 20, 20, 0, 0, 255);





        Physics phys = new Physics(globalGamestate);


        addKeyListener(this);

        //add items to the stack here:
        rendStack.add(ship);
        //rendStack.add(testtext);

        //frame delay variable
        int delay = evar_framedelay;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (framevar_runme) {

                    //////////////////////
                    //                  //
                    //   This frame's   //
                    //  code goes here  //
                    //                  //
                    //////////////////////


                }

                //changing frames:
                //if (<BOOLEAN VARIABLE INDICATING TO SWITCH FRAMES>){
                //frame.setContentPane(/*<TARGET PANE>*/);
                //frame.setVisible(true);
                //framevar_runme = false;
               // }


            repaint();
        }
    };



     new Timer(delay, taskPerformer).start();

}

    public void paintComponent(Graphics g) {

        rendStack.draw(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}
