package com.jcadungog.bastionbreach.singleplayer;

import com.jcadungog.bastionbreach.core.BastionBreachCard;
import com.jcadungog.bastionbreach.core.BastionBreachDataModel;
import com.jcadungog.bastionbreach.core.PlayerEnum;

import java.util.ArrayList;
import java.util.Random;

public class ComPlayerRandom implements IComPlayer{
	private BastionBreachDataModel _model;
	private PlayerEnum _player;
	public void playCard(){
		ArrayList<BastionBreachCard> playerHand = null;
		int index;
		BastionBreachCard chosenCard = null;
		Random random = new Random();
		// get the list of available cards
        try {
            playerHand = _model.GetHand(_player);
        }
        catch(Exception e){
            System.out.printf("ComPlayerRandom.playCard, GetHand: %s%n", e.getMessage());
            return;
        }
		// choose one at random
		if(playerHand.size() == 1){
        	// if there's only one card left, pick that card so random doesn't throw an exception
        	index = 0;
		}
		else {
			index = random.nextInt(playerHand.size());
		}
		try{
		    chosenCard = playerHand.get(index);
            // play it
            _model.SelectCard(_player, chosenCard);
        }
        catch(Exception e){
		    System.out.printf("ComPlayerRandom.playCard, SelectCard: %s %s, Index: %d, chosenCard: %s%n", e.getClass().getName(), e.getMessage(), index, chosenCard == null ? "" : chosenCard.toString(true));
		    e.printStackTrace();
		    return;
        }
	}
	public ComPlayerRandom(BastionBreachDataModel m, PlayerEnum p){
		_model = m;
		_player = p;
	}
}
