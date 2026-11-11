package com.detection;

import java.util.List;

public class AnalysisResult {

    private String riskLevel;
    private List<String> indicators;
    private String recommendation;

    public AnalysisResult(String riskLevel,
                          List<String> indicators,
                          String recommendation) {

        this.riskLevel = riskLevel;
        this.indicators = indicators;
        this.recommendation = recommendation;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public String getRecommendation() {
        return recommendation;
    }
}