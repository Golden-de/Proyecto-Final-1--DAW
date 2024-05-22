/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author usuario
 */
public class JuegoController {
    Tux tux;
    
    @FXML
    ImageView imageTux;
    @FXML
    ImageView fondo;
    @FXML
    ImageView poke1, poke2, poke3, poke4,poke5,poke6;
    
    
    
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
        
        Pokemon[] equipoPoke = new Pokemon[6];
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
        Pokemon masRapido=masRapido(equipoPoke);
        
        reordenarPorVelocidad(equipoPoke, masRapido);
        actualizarImagenes(equipoPoke);
    }
    
    private Pokemon masRapido(Pokemon [] pokemon)
    {
        Pokemon masRapido= pokemon[0];
        for(int i =1; i<pokemon.length; i++)
        {
            if(pokemon[i].getVelocidad()>masRapido.getVelocidad())
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
            // Actualizar la imagen en la posición correspondiente
            fotos[i].setImage(new Image("file:.\\imagesPokemon\\" + pokemon[i].getId() + ".png"));
            }
        }
    }
    
}
