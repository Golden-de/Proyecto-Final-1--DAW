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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author usuario
 */
public class JuegoController {
    Tux tux;
    @FXML
    Button ataqueBasico, AtaqueEs;
    @FXML
    ImageView imageTux;
    @FXML
    ImageView fondo;
    @FXML
    ImageView poke1, poke2, poke3, poke4,poke5,poke6;
    
    @FXML
    ListView <String> statsEquipo, statstux;
    Pokemon[] equipoPoke;
    @FXML
    Button Salir;
    
    
    @FXML
    private void initialize()
    {
        baseDeDatosControler bds;
        bds=new baseDeDatosControler();
        fondo.setImage(new Image("file:.\\Imagenes\\fondoCombate.png"));
        
        Singleton sin = Singleton.getInstancia();
        String diff= sin.getdificultad();
        imageTux.setImage(new Image("file:.\\Imagenes\\tux.png"));
        tux= new Tux();
        
        String entre= sin.getEntrenador();
        int idEntre= bds.idEntrenador(entre);
        List<Map<String,String>>equipo=bds.pokemonEntrenador(idEntre);
        
        equipoPoke = new Pokemon[6];
        ImageView[] fotos= {poke1, poke2, poke3, poke4,poke5,poke6};
        
        
        int i=0;
        for (Map<String, String> row : equipo)   
        {
            if (i <= equipoPoke.length) 
            {
                String idPk = row.get("ID_Pokemon");
                int idTipo=bds.IDtipoPoke(idPk);
                String tipo= bds.tipoPoke(idTipo);
                List<Map<String, String>> pokes = bds.obtenerDatosPokemon(idPk);

                if (!pokes.isEmpty()) {
                    Map<String, String> raw = pokes.get(0); // Obtener el primer elemento si existe
                    
                    int idPoke = Integer.parseInt(raw.get("ID_Pokemon"));
                    String nPoke = raw.get("Pokemon");
                    int vidaP = Integer.parseInt(raw.get("HP"));
                    int atac = Integer.parseInt(raw.get("Attack"));
                    int def = Integer.parseInt(raw.get("Defense"));
                    int atcEs = Integer.parseInt(raw.get("Special_Attack"));
                    int defensaESp = Integer.parseInt(raw.get("Special_Defense"));
                    int velo = Integer.parseInt(raw.get("Speed"));
                    
                    equipoPoke[i]=new Pokemon(idPoke, nPoke,vidaP,atac,def,atcEs,defensaESp,velo, tipo); 
                    fotos[i].setImage(new Image("file:.\\imagesPokemon\\"+idPoke+".png"));

                }   
            }
            i++;
        }
        // Si el equipo tiene menos de 6 Pokémon, añadir imágenes de Pokéball vacía
        for (int j = i; j < equipoPoke.length; j++) 
        {
            equipoPoke[j] = null;
            fotos[j].setImage(new Image("file:.\\Imagenes\\pokeball.png"));
            
        }
        if(equipo.size()>1)
        {
            Pokemon masRapido=masRapido(equipoPoke, equipo);
            reordenarPorVelocidad(equipoPoke, masRapido);
            actualizarImagenes(equipoPoke);
        } 
        datosPokemonCombate(equipoPoke[0]);
        datosTuxCombate(tux);
    }
    
    private Pokemon masRapido(Pokemon [] pokemon, List<Map<String, String>>equipo)
    {
        Pokemon masRapido= pokemon[0];
        for(int i =1; i<equipo.size(); i++)
        {
            if(pokemon[i].getVelocidad()>masRapido.getVelocidad() && pokemon[i]!=null)
            {
                masRapido=pokemon[i];
            }
        } 
        return masRapido;
    }
    
    private void reordenarPorVelocidad(Pokemon[] pokemons, Pokemon masRapido)
    {
        for (int i = 0; i < pokemons.length; i++) 
        {
            if (pokemons[i] != null && pokemons[i] == masRapido) 
            {
                // Intercambiamos el Pokémon más rápido con el Pokémon en la primera posición
                Pokemon temp = pokemons[0];
                pokemons[0] = pokemons[i];
                pokemons[i] = temp;
                break; // Terminamos el bucle después del intercambio
            }
        }
    }
    
