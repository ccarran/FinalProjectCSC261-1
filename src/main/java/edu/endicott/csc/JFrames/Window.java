/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.JFrames;

import java.util.ArrayList;
import edu.endicott.csc.finalproject.*;

/**
 *
 * @author cesar
 */
public abstract class Window extends javax.swing.JFrame {
    protected static ArrayList<Integer> navigationHistory = new ArrayList<Integer>();
    protected static int currentWindow = 0;
    
    public void goToPreviousPage(){
        Controller.getController().windows.get(currentWindow).setVisible(false);
        
        currentWindow = navigationHistory.get(navigationHistory.size() - 1);
        Controller.getController().currentWindow = currentWindow;
        
        Controller.getController().windows.get(currentWindow).setVisible(true);
        navigationHistory.remove(navigationHistory.size() - 1);
    };
    
    public void goToPage(int page){
        Controller.getController().windows.get(currentWindow).setVisible(false);
        
        if(currentWindow != 1 && currentWindow != 2){
            navigationHistory.add(currentWindow);
        }
        
        Controller.getController().windows.get(page).setVisible(true);
        currentWindow = page;
        Controller.getController().currentWindow = page;        
    };
    
    public int getCurrentWindow() {
        return currentWindow;
    }
}