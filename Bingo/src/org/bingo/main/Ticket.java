package org.bingo.main;

import java.util.HashSet;
import java.util.*;

public class Ticket {

	private int ticketId;
	private Integer[][] board;
	private static HashMap<Integer, Integer[][]> ticketIdToBoard = new HashMap<>();

	/* Constructor to create a ticket and associate unique ticket id to it's ticket board*/
	public Ticket(int ticketId, Integer[][] board){
		this.setTicketId(ticketId);
		this.setBoard(board);
		this.ticketIdToBoard.put(ticketId, board);
	}

	/* Generate getter and setters */
	public static Integer[][] getBoardByTicketId(int ticketId){
		return ticketIdToBoard.get(ticketId);
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Integer[][] getBoard() {
		return board;
	}

	public void setBoard(Integer[][] board) {
		this.board = board;
	}

	/* Generate random number in a ticket */
	public Integer[][] generateTicketNum(int numRange, int numPerRow, Integer[][] ticketSize) {
		HashSet<Integer> uniqueTicketNumbers = new HashSet<Integer>();
		//need number of rows, max number in row, number range
		//for each row generate random unique number
		for(int i = 0; i < ticketSize.length; i++){
			//assign initializer 0 at the beginning of each row to track max number per row
			int k = 0;
			HashSet<Integer> uniqueColumn = new HashSet<Integer>();
			for(int j = 0; j < ticketSize[0].length; j++){
				//if we did not reach max num per row limit, continue else break and move to next row.
				if(k < numPerRow){

					int randomCol = generateRandomNumber(ticketSize[0].length);
					//check if hashset contains that means duplicate, look for another else add column number into hashset
					while(uniqueColumn.contains(randomCol)){
						randomCol = generateRandomNumber(ticketSize[0].length);
					}
					uniqueColumn.add(randomCol);

					//check if hashset contains that means duplicate, look for another else add ticket number into hashset
					int randomNumber = generateRandomNumber(numRange);
					while(uniqueTicketNumbers.contains(randomNumber)){
						randomNumber = generateRandomNumber(numRange);
					}
					uniqueTicketNumbers.add(randomNumber);
					ticketSize[i][randomCol] = randomNumber;
					k++;
				} else {
					break;
				}	
			}
		}
		/* ToDo: Remove before pushing to production: It helps while testing */
		System.out.println("How is my ticket looks like? ");
		for(int i = 0; i < ticketSize.length; i++){
			for(int j = 0; j < ticketSize[0].length; j++){
				if(ticketSize[i][j] == null){
					ticketSize[i][j] = 0;
				}
				System.out.print(ticketSize[i][j]+" ");
			}
			System.out.println();
		}
		return ticketSize;
	}

	/* Generate Random Number Function*/
	public static int generateRandomNumber(int numRange){
		Random random = new Random();
		int randomNum = random.nextInt(numRange);
		return randomNum;
	}

}
