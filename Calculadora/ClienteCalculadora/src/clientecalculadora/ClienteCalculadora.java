/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clientecalculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author postgres
 */
public class ClienteCalculadora extends javax.swing.JFrame {

    Socket clienteSocket;
    InetSocketAddress addr;
    DataInputStream dis;
    DataOutputStream dos;
    boolean nueva = true;
    double op1 = 0;
    double op2 = 0;
    String operacion = "";

    /**
     * Creates new form ClienteCalculadora
     */
    public ClienteCalculadora() {
        initComponents();
        conectarAlServidor();

        // Registro el manejador de eventos para el cierre de la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                formWindowClosing(windowEvent);
            }
        });
    }

    /**
     * Conección al servidor
     */
    private void conectarAlServidor() {
        try {
            System.out.println("Creando socket cliente");
            clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");
            addr = new InetSocketAddress("192.168.0.1", 6666);
            clienteSocket.connect(addr);
            dis = new DataInputStream(clienteSocket.getInputStream());
            dos = new DataOutputStream(clienteSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Enviar la operación al servidor
     */
    private void enviarOperacionAlServidor(double op1, double op2, String operacion) {
        try {
            if (dos != null) {
                // Enviar operandos y operación al servidor
                dos.writeDouble(op1);
                dos.writeDouble(op2);
                dos.writeUTF(operacion);
                dos.flush(); // para asegurar que los datos se envíen

                // Esperar la respuesta del servidor
                String resultado = dis.readUTF();
                // Mostrar el resultado en la interfaz del cliente
                lineaTexto.setText(resultado);
            } else {
                mostrarMensajeError("Error: El flujo de salida (dos) es nulo.");
            }
        } catch (IOException ex) {
            mostrarMensajeError("Error al enviar la operación al servidor.");
            ex.printStackTrace();
        }
    }

    /**
     * Muestra un mensaje de error en la interfaz de usuario.
     */
    private void mostrarMensajeError(String mensaje) {
        // Considera mostrar el mensaje de error en la interfaz de usuario (puedes usar un cuadro de diálogo, etiqueta, etc.)
        System.err.println(mensaje);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        lineaTexto = new javax.swing.JTextField();
        botonAC = new javax.swing.JButton();
        botonOFF = new javax.swing.JButton();
        boton7 = new javax.swing.JButton();
        boton8 = new javax.swing.JButton();
        boton9 = new javax.swing.JButton();
        botonDivision = new javax.swing.JButton();
        boton4 = new javax.swing.JButton();
        boton5 = new javax.swing.JButton();
        boton6 = new javax.swing.JButton();
        botonMultiplicacion = new javax.swing.JButton();
        boton1 = new javax.swing.JButton();
        boton2 = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();
        botonResta = new javax.swing.JButton();
        boton0 = new javax.swing.JButton();
        botonPunto = new javax.swing.JButton();
        botonIgual = new javax.swing.JButton();
        botonSuma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setBackground(new java.awt.Color(255, 255, 255));

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setName(""); // NOI18N

        botonAC.setBackground(new java.awt.Color(255, 0, 0));
        botonAC.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        botonAC.setForeground(new java.awt.Color(255, 255, 255));
        botonAC.setText("AC");
        botonAC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonAC.setBorderPainted(false);
        botonAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonACActionPerformed(evt);
            }
        });

        botonOFF.setBackground(new java.awt.Color(0, 51, 255));
        botonOFF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        botonOFF.setForeground(new java.awt.Color(255, 255, 255));
        botonOFF.setText("OFF");
        botonOFF.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonOFF.setBorderPainted(false);
        botonOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOFFActionPerformed(evt);
            }
        });

        boton7.setText("7");
        boton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton7ActionPerformed(evt);
            }
        });

        boton8.setText("8");
        boton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton8ActionPerformed(evt);
            }
        });

        boton9.setText("9");
        boton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton9ActionPerformed(evt);
            }
        });

        botonDivision.setText("/");
        botonDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDivisionActionPerformed(evt);
            }
        });

        boton4.setText("4");
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });

        boton5.setText("5");
        boton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });

        boton6.setText("6");
        boton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton6ActionPerformed(evt);
            }
        });

        botonMultiplicacion.setText("*");
        botonMultiplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMultiplicacionActionPerformed(evt);
            }
        });

        boton1.setText("1");
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        boton2.setText("2");
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton3.setText("3");
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });

        botonResta.setText("-");
        botonResta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRestaActionPerformed(evt);
            }
        });

        boton0.setText("0");
        boton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton0ActionPerformed(evt);
            }
        });

        botonPunto.setText(".");
        botonPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPuntoActionPerformed(evt);
            }
        });

        botonIgual.setText("=");
        botonIgual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIgualActionPerformed(evt);
            }
        });

        botonSuma.setText("+");
        botonSuma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSumaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(boton0)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonPunto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonIgual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSuma))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(boton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonResta))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(boton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton6)
                        .addGap(24, 24, 24)
                        .addComponent(botonMultiplicacion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(boton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton9)
                        .addGap(24, 24, 24)
                        .addComponent(botonDivision))
                    .addComponent(lineaTexto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(botonAC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonOFF)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lineaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAC)
                    .addComponent(botonOFF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton7)
                    .addComponent(boton8)
                    .addComponent(boton9)
                    .addComponent(botonDivision))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton4)
                    .addComponent(boton5)
                    .addComponent(boton6)
                    .addComponent(botonMultiplicacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton1)
                    .addComponent(boton2)
                    .addComponent(boton3)
                    .addComponent(botonResta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonSuma)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton0)
                        .addComponent(botonPunto)
                        .addComponent(botonIgual)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        panel.getAccessibleContext().setAccessibleName("Calculadora");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteCalculadora().setVisible(true);
            }
        });
    }

    private void botonSumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSumaActionPerformed
        try {
            op1 = Double.parseDouble(lineaTexto.getText());
            operacion = "SUMA";
            nueva = true;
        } catch (NumberFormatException err) {
            System.err.println("Error: No se puede convertir a número.");
        }
    }//GEN-LAST:event_botonSumaActionPerformed

    private void botonRestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRestaActionPerformed
        try {
            op1 = Double.parseDouble(lineaTexto.getText());
            operacion = "RESTA";
            nueva = true;
        } catch (NumberFormatException err) {
            System.err.println("Error: No se puede convertir a número.");
        }
    }//GEN-LAST:event_botonRestaActionPerformed

    private void botonMultiplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMultiplicacionActionPerformed
        try {
            op1 = Double.parseDouble(lineaTexto.getText());
            operacion = "MULTIPLICACION";
            nueva = true;
        } catch (NumberFormatException err) {
            System.err.println("Error: No se puede convertir a número.");
        }
    }//GEN-LAST:event_botonMultiplicacionActionPerformed

    private void botonDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDivicionActionPerformed
        try {
            op1 = Double.parseDouble(lineaTexto.getText());
            operacion = "DIVISION";
            nueva = true;
        } catch (NumberFormatException err) {
            System.err.println("Error: No se puede convertir a número.");
        }
    }//GEN-LAST:event_botonDivicionActionPerformed

    private void boton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton7ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "7");
    }//GEN-LAST:event_boton7ActionPerformed

    private void boton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton8ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "8");
    }//GEN-LAST:event_boton8ActionPerformed

    private void boton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton9ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "9");
    }//GEN-LAST:event_boton9ActionPerformed

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "4");
    }//GEN-LAST:event_boton4ActionPerformed

    private void boton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton5ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "5");
    }//GEN-LAST:event_boton5ActionPerformed

    private void boton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton6ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "6");
    }//GEN-LAST:event_boton6ActionPerformed

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "1");
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "2");
    }//GEN-LAST:event_boton2ActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "3");
    }//GEN-LAST:event_boton3ActionPerformed

    private void boton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton0ActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + "0");
    }//GEN-LAST:event_boton0ActionPerformed

    private void botonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPuntoActionPerformed
        if (nueva) {
            lineaTexto.setText("");
            nueva = false;
        }
        lineaTexto.setText(lineaTexto.getText() + ".");
    }//GEN-LAST:event_botonPuntoActionPerformed

    private void botonIgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIgualActionPerformed
        try {
            if (clienteSocket == null || clienteSocket.isClosed()) {
                // Reconectar al servidor si el socket está cerrado
                conectarAlServidor();
            }
            if (dos != null) {
                op2 = Double.parseDouble(lineaTexto.getText());

                // Enviar la operación al servidor
                enviarOperacionAlServidor(op1, op2, operacion);

                // Restablecer variables de operación
                operacion = "";
                op1 = op2 = 0;
                nueva = true;
            } else {
                System.out.println("Error: El flujo de salida (dos) es nulo.");
            }

        } catch (NumberFormatException err) {
            System.err.println("Error: No se puede convertir a número.");
        }
    }//GEN-LAST:event_botonIgualActionPerformed

    private void botonACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonACActionPerformed
        try {
            lineaTexto.setText("");
            op1 = op2 = 0;
            operacion = "";

            // Cerrar flujos al presionar AC
            cerrarFlujos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_botonACActionPerformed

    private void botonOFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOFFActionPerformed
        try {
            if (clienteSocket != null && !clienteSocket.isClosed()) {
                System.out.println("Cerrando el socket cliente");
                cerrarFlujos(); // Llamada al nuevo método para cerrar flujos
                clienteSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Terminado");
        System.exit(0);
    }//GEN-LAST:event_botonOFFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton0;
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton2;
    private javax.swing.JButton boton3;
    private javax.swing.JButton boton4;
    private javax.swing.JButton boton5;
    private javax.swing.JButton boton6;
    private javax.swing.JButton boton7;
    private javax.swing.JButton boton8;
    private javax.swing.JButton boton9;
    private javax.swing.JButton botonAC;
    private javax.swing.JButton botonDivision;
    private javax.swing.JButton botonIgual;
    private javax.swing.JButton botonMultiplicacion;
    private javax.swing.JButton botonOFF;
    private javax.swing.JButton botonPunto;
    private javax.swing.JButton botonResta;
    private javax.swing.JButton botonSuma;
    private javax.swing.JTextField lineaTexto;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    // Método para manejar el cierre de la ventana
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        botonOFFActionPerformed(null); // Simula el clic en OFF al cerrar la ventana
    }

    // Método para cerrar los flujos
    private void cerrarFlujos() {
        try {
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
            if (clienteSocket != null && !clienteSocket.isClosed()) {
                clienteSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
