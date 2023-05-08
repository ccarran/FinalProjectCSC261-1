/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.finalproject;

import edu.endicott.csc.jframes.EntryWindow;
import edu.endicott.csc.jframes.WelcomeWindow;
import edu.endicott.csc.jframes.LoginWindow;
import edu.endicott.csc.jframes.MainWindow;
import edu.endicott.csc.jframes.Window;
import edu.endicott.csc.jframes.SearchWindow;
import edu.endicott.csc.jframes.CreateWindow;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;

/**
 *
 * @author cesar
 */
public class Controller {
    private static Controller instance;
    
    public static final int WELCOME_WINDOW = 0;
    public static final int LOGIN_WINDOW = 1;
    public static final int CREATE_WINDOW = 2;
    public static final int MAIN_WINDOW = 3;
    public static final int ENTRY_WINDOW = 4;
    public static final int SEARCH_WINDOW = 5;
    
    public final OkHttpClient httpClient = new OkHttpClient();
    
    public ArrayList<Window> windows = new ArrayList<>();
    public int currentWindow = 0;
    public ArrayList<GameList.AppList.Game> searchResults;
    
    public String username;
    public String currentGameID;
    public GameInfo currentGameInfo;
    public User user;
    
    private Controller() {
        windows.add(new WelcomeWindow());
        windows.add(new LoginWindow());
        windows.add(new CreateWindow());
        windows.add(new MainWindow());
        windows.add(new EntryWindow());
        windows.add(new SearchWindow());
    }
    
    /**
     *
     * @return
     */
    public static Controller getController() {
        if (Controller.instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */        
        //<editor-fold>
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        getController().windows.get(WELCOME_WINDOW).setVisible(true);
    }
}