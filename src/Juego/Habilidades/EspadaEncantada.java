package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class EspadaEncantada extends Habilidad {

	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;

	public EspadaEncantada() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/EspadaEncantada.jpg"));
		this.setCosto(6);
		this.setTipo(Habilidad.PRE_ATACAR);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		this.rival = rival;
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

		jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Hace que obtengas un daño adicional de 7 puntos cuando ataques a un enemigo no humano";

	}

	public String toString() {
		return "Espada encantada";
	}

	public boolean activacionAutomatica() {
		// jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		// ((Arquera)jugador).dispararFlecha(rival);
		this.descripcionActivacion="";
		if (rival.getRaza().equals("NoHumano")) {
			jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion()
					.concat(jugador.toString() + " ataca a " + rival.toString() + "\n")); 
			rival.serAtacado(jugador.getAtaque()+7);
			jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(rival.getDescripcionDeSituacion()));		
			return false;
		}
		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}