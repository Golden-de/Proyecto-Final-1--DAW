/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CombatePokemon;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.ResultSetMetaData;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

import javafx.fxml.FXML;
/**
 *
 * @author usuario
 */
public class baseDeDatosControler {
    
    public baseDeDatosControler(){}
    
    private static Connection realizarConexion() {
        Singleton singleton= Singleton.getInstancia();
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String user=singleton.getUsuario();
        String url=singleton.getUrl();
        String contraseña=singleton.getContraseña();
        
        try {
            // Establecer conexión a la base de datos
            conexion = DriverManager.getConnection(url , user, contraseña);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return conexion;
    }
    
    @FXML
    public boolean test() throws SQLException , ClassNotFoundException
    {
        Connection conexion;
        Singleton singleton= Singleton.getInstancia();
        boolean conexionExitosa = false;
        String usuario =singleton.getUsuario();
        String contraseña =singleton.getContraseña();
        String bdnombre = singleton.getNombreBaseDatos();
        String host=singleton.getHost();
        String ip=singleton.getIp();
        String driver=singleton.getDriver(); 
        String url = "jdbc:mysql://"+host+":"+ip+"/" + bdnombre;

            try 
            {
                Class.forName("com.mysql.jdbc."+driver);

                conexion = DriverManager.getConnection(url, usuario, contraseña);
                conexionExitosa = true;

            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                
            }
           singleton.setNombreBaseDatos(bdnombre);
           singleton.setContraseña(contraseña);
           singleton.setUrl(url);
           singleton.setUsuario(usuario);
           
        return conexionExitosa;
    }
    public static void cerrarConexion(Connection conexion) 
    {
        try 
        {
            if (conexion != null) 
            {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } 
        catch (Exception e) 
        {
            // Manejo de la excepción en caso de error al cerrar la conexión
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    public static List<Map<String, String>> obtenerDatos(ResultSet rs, List<Map<String, String>> ListaMapas) throws SQLException 
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            Map<String, String> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                String columnValue = rs.getString(i);  // Convert the column value to String
                row.put(columnName, columnValue);
            }
            ListaMapas.add(row);
        }
        return ListaMapas;
    }
    
   public List<Map<String,String>> nombrePok()
   {
        // Crear una lista de mapas para almacenar los nombres de los Pokémon
        List<Map<String, String>> listaDeMapas = new ArrayList<>();
        
        // Obtener una instancia del Singleton
        Singleton singleton = Singleton.getInstancia();
       
        // Establecer la conexión a la base de datos
        Connection conexion = realizarConexion();
        try {
            
            
            String consulta = "SELECT POKEMON FROM Pokemon";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();

            // Crear un nuevo mapa para cada Pokémon y agregarlo a la lista
            listaDeMapas= obtenerDatos(rs, listaDeMapas);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 

        // Cerrar la conexión a la base de datos
        cerrarConexion(conexion);
        
        return listaDeMapas;
    }
    // Método para obtener el id del pokemon dado su nombre
    public List<Map<String, String>> obtenerIDPokemon(String npokemon) 
    {
      
        Connection conexion=realizarConexion(); 
        List<Map<String, String>> listaPokemon = new ArrayList<>();
        try 
        {
            // Consulta SQL para obtener los datos del Pokémon
            String consulta = "SELECT ID_Pokemon FROM Pokemon where Pokemon ='"+ npokemon+"'";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();
            listaPokemon=obtenerDatos(rs,listaPokemon);
        }     
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        cerrarConexion(conexion);
        
      return listaPokemon;
    }
     public Integer obtenerID(String npokemon) 
    {
        int id=0;
        Connection conexion=realizarConexion(); 
        try 
        {
            // Consulta SQL para obtener los datos del Pokémon
            String consulta = "SELECT ID_Pokemon FROM Pokemon where Pokemon ='"+ npokemon+"'";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();
           if(rs.next())
           {
              id=rs.getInt("ID_Pokemon");
           }
        }     
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        cerrarConexion(conexion);
        
      return id;
    }
   // Método para obtener todos los datos de un Pokémon dado su Nombre
    public List<Map<String,String>> obtenerDatosPokemon(String IdPokemon) 
    {
        Connection conexion=realizarConexion(); 
        List<Map<String, String>> listaPokemon = new ArrayList<>();
        try 
        {
            
            // Consulta SQL para obtener los datos del Pokémon
            String consulta = "SELECT * FROM Pokemon where ID_Pokemon ='"+ IdPokemon+"'";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();
            listaPokemon=obtenerDatos(rs,listaPokemon);
        }     
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        cerrarConexion(conexion);
        
      return listaPokemon;
      
    }
    public Integer IDtipoPoke(String idPokemon)
    {
        // Establecer la conexión a la base de datos
        int IDtipo= 0;
        List<Map<String, String>> listaTipos= new ArrayList<>();
        Connection conexion=realizarConexion();
        try  
        {
            
            // Consulta SQL para obtener Pokémon y sus tipos asociados
            String consulta = "SELECT ID_Type from Rel_Pokemon_Type where ID_Pokemon ='" + idPokemon + "'";

            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet rs = statement.executeQuery();
            if(rs.next())
           {
              IDtipo=rs.getInt("ID_Type");
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return IDtipo;
    }
    
    public List<Map<String,String>> pokemonEntrenador(Integer ID_Entrenador)
    {
        Connection conexion= realizarConexion();
        List<Map<String, String>> listaEquipo = new ArrayList<>();
        try  
        {
            
            // Consulta SQL para obtener los Pokémon de cada entrenador
            String consulta = "SELECT ID_Pokemon from Rel_Trainer_Pokemon where ID_Trainer =' "+ID_Entrenador+"'";

            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet rs = statement.executeQuery();

            listaEquipo=obtenerDatos(rs,listaEquipo);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        cerrarConexion(conexion);
        
        return listaEquipo;
    }
    
    public  List<Map<String,String>> entrenadores()
    {
        List<Map<String, String>> listaEntrenadores = new ArrayList<>();
        Connection conexion= realizarConexion();
            
        String consulta= "Select Trainer from Trainers";
        try
        {
            
            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(consulta);
            
            //S.e ejecuta la consulta
            ResultSet rs= statement.executeQuery();
            
            //Se Porcesan los resultado de la consulta
            listaEntrenadores=obtenerDatos(rs,listaEntrenadores);
            
        }
       catch (SQLException e) 
        {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return listaEntrenadores;
    }
    
    public Integer idEntrenador(String nombre) 
    {
        int id=0;
        Connection conexion=realizarConexion();
        String consulta="Select ID_Trainer from Trainers where Trainer ='"+ nombre+"'";
      
        try
        {            
            PreparedStatement statement=conexion.prepareStatement(consulta);
            //S.e ejecuta la consulta
            ResultSet rs= statement.executeQuery();
            if(rs.next())
            {
                id=rs.getInt("ID_Trainer");
            }         
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return id;
    }
    
    public String tipoPoke(int idtipo)
    {
        String tipo= "";
        Connection conexion=realizarConexion();
        String consulta="Select Type from  Types where ID_Type ='"+ idtipo+"'";
      
        try
        {            
            PreparedStatement statement=conexion.prepareStatement(consulta);
            //S.e ejecuta la consulta
            ResultSet rs= statement.executeQuery();
            if(rs.next())
            {
                tipo=rs.getString("Type");
            }         
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return tipo;
    }
    public Integer nombretipo(String ntipo)
    {
        int idTipo=0; 
        Connection conexion=realizarConexion();
        String consulta="Select ID_Type from  Types where Type ='"+ ntipo+"'";
      
        try
        {            
            PreparedStatement statement=conexion.prepareStatement(consulta);
            //S.e ejecuta la consulta
            ResultSet rs= statement.executeQuery();
            if(rs.next())
            {
                idTipo=rs.getInt("ID_Type");
            }         
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return idTipo;
    }
    public Integer IDtipoTux(int tTux)
    {
        // Establecer la conexión a la base de datos
        int IDtipo= 0;
        List<Map<String, String>> listaTipos= new ArrayList<>();
        Connection conexion=realizarConexion();
        try  
        {
            
            // Consulta SQL para obtener Pokémon y sus tipos asociados
            String consulta = "SELECT ID_Type from Rel_Pokemon_Type where ID_Pokemon ='" + tTux + "'";

            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet rs = statement.executeQuery();
            if(rs.next())
           {
              IDtipo=rs.getInt("ID_Type");
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        cerrarConexion(conexion);
        
        return IDtipo;
    }
}


