package graphen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        inputDialog.setBounds(100, 100, 300, 250);
        inputDialog.setLayout(new BoxLayout(inputDialog.getContentPane(), BoxLayout.Y_AXIS));

        // start node
        inputDialog.add(labelStart = new JLabel("Startknoten:"));
        inputDialog.add(start = new JTextField());
        start.setColumns(12);

        // end node
        inputDialog.add(labelEnd = new JLabel("Endknoten:"));
        inputDialog.add(end = new JTextField());
        end.setColumns(12);

        // result
        inputDialog.add(labelPath = new JLabel("Weg:"));
        labelPath.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputDialog.add(labelDistance = new JLabel("Distanz:"));
        labelDistance.setAlignmentX(Component.LEFT_ALIGNMENT);

        inputDialog.add(panelControls = new JPanel());
        // calculate button
        panelControls.add(calculate = new JButton("Berechnen"));
        calculate.addActionListener(e -> calculate(e));

        // cancel button
        panelControls.add(cancel = new JButton("Abbrechen"));
        cancel.addActionListener(e -> cancel(e));
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
            Path shortestPath = graph.shortestPath(startNode, endNode);

            // set result labels
            labelPath.setText("Weg: " + shortestPath.getPath());
            labelDistance.setText("Distanz: " + shortestPath.getDistance());
        }
    }

    private void cancel(ActionEvent e) {
        // close dialog
        inputDialog.dispose();
    }
}
