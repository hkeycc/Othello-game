package Assignment2;
import java.util.Scanner;
public class Board {
	Scanner in = new Scanner(System.in);
	private Position[] boardPieces;
	private String save_file;
	
	
	
	
	
	public Board(String save_file) {
		this.save_file=save_file;
		this.boardPieces=new Position[64];
		
	}
	//5 initial positions
	public void initialize(int a) {
		for(int i=0;i<64;i++) {
			boardPieces[i] = new Position();
		 boardPieces[i].setPiece(Position.EMPTY);
		}
		switch(a){
			case 0:
				break;
			case 1: {
					System.out.println("\n1. The standard Othello starting position.");
					boardPieces[27].setPiece(Position.WHITE);
					boardPieces[28].setPiece(Position.BLACK);
					boardPieces[35].setPiece(Position.BLACK);
					boardPieces[36].setPiece(Position.WHITE);
						
			}
			break;
		    case 2:{System.out.println("\n2. A non-standard, offset starting position.");
		    		boardPieces[18].setPiece(Position.WHITE);
					boardPieces[19].setPiece(Position.BLACK);
					boardPieces[26].setPiece(Position.BLACK);
					boardPieces[27].setPiece(Position.WHITE);
		    	
		    }
		    break;
		    case 3:{System.out.println("\n3. A non-standard, offset starting position.");
		    		boardPieces[20].setPiece(Position.WHITE);
					boardPieces[21].setPiece(Position.BLACK);
					boardPieces[28].setPiece(Position.BLACK);
					boardPieces[29].setPiece(Position.WHITE);
		    	
		    }
		    break;
		    case 4:{System.out.println("\n4. A non-standard, offset starting position.");
		    		boardPieces[34].setPiece(Position.WHITE);
					boardPieces[35].setPiece(Position.BLACK);
					boardPieces[42].setPiece(Position.BLACK);
					boardPieces[43].setPiece(Position.WHITE);
		    	
		    }
		    break;
		    case 5:{System.out.println("\n5. A non-standard, offset starting position.");
		    	boardPieces[36].setPiece(Position.WHITE);
				boardPieces[37].setPiece(Position.BLACK);
				boardPieces[44].setPiece(Position.BLACK);
				boardPieces[45].setPiece(Position.WHITE);
		    }
		    break;
		}
	}
	
	
	//Draws 1D array in 2D board
	public void drawboard() {
		System.out.print("  "); 
	    for (int i = 0; i < 8; i++) {
	        System.out.print(i + " "); 
	    }
	    System.out.println();
	    
	    for (int j = 0; j < 8; j++) {
	        System.out.print(j + " "); 
	        int index = j * 8;
	        for (int i = index; i < index + 8; i++) {
	            System.out.print(boardPieces[i].getPiece() + " ");
	        }
	        System.out.println();
	    }
	}

	public String getBoardToString() {
		StringBuilder boardToStr = new StringBuilder();
		
		
		boardToStr.append("  ");
		for (int i = 0; i < 8; i++) {
			boardToStr.append(i).append(" "); 
		}
		boardToStr.append("\n");
		
		// Add each row of the board
		for (int j = 0; j < 8; j++) {
			boardToStr.append(j).append(" ");  
			int index = j * 8;
			for (int i = index; i < index + 8; i++) {
				boardToStr.append(boardPieces[i].getPiece()).append(" ");
			}
			boardToStr.append("\n");  
		}
		
		return boardToStr.toString();
	}
	
	
	private void checkPlayablePositions(String color) {
		
		// Every direction possible on the board
		int[] directions = {-1, 1, -8, 8, -9, -7, 7, 9};
		
		// Sets opponentColor and playerColor
		char opponentColor = color.equals("B") ? Position.WHITE : Position.BLACK;
		char playerColor = color.equals("B") ? Position.BLACK : Position.WHITE;
	
		
		for (int pos = 0; pos < 64; pos++) {

			
			if (boardPieces[pos].getPiece() != Position.EMPTY) {
				continue;
			}
	
			boolean isPlayable = false;
	
			// Check every direction
			for (int direction : directions) {
				int nextPos = pos + direction;
	
				// uses Helper method to check if the next position is within bounds
				if (isInBounds(nextPos, direction) && boardPieces[nextPos].getPiece() == opponentColor) {
					nextPos += direction;
	
					
					while (isInBounds(nextPos, direction)) {
						char currentPiece = boardPieces[nextPos].getPiece();
	
						
						if (currentPiece == Position.EMPTY) {
							break;
						}
	
						// If we find the player's own piece, valid move position
						if (currentPiece == playerColor) {
							isPlayable = true;
							break;
						}
	
						// Continue in the direction
						nextPos += direction;
					}
	
					
					if (isPlayable) {
						boardPieces[pos].setPiece(Position.PLAYABLE);
						break; 
					}
				}
			}
		}
	}
	
