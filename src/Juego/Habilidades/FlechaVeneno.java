package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Evento;
import Juego.ManejadorEventosTurnos;
import Juego.Programable;
import Juego.Personajes.Personaje;

public class FlechaVeneno extends Habilidad implements Programable {

	private double da�o = 25;
	private double da�oVeneno=4;
	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private String nombreRival="";
	private int cantTurnos;

	public FlechaVeneno() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/FlechaVeneno.png"));
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
		this.descripcionActivacion="El veneno da�a a " +nombreRival + "\n";
		rival.serAtacado(da�o);
		for (int i = 1; i <cantTurnos+1; i++) {
			ManejadorEventosTurnos.eventos.add(new Evento(this,i,rival));
			
		}
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Lanza una flecha capaz de envenenar al rival";
				
	}

	public String toString() {
		return "Flecha Venenosa";
	}

	

	@Override
	public void ejecutar() {
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		rival.recibirDa�o(da�oVeneno);
		
	}
	
	public String getDescripcionActivado() {
		return this.getDescripcionActivado();
		
	}

}