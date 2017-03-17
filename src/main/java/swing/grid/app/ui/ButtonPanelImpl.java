package swing.grid.app.ui;

import com.google.inject.name.Named;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanelImpl implements ButtonPanel {

    private BusinessFunction sortAction;

    private BusinessFunction registerAction;

    private boolean isSortedActionEnabled = false;

    private InternationalizationResourceBundle resourceBundle;

    @Inject
    public ButtonPanelImpl(@Named("MyAction") BusinessFunction sortAction,
                           @Named("RegisterAction") BusinessFunction registerAction,
                           InternationalizationResourceBundle resourceBundle) {
        this.sortAction = sortAction;
        this.registerAction = registerAction;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public JPanel createButtonPanel(Layout layout) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Layout.Menu.Button button : layout.getMenu().getButton()) {
            String buttonCaption = resourceBundle.getMessage(button.getValue());
            JButton buttonComponent = new JButton(buttonCaption);
            if (!isSortedActionEnabled) {
                addSortActionToButton(buttonComponent);
                isSortedActionEnabled = true;
            } else {
                addRegisterActionToButton(buttonComponent);
            }
            buttonComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(buttonComponent);
        }

        return panel;
    }

    private void addRegisterActionToButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registerAction.doAction();
            }
        });
    }

    private void addSortActionToButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sortAction.doAction();
            }
        });
    }

}