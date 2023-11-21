module com.froggish.froggish {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.froggish.froggish to javafx.fxml;
    exports com.froggish.froggish;
    exports com.froggish.froggish.control;
    opens com.froggish.froggish.control to javafx.fxml;
}