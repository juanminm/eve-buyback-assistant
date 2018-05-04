package org.github.juanminm.eba.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.github.juanminm.eba.helpers.AppraisalHelper;
import org.github.juanminm.eba.helpers.AppraisalHelper.EveApraisalMethod;
import org.github.juanminm.eba.helpers.JTableHelper;
import org.github.juanminm.eba.managers.SettingsManager;
import org.github.juanminm.eba.vo.Item;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {
    private static final Logger LOGGER = Logger
            .getLogger(MainWindow.class.getName());
    private final String EVEPRAISAL_URL = "evepraisalURL";
    private final String ASSETS_PASTE = "assetsPaste";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SettingsManager.getInstance().loadSettings();
        } catch (IOException e) {
            LOGGER.severe("Settings could not be loaded: " + e.getMessage());
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JTextArea assetsPasteInput;
    private JRadioButton assetsPasteRadio;
    private JScrollPane assetsPasteScrollPanel;
    private final ButtonGroup evepraisalMethodBGroup = new ButtonGroup();
    private JRadioButton evepraisalURLRadio;
    private JTextField evepraissalURLInput;
    private JFrame mainFrame;
    private JPanel parametersPanel;
    private JPanel outputPanel;
    private JButton submitBtn;
    private JTable itemListTable;
    private JScrollPane itemListTableScroll;
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmExit;
    private final ButtonGroup listItemToShowBGroup = new ButtonGroup();
    private JComboBox<String> marketCBox;
    private JLabel marketLbl;
    private JLabel listOfItemsLbl;
    private JLabel pricePercentLbl;
    private JSpinner pricePercentSp;
    private JRadioButton inBuybackRb;
    private JLabel sellBuyMarginLbl;
    private JSpinner sellBuyMarginSp;
    private JRadioButton sellOrderRb;
    private boolean showBuybackList = false;
    private JMenu mnTools;
    private JMenuItem mntmSettings;

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.setTitle("EVE Buyback Assistant");
        mainFrame.setResizable(false);
        mainFrame.setBounds(100, 100, 823, 650);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        evepraisalURLRadio = new JRadioButton("Evepraisal URL");
        evepraisalURLRadio.setActionCommand(EVEPRAISAL_URL);
        evepraisalMethodBGroup.add(evepraisalURLRadio);
        evepraisalURLRadio.setSelected(true);
        evepraisalURLRadio.setBounds(10, 7, 178, 23);
        evepraisalURLRadio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                evepraissalURLInput.setEnabled(true);
                assetsPasteInput.setEnabled(false);
            }
        });
        mainFrame.getContentPane().add(evepraisalURLRadio);

        evepraissalURLInput = new JTextField();
        evepraissalURLInput.setBounds(10, 36, 341, 20);
        mainFrame.getContentPane().add(evepraissalURLInput);
        evepraissalURLInput.setColumns(10);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(10, 200, 797, 2);
        mainFrame.getContentPane().add(separator1);

        assetsPasteRadio = new JRadioButton("Assets paste");
        assetsPasteRadio.setActionCommand(ASSETS_PASTE);
        evepraisalMethodBGroup.add(assetsPasteRadio);
        assetsPasteRadio.setBounds(421, 7, 233, 23);
        assetsPasteRadio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                assetsPasteInput.setEnabled(true);
                evepraissalURLInput.setEnabled(false);
            }
        });
        mainFrame.getContentPane().add(assetsPasteRadio);

        assetsPasteScrollPanel = new JScrollPane();
        assetsPasteScrollPanel.setBounds(421, 33, 386, 156);
        mainFrame.getContentPane().add(assetsPasteScrollPanel);

        assetsPasteInput = new JTextArea();
        assetsPasteInput.setEnabled(false);
        assetsPasteScrollPanel.setViewportView(assetsPasteInput);

        parametersPanel = new JPanel();
        parametersPanel.setBounds(10, 215, 797, 90);
        mainFrame.getContentPane().add(parametersPanel);
        GridBagLayout gbl_parametersPanel = new GridBagLayout();
        gbl_parametersPanel.columnWidths = new int[] { 0, 61, 75, 132, 96, 0,
                95, 0 };
        gbl_parametersPanel.rowHeights = new int[] { 30, 30, 30, 0 };
        gbl_parametersPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
                0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_parametersPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        parametersPanel.setLayout(gbl_parametersPanel);

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = evepraisalMethodBGroup.getSelection()
                        .getActionCommand();
                String input;
                EveApraisalMethod mode;
                List<Item> items;

                if (ASSETS_PASTE.equals(actionCommand)) {
                    input = assetsPasteInput.getText();
                    mode = EveApraisalMethod.POST;
                } else {
                    input = evepraissalURLInput.getText();
                    mode = EveApraisalMethod.GET;
                }

                items = AppraisalHelper.getInstance().getWorstMargins(input,
                        mode, (String) marketCBox.getSelectedItem(),
                        (float) pricePercentSp.getValue(),
                        (float) sellBuyMarginSp.getValue(), showBuybackList);

                JTableHelper.getInstance().fillTable(itemListTable, items);
            }
        });

        sellOrderRb = new JRadioButton("As sell order");
        sellOrderRb.setSelected(true);
        listItemToShowBGroup.add(sellOrderRb);
        sellOrderRb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showBuybackList = false;
            }
        });
        GridBagConstraints gbc_sellOrderRb = new GridBagConstraints();
        gbc_sellOrderRb.anchor = GridBagConstraints.WEST;
        gbc_sellOrderRb.insets = new Insets(0, 0, 5, 5);
        gbc_sellOrderRb.gridx = 4;
        gbc_sellOrderRb.gridy = 1;
        parametersPanel.add(sellOrderRb, gbc_sellOrderRb);
        GridBagConstraints gbc_submitBtn = new GridBagConstraints();
        gbc_submitBtn.insets = new Insets(0, 0, 5, 0);
        gbc_submitBtn.fill = GridBagConstraints.BOTH;
        gbc_submitBtn.gridx = 6;
        gbc_submitBtn.gridy = 1;
        parametersPanel.add(submitBtn, gbc_submitBtn);

        marketLbl = new JLabel("Market");
        GridBagConstraints gbc_marketLbl = new GridBagConstraints();
        gbc_marketLbl.anchor = GridBagConstraints.WEST;
        gbc_marketLbl.insets = new Insets(0, 0, 5, 5);
        gbc_marketLbl.gridx = 0;
        gbc_marketLbl.gridy = 0;
        parametersPanel.add(marketLbl, gbc_marketLbl);

        marketCBox = new JComboBox<>();
        marketCBox.setModel(new DefaultComboBoxModel<String>(new String[] {
                "Jita", "Universe", "Amarr", "Dodixie", "Hek", "Rens" }));
        marketCBox.setSelectedIndex(0);
        marketLbl.setLabelFor(marketCBox);
        GridBagConstraints gbc_marketCBox = new GridBagConstraints();
        gbc_marketCBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_marketCBox.gridwidth = 2;
        gbc_marketCBox.insets = new Insets(0, 0, 5, 5);
        gbc_marketCBox.gridx = 1;
        gbc_marketCBox.gridy = 0;
        parametersPanel.add(marketCBox, gbc_marketCBox);

        listOfItemsLbl = new JLabel("List of items to show");
        GridBagConstraints gbc_listOfItemsLbl = new GridBagConstraints();
        gbc_listOfItemsLbl.anchor = GridBagConstraints.WEST;
        gbc_listOfItemsLbl.insets = new Insets(0, 0, 5, 5);
        gbc_listOfItemsLbl.gridx = 4;
        gbc_listOfItemsLbl.gridy = 0;
        parametersPanel.add(listOfItemsLbl, gbc_listOfItemsLbl);

        pricePercentLbl = new JLabel("Market price percent");
        GridBagConstraints gbc_pricePercentLbl = new GridBagConstraints();
        gbc_pricePercentLbl.anchor = GridBagConstraints.WEST;
        gbc_pricePercentLbl.insets = new Insets(0, 0, 5, 5);
        gbc_pricePercentLbl.gridx = 0;
        gbc_pricePercentLbl.gridy = 1;
        parametersPanel.add(pricePercentLbl, gbc_pricePercentLbl);

        pricePercentSp = new JSpinner();
        pricePercentSp.setMinimumSize(new Dimension(100, 20));
        pricePercentSp.setModel(new SpinnerNumberModel(new Float(100),
                new Float(0), null, new Float(1)));
        GridBagConstraints gbc_pricePercentSp = new GridBagConstraints();
        gbc_pricePercentSp.fill = GridBagConstraints.HORIZONTAL;
        gbc_pricePercentSp.insets = new Insets(0, 0, 5, 5);
        gbc_pricePercentSp.gridx = 1;
        gbc_pricePercentSp.gridy = 1;
        parametersPanel.add(pricePercentSp, gbc_pricePercentSp);
        pricePercentLbl.setLabelFor(pricePercentSp);

        sellBuyMarginLbl = new JLabel("Sell-buy margin percent");
        GridBagConstraints gbc_sellBuyMarginLbl = new GridBagConstraints();
        gbc_sellBuyMarginLbl.insets = new Insets(0, 0, 0, 5);
        gbc_sellBuyMarginLbl.gridx = 0;
        gbc_sellBuyMarginLbl.gridy = 2;
        parametersPanel.add(sellBuyMarginLbl, gbc_sellBuyMarginLbl);

        sellBuyMarginSp = new JSpinner();
        sellBuyMarginSp.setModel(new SpinnerNumberModel(new Float(100),
                new Float(0), null, new Float(1)));
        GridBagConstraints gbc_spinner = new GridBagConstraints();
        gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
        gbc_spinner.insets = new Insets(0, 0, 0, 5);
        gbc_spinner.gridx = 1;
        gbc_spinner.gridy = 2;
        parametersPanel.add(sellBuyMarginSp, gbc_spinner);

        inBuybackRb = new JRadioButton("In buyback / Insta-sell");
        listItemToShowBGroup.add(inBuybackRb);
        inBuybackRb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showBuybackList = true;
            }
        });
        GridBagConstraints gbc_inBuybackRb = new GridBagConstraints();
        gbc_inBuybackRb.anchor = GridBagConstraints.WEST;
        gbc_inBuybackRb.insets = new Insets(0, 0, 0, 5);
        gbc_inBuybackRb.gridx = 4;
        gbc_inBuybackRb.gridy = 2;
        parametersPanel.add(inBuybackRb, gbc_inBuybackRb);

        outputPanel = new JPanel();
        outputPanel.setBounds(10, 317, 797, 269);
        mainFrame.getContentPane().add(outputPanel);
        GridBagLayout gbl_outputPanel = new GridBagLayout();
        gbl_outputPanel.columnWidths = new int[] { 286, 0 };
        gbl_outputPanel.rowHeights = new int[] { 114, 0 };
        gbl_outputPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_outputPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        outputPanel.setLayout(gbl_outputPanel);

        itemListTableScroll = new JScrollPane();
        GridBagConstraints gbc_itemListTableScroll = new GridBagConstraints();
        gbc_itemListTableScroll.fill = GridBagConstraints.BOTH;
        gbc_itemListTableScroll.gridx = 0;
        gbc_itemListTableScroll.gridy = 0;
        outputPanel.add(itemListTableScroll, gbc_itemListTableScroll);

        itemListTable = new JTable();
        itemListTableScroll.setViewportView(itemListTable);
        itemListTable.setCellSelectionEnabled(true);

        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new exitApp());
        mnFile.add(mntmExit);

        mnTools = new JMenu("Tools");
        menuBar.add(mnTools);

        mntmSettings = new JMenuItem("Settings");
        mnTools.add(mntmSettings);
        mntmSettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsDialog settingDialog = new SettingsDialog();
                settingDialog.setModal(true);
                settingDialog
                        .setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                settingDialog.setVisible(true);
            }
        });
    }

    static class exitApp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
