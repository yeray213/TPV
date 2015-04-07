/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpv;

import javax.swing.JInternalFrame;

/**
 *
 * @author Yeray
 */
public class HiloCTPV implements Runnable{

CTPV ctpv;

    public HiloCTPV(CTPV ctpv) {
        this.ctpv = ctpv;
    }



    @Override
    public void run() {
        //Metodo que crea las ventanas internas
        crearInternal();
    }

    public void crearInternal() {
        //Crear la ventana interna
        JInternalFrame interna = new JInternalFrame("TPV");
        
        //Añadir a la ventana la ventana interna
        ctpv.add(interna);
        interna.setVisible(true);
        interna.pack();      
    }
    
}
