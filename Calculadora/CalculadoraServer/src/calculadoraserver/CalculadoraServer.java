package calculadoraserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServer {

    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 6666);
            serverSocket.bind(addr);

            while (true) {
                System.out.println("Aceptando conexiones");

                Socket newSocket = serverSocket.accept();

                System.out.println("Conexión recibida");

                // Crear un nuevo hilo para manejar la conexión
                Thread thread = new Thread(() -> {
                    try {
                        InputStream is = newSocket.getInputStream();
                        OutputStream os = newSocket.getOutputStream();

                        DataInputStream dis = new DataInputStream(is);
                        DataOutputStream dos = new DataOutputStream(os);

                        try {
                            // Leer operandos y operación del cliente
                            double operando1 = dis.readDouble();
                            double operando2 = dis.readDouble();
                            String operacion = dis.readUTF();

                            // Realizar la operación
                            double resultado = realizarOperacion(operando1, operando2, operacion);

                            // Enviar el resultado al cliente
                            dos.writeUTF(String.valueOf(resultado));
                        } catch (EOFException e){
                            // Cliente cerró la conexión
                            System.out.println("El cliente cerró la conexión");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            // Cerrar flujos y socket al finalizar
                            cerrarFlujos(dis, dos);
                            newSocket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Iniciar el hilo para manejar la conexión
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double realizarOperacion(double operando1, double operando2, String operacion) {
        double resultado = 0.0;
        switch (operacion) {
            case "SUMA":
                resultado = operando1 + operando2;
                break;
            case "RESTA":
                resultado = operando1 - operando2;
                break;
            case "MULTIPLICACION":
                resultado = operando1 * operando2;
                break;
            case "DIVISION":
                if (operando2 != 0) {
                    resultado = operando1 / operando2;
                } else {
                    System.out.println("Error: División por cero.");
                }
                break;
            default:
                System.out.println("Operación no válida");
        }
        return resultado;
    }

    private static void cerrarFlujos(DataInputStream dis, DataOutputStream dos) {
        try {
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
