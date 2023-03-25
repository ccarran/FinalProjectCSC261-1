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
 
//Based on code from: https://www.geeksforgeeks.org/socket-programming-in-java/
public class Client {
    private Socket socket           = null;
    private DataInputStream input   = null;
    private DataInputStream in      = null;
    private DataOutputStream out    = null;
 
    public Client(String address, int port)
    {
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
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
 
        // string to read message from input
        String line = "";
 
        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = this.input.readLine();
                this.out.writeUTF(line);
                
                System.out.println(this.in.readUTF());
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }
 
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
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}