package swing.grid.app.ui;

import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final DataTable dataTable;

    private final ButtonPanel buttonPanel;

    @Inject
    public MainFrame(DataTable dataTable, ButtonPanel buttonPanel) {
        this.dataTable = dataTable;
        this.buttonPanel = buttonPanel;
    }

    public void createFrame(Layout layout, Data data) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel dataContainer = dataTable.createDataTable(layout, data);
        JPanel buttonContainer = buttonPanel.createButtonPanel(layout);

        mainPanel.add(dataContainer, BorderLayout.NORTH);
        mainPanel.add(buttonContainer, BorderLayout.SOUTH);
        add(mainPanel);
        configure();
    }

    private void configure() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}