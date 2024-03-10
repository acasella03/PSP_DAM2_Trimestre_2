package socketstreamserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketStreamServer {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket = serverSocket.accept();

            System.out.println("Conexión recibida");

            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);

            /*
            Esto significa que antes de enviar el contenido del mensaje, se envía un entero de 4 bytes que indica la longitud del mensaje. 
            Luego, el receptor (ya sea el servidor o el cliente) lee esos 4 bytes primero para determinar cuántos bytes adicionales debe 
            leer para obtener el mensaje completo.
             */
            //Conversación de 3 mensajes desde el cliente al servidor
            for (int i = 0; i < 3; i++) {
                int longitudMensaje = dis.readInt(); // Suponiendo que el tamaño del mensaje se envía como un entero de 4 bytes
                byte[] mensaje = new byte[longitudMensaje];
                is.read(mensaje);
                System.out.println("Mensaje recibido desde cliente: " + new String(mensaje, StandardCharsets.UTF_8));
            }

            //Conversación de 3 mensaje desde el servidor al cliente
            for (int i = 0; i < 3; i++) {
                String mensaje = "Mensaje " + (i + 1) + " desde servidor";
                byte[] bytesMensaje = mensaje.getBytes(StandardCharsets.UTF_8);
                os.write(bytesMensaje.length);
                os.write(bytesMensaje);
                os.flush();
                System.out.println("Mensaje enviado al cliente: " + mensaje);
            }

            //Sumar 5 números recibidos desde el cliente
            int suma = 0;
            for (int i = 0; i < 5; i++) {
                suma += dis.readInt();

            }

            System.out.println("Resultado de la suma de los números recibidos: " + suma);

            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
        }
    }
}
