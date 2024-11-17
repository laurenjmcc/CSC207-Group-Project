package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import interface_adapter.past_result.PastResultController;
import interface_adapter.past_result.PastResultState;
import interface_adapter.past_result.PastResultViewModel;

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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        NoPastResultLabel.setText("No Past Result");
        NoPastResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        BackButton.setText("Back");
        BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(NoPastResultLabel);
        add(Box.createVerticalStrut(10));  // Spacer for better layout
        add(BackButton);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("disease")) {
            final PastResultState state = (PastResultState) evt.getNewValue();
            this.removeAll();
            JLabel headerLabel = new JLabel("Results for: " + state.getProtein());
            headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(headerLabel);

            if (state.getDisease() == null || state.getDisease().isEmpty()) {
                JLabel noDiseaseLabel = new JLabel("No diseases found for the given protein.");
                noDiseaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(noDiseaseLabel);
            } else {
                JList<String> diseaseList = new JList<>(state.getDisease().toArray(new String[0]));
                diseaseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                diseaseList.setVisibleRowCount(5);
                JScrollPane scrollPane = new JScrollPane(diseaseList);
                scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalStrut(10)); // Add some vertical spacing
                add(scrollPane);
            }
            revalidate();
            repaint();
        }

    }
}

