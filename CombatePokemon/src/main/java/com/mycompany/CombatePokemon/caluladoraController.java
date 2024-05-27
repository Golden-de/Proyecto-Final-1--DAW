package com.mycompany.CombatePokemon;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
 

public class caluladoraController {

    ToggleGroup groupBon, efec;
    @FXML
    TextField constante, ataque, defensa;
    @FXML
    TextArea result;
    @FXML
    Button aleatorio, calcular, salir;
    @FXML
    RadioButton bon0, bon1, bon125, bon175;
    @FXML
    RadioButton e0,e025, e050, e1, e2, e4;
    @FXML
    Slider valor, nivel;
    @FXML
    Label mostrarValor, mostrarNivel;
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {
       imagen.setImage(new Image("file:.\\Imagenes\\pokemon.png"));
       
       groupBon =new ToggleGroup();
       bon0.setToggleGroup(groupBon);
       bon0.setSelected(true);
       bon0.setUserData(0.0);
       bon1.setToggleGroup(groupBon);
       bon1.setUserData(1.0);
       bon125.setToggleGroup(groupBon);
       bon125.setUserData(1.25);
       bon175.setToggleGroup(groupBon);
       bon175.setUserData(1.17);
       
       efec= new ToggleGroup();
       e0.setToggleGroup(efec);
       e0.setSelected(true);
       e0.setUserData(0.0);
       e025.setToggleGroup(efec);
       e025.setUserData(0.25);
       e050.setToggleGroup(efec);
       e050.setUserData(0.50);
       e1.setToggleGroup(efec);
       e1.setUserData(1.0);
       e2.setToggleGroup(efec);
       e2.setUserData(2.0);
       e4.setToggleGroup(efec);
       e4.setUserData(4.0);
       
    }
    @FXML
    public void valoresRamdom()
    {
        
        
        double cons =(double)(Math.random()*1);
         constante.setText(String.format("%.2f", cons));
        int atac=(int)(Math.random()*500);
        ataque.setText(Integer.toString(atac));
        int defe =(int)(Math.random()*500);
        defensa.setText(Integer.toString(defe));
         // Selecionar aleatoriamente un RadioButton 
        selecionarToggleAleatorio(groupBon);

        // Selecionar aleatoriamente un RadioButton
        selecionarToggleAleatorio(efec);
        
        // Gerar valores aleatórios para os sliders (valores inteiros de 0 a 100)
        int valorRandom = (int)(Math.random()*101); // Valor entre 0 e 100
        valor.setValue(valorRandom);
        
        
        int nivelRandom = (int)(Math.random()*101); // Valor entre 0 e 100
        nivel.setValue(nivelRandom);     
    }
    @FXML
    public void calcularDaño()
    {
        Pokemon poke= new Pokemon();
        int ataq=Integer.parseInt(ataque.getText());
        int def=Integer.parseInt(defensa.getText());
        int nive=(int)(nivel.getValue());
        int val=(int)(valor.getValue());
        double bonValue = (double) groupBon.getSelectedToggle().getUserData();
        double efecValue = (double) efec.getSelectedToggle().getUserData();
        
    }
    private void selecionarToggleAleatorio(ToggleGroup group) {
        List<Toggle> toggles = group.getToggles();
        Random random = new Random();
        int randomIndex = random.nextInt(toggles.size());
        Toggle randomToggle = toggles.get(randomIndex);
        group.selectToggle(randomToggle);
    }
    @FXML
    Button Salir;
    @FXML
    private void salir(ActionEvent event) {
        // Obtener la referencia a la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }
    @FXML
    private ImageView imagen;
        
    
    
    
}
