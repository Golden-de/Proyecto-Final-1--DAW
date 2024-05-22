/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author usuario
 */
public class SeleccionController {
    
    baseDeDatosControler bds;
    @FXML
    Button empezar;
    @FXML
    ChoiceBox<String> Dificultad;
    @FXML
    Button normas;
    @FXML
    ChoiceBox<String>entrenadores;
    @FXML
    ImageView fondo;
    @FXML
    TextArea logs;
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       Singleton sin= Singleton.getInstancia();
       ObservableList<String> optionsList = FXCollections.observableArrayList("Pesadilla", "Veterano", "Marine","Recluta");

       fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
      
       Dificultad.setItems(optionsList);
       
       // Verificar que la lista no esté vacía antes de seleccionar el primer elemento
       if (!optionsList.isEmpty()) 
       {
            Dificultad.setValue(optionsList.get(0));
       }
        
       bds= new baseDeDatosControler();   
       List<Map<String, String>>nEntrenador=bds.entrenadores();
       fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
       
       ObservableList<String> nombres = FXCollections.observableArrayList();
       for(Map<String, String>row: nEntrenador)
       {
           nombres.add(row.get("Trainer"));    
       }
       entrenadores.setItems(nombres);
       
       // Verificar que la lista no esté vacía antes de seleccionar el primer elemento
       if (!nEntrenador.isEmpty()) 
       {
            entrenadores.setValue(nombres.get(0));
       }   
    }
    
    @FXML
    public void juego(ActionEvent event)
    {
        Singleton sin= Singleton.getInstancia();
        String Dif= Dificultad.getValue();
        String entr=entrenadores.getValue();
        sin.setdificultad(Dif);
        sin.setEntrenador(entr);
        //abre la nueva ventana cerrando la principal
        //abre la nueva ventana cerrando la principal
        try 
        {
            // Cargar la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Juego.fxml"));
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
    Button atras;
    @FXML
    private void Atras(ActionEvent event)
    {
        //abre la nueva ventana cerrando la principal
        try 
        {
            // Cargar la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
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
   
}
