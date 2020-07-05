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

	private double da�oMinimo = 7;
	private double da�oMaximo = 13;

	private String tipoDeFlecha = "";
	private int cantidadDeDisparos = 2;

	public Arquera() {

		setAtaque((da�oMaximo+da�oMinimo)/2);
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
				"La arquera es una guerrera rapida, con la habilidad de disparar dos flechas por turno con da�o variable");

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

		double numeroAleatorio = Math.random() * (da�oMaximo - da�oMinimo);
		double disparo = ((double) Math.round((da�oMinimo + numeroAleatorio) * 100)) / 100;
		if (Math.random() <= getProbDa�oPenetrante()) {
			tipoDeFlecha = "penetrante ";
			disparo = (rival.getDefensa() < 1) ? disparo / (1 - rival.getDefensa()) : disparo;
		}
		rival.serAtacado(disparo);
		return this.toString() + " ataca a " + rival.toString() + rival.getDescripcionDeSituacion();
	}

	

	public void morir() {
		this.setDa�oMinimo(0);
		this.setDa�oMaximo(0);
		super.morir();
		}
	
	
	
	@Override
	public String toString() {
		return "Arquera";
	}

	public double getDa�oMinimo() {
		return da�oMinimo;
	}

	public void setDa�oMinimo(double da�oMinimo) {
		this.da�oMinimo = da�oMinimo;
	}

	public double getDa�oMaximo() {
		return da�oMaximo;
	}

	public void setDa�oMaximo(double da�oMaximo) {
		this.da�oMaximo = da�oMaximo;
	}
	
	public double getAtaque() {
		return ((da�oMaximo+da�oMinimo)/2);
	}
	
	public void setAtaque(double ataque) {
		super.setAtaque(ataque);
		this.da�oMinimo=Math.max(0,ataque-3);
		this.da�oMaximo=ataque+3;
	}

}
