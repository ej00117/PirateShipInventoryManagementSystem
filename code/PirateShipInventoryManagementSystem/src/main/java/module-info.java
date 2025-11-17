module edu.westga.cs3211.pirateshipinventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;

    opens edu.westga.cs3211.pirateshipinventorymanagementsystem.view to javafx.fxml;
    exports edu.westga.cs3211.pirateshipinventorymanagementsystem;
}
