package com.info6205.TSP.ui;



import com.info6205.TSP.util.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTSPAlgo extends JFrame {

    private JPanel mapPanel;
    private JButton startButton;
    private DoublePoint[] points; // Array of points with latitude and longitude
    private String[] cityNames; // Array of city names
    private boolean showBlackLine = false; // Add this variable to keep track of black line visibility

    private Timer timer; // Timer for animation
    private int currentLine = -1; // Current line being drawn

    public ViewTSPAlgo() {
        setTitle("Map App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPointsAndLines(g); // Method to draw points and lines on the panel
            }
        };
        //viewFinalGraph(Graphics g);
        add(mapPanel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Method to start the server and get points with latitude and longitude
                points = startServerAndGetPoints();
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
                if (currentLine >= points.length) {
                    timer.stop();
                }
            }
        });
    }

    private void drawPointsAndLines(Graphics g) {
        if (points != null) {
            for (int i = 0; i < points.length; i++) {
                // Convert latitude and longitude to x and y coordinates on the panel
                int x1 = (int) ((points[i].getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y1 = (int) ((90 - points[i].getLatitude()) * mapPanel.getHeight() / 180);
                g.setColor(Color.RED);
                g.fillOval(x1 - 5, y1 - 5, 10, 10); // Draw a red circle for each point
                g.drawString(points[i].getCityName(), x1, y1); // Draw city name next to the point
            }

            // Draw lines connecting each point with the next point based on array index
            for (int i = 0; i < points.length; i++) {
                int nextIndex = (i + 1) % points.length; // Get the index of the next point
                int x1 = (int) ((points[i].getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y1 = (int) ((90 - points[i].getLatitude()) * mapPanel.getHeight() / 180);
                int x2 = (int) ((points[nextIndex].getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y2 = (int) ((90 - points[nextIndex].getLatitude()) * mapPanel.getHeight() / 180);
                g.setColor(showBlackLine ? Color.BLACK : Color.GRAY); // Use gray color for black line if not visible
                g.drawLine(x1, y1, x2, y2); // Draw a line
            }

            // Draw animated lines for the current line being drawn
            if (currentLine >= 0 && currentLine < points.length) {
                int x1 = (int) ((points[currentLine].getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y1 = (int) ((90 - points[currentLine].getLatitude()) * mapPanel.getHeight() / 180);
                int nextIndex = (currentLine + 1) % points.length; // Get the index of the next point
                int x2 = (int) ((points[nextIndex].getLongitude() + 270) * mapPanel.getWidth() / 360);
                int y2 = (int) ((90 - points[nextIndex].getLatitude()) * mapPanel.getHeight() / 180);
                Graphics2D g2d = (Graphics2D) g;
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

    private DoublePoint[] startServerAndGetPoints() {
        // Method to start the server and get points with latitude and longitude
        // Replace with your own implementation to fetch points from the server
        // and convert them into Point objects with latitude and longitude


        DoublePoint[] points = new DoublePoint[6];
        points[0] = new DoublePoint(40.7128, -74.0060, "1"); // New York City
        points[1] = new DoublePoint(34.0522, -118.2437, "2"); // Los Angeles
        points[2] = new DoublePoint(29.7604, -95.3698, "3"); // Houston
        points[3] = new DoublePoint(30.2500, -97.7500, "4"); // Austin
        points[4] = new DoublePoint(41.8781, -87.6298, "5"); // Chicago
        points[5] = new DoublePoint(40.7128, -74.0060, "6");//NY
        return points;
    }

    public static void main(String[] args) {
        ViewTSPAlgo viewTSPAlgo = new ViewTSPAlgo();

        viewTSPAlgo.setVisible(true);
    }

}