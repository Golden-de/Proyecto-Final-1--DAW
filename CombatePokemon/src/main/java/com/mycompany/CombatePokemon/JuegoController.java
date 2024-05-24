/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
                int Vmasraoido1=pokemon[i].getVelocidad();
                int Vmasraoido2=masRapido.getVelocidad();
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
                defensaTux = equipoPoke[0].getDefensa();
                String tipoPoke = equipoPoke[0].getTipo();
                daño = tux.ataqueBasico(defensaTux, tipoPoke);
                equipoPoke[0].restarVida(daño);
                datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño
            }
        }
    }
    @FXML
    public void ataqueEspecial() {
        if (tux.getVelocidad() > equipoPoke[0].getVelocidad()) {
            // Tux ataca primero con ataque especial
            int defensaPoke = equipoPoke[0].getDefensaEspecial();
            String tipoPoke = equipoPoke[0].getTipo();
            int daño = tux.ataqueEspecial(defensaPoke, tipoPoke);
            equipoPoke[0].restarVida(daño);
            datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño

            // Verifica si el Pokémon sigue vivo antes de contraatacar
            if (equipoPoke[0].getHp() > 0) {
                int defensaTux = tux.getDefensaEspecial();
                String tipoTux = tux.getTipo();
                daño = equipoPoke[0].ataqueBasico(defensaTux, tipoTux);
                tux.restarVida(daño);
                datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño
            }
        } else {
            // El Pokémon ataca primero con ataque especial
            int defensaTux = tux.getDefensaEspecial();
            String tipoTux = tux.getTipo();
            int daño = equipoPoke[0].ataqueBasico(defensaTux, tipoTux);
            tux.restarVida(daño);
            datosTuxCombate(tux); // Actualiza la vista de Tux después de recibir daño

            // Verifica si Tux sigue vivo antes de contraatacar
            if (tux.getHp() > 0) {
                int defensaPoke = equipoPoke[0].getDefensaEspecial();
                String tipoPoke = equipoPoke[0].getTipo();
                daño = tux.ataqueEspecial(defensaPoke, tipoPoke);
                equipoPoke[0].restarVida(daño);
                datosPokemonCombate(equipoPoke[0]); // Actualiza la vista del Pokémon después de recibir daño
            }
        }
    }
}
