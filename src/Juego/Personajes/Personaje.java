package Juego.Personajes;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Juego.Habilidades.Habilidad;
import Juego.Habilidades.HabilidadVacia;
import Juego.Items.Item;

public abstract class Personaje {

	private double salud;
	private double ataque;
	private double defensa;
	private double velocidad;
	private double ataqueOriginal;
	private double defensaOriginal;
	private double velocidadOriginal;
	private boolean estaVivo;
	private double saludMaxima;
	private int puntosHabilidad;
	private double probDañoPenetrante = 0;
	private String raza = "";
	private ArrayList<Item> items = new ArrayList<>();
	private ArrayList<Habilidad> habilidades = new ArrayList<>();
	private ArrayList<Habilidad> habilidadesActivadas = new ArrayList<>();

	public ArrayList<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	protected ImageIcon imagen;
	private Personaje personaje = this;

	private String descripcionDeSituacion = "";
	private String descripcionPersonaje = "";

	public ImageIcon getImage() {
		return this.imagen;
	}

	public void atacar(Personaje p) {
		for (Habilidad h : this.getHabilidadesActivadas()) {
			if (h.getTipo() == Habilidad.PRE_ATACAR) {
				if (!h.activacionAutomatica())
					return;

			}
		}
		double disparo=this.getAtaque();
		if (Math.random() <= getProbDañoPenetrante()) {
			disparo = (p.getDefensa() < 1) ? disparo / (1 - p.getDefensa()) : disparo;
		}
		this.descripcionDeSituacion = this.descripcionDeSituacion
				.concat(this.toString() + " ataca a " + p.toString() + "\n");
		p.serAtacado(disparo);
		this.descripcionDeSituacion = this.descripcionDeSituacion.concat(p.getDescripcionDeSituacion());

		for (Habilidad h : this.getHabilidadesActivadas()) {
			if (h.getTipo() == Habilidad.POST_ATACAR) {
				h.activacionAutomatica();
			}
		}
	}

	public double serAtacado(Personaje p) {
		return this.serAtacado(p.getAtaque());
	}

	protected boolean verifPreSerAtacado(Double ataqueEnemigo) {
		for (Habilidad h : this.getHabilidadesActivadas()) {
			if (h.getTipo() == Habilidad.PRE_SERATACADO) {
				h.auxDouble1 = ataqueEnemigo;
				if (h.activacionAutomatica())
					return false;
			}
		}
		return true;
	}

	public double serAtacado(Double ataqueEnemigo) {
		if (!verifPreSerAtacado(ataqueEnemigo))
			return 0;

		if (Math.random() <= this.velocidad) {
			this.setDescripcionDeSituacion(
					this.getDescripcionDeSituacion().concat(this.toString() + " esquiva el ataque\n"));
			return 0;
		}
		double dañoRecibido = ataqueEnemigo * (1 - this.defensa);

		dañoRecibido = ((double) Math.round(dañoRecibido * 100)) / 100;
		if (this.salud - dañoRecibido <= 0) {
			this.estaVivo = false;
			this.salud = 0;
			descripcionDeSituacion = descripcionDeSituacion
					.concat(this.toString() + " recibe " + dañoRecibido + " de daño y muere\n");

		} else {
			this.salud -= dañoRecibido;
			descripcionDeSituacion = descripcionDeSituacion
					.concat(this.toString() + " recibe " + dañoRecibido + " de daño\n");
		}
		return ((double) Math.round(dañoRecibido * 100)) / 100;
	}

	public void recibirDaño(double daño) {
		double aux = getSalud() - daño;

		setSalud(Math.max(0, aux));
		if (getSalud() == 0) {
			descripcionDeSituacion = descripcionDeSituacion
					.concat(this.toString() + " recibe " + daño + " de daño y muere\n");
			morir();
		} else {
			descripcionDeSituacion = descripcionDeSituacion.concat(this.toString() + " recibe " + daño + " de daño\n");
		}

	}

