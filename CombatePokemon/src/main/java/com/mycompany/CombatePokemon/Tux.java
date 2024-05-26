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
    
    @Override
     public void restarVida(int dañoRecibido)
    {
        vida=vida -dañoRecibido;
    }
    
    @Override
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
    @Override
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
    
    @Override
   protected boolean estaMuerto() 
   {
       boolean estado=true;
       if (vida <= 0)
        {
            estado=false;
        }
    return estado;
   }
}
