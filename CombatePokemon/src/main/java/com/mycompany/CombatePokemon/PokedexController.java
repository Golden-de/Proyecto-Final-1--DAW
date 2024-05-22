/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

import java.util.List;
import java.util.Map;
import javafx.application.Platform;
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
 * @author usuario
 */
public class PokedexController {
    @FXML
    ImageView fondo;
    @FXML
    ImageView imagenPokemon;
    @FXML
    ChoiceBox<String> c;
    @FXML
    Label nombre, vida, ataque, defensa, ataqueEsp, defensaEsp, velocidad;
    @FXML
    Button atras;
    
   @FXML
    private void initialize()
    {
        baseDeDatosControler bds = new baseDeDatosControler();   
        List<Map<String, String>> npokemon = bds.nombrePok();
        fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));

        ObservableList<String> nombresPokemon = FXCollections.observableArrayList();
        for (Map<String, String> row : npokemon) {
            nombresPokemon.add(row.get("Pokemon"));
        }

        c.setItems(nombresPokemon);

        // Verificar que la lista no esté vacía antes de seleccionar el primer elemento
        if (!nombresPokemon.isEmpty()) {
            c.setValue(nombresPokemon.get(0));
        }

        // Cargar el Pokémon predeterminado
       
    }

    @FXML
    public void cargarPokemon()
    { 
        baseDeDatosControler bds = new baseDeDatosControler();
        String npokemon = c.getValue();
        if (npokemon != null && !npokemon.isEmpty()) 
        {
            int nomPokemon = bds.obtenerID(npokemon);
            if (nomPokemon != -1) {
            String np = Integer.toString(nomPokemon);                
            List<Map<String, String>> datosPok = bds.obtenerDatosPokemon(np);
            datosPok(datosPok);
        } 
        else 
        {
            System.out.println("No se pudo obtener el ID del Pokémon: " + npokemon);
        }
        }
    }

    private void datosPok(List<Map<String, String>> datosPok) 
    {
        for (Map<String, String> row : datosPok) 
        {
            try {
                // Asumiendo que ID_Pokemon es un número entero en formato String y que existe en el Map
                int id = Integer.parseInt(row.get("ID_Pokemon"));
                imagenPokemon.setImage(new Image("file:.\\imagesPokemon\\"+id+".png"));

                // Actualizar los labels
                nombre.setText("Nombre: " + row.getOrDefault("Pokemon", "Desconocido"));
                vida.setText("Vida: " + row.getOrDefault("HP", "Desconocido"));
                ataque.setText("Ataque: " + row.getOrDefault("Attack", "Desconocido"));
                defensa.setText("Defensa: " + row.getOrDefault("Defense", "Desconocido"));
                ataqueEsp.setText("Ataque Especial: " + row.getOrDefault("Special_Attack", "Desconocido"));
                defensaEsp.setText("Defensa Especial: " + row.getOrDefault("Special_Defense", "Desconocido"));
                velocidad.setText("Velocidad: " + row.getOrDefault("Speed", "Desconocido"));
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("ID_Pokemon no es un número válido: " + row.get("ID_Pokemon"));
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
