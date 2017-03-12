package com.jcadungog.bastionbreach.singleplayer;

import com.jcadungog.bastionbreach.core.BastionBreachDataModel;
import com.jcadungog.bastionbreach.core.PlayerEnum;

public class ComPlayerRandom implements IComPlayer{
	private BastionBreachDataModel _model;
	private PlayerEnum _player;
	public void playCard(){
		// get the list of available cards
		// choose one at random
		// play it
	}
	public ComPlayerRandom(BastionBreachDataModel m, PlayerEnum p){
		_model = m;
		_player = p;
	}
}
