/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JInternalFrame;

/**
 *
 * @author Yeray
 */
public class HiloCTPV extends Thread {

    CTPV ctpv;

    boolean encender = true;
    private Socket socket = null;

    
    //Crear la ventana interna
    JInternalFrame interna;
    
    HiloCTPV(Socket socket, CTPV ctpv) {
        this.socket = socket;
        this.ctpv = ctpv;
    }

    @Override
    public void run() {
            
            //Metodo que crea las ventanas internas
            crearInternal();
    }

    public void crearInternal() {
        
        interna = new JInternalFrame("TPV");
        
        //Añadir a la ventana la ventana interna        
        ctpv.add(interna);
        interna.setVisible(true);
        interna.pack();
        
        //sout
        System.out.println("creo la ventana");
    }

}
