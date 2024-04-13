package TCP;

import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int SERVER_PORT = 7171;

    public static void main(String args[]) {
        try (ServerSocket socket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Servidor UDP iniciado na porta 7878.");

            while(true) {
                ConnectionHandler connectionHandler = new ConnectionHandler(socket.accept());
                connectionHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}

class ConnectionHandler extends Thread {
    private final Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            String data = in.readUTF();
            out.writeUTF(data);
        } catch (EOFException e) {
            System.out.println("Conexão encerrada pelo cliente: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de E/S: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Falha ao fechar o socket: " + e.getMessage());
            }
        }
    }
}
