package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Contraataque extends Habilidad {

	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private double probActivar = 0.25;
	private int cantActivados = 0;

	public Contraataque() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Contraataque.png"));
		this.setCosto(3);
		this.setTipo(Habilidad.PRE_SERATACADO);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		this.rival = rival;

		this.jugador = jugador;

		this.descripcionActivacion = jugador.toString() + " esquiva el ataque y lo regresa" + "\n";

		cantActivados++;
		jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Da un 25% de probabilidad de esquivar y regresar un ataque futuro";

	}

	public String getDescripcion() {
		return "<html><p >" + this.toString() + "</p><p>Costo: " + this.getCosto() + "</p><p>"
				+ this.getDescripcionUso() + ". Activos (" + cantActivados + ")" + "</p></html>";
	};

	public String toString() {
		return "Contraataque";
	}

	public boolean activacionAutomatica() {
		if (Math.random() > probActivar)
			return false;
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));

		int indice = jugador.getHabilidadesActivadas().indexOf(this);
		if (indice < 0)
			return false;
		jugador.getHabilidadesActivadas().remove(indice);
		cantActivados--;
		rival.serAtacado(auxDouble1);
		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}