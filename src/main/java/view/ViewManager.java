package view;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements PropertyChangeListener {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(JPanel cardPanel, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;

        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String newState = (String) evt.getNewValue();
        System.out.println("Switching to view: " + newState);
        cardLayout.show(cardPanel, newState);
    }
}
