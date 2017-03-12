package com.jcadungog.bastionbreach.singleplayer;

public class WaitForComPlayerDelegate implements Runnable{
	private IComPlayer _comPlayer;
	
	@Override
	public void run() {
		_comPlayer.playCard();
	}
	
	public WaitForComPlayerDelegate(IComPlayer icp){
		_comPlayer = icp;
	}

}
