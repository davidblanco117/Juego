package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class TrioDeEsqueletos extends Habilidad {

	// private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private int cantEsqueletos = 3;
	private int contador = 0;

	public TrioDeEsqueletos() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/TrioEsqueletos.jpg"));
		this.setCosto(3);
		this.setTipo(Habilidad.PRE_SERATACADO);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		// this.rival=rival;

		
		this.jugador = jugador;
		
		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		jugador.getListaHabilidades().set(indice0, new HabilidadVacia());
		
		this.descripcionActivacion = "Esqueleto detiene el ataque" + "\n";

		int indice = jugador.getHabilidadesActivadas().indexOf(this);
		if (indice >= 0) {
			contador = 0;
			jugador.getHabilidadesActivadas().set(indice, this);
		} else
			jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Invoca esqueletos capaces de detener los proximos " + cantEsqueletos + " ataques";

	}

	public String toString() {
		return "Trio de Esqueletos";
	}

	public boolean activacionAutomatica() {
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		contador++;
		if (contador == 3) {
			int indice = jugador.getHabilidadesActivadas().indexOf(this);
			jugador.getHabilidadesActivadas().remove(indice);
		}
		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}
