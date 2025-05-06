//Ashley Cáceres Pagán, Project 4: GraphVisualizer, July 9, 2024, The GraphApp class extends Application from JavaFX
// and sets up the main GUI of the application. It includes the primary stage, input fields, buttons for adding edges
// and analyzing the graph, and a message label to display results. The class initializes the graph and handles user interactions.

package com.ashleycacerespagan.maximalpoints.graphvisualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphApp extends Application {
    private GraphPane graphPane;
    private TextField vertex1Field;
    private TextField vertex2Field;
    private Label messageLabel;

    @Override
    public void start(Stage primaryStage) {
        graphPane = new GraphPane(new Graph());
        vertex1Field = new TextField();
        vertex2Field = new TextField();
        messageLabel = new Label();

        // Top controls
        HBox topControls = new HBox(10);
        topControls.getChildren().addAll(createAddEdgeButton(), new Label("Vertex 1:"), vertex1Field, new Label("Vertex 2:"), vertex2Field);

        // Bottom controls
        HBox bottomControls = new HBox(10);
        bottomControls.getChildren().addAll(createIsConnectedButton(), createHasCyclesButton(), createDepthFirstSearchButton(), createBreadthFirstSearchButton());

        VBox bottomBox = new VBox(10);
        bottomBox.getChildren().addAll(bottomControls, messageLabel);

        BorderPane root = new BorderPane();
        root.setTop(topControls);
        root.setCenter(graphPane);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Graph Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createAddEdgeButton() {
        Button addEdgeButton = new Button("Add Edge");
        addEdgeButton.setOnAction(e -> {
            String vertex1 = vertex1Field.getText();
            String vertex2 = vertex2Field.getText();
            if (!graphPane.getGraph().getVertices().contains(new Vertex(0, 0, vertex1)) || !graphPane.getGraph().getVertices().contains(new Vertex(0, 0, vertex2))) {
                messageLabel.setText("One or both vertices not found: " + vertex1 + ", " + vertex2);
            } else {
                graphPane.getGraph().addEdge(vertex1, vertex2);
                graphPane.drawGraph();
            }
        });
        return addEdgeButton;
    }

    private Button createIsConnectedButton() {
        Button button = new Button("Is Connected?");
        button.setOnAction(e -> {
            boolean isConnected = graphPane.getGraph().isConnected();
            messageLabel.setText(isConnected ? "The graph is connected." : "The graph is not connected.");
        });
        return button;
    }

    private Button createHasCyclesButton() {
        Button button = new Button("Has Cycles?");
        button.setOnAction(e -> {
            boolean hasCycles = graphPane.getGraph().hasCycles();
            messageLabel.setText(hasCycles ? "The graph has cycles." : "The graph does not have cycles.");
        });
        return button;
    }

    private Button createDepthFirstSearchButton() {
        Button button = new Button("Depth First Search");
        button.setOnAction(e -> {
            String result = String.join(", ", graphPane.getGraph().depthFirstSearch());
            messageLabel.setText("DFS: " + result);
        });
        return button;
    }

    private Button createBreadthFirstSearchButton() {
        Button button = new Button("Breadth First Search");
        button.setOnAction(e -> {
            String result = String.join(", ", graphPane.getGraph().breadthFirstSearch());
            messageLabel.setText("BFS: " + result);
        });
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
