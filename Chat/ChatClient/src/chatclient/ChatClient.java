package chatclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket cliente");
            Socket clientSocket = new Socket();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la dirección IP del servidor: ");
            String serverIP = scanner.nextLine();

            System.out.println("Ingrese el puerto del servidor: ");
            int serverPort = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente

            clientSocket.connect(new InetSocketAddress(serverIP, serverPort));

            System.out.println("Conectado al servidor");

            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            // Solicitar al cliente que ingrese su apodo
            System.out.print("Ingrese su apodo: ");
            String nickname = scanner.nextLine();
            dos.writeUTF(nickname);

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    while (!clientSocket.isClosed()) {
                        try {
                            String message = dis.readUTF();
                            System.out.println(message);
                        } catch (EOFException e) {
                            // EOFException indica que el servidor se ha desconectado
                            System.out.println("El servidor se desconectó.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Hilo para enviar mensajes al servidor
            new Thread(() -> {
                try {
                    while (true) {
                        String message = scanner.nextLine();

                        // Verificar si el usuario quiere salir del chat
                        if (message.equalsIgnoreCase("bye")) {
                            dos.writeUTF(message); // Enviar el mensaje "bye" al servidor
                            System.out.println("Desconectándose del chat...");
                            break;
                        }else{
                            dos.writeUTF(message);
                        }
                        
                    }
                } catch (IOException e) {
                    // Manejar la excepción si ocurre un problema de E/S
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
