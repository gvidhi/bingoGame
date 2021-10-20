package org.bingo.main;

import java.util.*;

public class Housie {

	public enum winningCombination {
		NONE, EARLY, FULL, TOPLINE
	}

	/* Track which winning combination is found while playing and add it into hashmap, 
	next time if we encounter same winning than discard it. */
	private static HashMap<Integer, String> playerToWinningCombination = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("**** Lets Play Housie *****");
		System.out.println("Note: - Press 'Q' to quit any time.");

		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number range(1-n) : ");
		String numRange = in.next();

		System.out.println("Enter Number of players playing in the game : " );
		int numOfPlayers = in.nextInt();

		System.out.println("Enter Ticket Size: Default to 3x10(3 rows and 10 columns)");
		String ticketSize = in.nextLine();
		while(ticketSize.isEmpty()) {
			System.out.println(ticketSize!=null);

			if (ticketSize.isEmpty()) {
				System.out.println("3X10");
			}

			if (in.hasNextLine()) {
				ticketSize = in.nextLine();
			} else {
				ticketSize = null;
			}
		}

		System.out.println("Enter number per row : " );
		int numPerRow = in.nextInt();

		System.out.println("Select First N winning number : " );
		int firstNNum = in.nextInt();

		/* create a ticket for given number of players */
		for(int i = 1; i <= numOfPlayers; i++) {
			//create ticket
			int row = Integer.parseInt(ticketSize.split("X")[0]);
			int col = Integer.parseInt(ticketSize.split("X")[1]);
			
			Integer[][] board = new Integer[row][col];
			Ticket ticket = new Ticket(i, board);
			
			//fill up numbers in ticket
			ticket.generateTicketNum(Integer.valueOf(numRange), numPerRow, board);
			
			//now we have ticket with random unique numbers
			//create a player and assign current ticket to her/him
			Player player = new Player(i, ticket.getTicketId(), winningCombination.NONE.toString());
		}

		System.out.println(" **** Tickets created successfully **** ");

		ArrayList<Integer> calledNumberList = new ArrayList<Integer>();
		boolean fullHousie = false;

		int callNumber = -1;
		
		/* iterate until we  get full housie combination or Q. */
		while(fullHousie != true){
			System.out.println(" **** Press 'N' to generate next Number **** ");
			String input = new Scanner(System.in).next();
			switch(input){
			case "N":
				callNumber = Ticket.generateRandomNumber(Integer.valueOf(numRange));
				System.out.println("Next number is : "+callNumber);
			case "Q":
				break;
			}
			if(calledNumberList.contains(callNumber)){
				continue;
			} else {
				calledNumberList.add(callNumber);
				for(int i = 1; i <= numOfPlayers; i++) {
					if(Player.getWinningCombination(i) != winningCombination.FULL.toString()){
						fullHousie = checkResult(calledNumberList, i, false, firstNNum);
						if(fullHousie){
							break;
						}else {
							continue;
						}
					}else if(Player.getWinningCombination(i) == winningCombination.FULL.toString()){
						break;
					}else {
						continue;
					}
				}
			}
		}

		System.out.println("**** Game Over ****");
	}

	/* check result for each player */
	public static boolean checkResult(ArrayList<Integer> calledNumberList, int playerNum, boolean fullHousie, int firstNNum) {
		if(Player.getFirsN(Ticket.getBoardByTicketId(Player.getTicketIdByPlayerId(playerNum)),calledNumberList, firstNNum)){
			Player.setWinningCombinationById(playerNum, winningCombination.EARLY.toString());
			if(!playerToWinningCombination.values().contains(winningCombination.EARLY.toString())){
				playerToWinningCombination.put(playerNum, winningCombination.EARLY.toString());
				System.out.println("We have a winner: Player#"+playerNum+" has won 'First Five' winning combination");
			}	
			return false;
		}
		else if(Player.getTopLine(Ticket.getBoardByTicketId(Player.getTicketIdByPlayerId(playerNum)),calledNumberList)){
			Player.setWinningCombinationById(playerNum, winningCombination.TOPLINE.toString());
			if(!playerToWinningCombination.values().contains(winningCombination.TOPLINE.toString())){
				playerToWinningCombination.put(playerNum, winningCombination.TOPLINE.toString());
				System.out.println("We have a winner: Player#"+playerNum+" has won 'Top Line' winning combination");
			}
			return false;
		}
		else if(Player.getFullHousie(Ticket.getBoardByTicketId(Player.getTicketIdByPlayerId(playerNum)),calledNumberList)){
			Player.setWinningCombinationById(playerNum, winningCombination.FULL.toString());
			if(!playerToWinningCombination.values().contains(winningCombination.FULL.toString())){
				playerToWinningCombination.put(playerNum, winningCombination.FULL.toString());
				System.out.println("We have a winner: Player#"+playerNum+" has won 'Full Housie' winning combination");
			}
			return true;
		}else {
			return false;
		}
	}
}
