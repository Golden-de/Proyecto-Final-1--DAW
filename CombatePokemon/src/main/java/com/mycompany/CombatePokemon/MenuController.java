/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author usuario
 */
public class MenuController {
    @FXML
    Button entrar;
    @FXML
    ImageView fondo;
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
    }
    @FXML
    Button pokedex;
    @FXML
    public void abrir1()
   //abre la nueva ventana cerrando la principal
   {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokedex.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //cerramos la ventana actual buscando su referencia a través de algún 
            //control (en este caso el botón 'abrir1')
            Stage stageAcerrar = (Stage) pokedex.getScene().getWindow();
        
            stageAcerrar.close();
            
        } 
        catch (Exception e) 
        {
                System.out.println(e.getMessage());
        }
   }
    
    @FXML
    Button entrenadores;
    
    @FXML
    public void abrir2()
   //abre la nueva ventana cerrando la principal
   {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("entrenadores.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //cerramos la ventana actual buscando su referencia a través de algún 
            //control (en este caso el botón 'abrir1')
            Stage stageAcerrar = (Stage) pokedex.getScene().getWindow();
        
            stageAcerrar.close();
            
        } 
        catch (Exception e) 
        {
                System.out.println(e.getMessage());
        }
   }
    @FXML
    Button atras;
    @FXML
    private void Atras(ActionEvent event)
    {
        //abre la nueva ventana cerrando la principal
        try 
        {
            // Cargar la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Obtener la referencia a la ventana actual
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // Cerrar la ventana
            stage.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void entrarJuego()
   {
       //abre la nueva ventana cerrando la principal
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Seleccion.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //cerramos la ventana actual buscando su referencia a través de algún 
            //control (en este caso el botón 'abrir1')
            Stage stageAcerrar = (Stage) pokedex.getScene().getWindow();
        
            stageAcerrar.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
   }
}
