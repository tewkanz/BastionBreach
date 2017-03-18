package com.jcadungog.bastionbreach.core;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
/**
 * SUMMARY: Contains the internal model of the game.
 *          Eventually we might support multiplayer, so this would be responsible for communicating with the server
 * Created by Joshua Cadungog on 2/17/2017.
 */

public class BastionBreachDataModel {
    private int _player1Score;
    private int _player2Score;
    private ArrayList<BastionBreachCard>_player1Hand;
    private ArrayList<BastionBreachCard> _player2Hand;
    private ArrayList<BastionBreachCard> _centerCards;
    private ArrayList<BastionBreachCard> _pastPlayer1Cards;
    private ArrayList<BastionBreachCard> _pastPlayer2Cards;
    private int _player1selectedCardIndex;
    private int _player2selectedCardIndex;
    private int _round;
    private int _roundPointValue;
    BastionBreachDataModel(){
        this.initialize();
    }
	public void initialize(){
		_player1Score = 0;
        _player2Score = 0;
        _round = 1;
        _roundPointValue = 1;
        _player1selectedCardIndex = 0;
        _player2selectedCardIndex = 0;
        _player1Hand = new ArrayList<BastionBreachCard>((int) PlayingCardNumberEnum.PLAYING_CARD_KING.cardOrdinal());
        _player2Hand= new ArrayList<BastionBreachCard>((int) PlayingCardNumberEnum.PLAYING_CARD_KING.cardOrdinal());
        _centerCards = new ArrayList<BastionBreachCard>((int) PlayingCardNumberEnum.PLAYING_CARD_KING.cardOrdinal());
        _pastPlayer1Cards = new ArrayList<BastionBreachCard>((int) PlayingCardNumberEnum.PLAYING_CARD_KING.cardOrdinal());
        _pastPlayer2Cards = new ArrayList<BastionBreachCard>((int) PlayingCardNumberEnum.PLAYING_CARD_KING.cardOrdinal());
        int counter = 1;
        for(PlayingCardNumberEnum pc: PlayingCardNumberEnum.values()){
            _player1Hand.add(new BastionBreachCard(pc, PlayerEnum.PLAYER_1));
            _player2Hand.add(new BastionBreachCard(pc, PlayerEnum.PLAYER_2));
            _centerCards.add(ThreadLocalRandom.current().nextInt(1,counter+1),new BastionBreachCard(pc, PlayerEnum.PLAYER_CENTER));
        }
	}
    private int AdvanceRound() throws Exception{
        int roundResult;
        BastionBreachCard player1Card;
        BastionBreachCard player2Card;
        try {
            player1Card = _player1Hand.get(_player1selectedCardIndex);
        }
        catch(IndexOutOfBoundsException e){
            throw new Exception("Advance Round: Player 1's card not valid at index "+_player1selectedCardIndex);
        }
        try {
            player2Card = _player2Hand.get(_player2selectedCardIndex);
        }
        catch(IndexOutOfBoundsException e){
            throw new Exception("AdvanceRound: Player 2's card not valid at index "+_player2selectedCardIndex);
        }
        roundResult = player1Card.cardCompare(player2Card);
        // if the two cards are equal, we don't care what the center card was
        switch(roundResult){
            case 0:
                tieResultRound();
                break;
            case 1:
                if (player1Card.cardCompare(_centerCards.get(_round)) != 1) {
                    tieResultRound();
                    roundResult=0;
                }
                else{
                    player1WinsRound();
                }
                break;
            case -1:
                if(player2Card.cardCompare(_centerCards.get(_round)) != 1){
                    tieResultRound();
                    roundResult=0;
                }
                else{
                    player2WinsRound();
                }
                break;
        }
        _round++;
        _pastPlayer1Cards.add(player1Card);
        _pastPlayer2Cards.add(player2Card);
        return roundResult;
    }
    private void tieResultRound(){
        _roundPointValue++;
    }
    private void player1WinsRound(){
        _player1Score+=_roundPointValue;
        _roundPointValue=1;
    }
    private void player2WinsRound(){
        _player2Score+=_roundPointValue;
        _roundPointValue=1;
    }
    public ArrayList<BastionBreachCard>GetCenterCards(){
    	ArrayList<BastionBreachCard> result = new ArrayList<BastionBreachCard>(13);
    	for(BastionBreachCard bbc: _centerCards){
    		result.add(bbc);
    	}
    	return result;
    }
    public ArrayList<BastionBreachCard>GetHand(PlayerEnum p) throws Exception{
        ArrayList<BastionBreachCard> playerHand;
        switch(p){
            case PLAYER_1:
                playerHand = _player1Hand;
                break;
            case PLAYER_2:
                playerHand = _player2Hand;
                break;
            default:
                throw new Exception("GetHand: Player must be 1 or 2");
        }
        ArrayList<BastionBreachCard> result = new ArrayList<BastionBreachCard>(playerHand.size());
        for(BastionBreachCard bbc: playerHand){
            result.add(bbc);
        }
        return result;
    }
    public ArrayList<BastionBreachCard>GetPastPlayerCards(PlayerEnum p) throws Exception{
    	ArrayList<BastionBreachCard> pastArrayList;
    	switch(p){
    	case PLAYER_1:
    		pastArrayList = _pastPlayer1Cards;
    		break;
    	case PLAYER_2:
    		pastArrayList = _pastPlayer2Cards;
    		break;
    	default:
    		throw new Exception("GetPastPlayerCards: player must be 1 or 2");
    	}
    	ArrayList<BastionBreachCard> result = new ArrayList<BastionBreachCard>(_round -1 );
    	for(BastionBreachCard bbc : pastArrayList){
    		result.add(bbc);
    	}
    	return result;
    }
    public int GetPlayerScore(PlayerEnum p) throws Exception{
    	switch(p){
    	case PLAYER_1:
    		return _player1Score;
    	case PLAYER_2:
    		return _player2Score;
    	default:
    		throw new Exception("GetPlayerScore: player must be 1 or 2");
    	}
    }
    public void SelectCard(PlayerEnum p, BastionBreachCard bbc) throws Exception{
    	ArrayList<BastionBreachCard> playersHand;
    	int selectedIndex;
    	switch(p){
    	case PLAYER_1:
    		playersHand = _player1Hand;
    		break;
    	case PLAYER_2:
    		playersHand = _player2Hand;
    		break;
    	default:
    		throw new Exception("SelectCard: player must be 1 or 2");
    	}
    	selectedIndex = playersHand.indexOf(bbc);
    	if (!(selectedIndex > 0)){
    		throw new Exception("GetPlayerScore: Card not found: " + bbc.toString());
    	}
    	if (p == PlayerEnum.PLAYER_1){
    		_player1selectedCardIndex = selectedIndex;
    	}
    	else if (p == PlayerEnum.PLAYER_2){
    		_player2selectedCardIndex = selectedIndex;
    	}
    	if ((_player1selectedCardIndex>0)&&(_player2selectedCardIndex > 0)){
    	    AdvanceRound();
        }
    }
    public BastionBreachCard GetSelectedCard(PlayerEnum p) throws Exception{
    	switch(p){
    	case PLAYER_1:
    		if (_player1selectedCardIndex > 0){
    			return _player1Hand.get(_player1selectedCardIndex);
    		}
    		else{
    			return new BastionBreachCard(PlayingCardNumberEnum.PLAYING_CARD_UNKNOWN, PlayerEnum.PLAYER_1);
    		}
    	case PLAYER_2:
    		if (_player2selectedCardIndex > 0){
    			return _player2Hand.get(_player2selectedCardIndex);
    		}
    		else{
    			return new BastionBreachCard(PlayingCardNumberEnum.PLAYING_CARD_UNKNOWN, PlayerEnum.PLAYER_2);
    		}
    	default:
    		throw new Exception("GetSelectedCard: player must be 1 or 2");
    	}
    }
}
