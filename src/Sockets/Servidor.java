package Sockets;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends JFrame{
	
	private JPanel pnlPrincipal;
	private JTextArea txtArea;
	
	public Servidor() {
		
		setSize(250, 280);
		setTitle("Servidor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		setLayout(new BorderLayout());
		
		pnlPrincipal = new JPanel();
		
		txtArea = new JTextArea();
		pnlPrincipal.add(txtArea, BorderLayout.CENTER);
		
		add(pnlPrincipal);
		
		Thread miHilo = new Thread() {

			@Override
			public void run() {
				try {
					ServerSocket servidor = new ServerSocket(9999);
					
					Socket miSocket = servidor.accept();
					DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
					
					String mensaje = flujoEntrada.readUTF();
					
					txtArea.append("\n" + mensaje);
					
					miSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
	}
	
	public static void main(String[] args) {
		Servidor s = new Servidor();
		s.setVisible( true );
	}

}
