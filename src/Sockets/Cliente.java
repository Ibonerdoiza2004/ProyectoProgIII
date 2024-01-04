package Sockets;

import java.io.*;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Cliente {

    public static void main(String[] args) {
        new Cliente().iniciarCliente();
    }

    public void iniciarCliente() {
        JFrame frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton btnRecibirPantalla = new JButton("Recibir Pantalla");
        btnRecibirPantalla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarPantalla();
            }
        });
        frame.add(btnRecibirPantalla, BorderLayout.NORTH);

        frame.setVisible(true);

        try {
            Socket socket = new Socket("localhost", 5000); //Cambiar el string "localhost" a la IP del servidor
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String mensaje;
                        while ((mensaje = lector.readLine()) != null) {
                            if (mensaje.equals("IMAGEN")) {
                                int longitud = Integer.parseInt(lector.readLine());
                                String datosImagenBase64 = lector.readLine();
                                byte[] datosImagen = Base64.getDecoder().decode(datosImagenBase64);

                                ByteArrayInputStream bais = new ByteArrayInputStream(datosImagen);
                                BufferedImage imagen = ImageIO.read(bais);

                                // Mostrar la captura de pantalla en una ventana
                                JFrame ventanaImagen = new JFrame("Imagen del Servidor");
                                ventanaImagen.setSize(800, 600);
                                ventanaImagen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                ventanaImagen.add(new JLabel(new ImageIcon(imagen)));
                                ventanaImagen.setVisible(true);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solicitarPantalla() {
    }
}