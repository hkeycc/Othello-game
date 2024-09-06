package Assignment2;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
	private Player first;
	private Player second;
	private Player current;
	private Board board;
	Scanner in = new Scanner(System.in);
	
	public Player getFirst() {
		return first;
	}

	public void setFirst(Player first) {
		this.first = first;
	}

	public Player getSecond() {
		return second;
	}

	public void setSecond(Player second) {
		this.second = second;
	}

	public Game(Player p1, Player p2) {
		this.first=p1;
		this.second=p2;
	}
	//Starts the game
	public void start() {
		System.out.println("1. Load a Game\n\n2. Start a New Game\n\n3. Quit");
		System.out.println();
		if(Integer.parseInt(in.next())==2) {
			System.out.println();
			this.Start();
			
		}
		else System.exit(0);
	}
	//Shows all the options to the player
	public void Start() {
		
		board = new Board("game");
		board.initialize(1);
		board.drawboard();
		board.initialize(2);
		board.drawboard();
		board.initialize(3);
		board.drawboard();
		board.initialize(4);
		board.drawboard();
		board.initialize(5);
		board.drawboard();
		System.out.print("Choose your starting position: ");
		
		
	}
	//takes user input for board and loops the game until there are no more moves available for both parties
	public void play() {
		
		boolean valid=false;
		while(!valid) {
		switch(Integer.parseInt(in.next())){
			case 1: board.initialize(1);
					valid=true;
			break;
			case 2: board.initialize(2);
			valid=true;
			break;
			case 3: board.initialize(3);
			valid=true;
			break;
			case 4: board.initialize(4);
			valid=true;
			break;
			case 5 : board.initialize(5);
			valid=true;
			break;
			default: System.out.print("Wrong entry, please enter a number between 1-5: ");
			
		}}
		System.out.println("\n\n\nPosition chosen: ");
		
		board.drawboard();
		
		
		while(true) {
			this.setCurrent(first);
			System.out.println("\nPlayer 1, it's your turn to play.\n\nThis is the current state of the board with \":\"\nrepresenting the playable positions this turn. ");
		board.takeTurn(current,this);
		System.out.println("Player 1, do you wish to save the game? [Y/N]");
		String save = in.next();
		if(save.equals("Y")||save.equals("y")){
			this.save();
			System.out.println("Successful operation. The game has been saved, thank you for playing!");
			System.exit(0);

		}
			this.setCurrent(second);
			System.out.println("\nPlayer 2, it's your turn to play\n\nThis is the current state of the board with \":\"\nrepresenting the playable positions this turn ");
			board.takeTurn(current, this);

			System.out.println("Player 2, do you wish to save the game? [Y/N]");
			 save = in.next();
			if(save.equals("Y")||save.equals("y")){
			this.save();
			System.out.println("Successful operation. The game has been saved, thank you for playing!");
			System.exit(0);

		}
		}
	}

	public Player getCurrent() {
		return current;
	}

	public void setCurrent(Player current) {
		this.current = current;
	}
	
	private void save(){
		String filename = "currentGame.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            
            writer.write("First player: "+first.getPlayerName()); 
            writer.newLine();
            writer.write("Second player :"+second.getPlayerName());
            writer.newLine();
            writer.write("Current player: "+current.getPlayerName());

            writer.newLine();
            
            // Write the board state on the last line
            String boardState = board.getBoardToString(); 
            writer.write(boardState);
            
        } catch (IOException e) {
            System.err.println("Error saving the game: " + e.getMessage());
        }
	}

	
}
