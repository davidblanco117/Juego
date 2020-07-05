package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;

public class ReclamarTesoro extends Habilidad {

	private String descripcionActivacion;
	private double puntosSalud=0;
	private int puntosHabilidad=0;

	public ReclamarTesoro() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/ReclamarTesoro.jpg"));
		this.setCosto(0);
		this.setTipo(Habilidad.PRE_ACTIVA_HAB);
		
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		jugador.getListaHabilidades().set(indice0, new Tesoro());
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()+this.puntosHabilidad>10)?10:jugador.getPuntosHabilidad()+this.puntosHabilidad);
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Reclama tus "+ puntosSalud + " puntos de salud y " + puntosHabilidad + " acumulados";

	}

	public String toString() {
		return "ReclamarTesoro";
	}


	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

	public boolean activacionAutomatica() {
		puntosSalud+=Math.random()*3;
		puntosHabilidad++;
		return true;
	}

}