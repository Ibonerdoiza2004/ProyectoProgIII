package Clases;

import java.awt.*;


import javax.swing.*;

public class VentanaAjustes extends JFrame {
	
	private JLabel lblMusica;
	private JSlider slMusica;
	private JLabel lblSonido;
	private JSlider slSonido;
	
	public VentanaAjustes() {
		
		lblMusica = new JLabel("MÚSICA");
		lblMusica.setForeground(Color.YELLOW);
		lblMusica.setHorizontalAlignment(JLabel.CENTER);
		
		
		lblMusica.setFont(new Font("Arial", Font.BOLD, 40));
		
		JPanel pnlSlider1 = new JPanel(new GridLayout(1,3)); 
		slMusica = new JSlider();
		slMusica.setValue(100);
		pnlSlider1.setOpaque(false);
		slMusica.setOpaque(false);
		
		lblSonido = new JLabel("SONIDO");
		lblSonido.setForeground(Color.YELLOW);
		lblSonido.setHorizontalAlignment(JLabel.CENTER);
		lblSonido.setFont(new Font("Arial", Font.BOLD, 40));
		
		JPanel pnlSlider2 = new JPanel(new GridLayout(1,3)); 
		slSonido = new JSlider();
		slSonido.setValue(100);
		pnlSlider2.setOpaque(false);
		slSonido.setOpaque(false);
		
		JPanel pnlAjustes = new JPanel(new GridLayout(7,1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoFondo = new ImageIcon(getClass().getResource("FondoAjustes.jpg"));
                Image imagenFondo = iconoFondo.getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        setContentPane(pnlAjustes);
        
        JPanel pnlCentral = new JPanel(new GridLayout(5,1));
        
        pnlAjustes.add(new JLabel(" "));
        pnlAjustes.add(lblMusica);
        pnlSlider1.add(new JLabel(" "));
        pnlSlider1.add(slMusica);
        pnlSlider1.add(new JLabel(" "));
        pnlAjustes.add(pnlSlider1);
        pnlAjustes.add(new JLabel(" "));
        pnlAjustes.add(lblSonido);
        pnlSlider2.add(new JLabel(" "));
        pnlSlider2.add(slSonido);
        pnlSlider2.add(new JLabel(" "));
        pnlAjustes.add(pnlSlider2);
        
        
        
    
        
		
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("AJUSTES");
		this.setVisible(true);
	
	}
}
