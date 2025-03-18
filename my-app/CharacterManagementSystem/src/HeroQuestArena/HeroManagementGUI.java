package HeroQuestArena;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeroManagementGUI extends JFrame {

    private final GameManager gameManager;
    private JTextArea heroListTextArea, battleLogTextArea;
    private JTextField nameField, hpField, attackPowerField, defenseLevelField, specialField;
    private JComboBox<String> classComboBox, hero1ComboBox, hero2ComboBox, upgradeHeroComboBox, statToUpgradeComboBox;

    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color PANEL_COLOR = new Color(255, 255, 240);
    private static final Color BUTTON_COLOR = new Color(150, 200, 255);
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);

    public HeroManagementGUI(GameManager gameManager) {
        this.gameManager = gameManager;
        setTitle("Hero Quest Arena - Character Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR); 

        // Create main panels
        JPanel creationPanel = createCreationPanel();
        JPanel displayPanel = createDisplayPanel();
        JPanel upgradePanel = createUpgradePanel();
        JPanel battlePanel = createBattlePanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Create Hero", creationPanel);
        tabbedPane.addTab("View Heroes", displayPanel);
        tabbedPane.addTab("Upgrade Hero", upgradePanel);
        tabbedPane.addTab("Battle Simulation", battlePanel);

        add(tabbedPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createCreationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PANEL_COLOR);  // Set panel background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels and Fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(LABEL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, gbc);
        gbc.weightx = 0.0;

        JLabel classLabel = new JLabel("Class:");
        classLabel.setForeground(TEXT_COLOR);
        classLabel.setFont(LABEL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(classLabel, gbc);

        String[] classes = {"Warrior", "Mage", "Archer"};
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

        JButton createButton = new JButton("Create Hero");
        createButton.setBackground(BUTTON_COLOR); // Set button color
        createButton.setForeground(TEXT_COLOR);  //set button text color
        createButton.addActionListener(e -> createHero());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_COLOR);

        heroListTextArea = new JTextArea();
        heroListTextArea.setEditable(false);
        heroListTextArea.setBackground(Color.WHITE); // Set text area background
        heroListTextArea.setForeground(TEXT_COLOR);
        JScrollPane scrollPane = new JScrollPane(heroListTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton viewButton = new JButton("View Hero Details");
        viewButton.setBackground(BUTTON_COLOR);
        viewButton.setForeground(TEXT_COLOR);
        viewButton.addActionListener(e -> viewHeroDetails());
        panel.add(viewButton, BorderLayout.SOUTH);

        return panel;
    }

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

        upgradeHeroComboBox = new JComboBox<>();
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

        String[] stats = {"HP", "attackPower", "defenseLevel"};
        statToUpgradeComboBox = new JComboBox<>(stats);
        statToUpgradeComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(statToUpgradeComboBox, gbc);

        JButton upgradeButton = new JButton("Upgrade Stats");
        upgradeButton.setBackground(BUTTON_COLOR);
        upgradeButton.setForeground(TEXT_COLOR);
        upgradeButton.addActionListener(e -> upgradeStats());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(upgradeButton, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

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

        hero1ComboBox = new JComboBox<>();
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

        hero2ComboBox = new JComboBox<>();
        hero2ComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hero2ComboBox, gbc);

        battleLogTextArea = new JTextArea();
        battleLogTextArea.setEditable(false);
        battleLogTextArea.setBackground(Color.WHITE);
        battleLogTextArea.setForeground(TEXT_COLOR);

        JScrollPane battleLogScrollPane = new JScrollPane(battleLogTextArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(battleLogScrollPane, gbc);
        gbc.weighty = 0.0;

        JButton battleButton = new JButton("Simulate Battle");
        battleButton.setBackground(BUTTON_COLOR);
        battleButton.setForeground(TEXT_COLOR);
        battleButton.addActionListener(e -> simulateBattle());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(battleButton, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

    private void createHero() {
        try {
            String name = nameField.getText();
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Hero name cannot be empty.");
                return;
            }
            String classType = (String) classComboBox.getSelectedItem();
            int hp = Integer.parseInt(hpField.getText());
            int attackPower = Integer.parseInt(attackPowerField.getText());
            int defenseLevel = Integer.parseInt(defenseLevelField.getText());
            int special = Integer.parseInt(specialField.getText());

            Hero newHero = gameManager.createHero(name, classType, hp, attackPower, defenseLevel, special);
            gameManager.addHero(newHero);

            updateHeroComboBoxes(); // Refresh combo boxes *after* adding the hero
            JOptionPane.showMessageDialog(this, "Hero created successfully!");
            clearHeroCreationFields();
            viewHeroDetails();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers for HP, Attack, and Defense.");
        } catch (IllegalArgumentException e) { //Catch IllegalArgumentException
            JOptionPane.showMessageDialog(this, e.getMessage()); //Display the message from addHero
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating hero: " + e.getMessage());
        }
    }

    private void viewHeroDetails() {
        StringBuilder heroDetails = new StringBuilder();
        List<Hero> allHeroes = gameManager.getAllHeroes();
        if (allHeroes.isEmpty()) {
            heroDetails.append("No heroes created yet.");
        } else {
            for (Hero hero : allHeroes) {
                heroDetails.append(hero.toString()).append("\n");
            }
        }
        heroListTextArea.setText(heroDetails.toString());
    }

    private void upgradeStats() {
        try {
            String heroName = (String) upgradeHeroComboBox.getSelectedItem();
            String statToUpgrade = (String) statToUpgradeComboBox.getSelectedItem();

            if (heroName == null || heroName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please create a hero first.");
                return;
            }

            int upgradeAmount = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter upgrade amount:"));

            Hero heroToUpgrade = gameManager.getHero(heroName);
            if (heroToUpgrade != null) {
                gameManager.upgradeHeroStats(heroToUpgrade, statToUpgrade, upgradeAmount);
                viewHeroDetails(); // Refresh hero list
                JOptionPane.showMessageDialog(this, heroName + "'s " + statToUpgrade + " upgraded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Hero not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number for the upgrade amount.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Please select a hero and stat to upgrade.");
        }
    }

    private void simulateBattle() {
        String hero1Name = (String) hero1ComboBox.getSelectedItem();
        String hero2Name = (String) hero2ComboBox.getSelectedItem();

        Hero hero1 = gameManager.getHero(hero1Name);
        Hero hero2 = gameManager.getHero(hero2Name);

        if (hero1 != null && hero2 != null) {
            String battleResult = gameManager.simulateBattle(hero1, hero2);
            battleLogTextArea.setText(battleResult);
        } else {
            JOptionPane.showMessageDialog(this, "One or both heroes not found.");
        }
    }

    private void clearHeroCreationFields() {
        nameField.setText("");
        hpField.setText("");
        attackPowerField.setText("");
        defenseLevelField.setText("");
        specialField.setText("");
    }

    private void updateHeroComboBoxes() {
        upgradeHeroComboBox.removeAllItems();
        hero1ComboBox.removeAllItems();
        hero2ComboBox.removeAllItems();

        List<Hero> allHeroes = gameManager.getAllHeroes();
        if (allHeroes != null) {
            for (Hero hero : allHeroes) {
                upgradeHeroComboBox.addItem(hero.getName());
                hero1ComboBox.addItem(hero.getName());
                hero2ComboBox.addItem(hero.getName());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager gameManager = new GameManager();
            new HeroManagementGUI(gameManager);
        });
    }
}