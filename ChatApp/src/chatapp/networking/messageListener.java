/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOLO
 */
public class messageListener extends Thread{
    int port;
    ServerSocket server;
    writableGUI gui;
    String hostName,temp;
    //constructor
    public messageListener(String hostName,writableGUI gui,int port)
    {
        //intialize variables
        this.port=port;
        this.gui=gui;
        this.hostName=hostName;
        //initialize new serevr
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(messageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //defualt constructor
    public messageListener()
    {
        port=4455; //defualt port number
        //intialize new server
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(messageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void print () 
    {
        System.out.println(port);
    }
    @Override
    public void run()
    {
       
        try {
             Socket clientServer;
            //get a new connection
            while((clientServer=server.accept())!= null)
            {
                //get the client input
               InputStream is =clientServer.getInputStream();
               //convert the message to a string message 
               BufferedReader br=new BufferedReader(new InputStreamReader(is));
               //get new line
               String line =br.readLine();
               if(line!=null)   //check if line has a data 
               {
                   temp=hostName + " : " + line;
                   gui.write(temp); //then print it to the main screen
               }
            }
        } catch (IOException ex) {
            Logger.getLogger(messageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
