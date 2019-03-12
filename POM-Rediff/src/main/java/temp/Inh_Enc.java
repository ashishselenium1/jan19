package temp;

public class Inh_Enc {

	public static void main(String[] args) {
		
		Brain brain = new Brain();
		brain.weight=100;
		brain.size=10;
		
		Animal a = new Animal();		
		a.name="Dog";
		a.eat();
		a.sleep();
		a.move();
		a.b=brain;
		
		Cat c = new Cat();
		c.name="cat";
		c.eat();
		c.sleep();
		c.move();
		c.hunt();
		
		// Object class
		int i=10;
		String s = "Hello";
		
		
		Object o1=100;
		Object o2="Hello";
		Object o3 = c ;
				
		

	}

}
