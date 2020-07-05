package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;
import Juego.Personajes.Pistolero;

public class LibroVida extends Habilidad {

	public LibroVida() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/LibroVida.jpg"));
		this.setCosto(7);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		if (jugador.getClass() != Pistolero.class)
			return false;
		int indice = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice++;
		}
		((Pistolero) jugador).setModoZombie(false);
		jugador.getListaHabilidades().set(indice, new HabilidadVacia());
		((Pistolero) jugador).cambiarModo(((Pistolero) jugador).getModoNormal());
		jugador.setSalud(Math.min(30, jugador.getSaludMaxima()));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	public String toString() {
		return "Libro de la vida";
	}

	@Override
	public String getDescripcionUso() {

		return "Permite que un zombie resucite";
	}

}