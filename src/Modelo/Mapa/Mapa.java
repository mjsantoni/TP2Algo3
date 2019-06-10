package Modelo.Mapa;

import java.util.HashMap;
import Modelo.Excepciones.PosicionOcupadaException;
import Modelo.Posicionable.Posicionable;

public class Mapa {

	protected HashMap<Posicion, Posicionable> grilla;
	private int altura;
	private int ancho;

	public Mapa(int unaAltura,int unAncho) {
		grilla = new HashMap<>();
		altura = unaAltura;
		ancho = unAncho;

	}

	public Posicionable obtenerElementoEnPosicion(Posicion posicion) {
		return grilla.getOrDefault(posicion, null);
	}

	public void colocarElementoEnPosicion(Posicionable posicionable, Posicion posicion) {
		if (grilla.containsKey(posicion)) throw new PosicionOcupadaException();
		grilla.put(posicion, posicionable);
	}

	public void removerElementoEnPosicion(Posicion posicion) {
		grilla.remove(posicion);
	}


}
