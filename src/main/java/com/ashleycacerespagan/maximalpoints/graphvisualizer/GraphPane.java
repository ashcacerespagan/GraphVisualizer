//Ashley Cáceres Pagán, Project 4: GraphVisualizer, July 9, 2024, The GraphPane class extends Pane from JavaFX and is
// responsible for visually displaying the graph. It handles mouse click events to create new vertices and includes
// methods to draw edges between vertices.

package com.ashleycacerespagan.maximalpoints.graphvisualizer;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GraphPane extends Pane {
    private final Graph graph;
    private int vertexCounter = 0;

    public GraphPane(Graph graph) {
        this.graph = graph;
        this.setStyle("-fx-border-color: black; -fx-background-color: white;");
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        String vertexName = String.valueOf((char) ('A' + vertexCounter++));
        Vertex vertex = new Vertex(x, y, vertexName);
        graph.addVertex(vertex);
        drawGraph();
    }

    public void drawGraph() {
        this.getChildren().clear();
        for (Vertex vertex : graph.getVertices()) {
            Circle circle = new Circle(vertex.getX(), vertex.getY(), 15, Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            Text text = new Text(vertex.getX() - 10, vertex.getY() + 5, vertex.getName());
            this.getChildren().addAll(circle, text);
        }

        for (Vertex vertex : graph.getVertices()) {
            for (Graph.Edge edge : graph.getEdges(vertex.getName())) {
                Vertex dest = graph.getVertex(edge.destination());
                if (dest != null) {
                    javafx.scene.shape.Line line = new javafx.scene.shape.Line(vertex.getX(), vertex.getY(), dest.getX(), dest.getY());
                    line.setStroke(Color.BLACK);
                    this.getChildren().add(line);
                }
            }
        }
    }

    public Graph getGraph() {
        return graph;
    }
}
