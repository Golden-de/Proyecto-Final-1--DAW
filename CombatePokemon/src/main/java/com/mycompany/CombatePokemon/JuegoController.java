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
    }
    @FXML
    public void ataqueBasico()
    {
        
    }
    /*public void compararVelocidadYPelea(Pokemon pokemon1, Pokemon pokemon2) {
    if (pokemon1.getVelocidad() > pokemon2.getVelocidad()) {
        pokemon1.atacar(pokemon2);
        if (pokemon2.getHp() > 0) { // Si el Pokémon objetivo aún tiene vida, contraataca
            pokemon2.atacar(pokemon1);
        }
    } else if (pokemon1.getVelocidad() < pokemon2.getVelocidad()) {
        pokemon2.atacar(pokemon1);
        if (pokemon1.getHp() > 0) { // Si el Pokémon objetivo aún tiene vida, contraataca
            pokemon1.atacar(pokemon2);
        }
    } else {
        // Si las velocidades son iguales, lanzar una moneda
        if (Math.random() < 0.5) {
            pokemon1.atacar(pokemon2);
            if (pokemon2.getHp() > 0) { // Si el Pokémon objetivo aún tiene vida, contraataca
                pokemon2.atacar(pokemon1);
            }
        } else {
            pokemon2.atacar(pokemon1);
            if (pokemon1.getHp() > 0) { // Si el Pokémon objetivo aún tiene vida, contraataca
                pokemon1.atacar(pokemon2);
            }
        }
    }*/
    
}