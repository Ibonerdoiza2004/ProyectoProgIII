package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class VentanaRegistrarUsuario extends JFrame{

	
	//private GestorUsuarios gestor;
	
	private JTextField tfUsuario;
	private JPasswordField tfContrasenya;
	private JButton botonRegistrarse;
	private JButton botonIniciarSesion;
	
	public VentanaRegistrarUsuario() {
		
		setTitle("Registro/Inicio sesión");
		setSize( 400, 150 );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLayout( new BorderLayout(10, 10) );
		
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel( new FlowLayout( FlowLayout.LEFT ));
		JPanel panel2 = new JPanel( new FlowLayout( FlowLayout.LEFT ));
		tfUsuario = new JTextField( 20 );
		tfContrasenya = new JPasswordField( 15 );
		
		panel1.add(new JLabel("Nick: "));
		panel1.add(tfUsuario);
		panelCampos.add( panel1 );
		panel2.add(new JLabel("Contraseña: "));
		panel2.add(tfContrasenya);
		panelCampos.add( panel2 );
		
		JPanel panelBotones = new JPanel();
		botonRegistrarse = new JButton("Registrarse");
		botonIniciarSesion = new JButton("Iniciar Sesión");
		panelBotones.add(botonRegistrarse);
		panelBotones.add(botonIniciarSesion);
		
		add(panelCampos, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		getRootPane().setDefaultButton( botonIniciarSesion );
		
//		botonRegistrarse.addActionListener(new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	gestor.logout();
//		        gestor.registrarUsuario( tfUsuario.getText(), new String(tfContrasenya.getPassword()) );
//		        gestor.procesoPostLogin();
//		    }
//		});
		
//		botonIniciarSesion.addActionListener(new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		    	gestor.logout();
//		        gestor.loginUsuario( tfUsuario.getText(), new String(tfContrasenya.getPassword()) );
//		        gestor.procesoPostLogin();
//		    }
//		});
		
//		addWindowListener( new WindowAdapter() {
//			@Override
//			public void windowClosed(WindowEvent e) {
//				gestor.cierraVentanas();
//			}
//		});
	}  
	
	public static void main(String[] args) {
		VentanaRegistrarUsuario vr = new VentanaRegistrarUsuario();
		vr.setVisible(true);
	}
	
}
