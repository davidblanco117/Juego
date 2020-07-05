package Juego.Habilidades;


import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Espejo extends Habilidad {

	private Personaje jugador;
	private Personaje rival;
	private int indiceThis;
	private boolean activable=true;
	
	private String descripcionActivacion="";
	

	public Espejo() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Espejo.jpg"));
		this.setCosto(5);
		this.setTipo(Habilidad.PRE_ACTIVA_HAB);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		
		if(!activable) {
			this.setDescripcionNoActivacion("No puedes volver a activar " + this.toString());
			return false;
		}
		this.jugador=jugador;
		this.rival=rival;
		indiceThis=jugador.getListaHabilidades().indexOf(this);
		activable=false;

		rival.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad() - this.getCosto() < 0) ? 0
				: jugador.getPuntosHabilidad() - this.getCosto());
		return true;
	}

	public boolean activacionAutomatica() {
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		try {
			Habilidad f = auxHabilidad1.getClass().newInstance();
			jugador.getListaHabilidades().set(indiceThis,f);
			rival.getHabilidadesActivadas().remove(rival.getHabilidadesActivadas().indexOf(this));

			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	@Override
	public String getDescripcionUso() {
		return "Copia la proxima habilidad que use tu rival";
	}

	public String toString() {
		return "Espejo";
	}

}