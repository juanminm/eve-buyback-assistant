package org.github.juanminm.eba.helpers;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.github.juanminm.eba.managers.SettingsManager;

public class ProxyHelper {

    public static void enableProxy(boolean withAuth) {
        SettingsManager settingsManager = SettingsManager.getInstance();
        System.setProperty("https.proxyHost",
                settingsManager.getProperty("proxy.host"));
        System.setProperty("https.proxyPort",
                settingsManager.getProperty("proxy.port"));

        if (withAuth) {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            settingsManager.getProperty("proxy.username"),
                            settingsManager.getProperty("proxy.password")
                                    .toCharArray());
                }
            });
        }
    }

    public static void disableProxy() {
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
        Authenticator.setDefault(null);
    }
}
