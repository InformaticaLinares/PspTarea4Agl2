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

/**
 * * @author Marina
 */
public class LectorSocket {

    public static void main(String[] args) throws InterruptedException, IOException {
        Socket canal = null; //Socket para el canal de conexión con el escritor
        BufferedReader entrada = null; // Buffer para leer del stream
        String datosEntrada;  //para los valores que vamos leyendo del canal
        int id = Integer.parseInt(args[0]);
        boolean conex = false, leido = false;
        int num;
        String fichlog = "Resultado.txt";
        String ficherr = "Errores.txt";
        PrintStream ps = null;
        PrintStream pe = null;
        try {
            ps = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File(fichlog), true)), true);
            pe = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File(ficherr), true)), true);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            System.setOut(ps);
            System.setErr(pe);
        }
        ps.flush();
        pe.flush();
        while (leido == false) {
            if (conex == false) {
                try {
                    //Pido conexión al equipo a través del puerto 12500, donde escucha el escritor
                    canal = new Socket("localhost", 12500);
                    conex = true;
                } catch (Exception e) {
                    System.err.println("Consumidor " + id + ". Error al conectar");
                    conex = false;
                    Thread.sleep(500);
                }
            } else {
                try {
                    //Hay conexión --> necesito stream de entrada en el canal
                    //Lector con Buffer para no perder ningún dato
                    entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
                    //Vamos a leer mientras tengamos datos que leer
                    datosEntrada = entrada.readLine();

                    num = Integer.parseInt(datosEntrada);
                    System.out.println("Consumidor " + id + ". Nº Leido: " + num);
                    leido=true;
                    entrada.close();

                } catch (IOException e) {
                    Thread.sleep(100);
                    System.err.println("\nConsumidor " + id + ". Error al leer del canal\n");
                    conex=false;
                    leido=false;
                    System.err.println(e.toString()); //descripcion de lo que pasa
                }
                canal.close();
            }
        }
    }

}
