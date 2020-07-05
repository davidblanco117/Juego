package Juego;

import Juego.Personajes.Personaje;
import UI.Ventana;

public class Evento {
	private int turnoDeEjecucion;
	private Programable acciones;
	private Personaje personaje;

	public Evento(Programable e, int turnos,Personaje p) {
		this.turnoDeEjecucion = Ventana.turno + turnos;
		this.acciones = e;
		this.personaje=p;
	}

	public boolean ejecutar(Personaje p) {
		if (turnoDeEjecucion == Ventana.turno && this.personaje==p) {
			acciones.ejecutar();
			return true;
		}
		return false;

	}

	public int getTurnoDeEjecucion() {
		return turnoDeEjecucion;
	}

	public void setTurnoDeEjecucion(int turnoDeEjecucion) {
		this.turnoDeEjecucion = turnoDeEjecucion;
	}

	public Programable getAcciones() {
		return acciones;
	}

	public void setAcciones(Programable acciones) {
		this.acciones = acciones;
	}

}
