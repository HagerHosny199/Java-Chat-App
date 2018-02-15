/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOLO
 */
public class messageTransmitter extends Thread{
    
    String hostName,message,myName;
    int port;
    writableGUI gui;
    public messageTransmitter()
    {
        
    }
    public messageTransmitter(String myName,writableGUI gui,String hostName,String message,int port)
    {
        this.hostName=hostName;
        this.message=message;
        this.port=port;
        this.gui=gui;
        this.myName=myName;
    }
    
    @Override
    public void run()
    {
        try {
            Socket s = new Socket(hostName,port);
            s.getOutputStream().write(message.getBytes());
            String temp=myName +" : "+ message;
            gui.write(temp);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(messageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
