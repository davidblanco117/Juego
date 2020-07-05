package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;


public class GranadaDeHumo extends Habilidad implements Programable {

	private Personaje jugador;
	private String descripcionActivacion;
	private int cantTurnos;

	public GranadaDeHumo() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Humo.png"));
		this.setCosto(2);
		this.cantTurnos=2;
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		this.jugador=jugador;
		this.descripcionActivacion="El humo se desvanace" + "\n";
		
		jugador.setVelocidad(Math.min(0.5, jugador.getVelocidad()+0.2));
		
		

		ManejadorEventosTurnos.eventos.add(new Evento(this,this.cantTurnos,jugador));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Aumenta la velocidad en un 20% durante " + cantTurnos + " turnos ";
				
	}

	public String toString() {
		return "Granada de Humo";
	}

	

	@Override
	public void ejecutar() {
		jugador.setVelocidad(Math.max(jugador.getVelocidadOriginal(), jugador.getVelocidad()-0.2));
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		
	}
	
	public String getDescripcionActivado() {
		return this.getDescripcionActivado();
		
	}

}
