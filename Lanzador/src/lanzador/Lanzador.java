/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzador;

import java.io.IOException;

/**
 *
 * @author usuario
 */
public class Lanzador {
//hola
    public static void main(String[] args) throws InterruptedException {
        try {
//          Ejecuta el Suministrador
            Runtime.getRuntime().exec("java -jar EscritorSocket.jar");
            for (int i = 0; i < 100; i++) {
//          Ejecuta el consumidor
                Runtime.getRuntime().exec("java -jar LectorSocket.jar " + i);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            System.err.println("Error. " + e.toString());
        }finally{
            System.out.println("");
        }
    }
}
