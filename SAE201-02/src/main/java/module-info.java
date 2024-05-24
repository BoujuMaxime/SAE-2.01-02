module org.example.sae20102 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;

    exports org.example.sae20102.Vue;

    opens org.example.sae20102 to javafx.fxml;
    exports org.example.sae20102;
    exports org.example.sae20102.Model;
    opens org.example.sae20102.Model to javafx.fxml;
}