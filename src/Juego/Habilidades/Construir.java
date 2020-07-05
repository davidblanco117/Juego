package Juego.Habilidades;

import java.util.ArrayList;


import Juego.Personajes.Personaje;

public class Construir extends Habilidad {
	
	
	private ArrayList<Habilidad> habilidades= new ArrayList<>();
	public ArrayList<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	private int posicion;
	private String descripcion="Fabrica un artefacto";
	private String nombreClase="Costruir";
	
	public Construir() {
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		int aleatorio= (int) (Math.random()*100)%habilidades.size();
		jugador.getListaHabilidades().set(posicion, habilidades.get(aleatorio));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	
	@Override
	public String getDescripcionUso() {
		
		return descripcion;
	}
	
	public String toString() {
		return nombreClase;
	}
	
	public void setPosicion(int pos) {
		this.posicion=pos;
	}
	public void setDescripcionUso(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public int getPosicion() {
		return this.posicion;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase=nombreClase;
	}
	
	public String getNombreClase() {
		return this.nombreClase;
	}
}
