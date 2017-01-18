package verq.admin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SendRequest {
	
	String url = "";
	String data = "";
	String response = "";
	String requestMethod = "";
	int responseCode = 0;
	
	public SendRequest(String requestMethod, String url, String data){
		this.requestMethod = requestMethod;
		this.url = url;
		this.data = data;
	}
	
	public void execute(){
            
		try {
				URL obj = new URL(url);
			
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();		


            //add request header
            con.setRequestMethod(requestMethod);

            /*
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
			*/
            responseCode = con.getResponseCode();
            

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            
            StringBuilder responseBuilder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                    responseBuilder.append(inputLine);
            }
            in.close();
            
            response = responseBuilder.toString();
            
            
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + data);
            System.out.println("Response Code : " + responseCode);
            System.out.println("Response : " + response);	
            
            if(response.contains("error")){
            	responseCode = -50;
            }
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
			responseCode = -100;
		}
	}
	
	public int getResponseCode(){
		return responseCode;
	}
	
	public String getResponse(){
		return response;
	}
	
}
	
	
    
    