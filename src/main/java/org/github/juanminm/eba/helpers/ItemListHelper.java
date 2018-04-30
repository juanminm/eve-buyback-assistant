package org.github.juanminm.eba.helpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.github.juanminm.eba.helpers.AppraisalHelper.EveApraisalMethod;
import org.github.juanminm.eba.vo.Appraisal;
import org.github.juanminm.eba.vo.Evepraisal;
import org.github.juanminm.eba.vo.Item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemListHelper {
    
    private static ItemListHelper itemListHelper;

    public static ItemListHelper getInstance() {
        if (itemListHelper == null)
            itemListHelper = new ItemListHelper();
        
        return itemListHelper;
    }

    public List<Item> getItemList(String jsonResponse, EveApraisalMethod mode,
            boolean showBuybackList, float margin) {
        Gson gson = new GsonBuilder().create();
        List<Item> items;
        List<Item> itemOutputList = new ArrayList<>();
        Appraisal appraisal;

        appraisal = mode == EveApraisalMethod.GET
                ? gson.fromJson(jsonResponse, Appraisal.class)
                : gson.fromJson(jsonResponse, Evepraisal.class)
                        .getAppraisal();

        items = appraisal.getItems();

        for (Item item : items) {
            double minSellPrice = item.getPrices().getSell()
                    .getMin();
            double maxBuyPrice = item.getPrices().getBuy().getMax();

            if (!showBuybackList
                    && minSellPrice >= maxBuyPrice * margin / 100) {
                itemOutputList.add(item);
            } else if (showBuybackList && minSellPrice < maxBuyPrice * margin / 100) {
                itemOutputList.add(item);
            }
        }

        itemOutputList.sort(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return i1.getName().compareTo(i2.getName());
            }
        });

        return itemOutputList;
    }
}
