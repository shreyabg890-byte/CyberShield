package com.detection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextAnalyzer {

    private static final Pattern URL_PATTERN =
            Pattern.compile("https?://\\S+|www\\.\\S+",
                    Pattern.CASE_INSENSITIVE);

    public List<String> analyze(String message) {

        List<String> indicators = new ArrayList<>();

        if (message == null || message.trim().isEmpty()) {
            return indicators;
        }

        String text = message.toLowerCase();

        // 1. Check urgency-related words
        if (text.contains("urgent") ||
            text.contains("immediately") ||
            text.contains("act now") ||
            text.contains("within 24 hours")) {

            indicators.add("Urgent or pressure-based language detected");
        }

        // 2. Check sensitive information requests
        if ((text.contains("otp") ||
             text.contains("password") ||
             text.contains("cvv") ||
             text.contains("pin")) &&

            (text.contains("share") ||
             text.contains("send") ||
             text.contains("provide") ||
             text.contains("enter") ||
             text.contains("tell us"))) {

            if (!text.contains("never share") &&
                !text.contains("do not share") &&
                !text.contains("don't share") &&
                !text.contains("never send")) {

                indicators.add("Possible request for sensitive information");
            }
        }

        // 3. Check payment-related words
        if (text.contains("pay now") ||
            text.contains("transfer money") ||
            text.contains("upi") ||
            text.contains("bank account")) {

            indicators.add("Payment or financial request detected");
        }

        // 4. Check prize-related words
        if (text.contains("winner") ||
            text.contains("won a prize") ||
            text.contains("prize") ||
            text.contains("lottery") ||
            text.contains("claim reward") ||
            text.contains("claim your reward") ||
            text.contains("free gift")) {

            indicators.add("Possible fake prize or reward message");
        }

        // 5. Check whether a URL is present
        if (URL_PATTERN.matcher(text).find()) {

            indicators.add("Message contains a URL that needs verification");
        }

        return indicators;
    }
}