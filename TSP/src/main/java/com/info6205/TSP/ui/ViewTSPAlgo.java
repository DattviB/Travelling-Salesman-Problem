package com.info6205.TSP.ui;

import com.info6205.TSP.graph.City;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewTSPAlgo {
    private List<City> points;
    private Graph graph;
    private SpriteManager spriteManager;
    private Timer timer;
    private int currentEdgeIndex;

    public static void main(String[] args) {

    }

    public void viewFinalTour(List<City> locations) {
        points = new ArrayList<>(locations);

        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("TSP Visualization");
        spriteManager = new SpriteManager(graph);

        for (int i = 0; i < points.size() - 1; i++) {
            City city = points.get(i);
            String nodeId = city.getId(); // Unique ID for each node

            if (nodeId.length() > 5) {
                nodeId = nodeId.substring(nodeId.length() - 5);
                points.get(i).setId(nodeId);
            }
            Node node = graph.addNode(nodeId);
            node.setAttribute("layout.frozen");
            node.setAttribute("x", city.getLattitude());
            node.setAttribute("y", city.getLongitude());

            Sprite sprite = spriteManager.addSprite("s_" + nodeId);
            sprite.attachToNode(nodeId);
            sprite.setAttribute("ui.label", nodeId);
            sprite.setAttribute("ui.style", "shape:circle; fill-color:blue; size:10px;");
        }

        // Modify the loop that adds edges
        for (int i = 0; i < points.size() - 1; i++) {
            String sourceNodeId = points.get(i).getId();
            String targetNodeId = points.get((i + 1) % points.size()).getId();

            String edgeId = points.get(i).getId();

            // Use Timer to add a delay between each edge addition
            currentEdgeIndex = 0;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Add edge
                    Edge edge = graph.addEdge(edgeId, sourceNodeId, targetNodeId);
                    edge.setAttribute("ui.label", edge.getId());
                    edge.setAttribute("ui.style", "fill-color:red; size: 2px;");
                }
            }, i * 300); // Change the delay time as needed (in milliseconds)
        }

        graph.display();

//        currentEdgeIndex = 0;
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (currentEdgeIndex < points.size() - 1) {
//                    String edgeId = points.get(currentEdgeIndex).getId();
//                    Edge edge = graph.getEdge(edgeId);
//                    if (edge != null) {
//                        edge.setAttribute("ui.style", "fill-color:red; size: 2px; ");
//                    }
//                    currentEdgeIndex++;
//                } else {
//                    timer.cancel();
//                    timer.purge();
//                }
//            }
//        }, 500, 500);
    }

}