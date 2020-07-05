package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public abstract class Habilidad {

	public static final int PRE_SERATACADO = -3;
	public static final int POST_SERATACADO = 2;
	public static final int PRE_ATACAR = -1;
	public static final int POST_ATACAR = 1;
	public static final int PRE_ENEM_ACTIVA_HAB = -4;
	public static final int POST_ENEM_ACTIVA_HAB = 4;
	public static final int PRE_ACTIVA_HAB = -2;
	public static final int POST_ACTIVA_HAB = 3;
	public static final int FINALIZAR_TURNO = 5;
	
	//Variables auxiliares
	
	public int auxInt1=0;
	public int auxInt2=0;
	public int auxInt3=0;
	
	public double auxDouble1=0;
	public double auxDouble2=0;
	public double auxDouble3=0;
	
	public String auxString1="";
	public String auxString2="";
	public String auxString3="";
	
	public boolean auxBoolean1=false;
	public boolean auxBoolean2=false;
	public boolean auxBoolean3=false;
	
	public Habilidad auxHabilidad1;
	public Habilidad auxHabilidad2;
	public Habilidad auxHabilidad3;
	
	
	
	//Fin variables auxiliares
	
	
	
	
	
	
	
	private int costo;
	protected ImageIcon imagen;
	private int tipo=0;
	private String descripcionNoActivacion="No puedes activar " + this.toString();
	
	public ImageIcon getImage() {
		return this.imagen;
	}
	
	
	
	public boolean activar(Personaje jugador, Personaje rival) {
		if(this.costo<jugador.getPuntosHabilidad())
			return false;
		return true;
	}
	
	protected boolean comprobarPH(Personaje jugador, Personaje rival) {
		int ph=jugador.getPuntosHabilidad();
		if(this.getCosto()>ph) {
			this.descripcionNoActivacion="No tienes suficientes puntos de habilidad para activar " + this.toString();
			return false;
		}
		return true;
	}


	public int getCosto() {
		return costo;
	}



	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public String getDescripcion() {
		return "<html><p >"+this.toString() + "</p><p>Costo: "+ this.getCosto() + "</p><p>" + this.getDescripcionUso()
				+ "</p></html>";
	};
	
	public abstract String getDescripcionUso();



	public int getTipo() {
		return tipo;
	}



	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public boolean activacionAutomatica() {
		return false;
	}



	public String getDescripcionNoActivacion() {
		return descripcionNoActivacion;
	}

	public void setDescripcionNoActivacion(String descripcion) {
		this.descripcionNoActivacion=descripcion;
	}
	
	
}
