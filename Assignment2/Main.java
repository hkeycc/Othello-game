package Assignment2;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException; 
public class Main {

	public static void main(String[] args) {
		Player p1=new Player("KC");
		Player p2 = new Player("Susie");
		Game myGame= new Game(p1,p2);
		myGame.start();
		myGame.play();
	}
	
}
