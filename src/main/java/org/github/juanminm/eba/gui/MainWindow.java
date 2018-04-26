package org.github.juanminm.eba.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import org.github.juanminm.eba.vo.Item;

public class MainWindow {
    private final String EVEPRAISAL_URL = "evepraisalURL";
    private final String ASSETS_PASTE = "assetsPaste";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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
    private JPanel panel;
    private JPanel panel_1;
    private JButton submitBtn;
    private JTable excludedFromBuybackTable;
    private JScrollPane scrollPane;

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
        mainFrame.setTitle("EVE Buyback Apraisal");
        mainFrame.setResizable(false);
        mainFrame.setBounds(100, 100, 823, 557);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        evepraisalURLRadio = new JRadioButton("Evepraisal URL");
        evepraisalURLRadio.setActionCommand(EVEPRAISAL_URL);
        evepraisalMethodBGroup.add(evepraisalURLRadio);
        evepraisalURLRadio.setSelected(true);
        evepraisalURLRadio.setBounds(10, 7, 109, 23);
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
        assetsPasteRadio.setBounds(421, 7, 109, 23);
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

        panel = new JPanel();
        panel.setBounds(10, 215, 797, 23);
        mainFrame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 797, 0 };
        gbl_panel.rowHeights = new int[] { 20, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

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
                        mode);

                JTableHelper.getInstance().fillTable(excludedFromBuybackTable,
                        items);
            }
        });
        GridBagConstraints gbc_submitBtn = new GridBagConstraints();
        gbc_submitBtn.anchor = GridBagConstraints.EAST;
        gbc_submitBtn.fill = GridBagConstraints.VERTICAL;
        gbc_submitBtn.gridx = 0;
        gbc_submitBtn.gridy = 0;
        panel.add(submitBtn, gbc_submitBtn);

        panel_1 = new JPanel();
        panel_1.setBounds(10, 249, 797, 269);
        mainFrame.getContentPane().add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 286, 0 };
        gbl_panel_1.rowHeights = new int[] { 114, 0 };
        gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);
        
        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        panel_1.add(scrollPane, gbc_scrollPane);

        excludedFromBuybackTable = new JTable();
        scrollPane.setViewportView(excludedFromBuybackTable);
        excludedFromBuybackTable.setCellSelectionEnabled(true);
    }
}
