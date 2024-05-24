/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

/**
 *
 * @author Usuario
 */
public class Tux  extends Pokemon{
    private int nivel, vida, ataque, ataqueEs, velocidad, defensa, defensaEs, vidaMaxima;
    private String nombrePk= "Tux";
    private String tipo=" ";
    
    Tux()
    {
       Singleton sin =Singleton.getInstancia();
       String diff= sin.getdificultad();
        baseDeDatosControler bds= new baseDeDatosControler();
        
        int tTux= (int) (Math.random()*17);
        tipo=bds.tipoPoke(tTux);
        switch (diff) 
        {
            case "Pesadilla":
                nivel=100;
                vida=444;
                ataque=372;
                defensa=372;
                ataqueEs=372;
                defensaEs=372;
                velocidad=372;
                
            break;
            case "Veterano":
                nivel=75;
                vida=350;
                ataque=220;
                defensa=220;
                ataqueEs=220;
                defensaEs=220;
                velocidad=220;
            break;
            case "Marine":
                nivel=50;
                vida=227;
                ataque=189;
                defensa=189;
                ataqueEs=189;
                defensaEs=189;
                velocidad=189;
            break;
            case "Recluta":
                nivel=25;
                vida=180;
                ataque=112;
                defensa=112;
                ataqueEs=112;
                defensaEs=112;
                velocidad=112;
            break;
        } 
    }
    @Override
    public int getNivel()
    {
        return nivel;
    }
    @Override
    public String getNombre() {
        
        return nombrePk;
    }

    @Override
    public int getHp() {
        return vida;
    }

    @Override
    public int getAtaque() {
        return ataque;
    }

    @Override
    public int getDefensa() {
        return defensa;
    }

    @Override
    public int getAtaqueEspecial() {
        return ataqueEs;
    }

    @Override
    public int getDefensaEspecial() {
        return defensaEs;
    }

    @Override
    public int getVelocidad() {
        return velocidad;
    }
    
    public void setVidaMaxima()
    {
        vidaMaxima=vida;
    }
    public int getVidaMaxima()
    {
        return vidaMaxima;
    }
     
    @Override
    public String getTipo()
    {
        return tipo;
    }
    
    public void recibirdaño(int dañoRecibido)
    {
       vida=-dañoRecibido;
    }
}
