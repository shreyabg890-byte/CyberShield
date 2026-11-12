package com.detection;

import java.util.List;

public class RiskClassifier {

    public String classify(List<String> indicators) {

        if (indicators == null || indicators.isEmpty()) {
            return "LOW";
        }

        int count = indicators.size();

        if (count >= 3) {
            return "HIGH";
        }
        else if (count >= 1) {
            return "MEDIUM";
        }
        else {
            return "LOW";
        }
    }
}