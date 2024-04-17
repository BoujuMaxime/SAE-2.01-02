module org.example.sae20102 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sae20102 to javafx.fxml;
    exports org.example.sae20102;
}