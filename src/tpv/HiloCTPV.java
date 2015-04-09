/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpv;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Yeray
 */
public class HiloCTPV extends Thread {

    CTPV ctpv;

    boolean encender = true;
    private Socket socket;
    private int numeroTpv;
    private ServerSocket servidor;
    
    private boolean sw = true;

    //Crear la ventana interna
    JInternalFrame interna;
    JLabel label;

    HiloCTPV(Socket socket, CTPV ctpv, int numeroTpv) {
        this.socket = socket;
        this.ctpv = ctpv;
        this.numeroTpv = numeroTpv;


    }

    @Override
    public void run() {        
        //Metodo que crea las ventanas internas
        crearInternal();
        
        //Cerrar interna al cerrar el TPV
        try {
            while(true){ //Comprueba si hay socket
                if(socket.getInputStream().read() == -1){ try {
                    //si es -1 es que esta cerrado
                    //cierra el internal
                    
                    //Muestro un mensaje
                    JOptionPane.showMessageDialog(interna, "CLIENTE SERVIDO");                 
                    
                    //Cierro la ventana
                    interna.setClosed(true);
                    break; //Salir del while
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(HiloCTPV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloCTPV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearInternal() {
        interna = new JInternalFrame("TPV" + numeroTpv); //Creo el internal con el titulo
        
        //interna.setClosable(true); //Permitimos qe se pueda cerrar
        
        //Añadir a la ventana la ventana interna        
        ctpv.add(interna);
        interna.setVisible(true);
        interna.pack();
    }

}
