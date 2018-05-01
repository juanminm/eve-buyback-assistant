package org.github.juanminm.eba.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.github.juanminm.eba.vo.Item;

public class JTableHelper {
    public static JTableHelper jTableHelper = null;

    public static JTableHelper getInstance() {
        if (jTableHelper == null)
            jTableHelper = new JTableHelper();

        return jTableHelper;
    }

    public void fillTable(JTable excludedFromBuybackTable, List<Item> items) {
        excludedFromBuybackTable.setModel(new ItemTableModel(items));
    }

}

class ItemTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private List<ItemRow> itemRows = new ArrayList<>();

    public ItemTableModel(List<Item> items) {
        for (Item item : items) {
            ItemRow itemrow = new ItemRow(item);
            this.itemRows.add(itemrow);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return itemRows.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return itemRows.get(rowIndex).getUrlImage();
            case 1:
                return itemRows.get(rowIndex).getItem().getName();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
        }
        return null;
    }
}

class ItemRow {
    private Item item;
    private String urlImage = ""; // TODO Get image resource from EVE Online
                                  // image resources

    public ItemRow(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
