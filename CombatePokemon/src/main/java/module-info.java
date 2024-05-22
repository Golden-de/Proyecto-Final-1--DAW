module com.mycompany.proyectoenblanco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.mycompany.CombatePokemon to javafx.fxml;
    exports com.mycompany.CombatePokemon;
}
