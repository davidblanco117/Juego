package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;

public class Estandarte extends Habilidad implements Programable {

	private Personaje jugador;
	private String descripcionActivacion;
	private int cantTurnos;

	public Estandarte() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Estandarte.jpg"));
		this.setCosto(4);
		this.cantTurnos=4;
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		this.jugador=jugador;
		this.descripcionActivacion="Los efectos del estandarte se desvanecen" + "\n";
		
		jugador.setAtaque(jugador.getAtaque()+4);
		jugador.setVelocidad(Math.min(0.5, jugador.getVelocidad()+0.1));
		jugador.setDefensa(Math.min(0.5, jugador.getDefensa()+0.1));
		
		

		ManejadorEventosTurnos.eventos.add(new Evento(this,this.cantTurnos,jugador));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Aumenta las caracteristicas del personaje por los sig. "  + cantTurnos + " turnos ";
				
	}

	public String toString() {
		return "Estandarte";
	}

	

	@Override
	public void ejecutar() {
		jugador.setAtaque(jugador.getAtaque()-4);
		jugador.setVelocidad(Math.max(jugador.getVelocidadOriginal(), jugador.getVelocidad()-0.1));
		jugador.setDefensa(Math.max(jugador.getDefensaOriginal(), jugador.getDefensa()-0.1));
		
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		
	}
	
	public String getDescripcionActivado() {
		return this.getDescripcionActivado();
		
	}

}
