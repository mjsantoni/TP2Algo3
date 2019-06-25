package Vista.InventarioVista;

import Modelo.Juego.Juego;
import Modelo.Posicionable.Posicionable;
import Modelo.Tablero.Posicion;
import Vista.Comunicador;
import Vista.JuegoVista;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ReceptoraInventarioVista {

	private int tam;
	private int corrimientoX;
	private int corrimientoY;
	private ImageView imagenreceptora;
	private Comunicador comunicador;
	private JuegoVista juegoVista;
	private Juego juego;

	public ReceptoraInventarioVista(Posicion posicionaux, int tam_celda, int CorrimientoX, int CorrimientoY, Juego juego, JuegoVista juegoVista, Comunicador comunicador) {
		this.tam = tam_celda;
		this.corrimientoX = CorrimientoX;
		this.corrimientoY = CorrimientoY;
		this.comunicador = comunicador;
		this.juegoVista = juegoVista;
		this.juego = juego;

		imagenreceptora = new ImageView();
		imagenreceptora.setImage(null);
		imagenreceptora.setPickOnBounds(true);
		imagenreceptora.setFitHeight(tam);
		imagenreceptora.setFitWidth(tam);
		imagenreceptora.setLayoutX(posicionaux.getColumna() * tam + corrimientoX);
		imagenreceptora.setLayoutY(posicionaux.getFila() * tam + corrimientoY);
	}

	public Node getImagen() {
		return imagenreceptora;
	}

	public void setearOnDragOver() {

		imagenreceptora.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();

				if (dragboard.hasImage()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});
	}

	public void setearOnDragDropped() {

		imagenreceptora.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();

				if (dragboard.hasImage()) {

					imagenreceptora.setImage(dragboard.getImage());
					Posicionable posicionabletrasladado = comunicador.consultarPosicionable();
					int nuevaFila = (int)(imagenreceptora.getLayoutY() - corrimientoY)/tam;
					int nuevaColumna = (int)(imagenreceptora.getLayoutX()- corrimientoX)/tam;
					double MouseX = comunicador.consultarPosicionMouseX();
					double MouseY = comunicador.consultarPosicionMouseY();
					if((MouseX>540)&&(MouseX<1260)&&(MouseY>240)&&(MouseY<720)){ juego.getJugador().removerEnInventario(posicionabletrasladado);
					} else if((MouseX>1400)&&(MouseX<1500)&&(MouseY>560)&&(MouseY<660)) { juegoVista.getJuego().getMesaDeCrafteo().limpiar();
					} else { juego.getMesaDeCrafteo().removerElemento(posicionabletrasladado); }
					juego.getJugador().agregarEnInventarioEnPosicion(posicionabletrasladado, new Posicion(nuevaColumna,nuevaFila));
					juegoVista.getCrafteoVista().actualizarTodo();

					event.setDropCompleted(true);
				} else { event.setDropCompleted(false); }
				event.consume();
			}
		});
	}
}
