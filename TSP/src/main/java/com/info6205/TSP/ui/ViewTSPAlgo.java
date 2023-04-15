package com.info6205.TSP.ui;

import com.info6205.TSP.graph.City;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class ViewTSPAlgo extends JFrame {

    private JPanel mapPanel;
    private JButton startButton;
    private List<City> points; // Array of points with latitude and longitude
    private Set<Integer> completedLines = new HashSet<>(); // Set to keep track of completed lines
    private Timer timer; // Timer for animation
    private int currentLine = -1; // Current line being drawn
    Graphics2D g2d;
    public ViewTSPAlgo(List<City> locations) {
        setTitle("Map App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPointsAndLines(g); // Method to draw points and lines on the panel
                points = new ArrayList<>(locations);
                if (!timer.isRunning() && currentLine >= points.size() - 1) {
                    // Draw black lines after the timer is stopped and animation is complete
                    g.setColor(Color.BLACK);
                    for (int i = 0; i < points.size(); i++) {
                        int x1 = (int) ((points.get(i).getLongitude() + 270) * mapPanel.getWidth() / 360);
                        int y1 = (int) ((90 - points.get(i).getLattitude()) * mapPanel.getHeight() / 180);
                        int nextIndex = (i + 1) % points.size(); // Get the index of the next point
                        int x2 = (int) ((points.get(nextIndex).getLongitude() + 270) * mapPanel.getWidth() / 360);
                        int y2 = (int) ((90 - points.get(nextIndex).getLattitude()) * mapPanel.getHeight() / 180);
                        g.drawLine(x1, y1, x2, y2); // Draw a black line
                    }
                }
            }
        };
        //viewFinalGraph(Graphics g);
        add(mapPanel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Method to start the server and get points with latitude and longitude
                points = new ArrayList<>(locations);
                // Initialize city names array
                //cityNames = new String[]{"Boston","New York City", "Washington DC", "Houston","Chicago"};
                currentLine = 0; // Reset current line
                if (points != null) {
                    // Start the timer for animation
                    animatePoints();
                }
            }
        });
        add(startButton, BorderLayout.SOUTH);

        // Timer for animation with delay of 500 milliseconds
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment current line
                currentLine++;
                // Repaint the panel to update the animation
                mapPanel.repaint();
                // Stop the timer when all lines are drawn
                if (currentLine >= points.size()) {
                    timer.stop();
                }
            }
        });

    }

    private void drawPointsAndLines(Graphics g) {
        if (points != null) {
            for (int i = 0; i < points.size(); i++) {
                // Convert latitude and longitude to x and y coordinates on the panel
                int x1 = (int) ((points.get(i).getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y1 = (int) ((90 - points.get(i).getLattitude()) * mapPanel.getHeight() / 180);
                g.setColor(Color.RED);
                g.fillOval(x1 - 5, y1 - 5, 10, 10); // Draw a red circle for each point
                g.drawString(points.get(i).getId(), x1, y1); // Draw city name next to the point
            }



            // Draw animated lines for the current line being drawn
            if (currentLine >= 0 && currentLine < points.size()) {
                int x1 = (int) ((points.get(currentLine).getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y1 = (int) ((90 - points.get(currentLine).getLattitude()) * mapPanel.getHeight() / 180);
                int nextIndex = (currentLine + 1) % points.size(); // Get the index of the next point
                int x2 = (int) ((points.get(nextIndex).getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y2 = (int) ((90 - points.get(nextIndex).getLattitude()) * mapPanel.getHeight() / 180);
                g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2)); // Set thicker stroke for animated line
                g2d.setColor(Color.BLUE);
                g2d.drawLine(x1, y1, x2, y2); // Draw an animated blue line for the current line

            }

        }
    }

    private void animatePoints() {
        // Start the timer for animation
        timer.start();
    }

    private City[] startServerAndGetPoints() {
        // Method to start the server and get points with latitude and longitude
        // Replace with your own implementation to fetch points from the server
        // and convert them into Point objects with latitude and longitude


        City[] points = new City[6];
        points[0] = new City("1",40.7128, -74.0060 ); // New York City
        points[1] = new City("2",34.0522, -118.2437); // Los Angeles
        points[2] = new City("3",29.7604, -95.3698); // Houston
        points[3] = new City("4",30.2500, -97.7500); // Austin
        points[4] = new City("5",41.8781, -87.6298); // Chicago
        points[5] = new City("6",40.7128, -74.0060); // NY
        return points;
    }

    public static void main(String[] args) {

    }

}