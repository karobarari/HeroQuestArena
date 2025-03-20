package HeroQuestArena;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeroManagementGUI extends JFrame {

    private final GameManager gameManager;
    private JTextArea heroListTextArea, battleLogTextArea;
    private JTextField nameField, hpField, attackPowerField, defenseLevelField, specialField;
    private JComboBox<String> classComboBox, hero1ComboBox, hero2ComboBox, upgradeHeroComboBox, statToUpgradeComboBox;

    // Define consistent colors for the GUI
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color PANEL_COLOR = new Color(255, 255, 240);
    private static final Color BUTTON_COLOR = new Color(150, 200, 255);
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);

    /**
     * Constructor for the HeroManagementGUI class.
     * Initializes the GUI components and sets up the layout.
     *
     * @param gameManager The GameManager instance to use for hero management.
     */
    public HeroManagementGUI(GameManager gameManager) {
        this.gameManager = gameManager;
        setTitle("Hero Quest Arena - Character Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR); // Set main background color

        // Create main panels for each tab
        JPanel creationPanel = createCreationPanel();
        JPanel displayPanel = createDisplayPanel();
        JPanel upgradePanel = createUpgradePanel();
        JPanel battlePanel = createBattlePanel();

        // Use a tabbed pane to organize the main panels
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Create Hero", creationPanel);
        tabbedPane.addTab("View Heroes", displayPanel);
        tabbedPane.addTab("Upgrade Hero", upgradePanel);
        tabbedPane.addTab("Battle Simulation", battlePanel);

        add(tabbedPane, BorderLayout.CENTER); // Add tabbed pane to the main frame
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Make the frame visible
    }

    /**
     * Creates the panel for creating new heroes.
     *
     * @return The JPanel containing the hero creation components.
     */
    private JPanel createCreationPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for flexible layout
        panel.setBackground(PANEL_COLOR);  // Set panel background color

        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.anchor = GridBagConstraints.WEST; // Align components to the west

        // Labels and Fields for hero attributes
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(LABEL_FONT);

        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        panel.add(nameLabel, gbc);

        nameField = new JTextField(15); // Text field for hero name
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow field to expand horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill available space
        panel.add(nameField, gbc);
        gbc.weightx = 0.0; // Reset weight

        JLabel classLabel = new JLabel("Class:");
        classLabel.setForeground(TEXT_COLOR);
        classLabel.setFont(LABEL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE; // Do not fill
        panel.add(classLabel, gbc);

        String[] classes = {"Warrior", "Mage", "Archer"}; // Options for hero classes
        classComboBox = new JComboBox<>(classes);
        classComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(classComboBox, gbc);

        JLabel hpLabel = new JLabel("HP:");
        hpLabel.setForeground(TEXT_COLOR);
        hpLabel.setFont(LABEL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(hpLabel, gbc);

        hpField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hpField, gbc);

        JLabel attackPowerLabel = new JLabel("Attack Power:");
        attackPowerLabel.setForeground(TEXT_COLOR);
        attackPowerLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(attackPowerLabel, gbc);

        attackPowerField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(attackPowerField, gbc);

        JLabel defenseLevelLabel = new JLabel("Defense Level:");
        defenseLevelLabel.setForeground(TEXT_COLOR);
        defenseLevelLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(defenseLevelLabel, gbc);

        defenseLevelField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(defenseLevelField, gbc);

        JLabel specialLabel = new JLabel("Special:");
        specialLabel.setForeground(TEXT_COLOR);
        specialLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(specialLabel, gbc);

        specialField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(specialField, gbc);

        // Button to create the hero
        JButton createButton = new JButton("Create Hero");
        createButton.setBackground(BUTTON_COLOR); // Set button color
        createButton.setForeground(TEXT_COLOR);  //set button text color
        createButton.addActionListener(e -> createHero()); // Add action listener to create the hero
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(createButton, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        return panel;
    }

    /**
     * Creates the panel for displaying hero details.
     *
     * @return The JPanel containing the hero display components.
     */
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_COLOR);

        heroListTextArea = new JTextArea(); // Text area to display hero details
        heroListTextArea.setEditable(false); // Make the text area read-only
        heroListTextArea.setBackground(Color.WHITE); // Set text area background
        heroListTextArea.setForeground(TEXT_COLOR);
        JScrollPane scrollPane = new JScrollPane(heroListTextArea); // Add scroll functionality
        panel.add(scrollPane, BorderLayout.CENTER); // Add scrollable text area to the panel

        JButton viewButton = new JButton("View Hero Details"); // Button to trigger hero details display
        viewButton.setBackground(BUTTON_COLOR);
        viewButton.setForeground(TEXT_COLOR);
        viewButton.addActionListener(e -> viewHeroDetails()); // Add action listener to view details
        panel.add(viewButton, BorderLayout.SOUTH); // Add button to the bottom

        return panel;
    }

    /**
     * Creates the panel for upgrading hero stats.
     *
     * @return The JPanel containing the hero upgrade components.
     */
    private JPanel createUpgradePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PANEL_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel heroLabel = new JLabel("Hero to Upgrade:");
        heroLabel.setForeground(TEXT_COLOR);
        heroLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(heroLabel, gbc);

        upgradeHeroComboBox = new JComboBox<>(); // Combo box to select hero to upgrade
        upgradeHeroComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(upgradeHeroComboBox, gbc);
        gbc.weightx = 0.0;

        JLabel statLabel = new JLabel("Stat to Upgrade:");
        statLabel.setForeground(TEXT_COLOR);
        statLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(statLabel, gbc);

        String[] stats = {"HP", "attackPower", "defenseLevel"}; // Options for stats to upgrade
        statToUpgradeComboBox = new JComboBox<>(stats);
        statToUpgradeComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(statToUpgradeComboBox, gbc);

        JButton upgradeButton = new JButton("Upgrade Stats"); // Button to trigger stat upgrade
        upgradeButton.setBackground(BUTTON_COLOR);
        upgradeButton.setForeground(TEXT_COLOR);
        upgradeButton.addActionListener(e -> upgradeStats()); // Add action listener to upgrade stats
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(upgradeButton, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

    /**
     * Creates the panel for simulating battles between heroes.
     *
     * @return The JPanel containing the battle simulation components.
     */
    private JPanel createBattlePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PANEL_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel hero1Label = new JLabel("Hero 1:");
        hero1Label.setForeground(TEXT_COLOR);
        hero1Label.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(hero1Label, gbc);

        hero1ComboBox = new JComboBox<>(); // Combo box to select Hero 1
        hero1ComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hero1ComboBox, gbc);
        gbc.weightx = 0.0;

        JLabel hero2Label = new JLabel("Hero 2:");
        hero2Label.setForeground(TEXT_COLOR);
        hero2Label.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(hero2Label, gbc);

        hero2ComboBox = new JComboBox<>(); // Combo box to select Hero 2
        hero2ComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hero2ComboBox, gbc);

        battleLogTextArea = new JTextArea(); // Text area to display the battle log
        battleLogTextArea.setEditable(false);
        battleLogTextArea.setBackground(Color.WHITE);
        battleLogTextArea.setForeground(TEXT_COLOR);

        JScrollPane battleLogScrollPane = new JScrollPane(battleLogTextArea); // Add scroll functionality
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        panel.add(battleLogScrollPane, gbc);
        gbc.weighty = 0.0;

        JButton battleButton = new JButton("Simulate Battle"); // Button to trigger battle simulation
        battleButton.setBackground(BUTTON_COLOR);
        battleButton.setForeground(TEXT_COLOR);
        battleButton.addActionListener(e -> simulateBattle()); // Add action listener to simulate the battle
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(battleButton, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

    /**
     * Creates a new hero based on the input fields and adds it to the game.
     */
    private void createHero() {
        try {
            String name = nameField.getText(); // Get the hero name from the text field
            // Validate the hero name
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Hero name cannot be empty.");
                return;
            }
            String classType = (String) classComboBox.getSelectedItem(); // Get the selected class type
            int hp = Integer.parseInt(hpField.getText()); // Get HP and parse it as an integer
            int attackPower = Integer.parseInt(attackPowerField.getText()); // Get attack power and parse it as an integer
            int defenseLevel = Integer.parseInt(defenseLevelField.getText()); // Get defense level and parse it as an integer
            int special = Integer.parseInt(specialField.getText()); // Get special stat and parse it as an integer

            // Create a new hero using the GameManager
            Hero newHero = gameManager.createHero(name, classType, hp, attackPower, defenseLevel, special);
            gameManager.addHero(newHero); // Add the new hero to the game

            updateHeroComboBoxes(); // Refresh combo boxes *after* adding the hero
            JOptionPane.showMessageDialog(this, "Hero created successfully!"); // Show success message
            clearHeroCreationFields(); // Clear the input fields
            viewHeroDetails(); // Refresh the hero details display

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers for HP, Attack, and Defense.");
        } catch (IllegalArgumentException e) { //Catch IllegalArgumentException
            JOptionPane.showMessageDialog(this, e.getMessage()); //Display the message from addHero
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating hero: " + e.getMessage());
        }
    }

    /**
     * Displays the details of all heroes in the game.
     */
    private void viewHeroDetails() {
        StringBuilder heroDetails = new StringBuilder(); // Use StringBuilder for efficient string building
        List<Hero> allHeroes = gameManager.getAllHeroes(); // Get the list of all heroes from the GameManager
        // If there are no heroes created yet
        if (allHeroes.isEmpty()) {
            heroDetails.append("No heroes created yet.");
        } else {
            // Append the details of each hero to the StringBuilder
            for (Hero hero : allHeroes) {
                heroDetails.append(hero.toString()).append("\n");
            }
        }
        heroListTextArea.setText(heroDetails.toString()); // Set the text area to display the hero details
    }

    /**
     * Upgrades the stats of a selected hero.
     */
    private void upgradeStats() {
        try {
            String heroName = (String) upgradeHeroComboBox.getSelectedItem(); // Get the selected hero's name
            String statToUpgrade = (String) statToUpgradeComboBox.getSelectedItem(); // Get the selected stat to upgrade

            // Check if a hero has been created first
            if (heroName == null || heroName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please create a hero first.");
                return;
            }

            // Prompt the user to enter the upgrade amount
            int upgradeAmount = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter upgrade amount:"));

            Hero heroToUpgrade = gameManager.getHero(heroName); // Get the Hero object from the GameManager
            if (heroToUpgrade != null) {
                gameManager.upgradeHeroStats(heroToUpgrade, statToUpgrade, upgradeAmount); // Upgrade the hero's stats
                viewHeroDetails(); // Refresh hero list
                JOptionPane.showMessageDialog(this, heroName + "'s " + statToUpgrade + " upgraded successfully!"); // Show success message
            } else {
                JOptionPane.showMessageDialog(this, "Hero not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number for the upgrade amount.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Please select a hero and stat to upgrade.");
        }
    }

    /**
     * Simulates a battle between two selected heroes.
     */
    private void simulateBattle() {
        String hero1Name = (String) hero1ComboBox.getSelectedItem(); // Get the selected Hero 1's name
        String hero2Name = (String) hero2ComboBox.getSelectedItem(); // Get the selected Hero 2's name

        Hero hero1 = gameManager.getHero(hero1Name); // Get Hero 1 object from the GameManager
        Hero hero2 = gameManager.getHero(hero2Name); // Get Hero 2 object from the GameManager

        // Check that both heroes were selected
        if (hero1 != null && hero2 != null) {
            String battleResult = gameManager.simulateBattle(hero1, hero2); // Simulate the battle
            battleLogTextArea.setText(battleResult); // Display the battle log
        } else {
            JOptionPane.showMessageDialog(this, "One or both heroes not found.");
        }
    }

    /**
     * Clears the input fields in the hero creation panel.
     */
    private void clearHeroCreationFields() {
        nameField.setText("");
        hpField.setText("");
        attackPowerField.setText("");
        defenseLevelField.setText("");
        specialField.setText("");
    }

    /**
     * Updates the hero combo boxes with the current list of heroes.
     */
    private void updateHeroComboBoxes() {
        upgradeHeroComboBox.removeAllItems(); // Remove all existing items
        hero1ComboBox.removeAllItems();
        hero2ComboBox.removeAllItems();

        List<Hero> allHeroes = gameManager.getAllHeroes(); // Get the latest list of heroes
        if (allHeroes != null) {
            // Add each hero's name to the combo boxes
            for (Hero hero : allHeroes) {
                upgradeHeroComboBox.addItem(hero.getName());
                hero1ComboBox.addItem(hero.getName());
                hero2ComboBox.addItem(hero.getName());
            }
        }
    }

    /**
     * Main method to create and run the GUI.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager gameManager = new GameManager(); // Create a new GameManager
            new HeroManagementGUI(gameManager); // Create and display the GUI
        });
    }
}