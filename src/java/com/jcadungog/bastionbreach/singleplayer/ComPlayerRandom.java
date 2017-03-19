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
            System.out.printf("ComPlayerRandom.playCard: %s", e.getMessage());
            return;
        }
		// choose one at random
		index = random.nextInt(playerHand.size());
		try{
		    chosenCard = playerHand.get(index);
            // play it
            _model.SelectCard(_player, chosenCard);
        }
        catch(Exception e){
		    System.out.printf("ComPlayerRandom.playCard: %s", e.getMessage());
		    return;
        }
	}
	public ComPlayerRandom(BastionBreachDataModel m, PlayerEnum p){
		_model = m;
		_player = p;
	}
}
