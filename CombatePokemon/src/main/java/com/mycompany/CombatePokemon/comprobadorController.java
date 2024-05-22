package com.mycompany.CombatePokemon;

import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 

public class comprobadorController
{
    Singleton singleton= Singleton.getInstancia();
    
    Connection conexion = null;
    @FXML
    Button conectar;
    @FXML
    Label compro;
    @FXML
    TextField Usuario,driver,IP,host,nbd;
    @FXML
    TextField Contraseña;
    @FXML
    Button salir;
    @FXML
    ImageView fondo;
    
    @FXML
    private void initialize() 
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       fondo.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
    }
    
    
    @FXML
    private void mostrarConexion() throws SQLException, ClassNotFoundException{
        
        baseDeDatosControler bds;
        
        String user=Usuario.getText();
        String cont=Contraseña.getText();
        String driv=driver.getText();
        String ip=IP.getText();
        String h=host.getText();
        String nBase=nbd.getText();
        singleton.setUsuario(user);
        singleton.setContraseña(cont);
        singleton.setIp(ip);
        singleton.setHost(h);
        singleton.setdriver(driv);
        singleton.setNombreBaseDatos(nBase);
        
        
        
        bds=new baseDeDatosControler();
       
        Alert alerta =new Alert(AlertType.INFORMATION);
        
        boolean exitosa=bds.test();
        if (exitosa == true) {
            alerta.setContentText("Conexión exitosa!");
        } else {
            alerta.setContentText("No se pudo establecer la conexión.");
        }
        alerta.showAndWait();
    }
    
   
    
    @FXML
    private void salir(ActionEvent event) {
        // Obtener la referencia a la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }
    @FXML
    Button Entrar;
    @FXML
    private void abrir1()
   //abre la nueva ventana cerrando la principal
   {
        
        try 
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Inicio.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Obtener el Stage de la ventana principal
            Stage ventanaPrincipal = (Stage) Usuario.getScene().getWindow();

            // Cerrar la ventana principal
            ventanaPrincipal.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
   }
}
