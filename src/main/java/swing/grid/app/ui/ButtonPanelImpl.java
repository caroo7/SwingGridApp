package swing.grid.app.ui;

import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanelImpl implements ButtonPanel {

    private BusinessFunction businessFunction;

    private boolean isSortedActionEnabled = false;

    @Inject
    public ButtonPanelImpl(BusinessFunction businessFunction) {
        this.businessFunction = businessFunction;
    }

    @Override
    public JPanel createButtonPanel(Layout layout) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Layout.Menu.Button button : layout.getMenu().getButton()) {
            JButton buttonComponent = new JButton(button.getValue());
            if (!isSortedActionEnabled) {
                addActionToButton(buttonComponent);
                isSortedActionEnabled = true;
            }
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

}