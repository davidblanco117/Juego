package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Entrenamiento extends Habilidad {

	// private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;

	public Entrenamiento() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Entrenamiento.jpg"));
		this.setCosto(5);
		this.setTipo(Habilidad.POST_ATACAR);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		// this.rival = rival;
		this.jugador = jugador;

		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		jugador.getListaHabilidades().set(indice0, new HabilidadVacia());

		this.descripcionActivacion = "";

		jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Otorga la habilidad que tus puntos de ataque aumenten luego de cada ataque";

	}

	public String toString() {
		return "Entrenamiento";
	}

	public boolean activacionAutomatica() {
		this.descripcionActivacion = "";
		if (Math.random() < 0.5) {
			double factorDeincremento = Math.max(0.4, Math.random() * 1.5);
			jugador.setAtaque(jugador.getAtaque() + factorDeincremento);
		}
		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}