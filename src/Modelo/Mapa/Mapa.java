package Modelo.Mapa;

import java.util.HashMap;

import Modelo.Excepciones.PosicionFueraDeRangoException;
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

		if(posicion.estaFueraDeRango(altura,ancho)) throw new PosicionFueraDeRangoException();
		if (grilla.containsKey(posicion)) throw new PosicionOcupadaException(); //ESTO NO ANDA
		grilla.put(posicion, posicionable);

	}

	public void removerElementoEnPosicion(Posicion posicion) {
		grilla.remove(posicion);
	}

	public Posicion obtenerPosicionVacia() {
		for(int i = 0; i < ancho;i++)
			for(int j = 0; j < altura;j++) {
				Posicion posicion = new Posicion(i,j);
				if(this.obtenerElementoEnPosicion(posicion) == null)
					return posicion;
			}
		return null;
	}

}
