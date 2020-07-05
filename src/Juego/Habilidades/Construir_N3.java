package Juego.Habilidades;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Construir_N3 extends Construir {
	
	
	public Construir_N3() {
		setHabilidades(new ArrayList<Habilidad>());
		getHabilidades().add(new HabilidadVacia());
		getHabilidades().add(new HabilidadVacia());
		getHabilidades().add(new Bazuca());
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Yunque3.jpg"));
		this.setCosto(9);
		setPosicion(5);
		this.setNombreClase("Construir Nivel 3");
		this.setDescripcionUso("Construye un artefacto de rareza 3");
		
	}

	
}
