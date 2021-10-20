package org.bingo.main;

import java.util.*;

public class Player {

	private int playerId;
	private int ticketId;
	private static String winningCombination;
	private static HashMap<Integer, Integer> playerToTicket = new HashMap<Integer, Integer>();
	private static HashMap<Integer, String> winningComToPlayer = new HashMap<Integer, String>();

	/* Constructor to create player and assign tickets to each player as well as initial winning combination*/
	public Player(int playerId, int ticketId, String winningCombination){
		this.playerId = playerId;
		this.ticketId = ticketId;
		this.winningCombination = winningCombination;
		this.playerToTicket.put(playerId, ticketId);
		this.winningComToPlayer.put(playerId ,winningCombination);
	}

	/* Generate Getter and setter */
	public static int getTicketIdByPlayerId(int playerId){
		return playerToTicket.get(playerId);
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public static String getWinningCombination(Integer playerId) {
		return winningComToPlayer.get(playerId);
	}

	public void setWinningCombination(String winningCombination) {
		this.winningCombination = winningCombination;
	}

	public static void setWinningCombinationById(Integer playerId, String winningCombination) {
		winningComToPlayer.put(playerId, winningCombination);
	}

	/* Function to check full housie combination */
	public static boolean getFullHousie(Integer[][] board, ArrayList<Integer> calledNumberList){
		ArrayList<Integer> numbersInBoard = new ArrayList<Integer>();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] != 0) {
					numbersInBoard.add(board[i][j]);
				}
			}
		}
		if(calledNumberList.containsAll(numbersInBoard)){
			return true;
		} else {
			return false;
		}
	}

	/* Function to check top line combination */
	public static boolean getTopLine(Integer[][] board, ArrayList<Integer> calledNumberList){
		ArrayList<Integer> numbersInBoard = new ArrayList<Integer>();
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] != 0) {
					numbersInBoard.add(board[i][j]);
				}
			}
		}
		if(calledNumberList.containsAll(numbersInBoard)){
			return true;
		} else {
			return false;
		}
	}

	/* Function to get First N Combination */
	public static boolean getFirsN(Integer[][] board, ArrayList<Integer> calledNumberList, int firstNNum){
		ArrayList<Integer> numbersInBoard = new ArrayList<Integer>();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] != 0) {
					numbersInBoard.add(board[i][j]);
				}
			}
		}
		if(numbersInBoard.size() >= firstNNum && calledNumberList.containsAll(numbersInBoard)){
			return true;
		} else {
			return false;
		}
	}
}
