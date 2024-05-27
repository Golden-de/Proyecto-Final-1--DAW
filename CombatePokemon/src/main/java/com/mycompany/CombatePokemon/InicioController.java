package com.mycompany.CombatePokemon;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
 

public class InicioController {

    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       imagen.setImage(new Image("file:.\\Imagenes\\Inicio.png"));
    }
    
    
    Button bd;
    
    @FXML
    private void abrir1()
   //abre la nueva ventana dejando abierta la principal
   {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("comporbador.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
   }
    
    @FXML
    Button Entrar;
    @FXML
    private void abrir2()
   //abre la nueva ventana cerrando la principal
   {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //cerramos la ventana actual buscando su referencia a través de algún 
            //control (en este caso el botón 'abrir1'
            Stage stageAcerrar = (Stage) Entrar.getScene().getWindow();
        
            stageAcerrar.close();
            
        } 
        catch (Exception e) 
        {
                System.out.println(e.getMessage());
        }
   }
    
    @FXML
    Button Salir;
    
    
    @FXML
    private void salir(ActionEvent event) {
        // Obtener la referencia a la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }
    @FXML
    private ImageView imagen;
        
    
    
    
}