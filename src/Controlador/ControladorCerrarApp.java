package Controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;



public class ControladorCerrarApp implements EventHandler<ActionEvent> {

    private Button Boton;
    private AudioClip music;
    private Stage stage;

    public ControladorCerrarApp(Button miBoton, AudioClip music, Stage stage){
        this.Boton = miBoton;
        this.music = music;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        System.out.println("Se cierra la app");
        music.stop();
        stage.close();
    }
}