    private void actualizarImagenes(Pokemon[] pokemon) 
    {
        ImageView[] fotos = {poke1, poke2, poke3, poke4, poke5, poke6};
       
        for (int i = 0; i < pokemon.length; i++) 
        {
            if (pokemon[i] != null) 
            {
                int id= pokemon[i].getId();
                // Actualizar la imagen en la posición correspondiente
                fotos[i].setImage(new Image("file:.\\imagesPokemon\\" + pokemon[i].getId() + ".png"));
                fotos[i].setDisable(pokemon[i].getHp() <= 0); // Deshabilitar imagen si el HP es 0 o menor
               
            }
        }
    }
    
    public void datosPokemonCombate(Pokemon pokemon) 
    {
        if (pokemon != null) 
        {
            ObservableList<String> items = FXCollections.observableArrayList(
                "Nivel: "+ pokemon.getNivel(),
                "ID: " + pokemon.getId(),
                "Nombre: " + pokemon.getNombre(),
                "HP: " + pokemon.getHp(),
                "Ataque: " + pokemon.getAtaque(),
                "Defensa: " + pokemon.getDefensa(),
                "Ataque Especial: " + pokemon.getAtaqueEspecial(),
                "Defensa Especial: " + pokemon.getDefensaEspecial(),
                "Velocidad: " + pokemon.getVelocidad(),
                "Tipo: " + pokemon.getTipo()
            );
            statsEquipo.setItems(items);
        }
    }
    public void datosTuxCombate(Tux tux) 
    {
        if (tux != null) 
        {
            ObservableList<String> items = FXCollections.observableArrayList(
                "Nivel: "+ tux.getNivel(),
                "ID: " + tux.getId(),
                "Nombre: " + tux.getNombre(),
                "HP: " + tux.getHp(),
                "Ataque: " + tux.getAtaque(),
                "Defensa: " + tux.getDefensa(),
                "Ataque Especial: " + tux.getAtaqueEspecial(),
                "Defensa Especial: " + tux.getDefensaEspecial(),
                "Velocidad: " + tux.getVelocidad(),
                "Tipo: " + tux.getTipo()
            );
            statstux.setItems(items);
        }
    }
    
