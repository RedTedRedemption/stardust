package slythr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Stack {

	ArrayList<Primitive> stack = new ArrayList<Primitive>();


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
	    try {
            stack.remove(stack.indexOf(obj));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
	        System.out.println("Failed to remove item from stack: java.lang.ArrayIndexOutOfBoundsException << error handled sucessfully");
        }

        //handles a rarely-ocurring error involving out of bounds exceptions when trying to kill an object -- may result from frame
        //descrepencies and update speeds resulting in multiple attempts to remove an object -- shouldnt produce static semantics error, but keep an eye out
	}

	public Stack makeStack(List<Primitive> list) {
		//call <target> = makeStack(<origin_list>) to effectively convert a list into a stack and store at target
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

	//public void add(ArrayList<Primitive> addme)

	public ArrayList<Primitive> makeArrayList(){
		ArrayList<Primitive> tout = new ArrayList<>();
		for (Primitive obj : stack){
			tout.add(obj);
		}
		return tout;
	}

	//public Stack dump(){
        //empty for now, should return the stack (or an ArrayList?) and then clear it...will need to use temporary storage
        //buffer (i.e. store stack in temp, clear stack, return temp
	//}

	public Object[] toArray(){
		return stack.toArray();
	}

}
