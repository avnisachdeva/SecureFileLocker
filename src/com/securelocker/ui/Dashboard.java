
package com.securelocker.ui;

// Imports Swing components required to create the GUI
import javax.swing.*;

// Imports graphics, layouts, colors, and fonts
import java.awt.*;

// Imports classes used for custom painting
import java.awt.geom.RoundRectangle2D;

public class Dashboard {

    // Defines the main application window
    private JFrame frame;

    // Defines the primary background color
    private final Color backgroundColor = new Color(244, 247, 255);

    // Defines the main heading color
    private final Color headingColor = new Color(24, 43, 88);

    // Defines the default text color
    private final Color textColor = new Color(110, 126, 158);

    // Starts the application
    public static void main(String[] args) {

        // Creates and displays the GUI on the Swing event thread
        SwingUtilities.invokeLater(() -> new Dashboard().createGUI());
    }

    // Creates and arranges all GUI components
    private void createGUI() {

        // Creates the main application window
        frame = new JFrame("Secure File Locker");

        // Closes the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the initial window dimensions
        frame.setSize(760, 700);

        // Centers the window on the screen
        frame.setLocationRelativeTo(null);

        // Prevents the window from becoming too small
        frame.setMinimumSize(new Dimension(650, 620));

        // Creates a gradient background panel
        JPanel background = new JPanel() {

            // Paints a soft gradient behind the dashboard
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Creates a graphics copy for safe custom painting
                Graphics2D g2 = (Graphics2D) g.create();

                // Enables smooth rendering
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Creates the background gradient
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(249, 251, 255),
                    getWidth(), getHeight(),
                    new Color(222, 232, 255)
                );

                // Fills the panel with the gradient
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Releases the graphics copy
                g2.dispose();
            }
        };

        // Uses BorderLayout for the main background
        background.setLayout(new BorderLayout());

        // Creates the top application header
        JPanel header = createHeader();

        // Creates the central dashboard content
        JPanel content = createContent();

        // Adds the header to the top of the window
        background.add(header, BorderLayout.NORTH);

        // Adds the main content to the center
        background.add(content, BorderLayout.CENTER);

        // Sets the background as the main window content
        frame.setContentPane(background);

        // Displays the completed application window
        frame.setVisible(true);
    }

    // Creates the top header containing the app icon and name
    private JPanel createHeader() {

        // Creates the header panel
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 15));

        // Gives the header a white background
        header.setBackground(Color.WHITE);

        // Creates a simple lock icon
        JLabel icon = new JLabel("\uD83D\uDD12");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));

        // Creates the application name
        JLabel appName = new JLabel("Secure File Locker");
        appName.setFont(new Font("Segoe UI", Font.BOLD, 19));
        appName.setForeground(headingColor);

        // Adds the icon and application name
        header.add(icon);
        header.add(appName);

        // Returns the completed header
        return header;
    }

    // Builds the central dashboard and its action buttons
    private JPanel createContent() {

        // Creates the main content panel
        JPanel content = new JPanel();
        content.setOpaque(false);

        // Arranges the content vertically
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Adds spacing above the heading
        content.add(Box.createVerticalStrut(35));

        // Creates a large lock illustration
        JLabel lockIcon = new JLabel("\uD83D\uDD10", SwingConstants.CENTER);
        lockIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 65));
        lockIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lockIcon);

        // Creates the main dashboard heading
        JLabel title = new JLabel("SECURE FILE LOCKER");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(headingColor);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createVerticalStrut(8));
        content.add(title);

        // Creates a subtitle describing the application
        JLabel subtitle = new JLabel(
            "Encrypt  \u2022  Protect  \u2022  Keep Your Files Safe"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitle);

        // Adds spacing before the action buttons
        content.add(Box.createVerticalStrut(30));

        // Creates the file encryption button
        JButton lockButton = createActionButton(
            "\uD83D\uDD12", "Lock File",
            "Encrypt and secure your file",
            new Color(12, 183, 147),
            new Color(5, 151, 122)
        );

        // Creates the file decryption button
        JButton unlockButton = createActionButton(
            "\uD83D\uDD13", "Unlock File",
            "Decrypt and access your file",
            new Color(48, 137, 245),
            new Color(37, 103, 220)
        );

        // Creates the application exit button
        JButton exitButton = createActionButton(
            "\u2192", "Exit",
            "Close the application",
            new Color(239, 76, 100),
            new Color(215, 54, 83)
        );

        // Opens a file selection dialog for the lock action
        lockButton.addActionListener(e ->
            JOptionPane.showMessageDialog(
                frame,
                "File encryption will be added in the next step.",
                "Lock File",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        // Opens a file selection dialog for the unlock action
        unlockButton.addActionListener(e ->
            JOptionPane.showMessageDialog(
                frame,
                "File decryption will be added in the next step.",
                "Unlock File",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        // Closes the application when Exit is clicked
        exitButton.addActionListener(e -> frame.dispose());

        // Adds the three action buttons to the dashboard
        content.add(createButtonContainer(lockButton));
        content.add(Box.createVerticalStrut(15));
        content.add(createButtonContainer(unlockButton));
        content.add(Box.createVerticalStrut(15));
        content.add(createButtonContainer(exitButton));

        // Adds spacing before the footer
        content.add(Box.createVerticalStrut(25));

        // Creates the footer security message
        JLabel footer = new JLabel(
            "\u25C6   Your Security  \u2022  Our Priority   \u25C6"
        );
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        footer.setForeground(textColor);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adds the footer to the dashboard
        content.add(footer);

        // Adds bottom spacing
        content.add(Box.createVerticalStrut(20));

        // Returns the completed content panel
        return content;
    }

    // Creates a colorful button with a title and subtitle
    private JButton createActionButton(
            String iconText,
            String titleText,
            String description,
            Color startColor,
            Color endColor) {

        // Creates a custom button with rounded corners
        JButton button = new JButton() {

            // Paints the button background with a gradient
            @Override
            protected void paintComponent(Graphics g) {

                // Creates a graphics copy for custom painting
                Graphics2D g2 = (Graphics2D) g.create();

                // Enables smooth edges
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Slightly darkens the button when hovered
                Color first = getModel().isRollover()
                    ? startColor.darker() : startColor;

                // Creates the button gradient
                GradientPaint gradient = new GradientPaint(
                    0, 0, first,
                    getWidth(), getHeight(), endColor
                );

                // Draws the rounded button background
                g2.setPaint(gradient);
                g2.fill(new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), 22, 22
                ));

                // Releases the graphics copy
                g2.dispose();

                // Paints the button text and icon
                super.paintComponent(g);
            }
        };

        // Removes the default button fill and border
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // Uses a horizontal layout for the button content
        button.setLayout(new BorderLayout(15, 0));

        // Adds padding inside the button
        button.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 20));

        // Creates the icon shown on the left
        JLabel icon = new JLabel(iconText);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 29));
        icon.setForeground(Color.WHITE);

        // Creates the button title
        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Segoe UI", Font.BOLD, 19));
        title.setForeground(Color.WHITE);

        // Creates the explanatory subtitle
        JLabel detail = new JLabel(description);
        detail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        detail.setForeground(new Color(245, 250, 255));

        // Groups the title and subtitle vertically
        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.add(title);
        labels.add(Box.createVerticalStrut(4));
        labels.add(detail);

        // Creates a right-side arrow
        JLabel arrow = new JLabel("\u203A");
        arrow.setFont(new Font("Segoe UI", Font.BOLD, 30));
        arrow.setForeground(Color.WHITE);

        // Adds the icon, text, and arrow to the button
        button.add(icon, BorderLayout.WEST);
        button.add(labels, BorderLayout.CENTER);
        button.add(arrow, BorderLayout.EAST);

        // Sets the button's preferred dimensions
        button.setPreferredSize(new Dimension(440, 82));
        button.setMaximumSize(new Dimension(440, 82));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Returns the styled button
        return button;
    }

    // Centers each action button inside a transparent container
    private JPanel createButtonContainer(JButton button) {

        // Creates a panel that centers its child component
        JPanel container = new JPanel(new GridBagLayout());

        // Keeps the container transparent
        container.setOpaque(false);

        // Adds the button to the center
        container.add(button);

        // Sets the container's preferred height
        container.setMaximumSize(new Dimension(600, 82));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Returns the button container
        return container;
    }
}

