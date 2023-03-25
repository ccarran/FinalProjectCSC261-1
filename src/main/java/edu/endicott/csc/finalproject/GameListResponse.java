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
public class GameListResponse {
    private class AppList {
        private class Game {
            public int appid;
            public String name;
        }
        
        public ArrayList<Game> apps;
    }
    
    public AppList applist;
}
