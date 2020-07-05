package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class SugarSkull extends Habilidad {

	private Personaje jugador;
	private String descripcionActivacion;

	public SugarSkull() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/SugarSkull.jpg"));
		this.setCosto(3);
		this.setTipo(Habilidad.FINALIZAR_TURNO);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

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
		SugarSkull sk = new SugarSkull();
		sk.jugador = rival;

		jugador.getHabilidadesActivadas().add(this);
		rival.getHabilidadesActivadas().add(sk);
		jugador.setPuntosHabilidad(Math.max(jugador.getPuntosHabilidad() - this.getCosto(), 0));
		return true;
	}

	public void setJugador(Personaje p) {
		this.jugador = p;
	}

	@Override
	public String getDescripcionUso() {

		return "Hace que al final del turno de cada jugador se incremente una caracteristica aleatoria";

	}

	public String toString() {
		return "Calavera de azucar";
	}

	public boolean activacionAutomatica() {
		int aux = (int) (Math.random() * 100) % 5;
		double aux2;
		switch (aux) {
		case 0:
			aux2 = (Math.max(1, Math.random() * 3));
			jugador.setAtaque(jugador.getAtaque() + aux2);
			break;
		case 1:
			aux2 = (Math.random() * 0.05);
			jugador.setVelocidad(jugador.getVelocidad() + aux2);
			break;
		case 2:
			aux2 = (Math.random() * 0.05);
			jugador.setDefensa(jugador.getDefensa() + aux2);
			break;
		case 3:
			int aux3 = 1;
			jugador.setPuntosHabilidad(jugador.getPuntosHabilidad() + aux3);
			break;
		case 4:
			aux2 = Math.max(10, Math.random() * 20);
			jugador.setSalud(Math.min(jugador.getSalud() + aux2, jugador.getSaludMaxima()));
			break;
		default:
			break;
		}

		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}
