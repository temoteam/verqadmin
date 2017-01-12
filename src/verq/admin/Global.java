/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verq.admin;

/**
 *
 * @author school
 */
public class Global {
    
    public String token = "";
    public String id = "";
    public String request = "";
    public String data = "";
    
    public void setToken(String token){
        this.token = token;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setRequest(String request){
        this.request = request;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public String getToken(){
        return token;
    }
    
    public String getId(){
        return id;
    }
    
    public String getRequest(){
        return request;
    }
    
    public String getData(){
        return data;
    }
    
}
