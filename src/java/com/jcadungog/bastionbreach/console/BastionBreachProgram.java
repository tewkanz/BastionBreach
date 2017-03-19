package com.jcadungog.bastionbreach.console;

import com.jcadungog.bastionbreach.core.BastionBreachDataModel;
import com.jcadungog.bastionbreach.core.PlayerEnum;
import com.jcadungog.bastionbreach.singleplayer.ComPlayerRandom;
import com.jcadungog.bastionbreach.singleplayer.IComPlayer;
import com.jcadungog.bastionbreach.singleplayer.WaitForComPlayerDelegate;

/**
 * Summary: Entry point for the program. Instantiates the view controller and the other player delegate
 *          and kicks off the game.
 * Created by Joshua Cadungog on 3/18/2017.
 */
public class BastionBreachProgram {
    public static void main(String[] args){
        BastionBreachDataModel model = new BastionBreachDataModel();
        IComPlayer mode = new ComPlayerRandom(model, PlayerEnum.PLAYER_2);
        BastionBreachViewController view = new BastionBreachViewController(model, new WaitForComPlayerDelegate(mode));
        try {
            view.Run();
        }
        catch(Exception e){
            System.out.printf("%s%n", e.getMessage());
        }
    }
}
