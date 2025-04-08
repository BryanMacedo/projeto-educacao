module org.bryanmacedo.atividadesEducacionais {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens guiClasses.controller to javafx.fxml, javafx.graphics;
    exports guiClasses.controller to javafx.fxml;

    opens org.bryanmacedo.atividadesEducacionais to javafx.fxml;
    exports org.bryanmacedo.atividadesEducacionais;

    exports guiClasses.controller.memoryActivity to javafx.fxml;
    opens guiClasses.controller.memoryActivity to javafx.fxml, javafx.graphics;
    exports guiClasses.controller.mathActivity to javafx.fxml;
    opens guiClasses.controller.mathActivity to javafx.fxml, javafx.graphics;
    exports guiClasses.controller.vowelActivity to javafx.fxml;
    opens guiClasses.controller.vowelActivity to javafx.fxml, javafx.graphics;
}