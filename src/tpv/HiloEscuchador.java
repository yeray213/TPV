/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class HiloEscuchador implements Runnable {

    boolean sw = true;
    CTPV ctpv;

    int numeroTpv = 1; //Numero de TPV, para asignarlo al titulo

    HiloEscuchador(CTPV ctpv) {
        this.ctpv = ctpv;
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(3000);
            
            while (sw) {
                //Recibo la apertura de un TPV
                //Le paso la ventana a la que añadir el internal y el numero para el titulo
                //inicio el hilo
                new HiloCTPV(servidor.accept(), ctpv, numeroTpv++).start();

            }
        } catch (IOException ex) {
            Logger.getLogger(HiloEscuchador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
