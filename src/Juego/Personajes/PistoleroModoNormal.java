package Juego.Personajes;


import java.util.ArrayList;

import javax.swing.ImageIcon;

import Juego.Habilidades.Escopetazo;
import Juego.Habilidades.Habilidad;
import Juego.Habilidades.HabilidadVacia;
import Juego.Habilidades.Rafaga;
import Juego.Habilidades.Tesoro;
import Juego.Habilidades.Whisky;

public class PistoleroModoNormal extends Personaje {

	private String clase = "Pistolero";
	private int estado=0;

	public PistoleroModoNormal() {
		setAtaque(12);
		setSalud(80);
		setSaludMaxima(80);
		setDefensa(0.25);
		setVelocidad(0.2);
		setEstaVivo(true);
		setRaza("Humano");
		inicializarValoresOriginales();
		setHabilidades(new ArrayList<Habilidad>());
		getHabilidades().add(new Escopetazo());
		getHabilidades().add(new Rafaga());
		getHabilidades().add(new Whisky());
		getHabilidades().add(new Tesoro());
		int aux = this.getCantHabilidades();
		for (int i = aux; i < 6; i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Pistolero.jpg"));
		this.setDescripcionPersonaje("Cuando el pistolero muere se transforma en un zombie con habilidades distintas");

	}


	public int getEstado() {
		return this.estado;
	}
	
	@Override
	public String toString() {
		return clase;
	}

}
