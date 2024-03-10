package chatserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    private static final int PORT = 6666;
    private static final int MAX_CLIENTS = 10;
    private static List<ClientHandler> clients = new ArrayList<>();
    private static volatile boolean serverClosed = false;

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            // Solicitar el puerto al usuario al iniciar el servidor
            System.out.print("Ingrese el puerto para establecer la conexión: ");
            Scanner scanner = new Scanner(System.in);
            int serverPort = scanner.nextInt();

            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Esperando conexiones en el puerto " + serverPort);

            while (!serverClosed) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                if (clients.size() < MAX_CLIENTS) {
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    clientHandler.start();

                    System.out.println("Actualmente hay " + clients.size() + " usuarios conectados.");
                } else {
                    System.out.println("Número máximo de clientes alcanzado. Rechazando la conexión.");
                    // Puedes enviar un mensaje al cliente informándole que el servidor está lleno.
                    clientSocket.close();
                }

                // Verificar si no hay clientes conectados y mostrar el mensaje
                if (clients.isEmpty()) {
                    System.out.println("Ningún cliente conectado.");
                }
            }

            // Enviar señal de cierre a los clientes
            for (ClientHandler client : clients) {
                client.sendServerClosedSignal();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {

        private Socket clientSocket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private String nickname;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Establece flujos de entrada y salida para el cliente
                dis = new DataInputStream(clientSocket.getInputStream());
                dos = new DataOutputStream(clientSocket.getOutputStream());

                // Solicitar nickname al cliente
                nickname = dis.readUTF();

                // Informar a todos los clientes sobre la nueva conexión
                for (ClientHandler client : clients) {
                    if (client != this) {
                        client.sendMessage(nickname + " acaba de unirse al chat.");
                    }
                }

                // Escuchar mensajes del cliente y reenviarlos a todos los clientes
                while (true) {
                    String message = dis.readUTF();
                    if (message.equalsIgnoreCase("bye")){
                        System.out.println(nickname + " dejó el chat.");
                        break;
                    }
                    broadcastMessage(nickname + ": " + message);
                }
            } catch (IOException e) {
                // Cliente desconectado
                clients.remove(this);
                try {
                    broadcastMessage(nickname + " dejó el chat.");
                } catch (IOException ex) {
                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    // Cerrar el socket del cliente
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }

        private void sendMessage(String message) throws IOException {
            // Envía un mensaje al cliente
            dos.writeUTF(message);
        }

        private void broadcastMessage(String message) throws IOException {
            // Reenvía el mensaje a todos los clientes
            for (ClientHandler client : clients) {
                if (client != this) {
                    client.sendMessage(message);
                }
            }
            // Muestra el mensaje en el servidor
            System.out.println(message);
        }

        public void sendServerClosedSignal() {
            try {
                sendMessage("El servidor se desconectó.");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
