package Clases;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

	public class Musica extends JFrame{
	

	  // Crear un objeto Player con el archivo mp3
	  Player player = new Player (new FileInputStream ("src/Clases/musicaproyecto.mp3"));



	  // Crear un constructor para la clase
	  public Musica () throws JavaLayerException, FileNotFoundException {
	    // Añadir el listener a la ventana
	    this.addWindowListener (new WindowAdapter() {
	    	 public void windowOpened (WindowEvent e) {
	    		    // Reproducir el mp3 cuando la ventana se abre
	    		   
	    		    Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								player.play ();
							} catch (JavaLayerException e) {
								e.printStackTrace();
							}
								
						}
					});
	    		    t.start();
	    		  }
	    });
	    this.addWindowListener (new WindowAdapter() {
	    	public void windowClosing (WindowEvent e) {
	    	    // Detener el mp3 cuando la ventana se cierra
	    	    player.close ();
	    	  }
	    });

	    // Configurar las propiedades de la ventana
	    this.setSize (300, 200);
	    this.setTitle ("Reproductor de mp3");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setVisible (true);
	  }
	public static void main(String[] args) {
		try {
			new Musica();
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
