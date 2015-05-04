/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctpv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import tpv.ProductoPedido;

/**
 *
 * @author Yeray
 */
public class HiloInterna implements Runnable {

    private JList lista;
    private int numeroTpv;
    
    private JLabel total; 

    private DefaultListModel modeloLista = new DefaultListModel();

    private HashMap<String, ProductoPedido> listaPedidos; // Aqui se almacenan los productos pedidos

    //Fichero
    public static final String RUTA_FICHERO = "VENTAS.dat";

    HiloInterna(JList lista, int numeroTpv, JLabel total) {
        this.lista = lista;
        this.numeroTpv = numeroTpv;
        this.total = total;

        listaPedidos = new HashMap<>();
    }

    @Override
    public void run() {
        //Creamos el modelo
        lista.setModel(modeloLista);

        try {
            ServerSocket server = new ServerSocket(5000 + numeroTpv); //Para que no repita el puerto

            //Recibir los datos del TPV
            while (true) {
                Socket cliente = server.accept(); //Aceptamos lo que recibimos

                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

                //Recibimos productoPedido
                ProductoPedido pedido = (ProductoPedido) in.readObject();

                listaPedidos.put(pedido.getNombre(), pedido);

                //Actualizar Lista
                actualizarLista();

                //Escribimos en el fichero
                escribirFichero(pedido);
            }

        } catch (IOException ex) {
            Logger.getLogger(HiloInterna.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloInterna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarLista() {
        modeloLista.removeAllElements(); //Borramos toda la lista
        
        //Auxiliar total
        double totalCuenta = 0;

        for (String key : listaPedidos.keySet()) {            
            //Lo mostramos en la lista
            ProductoPedido pedido = listaPedidos.get(key);
            modeloLista.addElement(pedido);
            totalCuenta += pedido.getTotal();
        }
        
        total.setText(totalCuenta+"");

    }

    public void escribirFichero(ProductoPedido pedido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(RUTA_FICHERO, true); //Ponemos true para sobreescribir
            pw = new PrintWriter(fichero);

            pw.write("TPV" + numeroTpv);

            pw.write("\t" + pedido.toString() + "\t");

            pw.write("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para               

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
