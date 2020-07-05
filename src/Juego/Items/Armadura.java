package Juego.Items;

import Juego.Personajes.Personaje;

public class Armadura extends Item {

	
	public Armadura() {
		
	}
	
	@Override
	public void aplicarVentaja(Personaje p) {
		double aux= (p.getDefensa()+0.2);
		if(aux>=90)
			p.setDefensa(90);
		else
			p.setDefensa(aux);
		p.setVelocidad(p.getVelocidad()-20);
	}
	
}