	public boolean activarHabilidad(int indice, Personaje rival) {
		if (indice < 0 || indice > getListaHabilidades().size() - 1)
			return false;
		Habilidad h = getListaHabilidades().get(indice);
		boolean funciona = getListaHabilidades().get(indice).activar(this, rival);
		if (funciona) {
			
			@SuppressWarnings("unchecked")
			ArrayList<Habilidad> lista=(ArrayList<Habilidad>)this.getHabilidadesActivadas().clone();
			for (Habilidad hab : lista) {
				if (hab.getTipo() == Habilidad.PRE_ACTIVA_HAB) {
					hab.auxHabilidad1 = h;
					hab.activacionAutomatica();	
				}
			}
			
			
			this.setDescripcionDeSituacion(this
					.getDescripcionDeSituacion().concat(this.toString() + " activa la habilidad " + h.toString()
							+ " que " + h.getDescripcionUso().toLowerCase() + "\n")
					+ rival.getDescripcionDeSituacion());
			}
		return funciona;
	}

	public ArrayList<Habilidad> getListaHabilidades() {
		return habilidades;
	}

	public void aumentarPuntosHabilidad() {
		if (estaVivo)
			this.puntosHabilidad = Math.min(this.puntosHabilidad + 1, 10);
	}

	public void equiparItem(Item item) {
		this.items.add(item);
		item.aplicarVentaja(this);
	}

	public void morir() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Cementerio.jpg"));
		this.setEstaVivo(false);
		this.setSalud(0);
		this.setAtaque(0);
		this.setDefensa(0);
		this.setVelocidad(0);
		this.setPuntosHabilidad(0);
	}

	public void finalizarTurno() {
		for (Habilidad h : this.getHabilidadesActivadas()) 
			if (h.getTipo() == Habilidad.FINALIZAR_TURNO) 
				h.activacionAutomatica();
	}

	public double getSalud() {
		return salud;
	}

	public void setSalud(double salud) {
		this.salud = salud;
	}

	public double getAtaque() {
		return ataque;
	}

	public void setAtaque(double ataque) {
		this.ataque = ataque;
	}

	public double getDefensa() {
		return defensa;
	}

	public void setDefensa(double defensa) {
		this.defensa = defensa;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getSaludMaxima() {
		return saludMaxima;
	}

	public void setSaludMaxima(double saludMaxima) {
		this.saludMaxima = saludMaxima;
	}

	public int getPuntosHabilidad() {
		return this.puntosHabilidad;
	}

	public void setPuntosHabilidad(int puntosHabilidad) {
		this.puntosHabilidad = puntosHabilidad;
	}

	public String getDescripcionDeSituacion() {
		return descripcionDeSituacion;
	}

	public void setDescripcionDeSituacion(String descripcionDeSituacion) {
		this.descripcionDeSituacion = descripcionDeSituacion;
	}

	public String getDescripcionPersonaje() {
		return descripcionPersonaje;
	}

	public void setDescripcionPersonaje(String descripcionPersonaje) {
		this.descripcionPersonaje = descripcionPersonaje;
	}

	public double getAtaqueOriginal() {
		return ataqueOriginal;
	}

	public void setAtaqueOriginal(double ataqueOriginal) {
		this.ataqueOriginal = ataqueOriginal;
	}

	public double getDefensaOriginal() {
		return defensaOriginal;
	}

	public void setDefensaOriginal(double defensaOriginal) {
		this.defensaOriginal = defensaOriginal;
	}

	public double getVelocidadOriginal() {
		return velocidadOriginal;
	}

	public void setVelocidadOriginal(double velocidadOriginal) {
		this.velocidadOriginal = velocidadOriginal;
	}

	public void inicializarValoresOriginales() {
		this.ataqueOriginal = this.ataque;
		this.defensaOriginal = this.defensa;
		this.velocidadOriginal = this.velocidad;
		this.saludMaxima = this.salud;

	}

	public int getCantHabilidades() {
		int res = 0;
		for (Habilidad habilidad : habilidades) {
			if (!habilidad.toString().equals(new HabilidadVacia().toString()))
				res++;
		}
		return res;
	}

	public double getProbDañoPenetrante() {
		return probDañoPenetrante;
	}

	public void setProbDañoPenetrante(double probDañoPenetrante) {
		this.probDañoPenetrante = probDañoPenetrante;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public ArrayList<Habilidad> getHabilidadesActivadas() {
		return habilidadesActivadas;
	}

	public void setHabilidadesActivadas(ArrayList<Habilidad> habilidadesActivadas) {
		this.habilidadesActivadas = habilidadesActivadas;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

}
