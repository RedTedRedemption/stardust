package slythr;

import java.awt.*;
import javax.swing.*;

public class Render implements Runnable {

	private Thread t;
	private String threadName;
	private Graphics g;
	Stack renderStack;

	public Render(String name, Graphics G, Stack passedStack){
		threadName = name;
		renderStack = passedStack;
		g = G;
		System.out.println("making render thread");
	}

	public void run(){
		System.out.println("rendering");
		renderStack.draw(g);
	}

	public void start(Stack renderStack){
		if (t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
