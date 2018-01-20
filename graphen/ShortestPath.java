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
    private JButton calculate;

    ShortestPath(Graph g) {
        graph = g;

        inputDialog = new JDialog();
        inputDialog.setTitle("Kürzester Weg");
        inputDialog.setBounds(100, 100, 300, 130);
        inputDialog.setLayout(new FlowLayout());
        //inputDialog.setModal(true);
        //inputDialog.setVisible(true);

        // start node
        inputDialog.add(labelStart = new JLabel("Startknoten:"));
        inputDialog.add(start = new JTextField());
        start.setColumns(12);

        // end node
        inputDialog.add(labelEnd = new JLabel("Endknoten:"));
        inputDialog.add(end = new JTextField());
        end.setColumns(12);

        inputDialog.add(calculate = new JButton("Berechnen"));
        calculate.addActionListener(e -> calculate(e));
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
            graph.shortestPath(startNode, endNode);
            // calculate
            inputDialog.setVisible(false);
        }
    }
}
