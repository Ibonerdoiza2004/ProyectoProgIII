package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BaseDeDatos.ConexionSQlite;
import BaseDeDatos.MainBD;

public class VentanaDerrota extends JPanel{
	
	protected JButton btnContinuar;
	protected ConexionSQlite conSQL;
	protected Connection conn;
	protected Statement statement;
	protected ResultSet rs;
	
	public VentanaDerrota() {
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setPreferredSize(new Dimension(220,80));
		btnContinuar.setSize(new Dimension(220,80));
		JLabel l = new JLabel(Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase()+" HA PERDIDO");
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Serif", Font.BOLD, 60));
		l.setBounds((int)(Gestion.sizePantalla.getWidth()/2-400),20,800,80);
		ArrayList<Asesinato>cartasPerdedor = new ArrayList<>(Gestion.jugadores.get(Gestion.getNumTurno()).cartas);
		Gestion.jugadores.remove(Gestion.getNumTurno());
		Gestion.repartirCartas(cartasPerdedor);
		Gestion.setNumTurno(Gestion.getNumTurno()%Gestion.jugadores.size());
		

        this.add(l);
        this.add(btnContinuar);
        this.setLayout(null);
        btnContinuar.setBounds((int)(Gestion.sizePantalla.getWidth()/2-200),(int)(Gestion.sizePantalla.getHeight()/2-55),400,150);
		
		btnContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setEnabled(false);
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						boolean quedaJugador = false;
						for(Jugador j:Gestion.jugadores) {
							if(!j.npc) {
								quedaJugador = true;
							}
						}
						if(Gestion.jugadores.size()==1) {
							new VentanaVictoria();
							eliminarPanel();
						}else {
							if(quedaJugador) {
								int numeroJugador=Gestion.getNumTurno();
								while(Gestion.jugadores.get(numeroJugador).npc&&(numeroJugador+1)%Gestion.jugadores.size()!=Gestion.getNumTurno()) {
									numeroJugador = (numeroJugador+1)%Gestion.jugadores.size();
								}
								if(Gestion.jugadores.get((numeroJugador+1)%Gestion.jugadores.size())!=Gestion.jugadores.get(Gestion.getNumTurno())) {
									Gestion.siguientePanel=VentanaCartasInicio.class;
									new VentanaTexto("TURNO DE "+Gestion.jugadores.get(numeroJugador).getPersonaje().getNombre().toString().toUpperCase(),numeroJugador);
									eliminarPanel();
									VentanaCartasInicio v = new VentanaCartasInicio(numeroJugador);
									v.setVisible(false);
									String lockAnyadirALaVentana = "AnyadirALaVentana";
									synchronized (lockAnyadirALaVentana) {
										try {
											lockAnyadirALaVentana.wait();
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									v.setVisible(true);
								}else {
									Gestion.siguientePanel=VentanaTablero.class;
									new VentanaTexto("TURNO DE "+Gestion.jugadores.get(Gestion.getNumTurno()).getPersonaje().getNombre().toString().toUpperCase(),Gestion.getNumTurno());
									eliminarPanel();
									VentanaTablero v = new VentanaTablero();
								}
							}else {
								
								//________________________
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
									String upGanador = "UPDATE partida SET GANADOR = ' NO HAY GANADOR ' WHERE ID_PARTIDA = '" + idPartida +"'";
									statement.executeUpdate(upGanador);
								} catch (SQLException e) {
									e.printStackTrace();
								}
								//________________________
								
								Gestion.siguientePanel=VentanaInicio.class;
								new VentanaTexto("Solo quedan npcs",0);
								eliminarPanel();
								String lockSiguienteVentana = "AnyadirALaVentana";
								synchronized (lockSiguienteVentana) {
									try {
										lockSiguienteVentana.wait();
									} catch (InterruptedException ex) {
										ex.printStackTrace();
									}
								}
								Gestion.ventanaJuego.add(Gestion.vInicio);
								Gestion.ventanaJuego.repaint();
								
							}
						}
					}
				});
				t.start();
			}
		});
		setBounds(0, 0, (int)Gestion.sizePantalla.getWidth(), (int)Gestion.sizePantalla.getHeight());
		Gestion.ventanaJuego.add(this);
	}
	public void eliminarPanel() {
		Gestion.ventanaJuego.remove(this);
        Gestion.ventanaJuego.repaint();
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon iconoFondo = new ImageIcon(getClass().getResource("VentanaDerrotaFondo.jpeg"));
        Image imagenFondo = iconoFondo.getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
	
	

}
