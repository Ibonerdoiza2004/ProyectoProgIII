package Clases;

import java.awt.Image;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

import javax.swing.ImageIcon;
enum Implicados{ARMA, PERSONA, LUGAR}
public class Contenedor implements Serializable{
	protected ArrayList<Arma>armas = new ArrayList<>();
	protected ArrayList<Sospechoso>sospechosos = new ArrayList<>();
	protected ArrayList<Lugar>lugares = new ArrayList<>();
	protected HashMap<Implicados, Asesinato> implicados = new HashMap<>();
	protected ArrayList<Asesinato>todasLasCartas = new ArrayList<>();
	
	public Contenedor(ArrayList<Arma> armas, ArrayList<Sospechoso> sospechosos, ArrayList<Lugar> lugares,
			HashMap<Implicados, Asesinato> implicados, ArrayList<Asesinato> todasLasCartas) {
		super();
		this.armas = armas;
		this.sospechosos = sospechosos;
		this.lugares = lugares;
		this.implicados = implicados;
		this.todasLasCartas = todasLasCartas;
	}
	
	public Contenedor() {
		super();
		this.armas = new ArrayList<Arma>();
		for(Armas arma: Armas.values()) {
			//Añadir la foto
			URL armaURL = getClass().getResource("../cartasArmas/"+arma+".png");
			ImageIcon iArma = new ImageIcon(armaURL);
			armas.add(new Arma(arma, false, iArma));
		}
		
		
		this.sospechosos = new ArrayList<Sospechoso>();
		for(Sospechosos sospechoso: Sospechosos.values()) {
			//Añadir la foto
			URL sospechosoURL = getClass().getResource("../cartasSospechosos/"+sospechoso+".png");
			ImageIcon isospechoso = new ImageIcon(sospechosoURL);
			sospechosos.add(new Sospechoso(sospechoso, false, isospechoso));
		}
		
		this.lugares = new ArrayList<Lugar>();
		for(Sitio sitio:Sitio.values()) {
			//Añadir la foto
//			URL sitioURL = getClass().getResource("../cartasLugares/"+sitio+".png");
//			ImageIcon isitio = new ImageIcon(sitioURL);
			lugares.add(new Lugar(sitio, false, null));
		}
		
		
		this.implicados = new HashMap<Implicados, Asesinato>();
		Arma arma = armas.get((int)(Math.random()*armas.size()));
		arma.setImplicado(true);
		implicados.put(Implicados.ARMA, arma);
		Sospechoso sospechoso = sospechosos.get((int)(Math.random()*sospechosos.size()));
		sospechoso.setImplicado(true);
		implicados.put(Implicados.PERSONA, sospechoso);
		Lugar lugar = lugares.get((int)(Math.random()*lugares.size()));
		lugar.setImplicado(true);
		implicados.put(Implicados.LUGAR, lugar);

		for (Sospechoso sospechoso1:sospechosos) {
			todasLasCartas.add(sospechoso1);
		}
		for (Arma arma1:armas) {
			todasLasCartas.add(arma1);
		}
		for (Lugar lugar1:lugares) {
			todasLasCartas.add(lugar1);
		}
	}
	public static void main(String[]args) {
		Contenedor c1 = new Contenedor();
		System.out.println(c1.armas);
		System.out.println(c1.sospechosos);
		System.out.println(c1.lugares);
		System.out.println(c1.implicados);
		System.out.println(c1.todasLasCartas);
	}

}
