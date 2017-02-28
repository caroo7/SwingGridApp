package swing.grid.app.ui;

import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIRenderer extends JFrame {

    private final BusinessFunction businessFunction;

    private JTable table;

    private SortingTableModel model;

    @Inject
    public UIRenderer(BusinessFunction businessFunction) {
        this.businessFunction = businessFunction;
        this.model = new SortingTableModel();
        this.table = new JTable(this.model);
    }

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
        for (Layout.Menu.Button button : layout.getMenu().getButton()) {
            JButton buttonComponent = new JButton(button.getValue());
            addActionToButton(buttonComponent);
            buttonComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(buttonComponent);
        }

        return panel;
    }

    private void addActionToButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                businessFunction.doAction();
            }
        });
    }

    private JPanel createDataTable(Layout layout, Data data) {
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        createHeader(layout);
        createData(data);

        panel.add(scrollPane);
        return panel;
    }

    private void createHeader(Layout layout) {
        model.setColumnIdentifiers(layout.getGrid().getColumn().toArray());
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private void createData(Data data) {
        String[] row;
        for (Data.Car car : data.getCar()) {
            row = new String[]{
                    car.getMake(), car.getModel(), String.valueOf(car.getYear())
            };
            model.addRow(row);
        }
    }

    private void configure() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}