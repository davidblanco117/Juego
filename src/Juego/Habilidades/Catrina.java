package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Catrina extends Habilidad {

	// private Personaje rival;
	// private Personaje jugador;
	private String descripcionActivacion;

	public Catrina() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Catrina.jpg"));
		this.setCosto(9);
		// this.setTipo(Habilidad.FINALIZAR_TURNO);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		// this.rival=rival;

		// this.jugador = jugador;

		this.descripcionActivacion = "";
		int indice = 0;
		for (Habilidad h : rival.getHabilidadesActivadas()) {
			if (h.getClass() == SugarSkull.class) {
				SugarSkull sk = new SugarSkull();
				sk.setJugador(jugador);
				jugador.getHabilidadesActivadas().add(sk);
				break;
			}
			indice++;
		}
		if (indice == rival.getHabilidadesActivadas().size()){
			this.setDescripcionNoActivacion("El rival debe estar recibiendo los beneficios de \"Calavera de azucar\" para activar esta habilidad");
			return false;
		}
		rival.getHabilidadesActivadas().remove(indice);
		jugador.getHabilidadesActivadas().add(this);

		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		jugador.getListaHabilidades().set(indice0, new HabilidadVacia());
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Roba los efectos positivos de la Calabera de Azucar del rival";

	}

	public String toString() {
		return "Catrina";
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}