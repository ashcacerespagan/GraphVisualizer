module com.ashleycacerespagan.maximalpoints.graphvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ashleycacerespagan.maximalpoints.graphvisualizer to javafx.fxml;
    exports com.ashleycacerespagan.maximalpoints.graphvisualizer;
}