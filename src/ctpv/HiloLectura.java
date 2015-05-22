/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctpv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeray
 */
public class HiloLectura implements Runnable {

    public static final String RUTA_FICHERO = "VENTAS.dat";

    @Override
    public void run() {
        try {
            contarLineasFichero();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HiloLectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HiloLectura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void contarLineasFichero() throws FileNotFoundException, IOException {
        //Abrir fichero y leer el contenido
        FileReader fr = new FileReader(RUTA_FICHERO);
        BufferedReader bf = new BufferedReader(fr);

        long numeroLineas = 0;
        
        String linea;

        while ((linea = bf.readLine()) != null) {
            numeroLineas++;
        }
    }

}
