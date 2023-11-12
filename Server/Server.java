package Server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static byte buffer[] = new byte[1024]; 
    public static void main(String argv[]) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            String donnees = "";
            String message = "";
            int taille = 0;
            System.out.println("lancement du serveur"); 
            while (true) {
                DatagramPacket paquet = new DatagramPacket(buffer, buffer.length); 
                DatagramPacket envoi = null;
                socket.receive(paquet); 
                System.out.println("\n" + paquet.getAddress()); 
                taille = paquet.getLength(); 
                donnees = new String(paquet.getData(), 0, taille); 
                System.out.println("Donnees reçues = " + donnees); 
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
                message = timeStamp; 
                System.out.println("Donnees envoyees = " + message); 
                envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort());
                socket.send(envoi); 
            }
        }
    }
}