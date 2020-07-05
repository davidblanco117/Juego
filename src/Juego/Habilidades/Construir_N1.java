package Juego.Habilidades;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Construir_N1 extends Construir {
	
	
	//private ArrayList<Habilidad> habilidades= new ArrayList<>();
	public Construir_N1() {
		setHabilidades(new ArrayList<Habilidad>());
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Yunque1.jpg"));
		this.setCosto(3);
		getHabilidades().add(new GranadaDeHumo());
		getHabilidades().add(new Suministros());
		getHabilidades().add(new Grillete());
		setPosicion(3);
		this.setNombreClase("Construir Nivel 1");
		this.setDescripcionUso("Construye un artefacto de rareza 1");
		
	}

	
}
