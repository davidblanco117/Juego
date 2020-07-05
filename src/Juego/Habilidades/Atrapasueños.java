package Juego.Habilidades;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Atrapasueños extends Habilidad {

	public Atrapasueños() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Atrapasueños.jpg"));
		this.setCosto(3);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		int cantHabilidadesRival = rival.getCantHabilidades();

		if (cantHabilidadesRival == 0) {
			this.setDescripcionNoActivacion("El oponente no posee habilidades a las que aplicar " + this.toString());
			return false;
		}

		ArrayList<Integer> valoresPosibles = new ArrayList<>();
		int indice = 0;
		for (Habilidad hab : rival.getListaHabilidades()) {
			if (!hab.toString().equals(new HabilidadVacia().toString())) {
				valoresPosibles.add(indice);
			}
			indice++;
		}
		int auxIndice = (int) (Math.random() * 100) % valoresPosibles.size();
		int aux = valoresPosibles.get(auxIndice);

		Habilidad h = rival.getListaHabilidades().get(aux);
		h.setCosto(Math.min(h.getCosto() + 1, 10));

		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad() - this.getCosto() < 0) ? 0
				: jugador.getPuntosHabilidad() - this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Incrementa en 1 el costo de una habilidad al azar del rival";
	}

	public String toString() {
		return "Atrapasueños";
	}

}
