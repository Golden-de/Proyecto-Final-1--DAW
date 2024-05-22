/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

/**
 *
 * Clase para el intercambio de informaci�n entre diferentes ventanas
 * En nombre de la clase puede ser el que se quiera
 */
public class Singleton 
{
   private String usuario, contraseña,nombreBaseDatos, url,driver, IP,h, dificultad, entrenador;
   private final static Singleton INSTANCIA = new Singleton();
   private Singleton(){}
   //El constructor es privado para evitar que se puedan crear instancias de esta clase
   
   public static Singleton getInstancia()
   {
       return INSTANCIA;
   } 
    List<String> logs;
    // Métodos getter y setter para los datos de conexión
    public void addLog(String message) {
        logs.add(message + "\n");
    }

    public String getLog() {
        return logs.toString();
    }
    
    public String getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(String u) 
    {
        usuario = u;
    }

    public String getContraseña() 
    {
        return contraseña;
    }

    public void setContraseña(String c) 
    {
        contraseña= c;
    }

    public String getNombreBaseDatos() 
    {
        return nombreBaseDatos;
    }

    public void setNombreBaseDatos(String nBD) 
    {
        nombreBaseDatos = nBD;
    }
    
    public String getUrl ()
    {
        return url;
    }
    public void setdriver(String d) 
    {
        driver = d;
    }
    public String getDriver()
    {
        return driver;
    }    
    public void setIp(String ip)
    {
        IP=ip;
    }
    public String getIp()
    {
        return IP;
    }
    public void setHost(String Host)
    {
        h=Host;
    }
    public String getHost()
    {
        return h;
    }
    public void setUrl(String url)
    {
        this.url=url;
    }
    
   public void setdificultad(String diff)
   {
       dificultad=diff;
   }
   
   public String getdificultad()
   {
       return dificultad;
   }
   public void setEntrenador(String entre)
   {
       entrenador= entre;
   }
   public String getEntrenador()
   {
       return entrenador;
   }
}
