//Ashley Cáceres Pagán, Project 4: GraphVisualizer, July 9, 2024, The GraphTest class contains JUnit test cases to
// verify the functionality of the Graph class. It includes tests for adding vertices and edges, checking connectivity
// and cycles, and performing depth-first and breadth-first searches. The tests ensure that the graph operations work as expected.

package com.ashleycacerespagan.maximalpoints.graphvisualizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class GraphTest {

    @Test
    void testAddVertex() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        graph.addVertex(v1);
        assertEquals(1, graph.getVertices().size());
        assertNotNull(graph.getVertex("A"));
    }

    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        Vertex v2 = new Vertex(3.0, 4.0, "B");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge("A", "B");
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(1, graph.getEdges("B").size());
    }

    @Test
    void testHasCycles() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        Vertex v2 = new Vertex(3.0, 4.0, "B");
        Vertex v3 = new Vertex(5.0, 6.0, "C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        assertTrue(graph.hasCycles());
    }

    @Test
    void testIsConnected() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        Vertex v2 = new Vertex(3.0, 4.0, "B");
        Vertex v3 = new Vertex(5.0, 6.0, "C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        assertTrue(graph.isConnected());
    }

    @Test
    void testDepthFirstSearch() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        Vertex v2 = new Vertex(3.0, 4.0, "B");
        Vertex v3 = new Vertex(5.0, 6.0, "C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        List<String> dfsResult = graph.depthFirstSearch();
        assertEquals(3, dfsResult.size());
        assertEquals("A", dfsResult.get(0));
    }

    @Test
    void testBreadthFirstSearch() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1.0, 2.0, "A");
        Vertex v2 = new Vertex(3.0, 4.0, "B");
        Vertex v3 = new Vertex(5.0, 6.0, "C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        List<String> bfsResult = graph.breadthFirstSearch();
        assertEquals(3, bfsResult.size());
        assertEquals("A", bfsResult.get(0));
    }

    // Edge Cases and Error Handling

    @Test
    void testAddEdgeNonExistentVertices() {
        Graph graph = new Graph();
        graph.addEdge("X", "Y");
        assertTrue(graph.getVertices().isEmpty());
    }

    @Test
    void testHasCyclesEmptyGraph() {
        Graph graph = new Graph();
        assertFalse(graph.hasCycles());
    }

    @Test
    void testIsConnectedEmptyGraph() {
        Graph graph = new Graph();
        assertTrue(graph.isConnected());
    }

    @Test
    void testDepthFirstSearchEmptyGraph() {
        Graph graph = new Graph();
        List<String> dfsResult = graph.depthFirstSearch();
        assertTrue(dfsResult.isEmpty());
    }

    @Test
    void testBreadthFirstSearchEmptyGraph() {
        Graph graph = new Graph();
        List<String> bfsResult = graph.breadthFirstSearch();
        assertTrue(bfsResult.isEmpty());
    }
}