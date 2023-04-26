package edu.endicott.csc.client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesar
 */

import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import java.util.ArrayList;
 
//Based on code from: https://www.geeksforgeeks.org/socket-programming-in-java/
public class Client {
    private static Client client;
    
    private Socket socket           = null;
    private DataInputStream input   = null;
    private DataInputStream in      = null;
    private DataOutputStream out    = null;
 
    private Client(String address, int port) {   
        // establish a connection
        try {
            this.socket = new Socket(address, port);
            System.out.println("Connected");
 
            // takes input from terminal
            this.input = new DataInputStream(System.in);
 
            this.in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            
            // sends output to the socket
            this.out = new DataOutputStream(
                this.socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }
    
    public String requestInfo(String request) {
        // string to read message from input
        String line = "";
 
        try {
            line = this.input.readLine();
            this.out.writeUTF(line);

            System.out.println(this.in.readUTF());
        }
        catch (IOException i) {
            System.out.println(i);
        }
        
        return line;
    }
    
    public ArrayList<String> getFirstGames(int n){
        ArrayList<String> list = new ArrayList<>();
 
        for (int i = 0; i < n; i++){
            String line = "";
            
            try {
                line = this.input.readLine();
                this.out.writeUTF(line);

                System.out.println(this.in.readUTF());
            }
            catch (IOException ex) {
                System.out.println(ex);
            }

            list.add(line);
        }
        
        return list;
    }
    
    public void closeConnection() {
        // close the connection
        try {
            this.input.close();
            this.out.close();
            this.socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }
    
    public static Client getClient() {
        if (client == null){
            client = new Client("127.0.0.1", 5000);
        }
        return client;
    }
    
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}