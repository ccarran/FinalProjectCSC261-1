/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.finalproject;

/**
 *
 * @author cesar
 */
public class ServerResponse<T> {
    public T body;
    public int status;
    
    public ServerResponse<T> setStatus(int status) {
        this.status = status;
        return this;
    }
    
    public ServerResponse<T> setBody(T body) {
        this.body = body;
        return this;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public T getBody() {
        return this.body;
    }
}
