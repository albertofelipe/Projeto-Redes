package TCP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    private static final int SERVER_PORT = 7171;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        try (Socket socket = new Socket()) {
            while(true) {
                System.out.print("Digite a mensagem: ");
                String msg = sc.nextLine();

                System.out.print("Digite o endereço IP do servidor: ");
                String serverIP = sc.nextLine();

                socket.connect(new InetSocketAddress(serverIP, SERVER_PORT));

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                outputStream.writeUTF(msg);

                String receivedMessage = inputStream.readUTF();
                System.out.println("Received: " + receivedMessage);
            }

        } catch (UnknownHostException e) {
            System.out.println("Host desconhecido: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("Fim de arquivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
        sc.close();
    }
}

