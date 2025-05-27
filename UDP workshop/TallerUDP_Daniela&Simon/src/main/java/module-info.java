module com.example.curriculum_sis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.curriculum_sis to javafx.fxml;
    exports com.example.curriculum_sis;
}