/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorsocket;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/*** @author Marina
 */
public class LectorSocket {

    public static void main(String[] args) throws InterruptedException {
        Socket canal = null; //Socket para el canal de conexión con el escritor
        BufferedReader entrada = null; // Buffer para leer del stream
        String datosEntrada;  //para los valores que vamos leyendo del canal
        int id = Integer.parseInt(args[0]);
        boolean conex=false;
        int num;
        String fichlog = "Resultado.txt";
        PrintStream ps = null;
        try {
            ps = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                                    new File(fichlog), true)), true);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            System.setOut(ps);
            System.setErr(ps);
        }
        
        
        while (conex==false) {
            try {
                //Pido conexión al equipo a través del puerto 12500, donde escucha el escritor
                canal = new Socket("localhost", 12500);
                conex=true;
            } catch (Exception e) {
                conex=false;
                Thread.sleep(500);
            }
        }
        try {
            //Hay conexión --> necesito stream de entrada en el canal
            //Lector con Buffer para no perder ningún dato
            entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
            //Vamos a leer mientras tengamos datos que leer
            datosEntrada = entrada.readLine();

            num = Integer.parseInt(datosEntrada);
            System.out.println("Consumidor "+id+". Nº Leido: "+num);
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error al leer del canal");
            //System.err.println(e.toString()); //descripcion de lo que pasa
        }

        try {
            canal.close();
        } catch (IOException e) {
            //System.err.println("Error de socket");
            //System.err.println(e.toString());
        }

    }
    
}
