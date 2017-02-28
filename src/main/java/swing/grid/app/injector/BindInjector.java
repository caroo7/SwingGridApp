package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.UIRenderer;

import javax.swing.*;
import java.util.List;

public class BindInjector extends AbstractModule {

    private List<Layout.Menu.Button> buttons;

    public BindInjector(List<Layout.Menu.Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    protected void configure() {
        for(Layout.Menu.Button button: buttons) {
            Class clazz = null;
            try {
                clazz = Class.forName(button.getClazz());
            } catch (ClassNotFoundException e) {
                System.out.println("Cannot create class from string: " + button.getClazz());
                e.printStackTrace();
            }

            bind(BusinessFunction.class).to(clazz);
        }


        bind(JFrame.class).to(UIRenderer.class);
    }

}