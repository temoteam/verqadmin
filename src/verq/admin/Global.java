/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verq.admin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author school
 */
public class Global {
	
	public String domain = "http://192.168.20.172";    
    public String token = "";
    public String id = "";
    public String request = "";
    public String data = "";
    public ArrayList<String> checked;
    
    public Global(){
    	checked = new ArrayList<>();
    }
    
    public void setChecked(ArrayList checked){
    	this.checked = checked;
    }
    
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
    
    public ArrayList getChecked(){
    	return checked;
    }
    
    public String getDomain(){
    	return domain;
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
