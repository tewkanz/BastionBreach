package com.jcadungog.bastionbreach.core;

import java.net.Authenticator;

/**
 * Created by Joshua Cadungog on 2/17/2017.
 */

public class BastionBreachCard implements Comparable<BastionBreachCard> {
    public BastionBreachCard(PlayingCardNumberEnum number, PlayerEnum owner){
        _cardNumberExt=number;
        _owner=owner;
        _cardNumberInt=number.cardOrdinal();
    }

    private PlayingCardNumberEnum _cardNumberExt;
    private long _cardNumberInt;
    private PlayerEnum _owner;

    public int compareTo(BastionBreachCard bbc){
        if(_cardNumberExt == bbc.getNumber()){
            return 0;
        }
        if(_cardNumberInt > bbc.getNumber().cardOrdinal()){
            return 1;
        }
        return -1;
    }

    public PlayingCardNumberEnum getNumber(){
        return _cardNumberExt;
    }
    public PlayerEnum getOwner(){
        return _owner;
    }

    public int cardCompare(BastionBreachCard bbc) {
        if (this._cardNumberExt == PlayingCardNumberEnum.PLAYING_CARD_ACE) {
            if (bbc.getNumber().isFaceCard())
                return 1;
            else
                return -1;
        }
        if (bbc._cardNumberExt == PlayingCardNumberEnum.PLAYING_CARD_ACE) {
            if (this._cardNumberExt.isFaceCard())
                return 1;
            else
                return -1;
        }
        return this.compareTo(bbc);
    }

    public boolean equals(BastionBreachCard lhs){
        if(this._cardNumberInt == lhs.getNumber().cardOrdinal()){
            return true;
        }
        return false;
    }

    public int hashCode(){
        return (int) this._cardNumberInt;
    }
}
