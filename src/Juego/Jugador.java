package Juego;

import Juego.Personajes.Personaje;

public class Jugador {
	Personaje personaje;

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Jugador(Personaje personaje) {
		this.personaje = personaje;
	}

}
