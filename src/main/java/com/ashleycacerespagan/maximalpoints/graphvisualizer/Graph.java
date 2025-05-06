//Ashley Cáceres Pagán, Project 4: GraphVisualizer, July 9, 2024, The Graph class defines an undirected graph using an
// adjacency list representation. It includes methods to add vertices and edges, check for cycles and connectivity, and
// perform depth-first and breadth-first searches. An inner class Edge represents the edges of the graph, containing
// source, destination, and weight.

package com.ashleycacerespagan.maximalpoints.graphvisualizer;

import java.util.*;

public class Graph {
    private final Map<String, Vertex> vertices = new HashMap<>();
    private final Map<String, List<Edge>> adjList = new HashMap<>();

    public void addVertex(Vertex vertex) {
        if (!vertices.containsKey(vertex.getName())) {
            vertices.put(vertex.getName(), vertex);
            adjList.put(vertex.getName(), new ArrayList<>());
        }
    }

    public void addEdge(String name1, String name2) {
        addEdge(name1, name2, 1); // default weight of 1
    }

    public void addEdge(String name1, String name2, int weight) {
        if (vertices.containsKey(name1) && vertices.containsKey(name2)) {
            Edge edge1 = new Edge(name1, name2, weight);
            Edge edge2 = new Edge(name2, name1, weight);
            adjList.get(name1).add(edge1);
            adjList.get(name2).add(edge2);
        }
    }

    public boolean hasCycles() {
        Set<String> visited = new HashSet<>();
        for (String vertex : vertices.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCyclesDFS(vertex, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCyclesDFS(String current, Set<String> visited, String parent) {
        visited.add(current);
        for (Edge edge : adjList.get(current)) {
            String neighbor = edge.destination();
            if (!visited.contains(neighbor)) {
                if (hasCyclesDFS(neighbor, visited, current)) {
                    return true;
                }
            } else if (!neighbor.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        if (vertices.isEmpty()) {
            return true;
        }
        Set<String> visited = new HashSet<>();
        dfs(vertices.keySet().iterator().next(), visited);
        return visited.size() == vertices.size();
    }

    private void dfs(String vertex, Set<String> visited) {
        visited.add(vertex);
        for (Edge edge : adjList.get(vertex)) {
            String neighbor = edge.destination();
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    public List<String> depthFirstSearch() {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        if (!vertices.isEmpty()) {
            dfs(vertices.keySet().iterator().next(), visited, result);
        }
        return result;
    }

    private void dfs(String vertex, Set<String> visited, List<String> result) {
        visited.add(vertex);
        result.add(vertex);
        for (Edge edge : adjList.get(vertex)) {
            String neighbor = edge.destination();
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, result);
            }
        }
    }

    public List<String> breadthFirstSearch() {
        List<String> result = new ArrayList<>();
        if (vertices.isEmpty()) {
            return result;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = vertices.keySet().iterator().next();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);
            for (Edge edge : adjList.get(current)) {
                String neighbor = edge.destination();
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public List<Edge> getEdges(String vertexName) {
        return adjList.getOrDefault(vertexName, Collections.emptyList());
    }

    public static class Edge {
        private final String source;
        private final String destination;
        private final int weight;

        public Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String source() {
            return source;
        }

        public String destination() {
            return destination;
        }

        public int weight() {
            return weight;
        }
    }
}
