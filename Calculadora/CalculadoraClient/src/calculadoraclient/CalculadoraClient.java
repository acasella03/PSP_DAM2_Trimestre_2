package calculadoraclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClient {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 6666);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            // Leer operandos y operación desde el usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el primer operando: ");
            double operando1 = scanner.nextDouble();
            System.out.println("Ingrese el segundo operando:");
            double operando2 = scanner.nextDouble();
            System.out.println("Ingrese la operación tal cual como se indica (SUMA, RESTA, MULTIPLICACION, DIVISION): ");
            String operacion = scanner.next();

            // Enviar operandos y operación al servidor
            dos.writeDouble(operando1);
            dos.writeDouble(operando2);
            dos.writeUTF(operacion);

            // Recibir el resultado del servidor
            String resultado = dis.readUTF();
            System.out.println("El resultado de la operación es: " + resultado);

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
