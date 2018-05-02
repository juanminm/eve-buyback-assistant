package org.github.juanminm.eba.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTabbedPane settingTabPane;
    private JPanel buttonPane;
    private JButton okButton;
    private JButton cancelButton;
    private JButton applyBtn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SettingsDialog dialog = new SettingsDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public SettingsDialog() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        settingTabPane = new JTabbedPane(JTabbedPane.LEFT);
        getContentPane().add(settingTabPane, BorderLayout.CENTER);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            // TODO Save settings
            dispose();
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        applyBtn = new JButton("Apply");
        applyBtn.setEnabled(false);
        applyBtn.addActionListener(e -> {
            saveSettings();
            ((JButton) e.getSource()).setEnabled(false);
        });
        buttonPane.add(applyBtn);
    }

    private void saveSettings() {
        // TODO Save settings
    }

}
