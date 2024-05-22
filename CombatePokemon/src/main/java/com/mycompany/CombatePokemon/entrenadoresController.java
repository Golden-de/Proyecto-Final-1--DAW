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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class entrenadoresController {
    @FXML
    Label pok1, pok2, pok3, pok4, pok5, pok6, nombre;
    
    @FXML
    Button cargarEntrenador;
    
    @FXML
    ChoiceBox<String>c;
    
    @FXML
    Button Atras;
    
    @FXML
    ImageView fondo;
    
    @FXML
    ImageView imagenEntrenador;
    
    baseDeDatosControler bds;
    
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       bds= new baseDeDatosControler();   
       List<Map<String, String>>nEntrenador=bds.entrenadores();
       fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
       
       ObservableList<String> nombres = FXCollections.observableArrayList();
       for(Map<String, String>row: nEntrenador)
       {
           nombres.add(row.get("Trainer"));
           
       }
       c.setItems(nombres);
    }
    
    @FXML
    public void entrenador() throws ClassNotFoundException
    {
        String nEntre= c.getValue();
        imagenEntrenador.setImage(new Image("file:./Entrenadores/" + nEntre + ".png"));
        
        int id=bds.idEntrenador(nEntre);
        List<Map<String, String>>EquipoN=bds.pokemonEntrenador(id);
        
        // Actualizar la interfaz de usuario con los datos del equipo de Pokémon
        
        // Tienemos etiquetas llamadas pokemon1, pokemon2, ..., pokemon6 para los nombres de los Pokémon
        Label[] pokemonLabels = {pok1, pok2, pok3, pok4, pok5, pok6};
        
        for (Label label : pokemonLabels) 
        {
            label.setText("");
        }

        // Iterar sobre el equipo de Pokémon y actualizar las etiquetas
        int i = 0;
        for (Map<String, String> row : EquipoN)   
        {
            if (i < pokemonLabels.length) 
            {
                String idPk = row.get("ID_Pokemon");
                List<Map<String, String>> pokes = bds.obtenerDatosPokemon(idPk);

                if (!pokes.isEmpty()) {
                    Map<String, String> raw = pokes.get(0); // Obtener el primer elemento si existe
                    pokemonLabels[i].setText(raw.get("Pokemon")); 
                } 
                else 
                {
                    pokemonLabels[i].setText("");
                }
                i++;
            }
        }
    }
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
