package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;

public class Whisky extends Habilidad implements Programable {

	private double probFallar = 0.3333;
	private Personaje jugador;
	private String descripcionActivacion;
	private String descripcionEjecucion = "";
	private String nombreJugador = "";
	private int cantTurnos = 0;
	private double ataqueOriginal;
	private int indiceHabil;

	public Whisky() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Whisky.jpg"));
		this.setCosto(3);
		this.cantTurnos = 3;
		this.setTipo(Habilidad.PRE_ATACAR);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		this.jugador = jugador;
		nombreJugador = jugador.toString();
		this.descripcionActivacion = "Los efectos del Whisky han desaparecidos\n";
		this.descripcionEjecucion = jugador.toString() + " ha errado su disparo\n";
		this.ataqueOriginal = jugador.getAtaque();
		jugador.setAtaque(jugador.getAtaque() * 2);
		jugador.getHabilidadesActivadas().add(this);
		ManejadorEventosTurnos.eventos.add(new Evento(this, this.cantTurnos, jugador));

		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		indiceHabil = indice0;
		jugador.getListaHabilidades().set(indice0, new HabilidadVacia());
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Duplica tu ataque actual, pero tienes 1/3 de probabilidades de fallar el ataque";

	}

	public String toString() {
		return "Whisky";
	}

	@Override
	public void ejecutar() {
		if (nombreJugador.equals(jugador.toString())) {
			jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
			jugador.setAtaque(Math.max(0, (jugador.getAtaque() - ataqueOriginal)));
			jugador.getListaHabilidades().set(indiceHabil, this);
		}
		int indice = jugador.getHabilidadesActivadas().indexOf(this);
		if (indice >= 0)
			jugador.getHabilidadesActivadas().remove(indice);

	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

	public boolean activacionAutomatica() {
		if (Math.random() < probFallar) {
			jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionEjecucion));
			return false;
		}
		return true;
	}

}