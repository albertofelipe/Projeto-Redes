package UDP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner sc = new Scanner(System.in);

        try {
            aSocket = new DatagramSocket();
            byte[] buffer = new byte[1000];

            while (true) {
                System.out.println("Digite a mensagem a ser enviada: ");
                String msg = sc.nextLine();

                System.out.println("Digite o endereço IP do cliente: ");
                InetAddress clientAddress = InetAddress.getByName(sc.nextLine());

                DatagramPacket request = new DatagramPacket (msg.getBytes(), msg.length(), clientAddress, 6161);
                aSocket.send(request);

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);

                System.out.println("Mensagem recebida do cliente: " + new String(reply.getData()));
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
        sc.close();
    }
}

