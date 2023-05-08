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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import edu.endicott.csc.finalproject.GameInfo;
import edu.endicott.csc.finalproject.GameList;
import edu.endicott.csc.finalproject.ServerResponse;
import edu.endicott.csc.finalproject.User;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 *
 * @author cesar
 */
public class Client {
    public final int STATUS_CODE_ALL_OK                     = 1;
    public final int STATUS_CODE_USER_NOT_FOUND             = 2;
    public final int STATUS_CODE_WRONG_PASSWORD             = 3;
    public final int STATUS_CODE_USER_ALREADY_EXISTS        = 4;
    public final int STATUS_CODE_USER_COULD_NOT_BE_CREATED  = 5;
    public final int STATUS_CODE_GAME_NOT_FOUND             = 6;
    
    public final int STATUS_CODE_UNKOWN_ERROR               = -1;
    
    public static Client client = new Client();
    
    private Gson gson = new Gson();
    private OkHttpClient okhttpClient = new OkHttpClient.Builder().build();
    private String serverAddress = "http://127.0.0.1:8080";
    
    private Client() {
    }
    
    private JsonObject makeGETRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .build();
        
        Call call = okhttpClient.newCall(request);
        Response response = call.execute();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        
        return json;
    }
    private JsonObject makePOSTRequest(String url, RequestBody formBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();
        
        Call call = okhttpClient.newCall(request);
        Response response = call.execute();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        
        return json;
    }
    
    /**
     *
     * @param user
     * @param password
     * @return 
     * @throws IOException
     */
    public ServerResponse<User> login(String user, String password) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("password", password)
                .build();
        
        JsonObject json = this.makePOSTRequest(serverAddress + "/login", formBody);
        
        Type jsonType = new TypeToken<ServerResponse<User>>() {}.getType();
        ServerResponse<User> response =
                this.gson.fromJson(json, jsonType);
        
        return response;
    }
    
    /**
     *
     * @param user
     * @param password
     * @return
     * @throws IOException
     */
    public ServerResponse<User> createNewUser(String user, String password) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("password", password)
                .build();
        
        JsonObject json = this.makePOSTRequest(
                serverAddress + "/createUser", formBody);
        
        Type jsonType = new TypeToken<ServerResponse<User>>() {}.getType();
        ServerResponse response = 
                this.gson.fromJson(json, jsonType);
        
        return response;
    }
    
    /**
     *
     * @param user
     * @param gameid
     * @return
     * @throws IOException
     */
    public ServerResponse<User> addGameToUser(String user, String gameid) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("gameId", gameid)
                .build();
        
        JsonObject json = this.makePOSTRequest(
                serverAddress + "/addGameToUser", formBody);
        
         Type jsonType = new TypeToken<ServerResponse<User>>() {}.getType();
         ServerResponse<User> response =
                 this.gson.fromJson(json, jsonType);
         
         return response;
    }

    /**
     *
     * @param user
     * @param gameid
     * @return
     * @throws IOException
     */
    public ServerResponse<User> removeGameFromUser(String user, String gameid) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("gameId", gameid)
                .build();
        
        JsonObject json = this.makePOSTRequest(
                serverAddress + "/removeGameFromUser", formBody);
        
         Type jsonType = new TypeToken<ServerResponse<User>>() {}.getType();
         ServerResponse<User> response =
                 this.gson.fromJson(json, jsonType);
         
         return response;
    }

    /**
     *
     * @param user
     * @param gameid
     * @param score
     * @return
     * @throws IOException
     */
    public ServerResponse<User> updateGameScoreUser(String user, String gameid, String score) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("gameId", gameid)
                .add("score", score)
                .build();
        
        JsonObject json = this.makePOSTRequest(
                serverAddress + "/updateGameScoreFromUser", formBody);
        
         Type jsonType = new TypeToken<ServerResponse<User>>() {}.getType();
         ServerResponse<User> response =
                 this.gson.fromJson(json, jsonType);
         
         return response;
    }
    
    /**
     *
     * @param gameid
     * @return
     * @throws IOException
     */
    public JsonObject getUserInfo(String gameid) throws IOException {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(serverAddress + "/gameInfo").newBuilder();
        urlBuilder.addQueryParameter("gameRequest", gameid);
        String url = urlBuilder.build().toString();
        
        JsonObject json = makeGETRequest(url);
        
        return json;
    }
    
    /**
     *
     * @param gameid
     * @return
     * @throws IOException
     */
    public GameInfo getGameInfo(String gameid) throws IOException {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(serverAddress + "/gameInfo").newBuilder();
        urlBuilder.addQueryParameter("gameRequest", gameid); 
        String url = urlBuilder.build().toString();
        
        JsonObject json = this.makeGETRequest(url);
        
        Type jsonType = new TypeToken<ServerResponse<GameInfo>>() {}.getType();
        ServerResponse<GameInfo> response = 
                this.gson.fromJson(json, jsonType);
                
        return response.body;
    }
    
    /**
     *
     * @param keyWord
     * @return
     * @throws IOException
     */
    public ArrayList<GameList.AppList.Game> getSearchList(String keyWord) throws IOException {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(serverAddress + "/gameSearch").newBuilder();
        urlBuilder.addQueryParameter("keyWord", keyWord);
        String url = urlBuilder.build().toString();
        
        JsonObject json = this.makeGETRequest(url);
        
        Type jsonType = new TypeToken<ServerResponse<ArrayList<GameList.AppList.Game>>>() {}.getType();
        ServerResponse<ArrayList<GameList.AppList.Game>> response = 
                this.gson.fromJson(json, jsonType);
        
        return response.body;
    }
    
    /**
     *
     * @param user
     * @return
     * @throws IOException
     */
    public ArrayList<GameList.AppList.Game> getUserList(String user) throws IOException {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(serverAddress + "/getUserList").newBuilder();
        urlBuilder.addQueryParameter("user", user);
        String url = urlBuilder.build().toString();
        
        JsonObject json = this.makeGETRequest(url);
        
        Type jsonType = new TypeToken<ServerResponse<ArrayList<GameList.AppList.Game>>>() {}.getType();
        ServerResponse<ArrayList<GameList.AppList.Game>> response = 
                this.gson.fromJson(json, jsonType);
        
        return response.body;
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    public GameInfo getRandomGame() throws IOException {
        JsonObject json = this.makeGETRequest(serverAddress + "/randomGame");
        
        Type jsonType = new TypeToken<ServerResponse<GameInfo>>() {}.getType();
        ServerResponse<GameInfo> response = 
                this.gson.fromJson(json, jsonType);
        
        return response.body;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String args[]){
        
    }
}