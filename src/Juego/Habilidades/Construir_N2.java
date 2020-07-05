package Juego.Habilidades;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Construir_N2 extends Construir {
	
	
	public Construir_N2() {
		setHabilidades(new ArrayList<Habilidad>());
		getHabilidades().add(new HabilidadVacia());
		getHabilidades().add(new HabilidadVacia());
		getHabilidades().add(new HabilidadVacia());
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Yunque2.jpg"));
		this.setCosto(6);
		setPosicion(4);
		this.setNombreClase("Construir Nivel 2");
		this.setDescripcionUso("Construye un artefacto de rareza 2");
		
	}

	
}
