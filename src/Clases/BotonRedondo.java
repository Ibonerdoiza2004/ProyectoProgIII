package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.*;

public class BotonRedondo extends JButton {
	
	private JLabel lblLogo;

    public BotonRedondo(String texto) {
        super(texto);

        // Establecer tamaño fijo para que el botón sea redondo
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        // Hacer el botón transparente
        setContentAreaFilled(false);

        // Cambiar el color del texto
        setForeground(Color.WHITE);

        // Cambiar el color de fondo del botón
        setBackground(new Color(50, 205, 50)); // Puedes ajustar el color a tu gusto
        
        //______________________
    	lblLogo = new JLabel();
    	ImageIcon iconoLogo = new ImageIcon(getClass().getResource("Registro.png"));
    	Image imagenLogo = iconoLogo.getImage();
    	Image tamanoLogo = imagenLogo.getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH);
    	iconoLogo = new ImageIcon(tamanoLogo);
    	lblLogo.setIcon(iconoLogo);
        //______________________
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(255, 165, 0)); // Cambia el color cuando se presiona el botón
        } else {
            g.setColor(getBackground());
        }

        // Dibujar un círculo como fondo del botón
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        // Llamar al método paintComponent de la clase base para dibujar el contenido del botón
        super.paintComponent(g);
    }

    // Dibujar un borde redondeado alrededor del botón
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hacer el botón completamente transparente
    public boolean isOpaque() {
        return false;
    }

    public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public static void main(String[] args) {
		
//        BotonRedondo boton = new BotonRedondo("Haz clic");
//        JFrame jr = new JFrame();
//        JPanel p = new JPanel();
//        p.add(boton);
//        p.add(new JLabel(new ImageIcon()));
//        p.add(boton.getLblLogo());
//        jr.add(p, BorderLayout.NORTH);
//        jr.setBounds(300, 500, 600, 600);
//        jr.setVisible(true);
//        jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // Ejemplo de uso
	    BotonRedondo boton = new BotonRedondo("Registrar");
	    JFrame jr = new JFrame();
	    JPanel p = new JPanel();

	    ImageIcon iconoLogo = new ImageIcon(BotonRedondo.class.getResource("Registro.png"));
	    boton.getLblLogo().setIcon(iconoLogo);

	    p.add(boton);
	    p.add(boton.getLblLogo());
	    jr.add(p, BorderLayout.NORTH);
	    jr.setBounds(300, 500, 600, 600);
	    jr.setVisible(true);
	    jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
}
