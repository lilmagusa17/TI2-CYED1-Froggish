module com.froggish.froggish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.froggish.froggish to javafx.fxml;
    exports com.froggish.froggish;
    exports com.froggish.froggish.control;
    opens com.froggish.froggish.control to javafx.fxml;
    exports com.froggish.froggish.model;
    opens com.froggish.froggish.model to javafx.fxml;
    exports com.froggish.froggish.graph;
    opens com.froggish.froggish.graph to javafx.fxml;


}