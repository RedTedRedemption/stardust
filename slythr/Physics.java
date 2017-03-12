package slythr;

import java.util.ArrayList;
import java.util.List;

public class Physics {

	public Physics() {

	}

	// public boolean objectsCollide(slythr.Primitive obj1, slythr.Primitive obj2){
	// System.out.println("checking collison");
	// boolean collides;
	//
	// for (int vertrays = 0; vertrays <= obj1.height; vertrays = vertrays + 1){
	// collides = castRay(obj1.origin_x, (obj1.origin_y + vertrays), 1, obj2);
	// //PROBLEM IS PROBABLY HERE!!!!
	// System.out.println(obj1 + " and " + obj2 + "'s collision state is " +
	// collides);
	// if (collides){
	// return true;
	// }
	// }
	// return false;
	// }

	public boolean castRay(int startx, int starty, int endx, Primitive target) {
		System.out.println("casting ray");
		int targtop = getPointsRect(target)[1];
		int targbot = getPointsRect(target)[5];
		int targleft = getPointsRect(target)[0];
		int targright = getPointsRect(target)[3];
		if (starty <= targbot && starty >= targtop) {
			for (int i = startx; i <= endx; i = i + 1) {
				System.out.println("casting ray at point offset " + i);
				if (i >= targleft && i <= targright) {
					System.out.println("ray collides!");
					return true;
				}
			}
		}
		System.out.println("ray does not collide");
		return false;
	}

	public int[] getPointsRect(Primitive obj) {
		// [0, 1] [2, 3]
		// A----------B
		// | |
		// | | <== REMEMBER, ITS INVERTED!
		// | |
		// C----------D
		// [4,5] [6, 7]

		int ax = obj.getpos()[0];
		// System.out.println(ax);
		int ay = obj.getpos()[1];
		// System.out.println(ay);

		int bx = ax + obj.getWidth();
		// System.out.println(bx);
		int by = ay;
		// System.out.println(by);

		int cx = ax;
		// System.out.println(cx);
		int cy = ay + obj.getHeight();
		// System.out.println(cy);

		int dx = ax + obj.getWidth();
		int dy = ay + obj.getHeight();

		int[] tout = { ax, ay, bx, by, cx, cy, dx, dy };
		// System.out.println("retrived rect points: " + ax + " " + ay + " " +
		// bx);
		// System.out.println(tout);
		return tout;
	}

	public boolean pointInObj(int x, int y, Primitive obj) {
		// System.out.println("testing for point within object...");
		int[] point_arr = getPointsRect(obj);
		// System.out.println("Sample of point array: " + point_arr[0] + " " +
		// point_arr[1]);
        // System.out.println("point is in object");
// System.out.println("point is not in object");
        return x > point_arr[0] && x < point_arr[2] && y > point_arr[1] && y < point_arr[5];
	}

	public boolean doObjectsCollide(Primitive obj1, Primitive obj2) {
		// System.out.println("Starting collision test");
		int[] point_arr1 = getPointsRect(obj1);
		int[] point_arr2 = getPointsRect(obj2);
		if (pointInObj(point_arr1[0], point_arr1[1], obj2) || pointInObj(point_arr1[0], point_arr1[5], obj2)
				|| pointInObj(point_arr1[2], point_arr1[1], obj2) || pointInObj(point_arr1[2], point_arr1[5], obj2)) {
			// System.out.println("collision test returning true");
			return true;
		} else {
            // System.out.print("collision test returning true");
            return pointInObj(point_arr2[0], point_arr2[1], obj1) || pointInObj(point_arr2[0], point_arr2[5], obj1)
                    || pointInObj(point_arr2[2], point_arr2[1], obj1)
                    || pointInObj(point_arr2[2], point_arr2[5], obj1);
		}

	}

}