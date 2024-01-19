package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class VentanaRegistrarUsuario extends JFrame {

    private JTextField tfUsuario;
    private JPasswordField tfContrasenya;
    private JButton botonRegistrarse;
    private JButton botonIniciarSesion;

    public VentanaRegistrarUsuario() {

        setTitle("Registro/Inicio Sesión");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfUsuario = new JTextField(20);
        tfContrasenya = new JPasswordField(15);

        Font labelFont = UIManager.getFont("Label.font");
        panel1.add(new JLabel("Nick: "));
        panel1.add(tfUsuario);
        panelCampos.add(panel1);
        panel2.add(new JLabel("Contraseña: "));
        panel2.add(tfContrasenya);
        panelCampos.add(panel2);

        // Cambios de estilo en los botones
        JPanel panelBotones = new JPanel();
        botonRegistrarse = new JButton("Registrarse");
        botonIniciarSesion = new JButton("Iniciar Sesión");
        botonRegistrarse.setBackground(new Color(50, 205, 50)); // Verde
        botonIniciarSesion.setBackground(new Color(255, 165, 0)); // Naranja
        panelBotones.add(botonRegistrarse);
        panelBotones.add(botonIniciarSesion);

        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Cambios de estilo para los tfs
        tfUsuario.setFont(labelFont.deriveFont(Font.PLAIN, 14));
        tfContrasenya.setFont(labelFont.deriveFont(Font.PLAIN, 14));

        // Cambios en el color de fondo
        panelCampos.setBackground(new Color(240, 248, 255)); // Azul claro
        panelBotones.setBackground(new Color(173, 216, 230)); // Azul claro

        // Color de fondo del JFrame
        getContentPane().setBackground(new Color(240, 248, 255));

        getRootPane().setDefaultButton(botonIniciarSesion);
        
        
        botonIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Lógica para iniciar sesión con usurio (hacer desde MainBD)
				
				//Lógica de ventanas
				dispose(); //ver si el usuario está en la BD
			}
		});
        
        botonRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }

    public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(JTextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public JPasswordField getTfContrasenya() {
		return tfContrasenya;
	}

	public void setTfContrasenya(JPasswordField tfContrasenya) {
		this.tfContrasenya = tfContrasenya;
	}

	public JButton getBotonRegistrarse() {
		return botonRegistrarse;
	}

	public void setBotonRegistrarse(JButton botonRegistrarse) {
		this.botonRegistrarse = botonRegistrarse;
	}

	public JButton getBotonIniciarSesion() {
		return botonIniciarSesion;
	}

	public void setBotonIniciarSesion(JButton botonIniciarSesion) {
		this.botonIniciarSesion = botonIniciarSesion;
	}


	public static void main(String[] args) {
        VentanaRegistrarUsuario vr = new VentanaRegistrarUsuario();
        vr.setVisible(true);
    }
}