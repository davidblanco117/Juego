package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.Entrenamiento;
import Juego.Habilidades.FlechaDeReserva;
import Juego.Habilidades.FlechaPenetrante;
import Juego.Habilidades.FlechaTemporal;
import Juego.Habilidades.FlechaVeneno;
import Juego.Habilidades.Habilidad;
import Juego.Habilidades.HabilidadVacia;

public class Arquera extends Personaje {

	private double dañoMinimo = 7;
	private double dañoMaximo = 13;

	private String tipoDeFlecha = "";
	private int cantidadDeDisparos = 2;

	public Arquera() {

		setAtaque((dañoMaximo+dañoMinimo)/2);
		setSalud(230);
		setSaludMaxima(230);
		setDefensa(0.15);
		setVelocidad(0.3);
		setEstaVivo(true);
		setRaza("Humano");
		inicializarValoresOriginales();
		getHabilidades().add(new FlechaPenetrante());
		getHabilidades().add(new FlechaTemporal());
		getHabilidades().add(new FlechaVeneno());
		getHabilidades().add(new FlechaDeReserva());
		getHabilidades().add(new Entrenamiento());
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/arquera.jpg"));
		this.setDescripcionPersonaje(
				"La arquera es una guerrera rapida, con la habilidad de disparar dos flechas por turno con daño variable");

	}

	@Override
	public void atacar(Personaje rival) {
		for (Habilidad  h : this.getHabilidadesActivadas()) {
			if(h.getTipo()==Habilidad.PRE_ATACAR) {
				h.activacionAutomatica();
				
			}
		}

		for (int i = 0; i < cantidadDeDisparos; i++) {
			String nombreRival = rival.toString();
			dispararFlecha(rival);
			this.setDescripcionDeSituacion(this.getDescripcionDeSituacion()
					.concat(this.toString() + " lanza una flecha " + tipoDeFlecha + "a " + nombreRival + "\n"));
			this.setDescripcionDeSituacion(this.getDescripcionDeSituacion().concat(rival.getDescripcionDeSituacion()));
			rival.setDescripcionDeSituacion("");
		}
		
		for (Habilidad h : this.getHabilidadesActivadas()) {
			if (h.getTipo() == Habilidad.POST_ATACAR) {
				h.activacionAutomatica();
			}
		}

	}

	public String dispararFlecha(Personaje rival) {
		tipoDeFlecha = "";

		double numeroAleatorio = Math.random() * (dañoMaximo - dañoMinimo);
		double disparo = ((double) Math.round((dañoMinimo + numeroAleatorio) * 100)) / 100;
		if (Math.random() <= getProbDañoPenetrante()) {
			tipoDeFlecha = "penetrante ";
			disparo = (rival.getDefensa() < 1) ? disparo / (1 - rival.getDefensa()) : disparo;
		}
		rival.serAtacado(disparo);
		return this.toString() + " ataca a " + rival.toString() + rival.getDescripcionDeSituacion();
	}

	

	public void morir() {
		this.setDañoMinimo(0);
		this.setDañoMaximo(0);
		super.morir();
		}
	
	
	
	@Override
	public String toString() {
		return "Arquera";
	}

	public double getDañoMinimo() {
		return dañoMinimo;
	}

	public void setDañoMinimo(double dañoMinimo) {
		this.dañoMinimo = dañoMinimo;
	}

	public double getDañoMaximo() {
		return dañoMaximo;
	}

	public void setDañoMaximo(double dañoMaximo) {
		this.dañoMaximo = dañoMaximo;
	}
	
	public double getAtaque() {
		return ((dañoMaximo+dañoMinimo)/2);
	}
	
	public void setAtaque(double ataque) {
		super.setAtaque(ataque);
		this.dañoMinimo=Math.max(0,ataque-3);
		this.dañoMaximo=ataque+3;
	}

}
