package Juego;

import java.util.ArrayList;

import Juego.Personajes.Personaje;

public class ManejadorEventosTurnos {

	public static ArrayList<Evento> eventos=new ArrayList<>();
	
	
	
	public void manejar(Personaje p) {
		@SuppressWarnings("unchecked")
		ArrayList<Evento> e2=(ArrayList<Evento>) eventos.clone();

		//ArrayList<Evento> e2=eventos;
		for ( Evento evento : e2) {
			if(evento.ejecutar(p)) {
				eventos.remove(evento);
			}
		}
		
		eventos=e2;
		
	}
	
	
	
	
	
	
}
