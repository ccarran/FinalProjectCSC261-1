/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.finalproject;

import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class GameList {
    public class AppList {
        public class Game {
            public String appid;
            public String name;
            public int rating = 0;
            public int userNumber = 0;
        }
        
        public ArrayList<Game> apps;
    }
    
    public AppList applist;
}
