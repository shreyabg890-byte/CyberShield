package com.detection;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UrlAnalyzer {

    public List<String> analyze(String url) {

        List<String> indicators = new ArrayList<>();

        if (url == null || url.trim().isEmpty()) {
            return indicators;
        }

        String link = url.trim().toLowerCase();

        // Check whether HTTPS is missing
        if (!link.startsWith("https://")) {
            indicators.add("URL does not use HTTPS");
        }

        // Check for IP address instead of domain name
        if (link.matches("https?://\\d{1,3}(\\.\\d{1,3}){3}.*")) {
            indicators.add("URL uses an IP address instead of a domain name");
        }

        // Check for suspicious URL characters
        if (link.contains("@")) {
            indicators.add("URL contains @ symbol, which may hide the actual destination");
        }

        // Check for suspicious keywords
        if (link.contains("login") ||
            link.contains("verify") ||
            link.contains("claim-prize") ||
            link.contains("free-reward")) {

            indicators.add("URL contains potentially suspicious keywords");
        }

        return indicators;
    }
}