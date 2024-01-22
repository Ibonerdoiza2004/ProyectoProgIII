package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import BaseDeDatos.ConexionSQlite;
import BaseDeDatos.MainBD;

public class VentanaVictoria extends JPanel{

	protected JButton btnVolverAlMenu;
	protected ConexionSQlite conSQL;
	protected Connection conn;
	protected Statement statement;
	protected ResultSet rs;
	
	public VentanaVictoria() {
		
		
		btnVolverAlMenu = new JButton("Volver al menú");
		btnVolverAlMenu.setPreferredSize(new Dimension(220,80));
		btnVolverAlMenu.setSize(new Dimension(220,80));
		JLabel l = new JLabel(Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase()+" HA GANADO");
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Serif", Font.BOLD, 60));
		l.setBounds((int)(Gestion.sizePantalla.getWidth()/2-400),20,800,80);
		add(l);
      
        this.add(btnVolverAlMenu);
        this.setLayout(null);
        btnVolverAlMenu.setLocation(670,780);
	  
		btnVolverAlMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVolverAlMenu.setEnabled(false);
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						Gestion.ventanaJuego.add(Gestion.vInicio);
						Gestion.ventanaJuego.repaint();
						eliminarPanel();
					}
				});
				t.start();
			}
		});
		setBounds(0,0,(int)Gestion.sizePantalla.getWidth(),(int)Gestion.sizePantalla.getHeight());
		Gestion.ventanaJuego.add(this);
		
		
		
		conSQL = new ConexionSQlite();
		conn = MainBD.conn;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			String sent = "SELECT * FROM jugador WHERE NUM_PARTIDAS_JUGADAS = 0";
			try {
				rs = statement.executeQuery(sent);
				while (rs.next()) {
					String idActual = rs.getString(1);
					int numPartidas = rs.getInt(4);
					numPartidas = numPartidas + 1;
					String updateSent = "UPDATE jugador SET NUM_PARTIDAS_JUGADAS = " + numPartidas + " WHERE ID_JUGADOR = '" + idActual + "'";
					statement.executeUpdate(updateSent);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		String actPartida = "SELECT * FROM partida WHERE DURACION = 0";
		try {
			rs = statement.executeQuery(actPartida);
			String idPartida = rs.getString(1);
			String upPartida = "UPDATE partida SET DURACION = 1 WHERE ID_PARTIDA = '" + idPartida + "'";
			statement.executeUpdate(upPartida);
			String upGanador = "UPDATE partida SET GANADOR = '" + Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString() + "' WHERE ID_PARTIDA = '" + idPartida +"'";
			statement.executeUpdate(upGanador);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaVictoriaFondo.jpeg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	
	public static void main(String[] args) {
		new VentanaVictoria();
	}

}
