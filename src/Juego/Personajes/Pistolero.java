package Juego.Personajes;


import Juego.Habilidades.Habilidad;

public class Pistolero extends Personaje {

	private String clase = "Pistolero";
	private Personaje modoNormal;
	private Personaje modoZombie;
	private Personaje modo;
	private boolean isModoZombie=false;
	

	public Pistolero() {
		setEstaVivo(true);
		inicializarValoresOriginales();
		setModoNormal(new PistoleroModoNormal());
		modoZombie=new PistoleroZombie();
		cambiarModo(modoNormal);
		
	}

	public double serAtacado(Personaje p) {
		return this.serAtacado(p.getAtaque());
	}

	public double serAtacado(Double ataqueEnemigo) {
		for (Habilidad  h : this.getHabilidadesActivadas()) {
			if(h.getTipo()==Habilidad.PRE_SERATACADO) {
				if (h.activacionAutomatica())
					return 0;
			}
		}
		
		if (Math.random() <= this.getVelocidad()) {
			this.setDescripcionDeSituacion(
					this.getDescripcionDeSituacion().concat(this.toString() + " esquiva el ataque\n"));
			return 0;
		}
		double dañoResultante = ataqueEnemigo * (1 - this.getDefensa());
		dañoResultante = ((double) Math.round(dañoResultante * 100)) / 100;

		if (this.getSalud() - dañoResultante <= 0) {
			if(!isModoZombie) {
			isModoZombie=true;
			this.setDescripcionDeSituacion(this.getDescripcionDeSituacion()
					.concat(this.toString() + " recibe " + dañoResultante + " de daño\n"));
			cambiarModo(modoZombie);
			this.setDescripcionDeSituacion(
					this.getDescripcionDeSituacion().concat("Pistolero sufre una transformacion a un zombie\n"));
			}
			else {
				setEstaVivo(false);
				this.setDescripcionDeSituacion(this.getDescripcionDeSituacion()
						.concat(this.toString() + " recibe " + dañoResultante + " de daño y muere\n"));
			}

		} else {
			this.setSalud(this.getSalud() - dañoResultante);
			this.setDescripcionDeSituacion(this.getDescripcionDeSituacion()
					.concat(this.toString() + " recibe " + dañoResultante + " de daño\n"));

		}
		return ((double) Math.round(dañoResultante * 100)) / 100;

	}

	
	public void cambiarModo(Personaje p) {
		this.setRaza(p.getRaza());
		this.clase=p.toString();
		this.imagen=p.getImage();
		this.setSalud(p.getSalud());
		this.setDefensa(p.getDefensa());
		this.setAtaque(p.getAtaque());
		this.setVelocidad(p.getVelocidad());
		this.setHabilidades(p.getHabilidades());
		this.setDescripcionPersonaje(p.getDescripcionPersonaje());
		inicializarValoresOriginales();
		//this.setHabilidadesActivadas(new ArrayList<Habilidad>());
		
	};
	
	
	
	@Override
	public String toString() {
		return clase;
	}

	public Personaje getModoNormal() {
		return modoNormal;
	}

	public void setModoNormal(Personaje modoNormal) {
		this.modoNormal = modoNormal;
	}

	public Personaje getModo() {
		return modo;
	}

	public void setModo(Personaje modo) {
		this.modo = modo;
	}
	
	public boolean estaEnModoZombie() {
		return isModoZombie;
	}

	public void setModoZombie(boolean modo) {
		this.isModoZombie = modo;
	}

}

