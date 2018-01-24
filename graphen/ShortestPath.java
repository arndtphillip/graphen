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

    private JRadioButton shortestPath, depthFirstSearch;

    private JPanel panelControls;
    private JButton calculate, cancel;

    ShortestPath(Graph g) {
        graph = g;

        inputDialog = new JDialog();
        inputDialog.setTitle("Kürzester Weg");
        inputDialog.setBounds(100, 100, 300, 300);
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
        inputDialog.add(labelDistance = new JLabel("Distanz:"));


        shortestPath = new JRadioButton("Kürzester Weg");
        shortestPath.setSelected(true);
        depthFirstSearch = new JRadioButton("Tiefensuche");
        //pigButton.setMnemonic(KeyEvent.VK_P);
        //pigButton.setActionCommand(pigString);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(shortestPath);
        group.add(depthFirstSearch);

        JPanel panelSelect = new JPanel();
        panelSelect.setLayout(new FlowLayout());
        panelSelect.add(shortestPath);
        panelSelect.add(depthFirstSearch);

        inputDialog.add(panelSelect);

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
            if(shortestPath.isSelected())
                path = graph.shortestPath(startNode, endNode);
            else
                path = graph.depthFirstSearch(startNode, endNode);

            // set result labels
            labelPath.setText("Weg: " + path.getPath());
            labelDistance.setText("Distanz: " + path.getDistance());
        }
    }

    private void cancel(ActionEvent e) {
        // close dialog
        inputDialog.dispose();
    }
}
