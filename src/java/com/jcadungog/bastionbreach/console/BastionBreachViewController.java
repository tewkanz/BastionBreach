package com.jcadungog.bastionbreach.console;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

import com.jcadungog.bastionbreach.core.BastionBreachCard;
import com.jcadungog.bastionbreach.core.BastionBreachDataModel;

public class BastionBreachViewController {
	public void Run(){
		boolean startNewGame;
		boolean quit = false;
		boolean gameOver = false;
		String cardToPlay;
		FutureTask<Boolean> waitForOtherPlayer;
		showSplash();
		while(!quit){
			startNewGame = promptNewGame();
			if(startNewGame){
				_model.initialize();
				while(!gameOver){
					// get data, format stuff
					//cardToPlay = promptForCard(player1Score, player2Score, opponentPlayedCards, centerCards, playersCards);
					// try to translate that into a card
					// _model Try to play card
					// if failure, communicate that back and try again
					waitForOtherPlayer = new FutureTask<Boolean>(_waitForOtherPlayerDelegate, true);
					try {
						waitForOtherPlayer.get(60, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//show end state
			}
			else{
				quit=true;
			}
		}
	}
	private BastionBreachDataModel _model;
	private Runnable _waitForOtherPlayerDelegate;
	private void showSplash(){
		System.out.println("Welcome to Bastion Breach, a draconic card game!");
	}
	private boolean promptNewGame(){
		Scanner sc = new Scanner(System.in);
		Console c = System.console();
		String response;
		while(true){
			System.out.println("Would you like to start a new game? (Y/N)");
			response = c.readLine().toUpperCase();
			switch (response){
			case "Y":
			case "YES":
				return true;
			case "N":
			case "NO":
			case "":
				return false;
			default:
				System.out.println("Please enter yes or no.");
			}
		}
	}
	private String promptForCard(int player1Score, int player2SCore, ArrayList<BastionBreachCard> opponentPlayedCards, ArrayList<BastionBreachCard> centerCards, ArrayList<BastionBreachCard> playersCards){
		return "";
	}
}
