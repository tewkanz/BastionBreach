package com.jcadungog.bastionbreach.console;
import java.io.BufferedReader;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

import com.jcadungog.bastionbreach.core.*;

public class BastionBreachViewController {
	BastionBreachViewController(BastionBreachDataModel model, Runnable otherPlayerDelegate){
		_model = model;
		_waitForOtherPlayerDelegate = otherPlayerDelegate;
		_player = PlayerEnum.PLAYER_1;
	}
	public void Run() throws Exception{
		boolean startNewGame;
		boolean quit = false;
		boolean gameOver = false;
		String userInput;
		PlayingCardNumberEnum cardToPlay;
		FutureTask<Boolean> waitForOtherPlayer;
		int player1Score;
		int player2Score;
		ArrayList<BastionBreachCard> playerPlayedCards;
		ArrayList<BastionBreachCard> opponentPlayedCards;
		ArrayList<BastionBreachCard> centerCards;
		ArrayList<BastionBreachCard> playersCards;
		showSplash();
		while(!quit){
			startNewGame = promptNewGame();
			if(startNewGame){
				_model.initialize();
				while(!gameOver){
					// get data, format stuff
					player1Score = _model.GetPlayerScore(PlayerEnum.PLAYER_1);
					player2Score = _model.GetPlayerScore(PlayerEnum.PLAYER_2);
					opponentPlayedCards = _model.GetPastPlayerCards(PlayerEnum.OtherPlayer(_player));
					playerPlayedCards = _model.GetPastPlayerCards(_player);
					centerCards = _model.GetCenterCards();
					playersCards = _model.GetHand(_player);
					userInput = promptForCard(player1Score, player2Score, opponentPlayedCards, centerCards, playerPlayedCards, playersCards);
					// try to translate that into a card
					cardToPlay = PlayingCardNumberEnum.GetCardForSymbol(userInput);
					if (cardToPlay == PlayingCardNumberEnum.PLAYING_CARD_UNKNOWN){
						System.out.println("Please enter a valid card.");
						continue;
					}
					// _model Try to play card
					try{
						_model.SelectCard(_player, new BastionBreachCard(cardToPlay, _player));
					}
					// if failure, communicate that back and try again
					catch(Exception e){
						System.out.printf("BastionBreachViewController.Run: %s",e.getMessage());
						continue;
					}
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
	private PlayerEnum _player;
	private Runnable _waitForOtherPlayerDelegate;
	private void showSplash(){
		System.out.println("Welcome to Bastion Breach, a draconic card game!");
	}
	private boolean promptNewGame(){
		Scanner s = new Scanner(System.in);
		String response;
		while(true){
			System.out.println("Would you like to start a new game? (Y/N)");
			response = s.nextLine();
			response = response.toUpperCase();
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
	private String promptForCard(int player1Score, int player2Score, ArrayList<BastionBreachCard> opponentPlayedCards, ArrayList<BastionBreachCard> centerCards, ArrayList<BastionBreachCard> playersPlayedCards, ArrayList<BastionBreachCard> playersCards){
		Scanner s = new Scanner(System.in);
		System.out.printf("Player 1: %d%nPlayer 2: %d%n", player1Score, player2Score);
		System.out.printf("%n%n");
		for(BastionBreachCard bbc: opponentPlayedCards){
			System.out.printf("%s ", bbc.toString());
		}
		System.out.printf("%n%n");
		for(BastionBreachCard bbc: centerCards){
			System.out.printf("%s ", bbc.toString());
		}
		System.out.printf("%n%n");
		for(BastionBreachCard bbc: playersPlayedCards){
			System.out.printf("%s ", bbc.toString());
		}
		System.out.printf("%n%nYour Hand: %n");
		for(BastionBreachCard bbc: playersCards){
			System.out.printf("%s ", bbc.toString());
		}
		System.out.printf("%n%nChoose a card to play: ");
		return s.nextLine();
	}
}
