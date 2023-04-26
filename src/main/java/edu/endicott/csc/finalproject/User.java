/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.finalproject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cesar
 */
public class User {
    public String password;
    public HashMap<String, Integer> gamelist = new HashMap<>();
    
    public User(String newPassword) {
        this.password = newPassword;
    }
    
    public void updateGameEntry(String gameId, int score) {
        this.gamelist.put(gameId, score);
    }
}
