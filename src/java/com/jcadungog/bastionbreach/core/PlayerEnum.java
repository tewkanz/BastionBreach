package com.jcadungog.bastionbreach.core;
/**
 * Created by Joshua Cadungog on 2/17/2017.
 */

public enum PlayerEnum {
    PLAYER_1,
    PLAYER_2,
    PLAYER_CENTER;
    public static PlayerEnum OtherPlayer(PlayerEnum p){
        switch(p){
            case PLAYER_1:
                return PLAYER_2;
            case PLAYER_2:
                return PLAYER_1;
            case PLAYER_CENTER:
                return PLAYER_CENTER;
            default:
                return PLAYER_CENTER;
        }
    }
}
