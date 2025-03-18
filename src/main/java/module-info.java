module org.bryanmacedo.separacaosilabica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    opens guiClasses.controller to javafx.fxml;
    exports guiClasses.controller to javafx.fxml;

    opens org.bryanmacedo.separacaosilabica to javafx.fxml;
    exports org.bryanmacedo.separacaosilabica;
}