package Juego.Personajes;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Juego.Habilidades.Catrina;
import Juego.Habilidades.Habilidad;
import Juego.Habilidades.HabilidadVacia;
import Juego.Habilidades.LibroVida;
import Juego.Habilidades.SugarSkull;
import Juego.Habilidades.TrioDeEsqueletos;

public class PistoleroZombie extends Personaje{

	private String clase = "Pist. Zombie";
	private int estado=2;

	public PistoleroZombie() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/PistoleroZombie.jpg"));
		this.setSalud(80);
		this.setAtaque(15);
		this.setVelocidad(0.1);
		this.clase = "Pist. Zombie";
		setRaza("NoHumano");
		setHabilidades(new ArrayList<Habilidad>());
		getHabilidades().add(new LibroVida());
		getHabilidades().add(new TrioDeEsqueletos());
		getHabilidades().add(new SugarSkull());
		getHabilidades().add(new Catrina());
		setSaludMaxima(80);
		setDefensa(0.25);
		setVelocidad(0.2);
		setEstaVivo(true);
		// inicializarValoresOriginales();
		int aux = this.getCantHabilidades();
		for (int i = aux; i < 6; i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/PistoleroZombie.jpg"));
		this.setDescripcionPersonaje("Dicen que sin carne ni piel se mejora la punteria");

	}

	public int getEstado() {
		return this.estado;
	}
	
	@Override
	public String toString() {
		return clase;
	}

}