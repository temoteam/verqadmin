/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verq.admin;

/**
 *
 * @author Sergey
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** @see http://stackoverflow.com/a/13919878/230513 */
public class ChooseStreamForm extends javax.swing.JFrame{
	
	private Global global;
	
	public ChooseStreamForm(Global global) {
        this.global = global;
        this.setTitle("VerQ Admin");
        
        initComponents();
    }

    

    
	DefaultListModel<JCheckBox> model = null;
    
    private void initComponents() {
    	
    			
            	
            	model = new DefaultListModel<JCheckBox>();
            	JCheckBoxList checkBoxList = new JCheckBoxList(model);
            	getData();
            	
            	
            	javax.swing.JButton jButton1 = new javax.swing.JButton();
            	jButton1.setText("Продолжить");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });
            	
                JFrame f = new JFrame("CheckTable");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLayout(new GridLayout(0, 1));
                
                f.add(checkBoxList);
                f.add(jButton1);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                
         
    }
    
    public void getData(){
    	String token = global.getToken();
		String data = "token=" + "587f26b50d0cd1a7f38fd879";
		
		String url = global.getDomain() + "/api/getAllStreams?" + data;
		String response = sentGet(url);
		
    	
    	//SendRequest getAllStreams = new SendRequest("GET", url, data);  
    	
    	//getAllStreams.execute();
    	
    	JSONParser parser = new JSONParser();
        JSONArray allStreams = new JSONArray();
		try {
			allStreams = (JSONArray) parser.parse(response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for(int i = 0; i < allStreams.size(); i++){
        	JSONObject stream = new JSONObject();
        	try {
				stream = (JSONObject) parser.parse(allStreams.get(i).toString());
				
				String id = stream.get("owner_id").toString();
				
				JSONObject user = new JSONObject();
				user = (JSONObject) parser.parse(sentGet(global.getDomain() + "/api/getuser?" + "id=" + id + "&token="  + "587f26b50d0cd1a7f38fd879"));
				
				String status = user.get("status").toString();
				
				id = stream.get("id").toString();
				JCheckBox d = new JCheckBox(status);
				d.setName(id);
				model.addElement(d);
				ArrayList a = global.getChecked();
				a.add(id);
				global.setChecked(a);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        System.out.print(global.getChecked());
        
        
        
    }
    
    
    private static void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	System.out.print("lala");
    }
    
    public String sentGet(String url){
    	String response = "";
    	try {
			URL obj = new URL(url);
		
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
	
	
	        //add request header
	        //con.setRequestMethod("GET");
	        
	        con.getResponseCode();
	        
	
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        
	        StringBuilder responseBuilder = new StringBuilder();
	
	        while ((inputLine = in.readLine()) != null) {
	                responseBuilder.append(inputLine);
	        }
	        in.close();
	        
	        response = responseBuilder.toString();
	        
	        System.out.println("Response : " + response);	
	        
        
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return response;
    }
    
    public static void main(String[] args) {
    	try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new ChooseStreamForm(new Global()).setVisible(true);
            }
        });
    }
    
    

}