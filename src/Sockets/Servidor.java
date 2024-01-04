package Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;
import java.net.ServerSocket;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class Servidor {

    private Robot robot;
    private List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        new Servidor().iniciarServidor();
    }

    public void iniciarServidor() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JButton btnEnviarPantalla = new JButton("Enviar Pantalla");
        btnEnviarPantalla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarPantallaATodos();
            }
        });
        frame.add(btnEnviarPantalla, BorderLayout.NORTH);

        frame.setVisible(true);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socketCliente = serverSocket.accept();
                PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
                clientes.add(escritor);

                // Crea un nuevo hilo para manejar la comunicación con el cliente
                ManejadorCliente manejador = new ManejadorCliente(socketCliente);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarPantallaATodos() {
        BufferedImage imagen = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagen, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] datosImagen = baos.toByteArray();

        for (PrintWriter cliente : clientes) {
            cliente.println("IMAGEN");
            cliente.println(datosImagen.length);
            cliente.flush();
            cliente.println(Base64.getEncoder().encodeToString(datosImagen));
            cliente.flush();
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket socket;
        private BufferedReader lector;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
            try {
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

                        // Mostrar la imagen en una ventana (puedes adaptar esto según tus necesidades)
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
    }
}
