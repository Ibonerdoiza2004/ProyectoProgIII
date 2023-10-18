package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class VentanaPersonaje extends JFrame{
	
	private static Dimension TAMAYO_VENTANA = new Dimension(700,400);
	
	public static void main(String[] args) {
		
		VentanaPersonaje vent = new VentanaPersonaje();
		vent.setVisible( true  );
	}
	
	private JLabel lblPersonaje;
	private int front, back, left, right;
	
	public VentanaPersonaje() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("CLUEDO");
		setSize( TAMAYO_VENTANA );
		setLocationRelativeTo( null );
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.black);
		add(pnl);
		
		
		lblPersonaje = new JLabel();
		lblPersonaje.setIcon(new ImageIcon(getClass().getResource("front1.png")));
		//lblPersonaje.setSize(100,100);
		
		front = 0;
		left = 1;
		right = 1;
		back = 1;
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (front == 0) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("front2.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()+4);
						front ++;
					}
					else if (front == 1) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("front3.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()+4);
						front ++;
					}
					else {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("front1.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()+4);
						front = 0;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (back == 1) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("back2.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()-4);
						back ++;
					}
					else if (back == 2) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("back3.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()-4);
						back ++;
					}
					else {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("back1.png")));
						lblPersonaje.setLocation(lblPersonaje.getX(), lblPersonaje.getY()-4);
						back = 1;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (right == 1) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("right2.png")));
						lblPersonaje.setLocation(lblPersonaje.getX()+4, lblPersonaje.getY());
						right ++;
					}
					else if (right == 2) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("right3.png")));
						lblPersonaje.setLocation(lblPersonaje.getX()+4, lblPersonaje.getY());
						right ++;
					}
					else {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("right1.png")));
						lblPersonaje.setLocation(lblPersonaje.getX()+4, lblPersonaje.getY());
						right = 1;
					}
				}
				
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (left == 1) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("left2.png")));
						left ++;
						lblPersonaje.setLocation(lblPersonaje.getX()-4, lblPersonaje.getY());
					}
					else if(left == 2) {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("left3.png")));
						left ++;
						lblPersonaje.setLocation(lblPersonaje.getX()-4, lblPersonaje.getY());
					}
					else {
						lblPersonaje.setIcon(new ImageIcon(getClass().getResource("left1.png")));
						left = 1;
						lblPersonaje.setLocation(lblPersonaje.getX()-4, lblPersonaje.getY());
					}
				}
				
			}
		});
		
		pnl.add(lblPersonaje,BorderLayout.SOUTH);
		
		add(pnl);
		
	}
	
}
