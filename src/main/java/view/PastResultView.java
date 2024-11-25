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
    private JLabel protein_name;
    private JLabel accession_number;
    private JLabel description;

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
            System.out.println("PastResultView: Description: " + state.getDescription());
            System.out.println("PastResultView: ID: " + state.getId());

            this.removeAll(); // Clear existing components

            // Header label
            JLabel headerLabel = new JLabel("Protein: " + state.getProtein() + " (" + state.getId() + ")");
            headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Format description
            String formattedDescription = state.getDescription().replaceAll("\\.", ".\n");

            // Use a JTextArea for displaying the description
            JTextArea descriptionArea = new JTextArea(formattedDescription);
            descriptionArea.setLineWrap(true); // Enable line wrapping
            descriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
            descriptionArea.setEditable(false); // Make it non-editable
            descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Optional: Wrap in a JScrollPane in case the description is long
            JScrollPane scrollPane = new JScrollPane(descriptionArea);
            scrollPane.setPreferredSize(new Dimension(400, 200)); // Adjust size as needed

            // Back button
            BackButton.setText("Back");
            BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            BackButton.addActionListener(backEvt -> {
                CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
                cardLayout.show(this.getParent(), "logged in");
            });

            // Add components to the panel
            add(headerLabel);
            add(scrollPane); // Add scrollable description
            add(Box.createVerticalStrut(10)); // Add spacing
            add(BackButton);

            revalidate();
            repaint(); // Refresh the panel to display updated components
        }
    }
}

