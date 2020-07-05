package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Arquera;
import Juego.Personajes.Personaje;

public class FlechaDeReserva extends Habilidad {

	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private String tipoDeFlecha;
	private double dañoMaximo;
	private double dañoMinimo;

	public FlechaDeReserva() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/FlechaDeReserva.jpg"));
		this.setCosto(4);
		this.setTipo(Habilidad.PRE_ATACAR);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		if (jugador.getClass() != Arquera.class)
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

		dañoMinimo = ((Arquera) jugador).getDañoMinimo();
		dañoMaximo = ((Arquera) jugador).getDañoMaximo();
		dañoMinimo -= dañoMinimo * 0.2;
		dañoMaximo -= dañoMaximo * 0.2;
		((Arquera) jugador).setDañoMinimo(Math.max(0, dañoMinimo));
		((Arquera) jugador).setDañoMaximo(Math.max(0, dañoMaximo));
		((Arquera) jugador).setAtaque((dañoMaximo+dañoMinimo)/2);
		jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Añade una flecha extra que permite tener un ataque adicional";

	}

	public String toString() {
		return "Flecha de reserva";
	}

	public String dispararFlecha(Personaje rival) {
		tipoDeFlecha = "";
		dañoMinimo = ((Arquera) jugador).getDañoMinimo();
		dañoMaximo = ((Arquera) jugador).getDañoMaximo();
		
		double numeroAleatorio = Math.random() * (dañoMaximo - dañoMinimo);
		double disparo = ((double) Math.round((dañoMinimo + numeroAleatorio) * 100)) / 100;
		if (Math.random() <= jugador.getProbDañoPenetrante()) {
			tipoDeFlecha = "penetrante ";
			disparo = (rival.getDefensa() < 1) ? disparo / (1 - rival.getDefensa()) : disparo;
		}
		rival.serAtacado(disparo);
		return jugador.toString() + " ataca a " + rival.toString() + rival.getDescripcionDeSituacion();
	}

	public boolean activacionAutomatica() {
		// jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion().concat(this.descripcionActivacion));
		// ((Arquera)jugador).dispararFlecha(rival);

		String nombreRival = rival.toString();
		dispararFlecha(rival);
		jugador.setDescripcionDeSituacion(jugador.getDescripcionDeSituacion()
				.concat(jugador.toString() + " lanza una flecha " + tipoDeFlecha + "a " + nombreRival + "\n"));
		jugador.setDescripcionDeSituacion(
				jugador.getDescripcionDeSituacion().concat(rival.getDescripcionDeSituacion()));
		rival.setDescripcionDeSituacion("");

		return true;
	}

	public String getDescripcionActivado() {
		return this.descripcionActivacion;

	}

}
