package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in); 

            while (true) { 
                System.out.print("Enter your message: "); 
                String ch = scanner.nextLine(); 
                byte buffer[] = ch.getBytes(); 
                DatagramPacket donneesEmises = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 1234);
                socket.send(donneesEmises);
                byte[] bufferRecu = new byte[1024]; 
                DatagramPacket donneesRecues = new DatagramPacket(bufferRecu, bufferRecu.length); 
                socket.receive(donneesRecues); 
                String message = new String(donneesRecues.getData(), 0, donneesRecues.getLength());
                System.out.println("Message Received : " + message);
                System.out.println("from : " + donneesRecues.getAddress() + ":" + donneesRecues.getPort());
            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}