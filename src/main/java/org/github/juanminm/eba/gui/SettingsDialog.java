package org.github.juanminm.eba.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class SettingsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTabbedPane settingTabPane;
    private JPanel buttonPane;
    private JButton okButton;
    private JButton cancelButton;
    private JButton applyBtn;
    private JPanel proxyPanel;
    private JRadioButton noProxyRBtn;
    private JRadioButton withProxyRBtn;
    private JLabel hostLbl;
    private JTextField hostInput;
    private JLabel portLbl;
    private JCheckBox credentialsChk;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JSpinner portSp;
    private final ButtonGroup proxyBGroup = new ButtonGroup();

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
        setBounds(100, 100, 740, 460);
        getContentPane().setLayout(new BorderLayout());

        settingTabPane = new JTabbedPane(JTabbedPane.LEFT);
        getContentPane().add(settingTabPane, BorderLayout.CENTER);

        proxyPanel = new JPanel();
        settingTabPane.addTab("Network", null, proxyPanel, null);
        GridBagLayout gbl_proxyPanel = new GridBagLayout();
        gbl_proxyPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_proxyPanel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0 };
        gbl_proxyPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
                Double.MIN_VALUE };
        gbl_proxyPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                1.0, Double.MIN_VALUE };
        proxyPanel.setLayout(gbl_proxyPanel);

        noProxyRBtn = new JRadioButton("Without proxy");
        proxyBGroup.add(noProxyRBtn);
        noProxyRBtn.setSelected(true);
        GridBagConstraints gbc_noProxyRBtn = new GridBagConstraints();
        gbc_noProxyRBtn.fill = GridBagConstraints.HORIZONTAL;
        gbc_noProxyRBtn.gridwidth = 5;
        gbc_noProxyRBtn.insets = new Insets(0, 0, 5, 0);
        gbc_noProxyRBtn.anchor = GridBagConstraints.NORTH;
        gbc_noProxyRBtn.gridx = 0;
        gbc_noProxyRBtn.gridy = 0;
        proxyPanel.add(noProxyRBtn, gbc_noProxyRBtn);

        withProxyRBtn = new JRadioButton("With proxy");
        proxyBGroup.add(withProxyRBtn);
        GridBagConstraints gbc_withProxyRBtn = new GridBagConstraints();
        gbc_withProxyRBtn.gridwidth = 5;
        gbc_withProxyRBtn.anchor = GridBagConstraints.WEST;
        gbc_withProxyRBtn.insets = new Insets(0, 0, 5, 0);
        gbc_withProxyRBtn.gridx = 0;
        gbc_withProxyRBtn.gridy = 1;
        proxyPanel.add(withProxyRBtn, gbc_withProxyRBtn);

        hostLbl = new JLabel("Host");
        GridBagConstraints gbc_hostLbl = new GridBagConstraints();
        gbc_hostLbl.anchor = GridBagConstraints.WEST;
        gbc_hostLbl.insets = new Insets(0, 0, 5, 5);
        gbc_hostLbl.gridx = 0;
        gbc_hostLbl.gridy = 2;
        proxyPanel.add(hostLbl, gbc_hostLbl);

        hostInput = new JTextField();
        hostLbl.setLabelFor(hostInput);
        GridBagConstraints gbc_hostInput = new GridBagConstraints();
        gbc_hostInput.gridwidth = 2;
        gbc_hostInput.fill = GridBagConstraints.HORIZONTAL;
        gbc_hostInput.insets = new Insets(0, 0, 5, 5);
        gbc_hostInput.gridx = 1;
        gbc_hostInput.gridy = 2;
        proxyPanel.add(hostInput, gbc_hostInput);
        hostInput.setColumns(10);

        portLbl = new JLabel("Port");
        GridBagConstraints gbc_portLbl = new GridBagConstraints();
        gbc_portLbl.insets = new Insets(0, 0, 5, 5);
        gbc_portLbl.gridx = 3;
        gbc_portLbl.gridy = 2;
        proxyPanel.add(portLbl, gbc_portLbl);

        portSp = new JSpinner();
        portLbl.setLabelFor(portSp);
        portSp.setModel(new SpinnerNumberModel(80, 0, 65535, 1));
        GridBagConstraints gbc_portSp = new GridBagConstraints();
        gbc_portSp.insets = new Insets(0, 0, 5, 0);
        gbc_portSp.gridx = 4;
        gbc_portSp.gridy = 2;
        proxyPanel.add(portSp, gbc_portSp);

        credentialsChk = new JCheckBox("Requires credentials");
        GridBagConstraints gbc_credentialsChk = new GridBagConstraints();
        gbc_credentialsChk.anchor = GridBagConstraints.WEST;
        gbc_credentialsChk.gridwidth = 5;
        gbc_credentialsChk.insets = new Insets(0, 0, 5, 0);
        gbc_credentialsChk.gridx = 0;
        gbc_credentialsChk.gridy = 3;
        proxyPanel.add(credentialsChk, gbc_credentialsChk);

        usernameLbl = new JLabel("Username");
        GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
        gbc_usernameLbl.gridwidth = 2;
        gbc_usernameLbl.anchor = GridBagConstraints.EAST;
        gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLbl.gridx = 0;
        gbc_usernameLbl.gridy = 4;
        proxyPanel.add(usernameLbl, gbc_usernameLbl);

        usernameInput = new JTextField();
        usernameInput.setEnabled(false);
        usernameLbl.setLabelFor(usernameInput);
        GridBagConstraints gbc_usernameInput = new GridBagConstraints();
        gbc_usernameInput.gridwidth = 3;
        gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
        gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
        gbc_usernameInput.gridx = 2;
        gbc_usernameInput.gridy = 4;
        proxyPanel.add(usernameInput, gbc_usernameInput);
        usernameInput.setColumns(10);

        passwordLbl = new JLabel("Password");
        GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
        gbc_passwordLbl.gridwidth = 2;
        gbc_passwordLbl.anchor = GridBagConstraints.EAST;
        gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
        gbc_passwordLbl.gridx = 0;
        gbc_passwordLbl.gridy = 5;
        proxyPanel.add(passwordLbl, gbc_passwordLbl);

        passwordInput = new JPasswordField();
        passwordInput.setEnabled(false);
        passwordLbl.setLabelFor(passwordInput);
        GridBagConstraints gbc_passwordInput = new GridBagConstraints();
        gbc_passwordInput.gridwidth = 3;
        gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
        gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordInput.gridx = 2;
        gbc_passwordInput.gridy = 5;
        proxyPanel.add(passwordInput, gbc_passwordInput);
        passwordInput.setColumns(10);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            saveSettings();
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
        });
        buttonPane.add(applyBtn);
    }

    private void enableApplyButton() {
        applyBtn.setEnabled(true);
    }

    private void saveSettings() {
        //TODO Set each property and save it to a properties file
        if (applyBtn.isEnabled())
            applyBtn.setEnabled(false);
    }

}
