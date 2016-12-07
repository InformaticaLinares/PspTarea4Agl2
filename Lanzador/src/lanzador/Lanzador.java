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

    public static void main(String[] args) throws InterruptedException, IOException {
//   Ejecuta el Suministrador
        Runtime.getRuntime().exec("java -jar EscritorSocket.jar");

        int aux = 1;
        while (aux <= 100) {
            Runtime.getRuntime().exec("java -jar LectorSocket.jar " + (aux));
            Thread.sleep(100);
            aux++;
        }
    }
}
