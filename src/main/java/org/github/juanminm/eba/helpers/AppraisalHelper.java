package org.github.juanminm.eba.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.github.juanminm.eba.vo.Appraisal;
import org.github.juanminm.eba.vo.Evepraisal;
import org.github.juanminm.eba.vo.Item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AppraisalHelper {
    public static enum EveApraisalMethod {
        GET, POST
    }

    public static AppraisalHelper aHInstance = null;

    private static final Logger LOGGER = Logger
            .getLogger(AppraisalHelper.class.getName());

    public static AppraisalHelper getInstance() {
        if (aHInstance == null)
            aHInstance = new AppraisalHelper();

        return aHInstance;
    }

    final String DEFAULT_MARKET = "jita"; // TODO Modifiable
    final float DEFAULT_MARKET_PERCENTAGE = 1.0f; // Modifiable
    final String DEFAULT_PERSIST_APPRAISAL = "no"; // Modifiable
    final float SELL_BUY_MARGIN = 1.2f; // TODO Changeable

    public List<Item> getWorstMargins(String input, EveApraisalMethod mode) {
        HttpsURLConnection conn = null;
        List<Item> excludedItems = new ArrayList<>();
        String jsonResponse = "";
        int responseCode;
        try {
            LOGGER.info("Establishing connection to Evepraisal...");
            if (mode == EveApraisalMethod.GET) {
                conn = getAppraisal(input);
            } else if (mode == EveApraisalMethod.POST) {
                conn = createAppraisal(input);
            }

            if (conn != null) {
                Gson gson = new GsonBuilder().create();
                BufferedReader in;
                List<Item> items;
                String line;

                responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    Appraisal appraisal;

                    in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

                    while ((line = in.readLine()) != null) {
                        jsonResponse += line;
                    }
                    LOGGER.info(jsonResponse);

                    appraisal = mode == EveApraisalMethod.GET
                            ? gson.fromJson(jsonResponse, Appraisal.class)
                            : gson.fromJson(jsonResponse, Evepraisal.class)
                                    .getAppraisal();

                    items = appraisal.getItems();

                    for (Item item : items) {
                        double minSellPrice = item.getPrices().getSell()
                                .getMin();
                        double maxBuyPrice = item.getPrices().getBuy().getMax();

                        if (minSellPrice >= maxBuyPrice * SELL_BUY_MARGIN) {
                            excludedItems.add(item);
                        }
                    }

                    excludedItems.sort(new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                } else {
                    LOGGER.warning(
                            "Connection to server could not be made. HTTP "
                                    + responseCode);
                }

            } else {
                LOGGER.warning("Connection has not been established.");
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return excludedItems;
    }

    private HttpsURLConnection createAppraisal(String assetList)
            throws IOException {
        String EVEAPPRAISAL_POST_URL = "https://evepraisal.com/appraisal.json";
        String urlParameters = "market=" + DEFAULT_MARKET + "&raw_textarea="
                + assetList + "&persist=" + DEFAULT_PERSIST_APPRAISAL;
        URL url;
        byte[] postData;
        int postDataLength;

        url = new URL(EVEAPPRAISAL_POST_URL);
        postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        postDataLength = postData.length;
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length",
                Integer.toString(postDataLength));

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        wr.flush();
        wr.close();

        return conn;
    }

    private HttpsURLConnection getAppraisal(String url) throws IOException {
        URL obj = new URL(url + ".json");
        HttpsURLConnection conn;

        conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }

}
