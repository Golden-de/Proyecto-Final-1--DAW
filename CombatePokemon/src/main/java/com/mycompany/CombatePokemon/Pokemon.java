/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

/**
 *
 * @author Usuario
 */
public class Pokemon {
    private int id, nivel, vida, ataque, ataqueEs, velocidad, defensa, defensaEs;
    private String nombrePk, tipo;
    
     Pokemon()
    {
      
    }
    
    Pokemon(int i, String n, int hp, int at, int atEs, int def, int defEs, int vel, String t)
    {
        Singleton sin= Singleton.getInstancia();
        String diff =sin.getdificultad();
        switch(diff)
        {
            case "Pesadilla":
                nivel= 1+ (int)(Math.random()*24);
                double mejora =(nivel- 1)/24.0;
                id = i;
                nombrePk = n;
                vida = (int)(hp*  (1+ mejora));
                ataque = (int)(at* (1+ mejora));
                defensa = (int)(def* (1+ mejora));
                ataqueEs = (int)(atEs* (1+ mejora));
                defensaEs = (int)(defEs*(1+ mejora));
                velocidad = (int)(vel*(1+ mejora));
                tipo=t;
            break;
            case "Veterano":
                nivel=25+ (int)(Math.random()*26);
                mejora= (nivel-1)/25.0;
                id = i;
                nombrePk = n;
                vida = (int)(hp*  (1+ mejora));
                ataque = (int)(at*  (1+ mejora));
                defensa = (int)(def*  (1+ mejora));
                ataqueEs = (int)(atEs*  (1+ mejora));
                defensaEs = (int)(defEs*  (1+ mejora));
                velocidad = (int)(vel*  (1+ mejora));
                tipo=t;
            break;
            case "Recluta":
                nivel=50+ (int)(Math.random()*25);
                mejora= (nivel-1)/24.0;
                id = i;
                nombrePk = n;
                vida = (int)(hp*  (1+ mejora));
                ataque = (int)(at*  (1+ mejora));
                defensa = (int)(def*  (1+ mejora));
                ataqueEs = (int)(atEs*  (1+ mejora));
                defensaEs = (int)(defEs*  (1+ mejora));
                velocidad = (int)(vel*  (1+ mejora));
                tipo=t;
            break;
            case "Marine":
                nivel=75+ (int)(Math.random()*25);
                mejora= (nivel-1)/24.0;
                id = i;
                nombrePk = n;
                vida = (int)(hp*  (1+ mejora));
                ataque = (int)(at*  (1+ mejora));
                defensa = (int)(def*  (1+ mejora));
                ataqueEs = (int)(atEs*  (1+ mejora));
                defensaEs = (int)(defEs*  (1+ mejora));
                velocidad = (int)(vel*  (1+ mejora));
                tipo=t;
            break;
        }
        
    }
        // Getters
    public int getNivel()
    {
        return nivel;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombrePk;
    }

