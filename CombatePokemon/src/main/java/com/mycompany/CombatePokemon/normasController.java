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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class normasController {
    @FXML
    Label normas;
    @FXML
    Button salir;
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
        normas.setText("Elegir Entrenador: Selecciona uno de los 12 entrenadores disponibles.\n" +
            "Seleccionar Dificultad: Elige la dificultad entre Pesadilla, Veterano, Marine, y Recluta.\n" +
            "Comenzar: Pulsa el botón para iniciar el combate.\n" +
            "2. Asignación de Pokémon\n" +
            "Carga de Pokémon: Los Pokémon de tu entrenador tendrán niveles aleatorios según la dificultad:\n" +
            "Pesadilla: Niveles entre 1 y 25.\n" +
            "Veterano: Niveles entre 25 y 50.\n" +
            "Marine: Niveles entre 50 y 75.\n" +
            "Recluta: Niveles entre 75 y 100.\n" +
            "3. Combate\n" +
            "Turnos: El Pokémon con mayor velocidad comienza. En caso de empate, se decide con una moneda.\n" +
            "Ataques: Cada Pokémon tiene un ataque. Puedes usar un ataque especial, pero tu Pokémon se tomará un turno "+"\n"+"de descanso.\n" +
            "TUX: El oponente tiene un 20% de probabilidad de usar un ataque especial.\n" +
            "4. Desarrollo del Juego\n" +
            "Cambio de Pokémon: Si tu Pokémon actual es derrotado, elige otro de tus Pokémon.\n" +
            "Derrotar a TUX: Si vences a TUX, aparecerá otro del mismo nivel. El juego continúa hasta que todos "+"\n"+"tus Pokémon sean derrotados.\n" +
            "5. Abandonar el Juego\n" +
            "Salir: Puedes abandonar la partida en cualquier momento. No se puede cambiar la configuración mientras juegas.");
    }
   
     @FXML
    private void Atras(ActionEvent event)
    {
        //abre la nueva ventana cerrando la principal
        try 
        {
            // Cargar la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Seleccion.fxml"));
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