	// Helper method 
	private boolean isInBounds(int pos, int direction) {
		
		if (pos < 0 || pos >= 64) return false;
	
		
		if ((direction == -1 && pos % 8 == 0) || (direction == 1 && pos % 8 == 7)) {
			return false; 
		}
	
		return true;
	}
	
	
		//Checks if there are any available moves left
		private boolean canPlay() {
			for(int i =0;i<64;i++) {
				if (boardPieces[i].getPiece()==Position.PLAYABLE) {
					return true;
				}
				
			}
			 return false;
		}
		// If players can no longer play, it reports whether there's a win
		private boolean checkWin() {
			int count1 =0;
			int count2=0;
			if(!canPlay()) {
				for(int i=0;i<64;i++) {
					if(boardPieces[i].getPiece()==Position.BLACK) {
						count1+=1;
				}
				else if(boardPieces[i].getPiece()==Position.WHITE) {
					count2+=1;
				}
			}
				if (count2>count1) {
					System.out.println("Win found, white has won!");
					return true;
				}
				else if (count1>count2) {
					System.out.println("Win found, black has won!");
					return true;
				}
				else if(count1==count2) {
					System.out.println("Draw!");
					return true;
				}
				
				return false;
		}
			return false;
		}
		// Current player's turn, sets the player's piece and checks whether the game has ended/there are no more moves
		public void takeTurn(Player current, Game game) {
			String color="";
			if (current.equals(game.getFirst()))
				color="B";
			 if(current.equals(game.getSecond()))
				 color="W";
			
			checkPlayablePositions(color);
			for(int i=0;i<64;i++) {
				if(boardPieces[i].getPiece()!=Position.BLACK&&boardPieces[i].getPiece()!=Position.WHITE&&boardPieces[i].getPiece()!=Position.PLAYABLE) {
					boardPieces[i]=new UnplayablePosition(UnplayablePosition.UNPLAYABLE);
				}
			}
			
			this.drawboard();
			if(checkWin()) {
				return;
			}
			if(!canPlay()) {
				System.out.println("No playable positions available, your turn will be skipped");
				return;
			}
			System.out.println("Write 10 to end the game");
			
			System.out.print("\nEnter row (1-8): ");
			
			boolean moveDone = true;
			while(moveDone) {
			String userMove1=in.next();
			if(Integer.parseInt(userMove1)==10)
			{System.out.println("Thank you for playing Othello!");
				System.exit(0);}
			
			System.out.print("\nEnter column (0-7): ");
			String userMove2 = in.next();
			
			int currentMove = Integer.parseInt(userMove1)*8+Integer.parseInt(userMove2);
			
			
			
			
			
			
			if(boardPieces[currentMove].canPlay()&&currentMove<64&&currentMove>0&&boardPieces[currentMove].getPiece()!=Position.BLACK&&boardPieces[currentMove].getPiece()!=Position.WHITE) {
				
				
				
				
				if(color.equals("B")) {
					
					
				boardPieces[currentMove].setPiece(Position.BLACK);
				flip(color,currentMove);
				}
				else if(color.equals("W")) {
					
					boardPieces[currentMove].setPiece(Position.WHITE);
					flip(color,currentMove);}
				if(checkWin()) {
					System.out.println("Game has ended");
					return;
				}
				for(int i=0;i<64;i++) {
					if(boardPieces[i].getPiece()==UnplayablePosition.UNPLAYABLE||boardPieces[i].getPiece()==Position.PLAYABLE) {
						boardPieces[i]=new Position();
						boardPieces[i].setPiece(Position.EMPTY);
					}
				}
				moveDone=false;
			}
			else
				System.out.print("Invalid move, enter your move again: ");
				continue;
		}
		
		
	}
		
		private void flip(String color, int index) {
			int[] directions = {-1, 1, -8, 8, -9, -7, 7, 9}; 
			char playerColor = color.equals("B") ? Position.BLACK : Position.WHITE;
			char opponentColor = color.equals("B") ? Position.WHITE : Position.BLACK;
		
			
			for (int direction : directions) {
				int pos = index + direction;
				boolean flip = false;
		
				
				while (isInBounds(pos, direction) && boardPieces[pos].getPiece() == opponentColor) {
					pos += direction;
				}
		
				
				if (isInBounds(pos, direction) && boardPieces[pos].getPiece() == playerColor) {
					flip = true;
				}
		
				
				if (flip) {
					int flipPosition = index + direction;
					while (flipPosition != pos) {
						boardPieces[flipPosition].setPiece(playerColor);
						flipPosition += direction;
					}
				}
			}
		}
		
	
		
	
}
