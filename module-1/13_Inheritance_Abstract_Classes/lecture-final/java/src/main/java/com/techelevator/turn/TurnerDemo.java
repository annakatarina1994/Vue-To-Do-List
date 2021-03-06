package com.techelevator.turn;

public class TurnerDemo {

	public static void main(String[] args) {

		Turner obj = new Leaf();  // programming to the interface
		obj.turn();
		
		obj = new Page();
		obj.turn();
		
		
		System.out.println("************  setting up array of Turner interface objects *************");
		Turner[] objects = new Turner[5];
		
		for (int i = 0; i < objects.length; i++) {
			int random = (int)(Math.random() * 3);  // will return 0, 1, or 2
//			if (random == 0) {
//				objects[i] = new Leaf();
//			}
//			else if (random == 1) {
//				objects[i] = new Page();
//			}
//			else {
//				objects[i] = new Pancake();
//			}
			switch (random) {
			case 0:
				objects[i] = new Leaf();
				break;
			case 1:
				objects[i] = new Page();
				break;
			default:
				objects[i] = new Pancake();
			}
		}
		
		for (Turner o: objects) {
			if (o instanceof Leaf) { // instanceof operator returns true if o is an instance of the class Leaf
				System.out.print("Leaf: ");
			}
			else if (o instanceof Page) {
				System.out.print("Page: ");
			}
			else {
				System.out.print("Pancake: ");
			}
			o.turn();
		}

	}

}