    public int getHp() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEs;
    }

    public int getDefensaEspecial() {
        return defensaEs;
    }

    public int getVelocidad() {
        return velocidad;
    }
    public String getTipo() 
    {
        return tipo;
    }
    
    public void restarVida(int dañoRecibido)
    {
        vida=-dañoRecibido;
    }
    
    public Integer ataqueBasico(int defensaR, String tipoRival)
    {
        baseDeDatosControler bds= new baseDeDatosControler();
        double[][] tablaTipos= tablaTipos();
        int idtipo=bds.nombretipo(tipo);
        int idTipoRival=bds.nombretipo(tipoRival);
        
        double efectividad=tablaTipos[idtipo][idTipoRival];
        int dEnemigoEs= defensaR;
        int v = 85 + (int)(Math.random() * 16);        
        int daño = (int) (0.1*1*efectividad*v*(((0.2*nivel+1)*ataqueEs)/(25 *dEnemigoEs)+2));
        return daño;
    }
     public Integer ataqueEspecial(int defensaR, String tipoRival)
    {
        baseDeDatosControler bds= new baseDeDatosControler();
        double[][] tablaTipos= tablaTipos();
        int idtipo=bds.nombretipo(tipo);
        int idTipoRival=bds.nombretipo(tipoRival);
        
        double efectividad=tablaTipos[idtipo][idTipoRival];
        int dEnemigo= defensaR;
        int v = 85 + (int)(Math.random() * 16);        
        int daño = (int) (0.1*1*efectividad*v*(((0.2*nivel+1)*ataque)/(25 *dEnemigo)+2));
        return daño;
    }
    private double[][] tablaTipos()
    {
        double[][] tabla = new double [18][18];
        for( int i=0;i<18;i++)
        {
            for(int j=0;j<18;j++)
            {
                tabla[i][j] = 1;
            }
        } 
        
        for(int i = 0;i <18;i++)
        {
            tabla[0][i] = 0;
        }
        
        tabla[1][13] = 1.25 ;
        tabla[1][14] = 0 ;
        tabla[1][17] = 1.25 ;
        tabla[2][2] = 1.25 ;
        tabla[2][3] = 1.25 ;
        tabla[2][4] = 1.75  ;
        tabla[2][6] = 1.25 ;
        tabla[2][12] = 1.75  ;
        tabla[2][13] = 1.25 ;
        tabla[2][15] = 1.25 ;
        tabla[2][17] = 1.75  ;
        tabla[3][2] = 1.75  ;
        tabla[3][3] = 1.25 ;
        tabla[3][4] = 1.25 ;
        tabla[3][9] = 1.75  ;
        tabla[3][13] = 1.75 ;
        tabla[3][15] = 1.25 ;
        tabla[4][2] = 1.25 ;
        tabla[4][3] = 1.75 ;
        tabla[4][4] = 1.25 ;
        tabla[4][8] = 1.25 ;
        tabla[4][9] = 1.75 ;
        tabla[4][10] = 1.25 ;
        tabla[4][12] = 1.25 ;
        tabla[4][13] = 1.75 ;
        tabla[4][15] = 1.25 ;
        tabla[4][17] = 1.25 ;
        tabla[5][3] = 1.75 ;
        tabla[5][4] = 1.25 ;
        tabla[5][5] = 1.25 ;
        tabla[5][9] = 0 ;
        tabla[5][10] = 1.75 ;
        tabla[5][15] = 1.25 ;
        tabla[6][2] = 1.25 ;
        tabla[6][3] = 1.25 ;
        tabla[6][4] = 1.75 ;
        tabla[6][6] = 1.25 ;
        tabla[6][9] = 1.75 ;
        tabla[6][10] = 1.75 ;
        tabla[6][15] = 1.75 ;
        tabla[6][17] = 1.25 ;
        tabla[7][1] = 1.75 ;
        tabla[7][6] = 1.75 ;
        tabla[7][8] = 1.25 ;
        tabla[7][10] = 1.25 ;
        tabla[7][11] = 1.25 ;
        tabla[7][12] = 1.25 ;
        tabla[7][13] = 1.75 ;
        tabla[7][14] = 0 ;
        tabla[7][16] = 1.75 ;
        tabla[7][17] = 1.75 ;
        tabla[8][4] = 1.75 ;
        tabla[8][8] = 1.25 ;
        tabla[8][9] = 1.25 ;
        tabla[8][13] = 1.25 ;
        tabla[8][14] = 1.25 ;
        tabla[8][17] = 0 ;
        tabla[9][2] = 1.75 ;
        tabla[9][4] = 1.25 ;
        tabla[9][5] = 1.25 ;
        tabla[9][8] = 1.75 ;
        tabla[9][10] = 0 ;
        tabla[9][12] = 1.25 ;
        tabla[9][13] = 1.75 ;
        tabla[9][17] = 1.75 ;
        tabla[10][4] = 1.75 ;
        tabla[10][5] = 1.25 ;
        tabla[10][7] = 1.75 ;
        tabla[10][12] = 1.75 ;
        tabla[10][13] = 1.25 ;
        tabla[10][17] = 1.25 ;
        tabla[11][7] = 1.75 ;
        tabla[11][8] = 1.75 ;
        tabla[11][11] = 1.25 ;
        tabla[11][16] = 0 ;
        tabla[11][17] = 1.25 ;
        tabla[12][2] = 1.25 ;
        tabla[12][4] = 1.75 ;
        tabla[12][7] = 1.25 ;
        tabla[12][8] = 1.25 ;
        tabla[12][10] = 1.25 ;
        tabla[12][11] = 1.75 ;
        tabla[12][14] = 1.25 ;
        tabla[12][16] = 1.75 ;
        tabla[12][17] = 1.25 ;
        tabla[13][2] = 1.75 ;
        tabla[13][6] = 1.75 ;
        tabla[13][7] = 1.25 ;
        tabla[13][9] = 1.25 ;     
        tabla[13][10] = 1.75 ;
        tabla[13][12] = 1.75 ;
        tabla[13][17] = 1.25 ;
        tabla[14][1] = 0 ;
        tabla[14][11] = 1.75 ;
        tabla[14][14] = 1.75 ;
        tabla[14][16] = 1.25 ;
        tabla[15][15] = 1.75 ;
        tabla[15][17] = 1.25 ;
        tabla[16][7] = 1.25 ;
        tabla[16][11] = 1.75 ;
        tabla[16][14] = 1.75 ;
        tabla[16][16] = 1.25 ;
        tabla[17][2] = 1.25 ;
        tabla[17][3] = 1.25 ;
        tabla[17][5] = 1.25 ;
        tabla[17][6] = 1.75 ;
        tabla[17][13] = 1.75 ;
        tabla[17][17] = 1.25 ;
        
        return tabla ;
    }
    
   public boolean estaMuerto() 
   {
    return vida <= 0;
   }
}
