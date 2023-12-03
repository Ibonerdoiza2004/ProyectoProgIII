package Sockets;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cliente extends JFrame{
	
	private JTextField txtEnviar;
	private JButton btnEnviar;
	private JPanel pnlPrincipal;
	
	public Cliente() {
		
		setSize(250, 280);
		setTitle("Cliente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		setLayout(new BorderLayout());
		
		pnlPrincipal = new JPanel();
		
		txtEnviar = new JTextField(20);
		pnlPrincipal.add(txtEnviar, BorderLayout.NORTH);
		
		btnEnviar = new JButton("Enviar");
		pnlPrincipal.add(btnEnviar, BorderLayout.CENTER);
		
		add(pnlPrincipal);
		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket misocket = new Socket("192.168.1.101", 9999);
					
					//Contruir flujo de datos de salida
					DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
					
					flujo_salida.writeUTF(txtEnviar.getText());
					flujo_salida.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setVisible( true );
	}
}
