/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escritorsocket;

/**
 * * @author
 */
import java.net.*;
import java.io.*;
import java.util.Random;

public class EscritorSocket {

    public static void main(String[] args) {
        ServerSocket conexion = null; //Socket para aceptar conexiones
        Socket canal = null;   // Socket para establecer el canal
        PrintWriter salidaStream;
        int aux = 1;
        while (aux < 100) {
            try {
                conexion = new ServerSocket(12500);
                //Pido al SO que abra el puerto 12500 para la escucha de la conexion
            } catch (IOException e) {
                System.err.println("No se puede abrir el puerto");
                System.err.print(e.toString());
                System.exit(-1);
            }
            
            try {
                System.out.println("Proceso escritor esperando conexión del proceso lector");
                //Como estoy esperando al otro proceso, tengo que hacer esperar a este
                canal = conexion.accept(); //conexion.accept() bloque el proceso, le pone a dormir 
                //para mandar datos al proceso del otro lado del canal voy a usar los 
                //métodos read y write, luego necesito un objeto de tipo PrintWriter
                //a partir del estream de salida del socket o canal de comunicacion

                salidaStream = new PrintWriter(canal.getOutputStream());
                aux++;
                salidaStream.println(aux);

                //Mando el mensaje
                salidaStream.flush(); //Limpio el canal
                salidaStream.close(); //cierro el Stream del canal. No genera excepcio0
            } catch (Exception e) {
               // System.err.println("Error de conexión o al escribir en el canal");
               // System.err.print(e.toString());
            }

            try {
                canal.close(); //cierro canal, puede elevar IOException
                conexion.close(); //cierro el ServerSocket
            } catch (IOException e) {
                System.err.println("Error al cerrar el canal o el ServerSocket");
            }
        }
    }

}
