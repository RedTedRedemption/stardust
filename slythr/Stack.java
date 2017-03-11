package slythr;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Stack {

	List<Primitive> stack = new ArrayList<Primitive>();

	public Stack() {
		// java.util.List = new java.util.ArrayList<Object>()

		// System.out.println("stack has been created!");
	}

	public void add(Primitive obj) {
		stack.add(obj);

	}

	public void printMe() {
		for (Primitive tout : stack) {
			System.out.println(tout);
		}

	}

	public int len() {
		return stack.size();
	}

	public Object get(int index) {
		return stack.get(index);
	}

	public void draw(Graphics g) {
		for (Primitive obj : stack) {
			obj.draw(g);

		}
	}

	public void remove(Primitive obj) {
		stack.remove(stack.indexOf(obj));
	}

	public Stack makeStack(List<Primitive> list) {

		Stack newStack = new Stack();
		for (Primitive obj : list) {
			newStack.add(obj);
		}
		return newStack;

	}

	public void flush(){
//		for (Primitive stackitem : stack){
//			stack.remove(stack.indexOf(stackitem));
//		}
		stack.clear();
	}

	public void add(Stack addme){
		for (Primitive obj : addme.makeArrayList()){
			stack.add(obj);
		}
	}

	public ArrayList<Primitive> makeArrayList(){
		ArrayList<Primitive> tout = new ArrayList<>();
		for (Primitive obj : stack){
			tout.add(obj);
		}
		return tout;
	}

}
