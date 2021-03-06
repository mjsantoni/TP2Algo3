package Controlador;

import Vista.JuegoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ControladorMoverJugadorArriba implements EventHandler<ActionEvent> {

		private JuegoVista juego;

		public ControladorMoverJugadorArriba(JuegoVista juego){
			this.juego = juego;
		}

		@Override
		public void handle(ActionEvent actionEvent) {
			juego.getJuego().moverJugadorEnVertical(-1);
			juego.actualizarTodo();
		}
	}
