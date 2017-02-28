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

    private final DataTable dataTable;

    @Inject
    public UIRenderer(BusinessFunction businessFunction, DataTable dataTable) {
        this.businessFunction = businessFunction;
        this.dataTable = dataTable;
    }

    public void createFrame(Layout layout, JPanel dataPanel) {
        JPanel mainPanel = new JPanel(new BorderLayout());

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

    private void configure() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}