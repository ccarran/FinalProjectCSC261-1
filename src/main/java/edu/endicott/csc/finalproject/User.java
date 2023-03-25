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
public class User {
    public String password;
    public ArrayList<String> gameids;
    
    public User(String newPassword, ArrayList<String> gameList) {
        this.password = newPassword;
        if (gameList == null) {
            this.gameids = new ArrayList<>();
        }
        else {
            this.gameids = gameList;
        }
    }
}
