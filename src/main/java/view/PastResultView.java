package view;

import interface_adapter.past_result.PastResultController;
import interface_adapter.past_result.PastResultState;
import interface_adapter.past_result.PastResultViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PastResultView extends JPanel implements PropertyChangeListener {
    private final JLabel NoPastResultLabel = new JLabel();
    private final JButton BackButton = new JButton();
    private final PastResultViewModel pastResultViewModel;
    private PastResultController pastresultController;

    public void setPastResultController(PastResultController pastResultController) {
        this.pastresultController = pastResultController;
    }

    public PastResultView(PastResultViewModel pastResultViewModel) {
        this.pastResultViewModel = pastResultViewModel;

        this.pastResultViewModel.addPropertyChangeListener(this);

        // Set up layout and components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        NoPastResultLabel.setText("No Past Result");
        NoPastResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        BackButton.setText("Back");
        BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        add(NoPastResultLabel);
        add(Box.createVerticalStrut(10));  // Spacer for better layout
        add(BackButton);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("disease")) {
            final PastResultState state = (PastResultState) evt.getNewValue();

            // Debugging output to verify state
            System.out.println("PastResultView: Property change detected for 'disease'.");
            System.out.println("PastResultView: Protein name: " + state.getProtein());
            System.out.println("PastResultView: Diseases: " + state.getDisease());
            this.removeAll();

            // Add the "Results for" label
            JLabel headerLabel = new JLabel("Results for: " + state.getProtein());
            headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(headerLabel);

            if (state.getDisease() == null || state.getDisease().isEmpty()) {
                // Handle case where no diseases are found
                JLabel noDiseaseLabel = new JLabel("No diseases found for the given protein.");
                noDiseaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(noDiseaseLabel);
            } else {
                // Create and populate the JList with diseases


                // Add a scrollable pane for the disease list

                // Add the scrollable list to the panel
                add(Box.createVerticalStrut(10)); // Add some vertical spacing
            }

            // Revalidate and repaint to refresh the panel
            System.out.println("PastResultView: UI updated. Calling revalidate and repaint.");
            revalidate();
            repaint();
        }

    }
}

