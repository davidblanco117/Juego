package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Arquera;
import Juego.Personajes.Personaje;

public class FlechaDeReserva extends Habilidad {

	private Personaje rival;
	private Personaje jugador;
	private String descripcionActivacion;
	private String tipoDeFlecha;
	private double da�oMaximo;
	private double da�oMinimo;

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

		da�oMinimo = ((Arquera) jugador).getDa�oMinimo();
		da�oMaximo = ((Arquera) jugador).getDa�oMaximo();
		da�oMinimo -= da�oMinimo * 0.2;
		da�oMaximo -= da�oMaximo * 0.2;
		((Arquera) jugador).setDa�oMinimo(Math.max(0, da�oMinimo));
		((Arquera) jugador).setDa�oMaximo(Math.max(0, da�oMaximo));
		((Arquera) jugador).setAtaque((da�oMaximo+da�oMinimo)/2);
		jugador.getHabilidadesActivadas().add(this);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "A�ade una flecha extra que permite tener un ataque adicional";

	}

	public String toString() {
		return "Flecha de reserva";
	}

	public String dispararFlecha(Personaje rival) {
		tipoDeFlecha = "";
		da�oMinimo = ((Arquera) jugador).getDa�oMinimo();
		da�oMaximo = ((Arquera) jugador).getDa�oMaximo();
		
		double numeroAleatorio = Math.random() * (da�oMaximo - da�oMinimo);
		double disparo = ((double) Math.round((da�oMinimo + numeroAleatorio) * 100)) / 100;
		if (Math.random() <= jugador.getProbDa�oPenetrante()) {
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
