package swing.grid.app.render;

import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UIRenderer extends JFrame {

    public void createFrame(Layout layout, Data data) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel dataPanel = createDataTable(layout, data);
        JPanel buttonPanel = createButtonPanel(layout);

        mainPanel.add(dataPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        configure();
    }

    private JPanel createButtonPanel(Layout layout) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(Layout.Menu.Button button:  layout.getMenu().getButton()) {
            JButton buttonComponent = new JButton(button.getValue());
            buttonComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(buttonComponent);
        }

        return panel;
    }

    private JPanel createDataTable(Layout layout, Data data) {
        JPanel panel = new JPanel();

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        createHeader(layout, model, table);
        createData(data, model);

        panel.add(scrollPane);
        return panel;
    }

    private void createData(Data data, DefaultTableModel model) {
        for(Data.Car car: data.getCar()) {
            String[] row = new String[] {
                    car.getMake(), car.getModel(), String.valueOf(car.getYear())
            };
            model.addRow(row);
        }
    }

    private void createHeader(Layout layout, DefaultTableModel model, JTable table) {
        model.setColumnIdentifiers(layout.getGrid().getColumn().toArray());
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private void configure() {
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}