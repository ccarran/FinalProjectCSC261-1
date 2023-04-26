package edu.endicott.csc.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesar
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.endicott.csc.finalproject.GameInfo;
import edu.endicott.csc.finalproject.GameList;
import edu.endicott.csc.finalproject.User;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 

//Based on code from: https://www.geeksforgeeks.org/socket-programming-in-java/
public class Server
{
    private Socket socket               = null;
    private ServerSocket server         = null;
    private DataInputStream in          = null;
    private DataOutputStream out        = null;
    private GameInfo appDetails;
    
    private User currentUser;
    private Gson gson = new Gson();
    
    private String username;
    
    public Server(int port)
    {   
        this.checkSteamAPI();
        
        try
        {
            server = new ServerSocket(port);
            
            // Check if changes have been made to the steam database
            checkSteamAPI();
            
            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            
            this.out = new DataOutputStream(this.socket.getOutputStream());
 
            String line = "";
            
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    
                    this.appDetails = this.getGameDataFromAPI(line);
                    
                    if (this.appDetails.success){
                        this.out.writeUTF(this.appDetails.data.name);
                        System.out.println(this.appDetails.data.name);
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    // This gets the game info from the INFO API
    private GameInfo getGameDataFromAPI(String appid) {
        GameInfo tmpGameEntry = null;
        
        try {
            URL steamGameURL = new URL("https://store.steampowered.com/api/appdetails?appids=" + appid);
            HttpURLConnection connectGamedata = (HttpURLConnection)steamGameURL.openConnection();
            BufferedReader readerGamedata = new BufferedReader(new InputStreamReader(connectGamedata.getInputStream()));
            
            JsonObject gamedataJson = JsonParser.parseReader(readerGamedata).getAsJsonObject().getAsJsonObject(appid);
        
            tmpGameEntry = this.gson.fromJson(gamedataJson, GameInfo.class);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmpGameEntry;
    }
    
    private void addGameToUser(String gameid) {
        try {
            this.currentUser.updateGameEntry(gameid, 0);
            
            File gamelistFile = new File("data/gamelist", "allGames.json");
            JsonObject listJson = JsonParser.parseReader(new FileReader(gamelistFile)).getAsJsonObject();
            
            GameList gamelist = this.gson.fromJson(listJson, GameList.class);
                        
            for(int i = 0; i < gamelist.applist.apps.size(); i++) {
                if(gamelist.applist.apps.get(i).appid.equals(gameid)) {
                    gamelist.applist.apps.get(i).userNumber += 1;
                    break;
                }
            }
            
            FileWriter file = new FileWriter("data/gamelist/allGames.json");
            file.write(gson.toJson(gamelist, GameList.class));
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private void updateUserGameScore(String gameid, int score) {
        this.currentUser.updateGameEntry(gameid, score);
        
        try {            
            File gamelistFile = new File("data/gamelist", "allGames.json");
            JsonObject listJson = JsonParser.parseReader(new FileReader(gamelistFile)).getAsJsonObject();
            
            GameList gamelist = this.gson.fromJson(listJson, GameList.class);
            
            for(int i = 0; i < gamelist.applist.apps.size(); i++) {
                if(gamelist.applist.apps.get(i).appid.equals(gameid)) {
                    int currentGameScore = gamelist.applist.apps.get(i).rating *
                            gamelist.applist.apps.get(i).userNumber;
                    
                    gamelist.applist.apps.get(i).rating = currentGameScore + 
                            score / gamelist.applist.apps.get(i).userNumber;
                    break;
                }
            }
            
            FileWriter file = new FileWriter("data/gamelist/allGames.json");
            file.write(gson.toJson(gamelist, GameList.class));
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void loadUserInfo() {        
        File dir = new File("data/users");
        dir.mkdirs();
        File userInfo = new File(dir, this.username + ".json");
        if (userInfo.exists()) {
            try {
                JsonObject userJson = JsonParser.parseReader(new FileReader(userInfo)).getAsJsonObject();
                
                currentUser = this.gson.fromJson(userJson, User.class);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                userInfo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void storeUserInfo() {
        try {
            FileWriter file = new FileWriter("data/users/" + username + ".json");
            file.write(gson.toJson(this.currentUser, User.class));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // This gets the game list from the LIST API
    private void checkSteamAPI() {
        GameList steamGameList = null;
        
        try {
            // First, we check if we have that list already       
            File dir = new File("data/gamelist");
            dir.mkdirs();
            File steamGamesList = new File(dir, "allGames.json");
            if(!steamGamesList.exists()) {
                //Then, we get the list of games from the Steam API
                URL steamGameURL = new URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=STEAMKEY&format=json");
                HttpURLConnection connectListdata = (HttpURLConnection) steamGameURL.openConnection();
                BufferedReader readerListdata = new BufferedReader(new InputStreamReader(connectListdata.getInputStream()));

                //We parse the list into a json
                JsonObject gameListJson = JsonParser.parseReader(readerListdata).getAsJsonObject();

                steamGameList = this.gson.fromJson(gameListJson, GameList.class);
   
                steamGameList.applist.apps.sort((o1, o2) -> o1.name.compareTo(
                        o2.name));
                
                // Finally, we write to the file
                FileWriter writer = new FileWriter("data/gamelist/allGamesFile.json");
                writer.write(this.gson.toJson(steamGameList));
                writer.close();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setUsername(String username) {
        this.username = username;
    }
    
    // TBD: FINISH THE USE STATEMENT    
    public static void main(String args[])
    {
        if (args.length == 0) {
            System.out.println("Correct Use:");
        }
        for(String s:args){
            if (s.equals("--help")){
                System.out.println("Correct Use:");
                break;
            }
        }
        
        Server server = new Server(5000);
    }
}