    @FXML
    public void ataque() 
    {
        int cont=equipoMuerto(equipoPoke);
        if(cont<equipoPoke.length)
        {
            boolean estado=mueto(equipoPoke[0]);
            if(estado==true)
            {
                if (tux.getVelocidad() > equipoPoke[0].getVelocidad()) 
                {
                    // Tux ataca primero
                    int defensaPoke = equipoPoke[0].getDefensa();
                    String tipoPoke = equipoPoke[0].getTipo();
                    int daño = tux.ataqueBasico(defensaPoke, tipoPoke);
                    equipoPoke[0].restarVida(daño);
                    datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño

                    // Verifica si el Pokémon sigue vivo antes de contraatacar
                    if (equipoPoke[0].getHp() > 0) 
                    {
                        defensaPoke = tux.getDefensa();
                        String tipoTux = tux.getTipo();
                        daño = equipoPoke[0].ataqueBasico(defensaPoke, tipoTux);
                        tux.restarVida(daño);
                        datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño
                        //deshabilitamos lo botones 
                        //ataqueBasico.setDisable(true);
                        //AtaqueEs.setDisable(true);
                    }
                } 
                else 
                {
                    // El Pokémon ataca primero
                    int defensaTux = tux.getDefensa();
                    String tipoTux = tux.getTipo();
                    int daño = equipoPoke[0].ataqueBasico(defensaTux, tipoTux);
                    tux.restarVida(daño);
                    datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño

                    // Verifica si Tux sigue vivo antes de contraatacar
                    if (tux.getHp() > 0) 
                    {
                        int defensarival = equipoPoke[0].getDefensa();
                        String tipoPoke = equipoPoke[0].getTipo();
                        daño = tux.ataqueBasico(defensarival, tipoPoke);
                        equipoPoke[0].restarVida(daño);
                        datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño
                    }
                }
            }
            else
            {
                //deshabilitamos lo botones 
                ataqueBasico.setDisable(true);
                AtaqueEs.setDisable(true);
            }
        }
        else
        {
            mostrarVentanaPerdida();
        }
        tuxMuerto();
       
    }
    @FXML
    public void ataqueEspecial() 
    {
        int cont=equipoMuerto(equipoPoke);
        if(cont<equipoPoke.length)
        {
            boolean estado=mueto(equipoPoke[0]);
            if(estado==true)
            {
                if (tux.getVelocidad() > equipoPoke[0].getVelocidad()) 
                {
                    // Tux ataca primero con ataque especial
                    int defensaPoke = equipoPoke[0].getDefensaEspecial();
                    String tipoPoke = equipoPoke[0].getTipo();
                    int daño = tux.ataqueEspecial(defensaPoke, tipoPoke);
                    equipoPoke[0].restarVida(daño);
                    datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño
                    // Verifica si el Pokémon sigue vivo antes de contraatacar
                    if (equipoPoke[0].getHp() > 0) 
                    {
                        int defensaTux = tux.getDefensaEspecial();
                        String tipoTux = tux.getTipo();
                        daño = equipoPoke[0].ataqueBasico(defensaTux, tipoTux);
                        tux.restarVida(daño);
                        datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño

                    }
                } 
                else 
               {
                    // El Pokémon ataca primero con ataque especial
                    int defensaTux = tux.getDefensaEspecial();
                    String tipoTux = tux.getTipo();
                    int daño = equipoPoke[0].ataqueBasico(defensaTux, tipoTux);
                    tux.restarVida(daño);
                    datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño

                    // Verifica si Tux sigue vivo antes de contraatacar
                    if (tux.getHp() > 0) 
                    {
                        int defensaPoke = equipoPoke[0].getDefensaEspecial();
                        String tipoPoke = equipoPoke[0].getTipo();
                        daño = tux.ataqueEspecial(defensaPoke, tipoPoke);
                        equipoPoke[0].restarVida(daño);
                        datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño
                    }
                }
            }
            else
            { 
                //deshabilitamos lo botones 
                ataqueBasico.setDisable(true);
                AtaqueEs.setDisable(true); 

            }
        }
        else
        {
            mostrarVentanaPerdida();
        }
        
        tuxMuerto();
        
    }
    private void tuxMuerto()
    {
       if(tux.getHp()<=0)
        {
            System.out.println("has derrotado a tux" + "/n");
            System.out.println("!!!!!tux esta reviviendo¡¡¡¡¡¡" + "/n");
            tux=new Tux();
            System.out.println("TUX HA REVIVIDO" + "/n");
            datosTuxCombate(tux);
        } 
    }
    
    @FXML
    public void cambiarPokemon1() {
        cambiarPokemon(0);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    @FXML
    public void cambiarPokemon2() {
        cambiarPokemon(1);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    @FXML
    public void cambiarPokemon3() {
        cambiarPokemon(2);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    @FXML
    public void cambiarPokemon4() {
        cambiarPokemon(3);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    @FXML
    public void cambiarPokemon5() {
        cambiarPokemon(4);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    @FXML
    public void cambiarPokemon6() {
        cambiarPokemon(5);
        //Habilitamos los botones
        ataqueBasico.setDisable(false);
        AtaqueEs.setDisable(false);
    }

    private void cambiarPokemon(int indice) {
        if (equipoPoke != null && indice < equipoPoke.length && equipoPoke[indice] != null) {
            Pokemon seleccionado = equipoPoke[indice];
            for (int i = indice; i > 0; i--) {
                equipoPoke[i] = equipoPoke[i - 1];
            }
            equipoPoke[0] = seleccionado;

            actualizarImagenes(equipoPoke);
            datosPokemonCombate(equipoPoke[0]);
        }
    }
    private boolean mueto(Pokemon poke)
    {
        boolean estado=true;
        if (poke.getHp()<=0)
        {
            estado= false;
        }
        return estado;
    }
    private int equipoMuerto(Pokemon[] equipo)
    {
        int cont=0;
        for(int i=0;i<equipo.length; i++)
        {
            if(equipo[i].getHp()<=0 && equipo[i]!=null)
            {
                cont++;
            }
        }
        return cont;
    }
        
    
    private void mostrarVentanaPerdida() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("¡Has perdido!");
    alert.setHeaderText(null);
    alert.setContentText("Todos tus Pokémon han sido derrotados. ¡Inténtalo de nuevo!");

    alert.showAndWait();
    
    }
    @FXML
    private void cerrar(ActionEvent event )
    {
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
