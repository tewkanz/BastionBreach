package com.jcadungog.bastionbreach.core;

/**
 * Created by Joshua Cadungog on 2/17/2017.
 */

public enum PlayingCardNumberEnum {
    PLAYING_CARD_UNKNOWN(0, ""),
    PLAYING_CARD_ACE (1, "A"),
    PLAYING_CARD_TWO (2, "2"),
    PLAYING_CARD_THREE (3, "3"),
    PLAYING_CARD_FOUR (4, "4"),
    PLAYING_CARD_FIVE (5, "5"),
    PLAYING_CARD_SIX (6, "6"),
    PLAYING_CARD_SEVEN (7, "7"),
    PLAYING_CARD_EIGHT (8, "8"),
    PLAYING_CARD_NINE (9, "9"),
    PLAYING_CARD_TEN (10, "10"),
    PLAYING_CARD_JACK (11, "J"),
    PLAYING_CARD_QUEEN (12, "Q"),
    PLAYING_CARD_KING (13, "K");

    private final long _ordinal;
    private final String _symbol;
    PlayingCardNumberEnum(long ordinal, String symbol){
        this._ordinal = ordinal;
        this._symbol = symbol;
    }

    public long cardOrdinal(){
        return this._ordinal;
    }
    public String symbol(){ return this._symbol; }
    public static PlayingCardNumberEnum GetCardForSymbol(String symbol){
        switch(symbol){
            case "A":
                return PLAYING_CARD_ACE;
            case "2":
                return PLAYING_CARD_TWO;
            case "3":
                return PLAYING_CARD_THREE;
            case "4":
                return PLAYING_CARD_FOUR;
            case "5":
                return PLAYING_CARD_FIVE;
            case "6":
                return PLAYING_CARD_SIX;
            case "7":
                return PLAYING_CARD_SEVEN;
            case "8":
                return PLAYING_CARD_EIGHT;
            case "9":
                return PLAYING_CARD_NINE;
            case "10":
                return PLAYING_CARD_TEN;
            case "J":
                return PLAYING_CARD_JACK;
            case "Q":
                return PLAYING_CARD_QUEEN;
            case "K":
                return PLAYING_CARD_KING;
            default:
                return PLAYING_CARD_UNKNOWN;
        }
    }

    public boolean isFaceCard(){
        switch((int) _ordinal){
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }
}
