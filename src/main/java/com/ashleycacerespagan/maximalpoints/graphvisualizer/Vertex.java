//Ashley Cáceres Pagán, Project 4: GraphVisualizer, July 9, 2024, The Vertex class represents a vertex in the graph,
// containing its x and y coordinates and a name. It is immutable and provides getter methods to access these properties.

package com.ashleycacerespagan.maximalpoints.graphvisualizer;

public class Vertex {
    private final double x;
    private final double y;
    private final String name;

    public Vertex(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
