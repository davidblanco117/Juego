package Juego.Habilidades;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Pacto extends Habilidad {

	private int factorDesacrificio = 4;

	public Pacto() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Pacto.jpg"));
		this.setCosto(5);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		int cantHabilidades = jugador.getCantHabilidades();
		int cantHabilidadesRival = rival.getCantHabilidades();

		if (cantHabilidades == 6) {
			this.setDescripcionNoActivacion("No tienes mas espacio disponible para robar una habilidad");
			return false;
		}
		if (cantHabilidadesRival == 0) {
			this.setDescripcionNoActivacion("El oponente no posee habilidades que puedas robar");
			return false;
		}

		jugador.setSalud(jugador.getSalud() - (jugador.getSalud() / factorDesacrificio));

		ArrayList<Integer> valoresPosibles = new ArrayList<>();
		int indice = 0;
		for (Habilidad hab : rival.getListaHabilidades()) {
			if (!hab.toString().equals(new HabilidadVacia().toString()))
				valoresPosibles.add(indice);
			indice++;
		}
		int auxIndice = (int) (Math.random() * 100) % valoresPosibles.size();
		int aux = valoresPosibles.get(auxIndice);
		Habilidad h = rival.getListaHabilidades().get(aux);
		try {
			Habilidad f = h.getClass().newInstance();
			jugador.getListaHabilidades().set(cantHabilidades, f);
			rival.getListaHabilidades().set(aux, new HabilidadVacia());
			jugador.setPuntosHabilidad((jugador.getPuntosHabilidad() - this.getCosto() < 0) ? 0
					: jugador.getPuntosHabilidad() - this.getCosto());

		} catch (InstantiationException | IllegalAccessException e) {
			return false;
		}

		return true;
	}

	@Override
	public String getDescripcionUso() {
		return "Sacrifica 1/" + factorDesacrificio + " de tus puntos de vida para robar una habilidad enemiga";
	}

	public String toString() {
		return "Pacto";
	}

}