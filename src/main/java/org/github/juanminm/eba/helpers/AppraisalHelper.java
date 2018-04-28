package org.github.juanminm.eba.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class AppraisalHelper {
    public static enum EveApraisalMethod {
        GET, POST
    }

    public static AppraisalHelper aHInstance = null;

    private static final Logger LOGGER = Logger
            .getLogger(AppraisalHelper.class.getName());

    public static AppraisalHelper getInstance() {
        if (appraisalHelper == null)
            appraisalHelper = new AppraisalHelper();

        return appraisalHelper;
    }

    final String DEFAULT_PERSIST_APPRAISAL = "no"; // Modifiable

    public String getEvepraisalJson(String input, EveApraisalMethod mode,
            String market, float pricePercent) throws IOException {
        HttpsURLConnection conn = null;
        String jsonResponse = "";
        int responseCode;

        LOGGER.info("Establishing connection to Evepraisal...");
        if (mode == EveApraisalMethod.GET) {
            conn = getAppraisal(input);
        } else if (mode == EveApraisalMethod.POST) {
            conn = createAppraisal(input, market, pricePercent);
        }

        if (conn != null) {
            BufferedReader in;
            String line;

            responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                while ((line = in.readLine()) != null) {
                    jsonResponse += line;
                }
                LOGGER.info(jsonResponse);

            } else {
                LOGGER.warning("Connection to server could not be made. HTTP "
                        + responseCode);
            }
        } else {
            LOGGER.warning("Connection has not been established.");
        }

        return jsonResponse;
    }

    private HttpsURLConnection createAppraisal(String assetList, String market,
            float pricePercent) throws IOException {
        String EVEAPPRAISAL_POST_URL = "https://evepraisal.com/appraisal.json";
        String urlParameters = String.format(
                "market=%s&raw_textarea=%s&price_percentage=%s&persist=%s",
                market.toLowerCase(), assetList, pricePercent,
                DEFAULT_PERSIST_APPRAISAL);
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
