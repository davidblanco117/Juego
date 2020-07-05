package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;

public class FlechaTemporal extends Habilidad implements Programable {

	private double daño = 50;
	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private String nombreRival="";
	private int cantTurnos;

	public FlechaTemporal() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/FlechaTemporal.jpg"));
		this.setCosto(3);
		this.cantTurnos=3;
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		this.rival=rival;
		this.jugador=jugador;
		nombreRival=rival.toString();
		this.descripcionActivacion="La flecha temporal aparece y ataca a " +nombreRival + "\n";

		ManejadorEventosTurnos.eventos.add(new Evento(this,this.cantTurnos,rival));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Lanza una flecha que se desvanece en el tiempo y genera "+ daño +" en " + cantTurnos + " turnos ";
				
	}

	public String toString() {
		return "Flecha Temporal";
	}

	

	@Override
	public void ejecutar() {
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		rival.serAtacado(daño);
		
	}
	
	public String getDescripcionActivado() {
		return this.descripcionActivacion;
		
	}

	

}
