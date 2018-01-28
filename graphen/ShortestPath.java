package graphen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasse zur Dialogdarstellung des Dijkstra-Algorithmus
 * Fragt Start- und Zielknoten ab, berechnet den kürzesten Weg und gibt diesen aus
 * @author Philipp Arndt, 561164
 */
class ShortestPath implements ActionListener {
    private Graph graph;

    private JDialog inputDialog;
    private JLabel labelStart, labelEnd;
    private JTextField start, end;
    private JLabel labelPath, labelDistance;

    private JPanel panelControls;
    private JButton calculate, cancel;

    ShortestPath(Graph g) {
        graph = g;

        inputDialog = new JDialog();
        inputDialog.setTitle("Kürzester Weg");
        inputDialog.setBounds(100, 100, 300, 300);
        inputDialog.setLayout(new BoxLayout(inputDialog.getContentPane(), BoxLayout.Y_AXIS));

        // start node
        JPanel panelStart = new JPanel();
        panelStart.add(labelStart = new JLabel("Startknoten:"));
        panelStart.add(start = new JTextField());
        start.setColumns(12);
        inputDialog.add(panelStart);

        // end node
        JPanel panelEnd = new JPanel();
        panelEnd.add(labelEnd = new JLabel("Endknoten:"));
        panelEnd.add(end = new JTextField());
        end.setColumns(12);
        inputDialog.add(panelEnd);

        // result
        JPanel panelResult = new JPanel();
        panelResult.add(labelPath = new JLabel(""));
        panelResult.add(labelDistance = new JLabel(""));
        inputDialog.add(panelResult);

        panelControls = new JPanel();
        // calculate button
        panelControls.add(calculate = new JButton("Berechnen"));
        calculate.addActionListener(e -> calculate(e));

        // cancel button
        panelControls.add(cancel = new JButton("Abbrechen"));
        cancel.addActionListener(e -> cancel(e));

        inputDialog.add(panelControls);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputDialog.setVisible(true);
    }

    private void calculate(ActionEvent event) {
        int startNode = 0;
        int endNode = 0;
        try {
            startNode = Integer.parseInt(start.getText());
            endNode = Integer.parseInt(end.getText());
        }
        catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        if(startNode > 0 && endNode > 0) {
            // calculate path
            Path path;
            path = graph.shortestPath(startNode, endNode);

            // set result labels
            labelPath.setText("Weg: " + path.getPath() + ", ");
            labelDistance.setText("Distanz: " + path.getDistance());
        }
    }

    private void cancel(ActionEvent e) {
        // close dialog
        inputDialog.dispose();
    }
}
