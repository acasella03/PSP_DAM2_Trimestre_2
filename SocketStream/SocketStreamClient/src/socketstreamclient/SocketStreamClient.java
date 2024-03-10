package socketstreamclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketStreamClient {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 6666);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            DataOutputStream dos = new DataOutputStream(os);

            Scanner scanner = new Scanner(System.in);

            // Conversación de 3 mensajes desde el cliente al servidor
            for (int i = 0; i < 3; i++) {
                String mensaje = "Mensaje " + (i + 1) + " desde cliente";
                byte[] bytesMensaje = mensaje.getBytes(StandardCharsets.UTF_8);
                dos.writeInt(bytesMensaje.length);
                dos.write(bytesMensaje);
                dos.flush();
            }

            // Conversación de 3 mensajes desde el servidor al cliente
            for (int i = 0; i < 3; i++) {
                int longitudMensaje = is.read();
                byte[] mensaje = new byte[longitudMensaje];
                is.read(mensaje);
                System.out.println("Mensaje recibido desde servidor: " + new String(mensaje, StandardCharsets.UTF_8));
            }

            // Enviar lista de 5 números al servidor
            for (int i = 0; i < 5; i++) {
                System.out.print("Ingrese el número " + (i + 1) + ": ");
                int numero = scanner.nextInt();
                dos.writeInt(numero);
                dos.flush();
            }

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
