package com.jcadungog.bastionbreach.core;

/**
 * Created by Joshua Cadungog on 2/17/2017.
 */

public enum PlayingCardNumberEnum {
    PLAYING_CARD_UNKNOWN(0),
    PLAYING_CARD_ACE (1),
    PLAYING_CARD_TWO (2),
    PLAYING_CARD_THREE (3),
    PLAYING_CARD_FOUR (4),
    PLAYING_CARD_FIVE (5),
    PLAYING_CARD_SIX (6),
    PLAYING_CARD_SEVEN (7),
    PLAYING_CARD_EIGHT (8),
    PLAYING_CARD_NINE (9),
    PLAYING_CARD_TEN (10),
    PLAYING_CARD_JACK (11),
    PLAYING_CARD_QUEEN (12),
    PLAYING_CARD_KING (13);

    private final long _ordinal;
    PlayingCardNumberEnum(long ordinal){
        this._ordinal = ordinal;
    }

    public long cardOrdinal(){
        return this._ordinal;
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
