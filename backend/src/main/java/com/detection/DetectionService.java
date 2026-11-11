package com.detection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectionService {

    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    private final UrlAnalyzer urlAnalyzer = new UrlAnalyzer();

    private final RiskClassifier riskClassifier =
            new RiskClassifier();

    private final RecommendationService recommendationService =
            new RecommendationService();

    private static final Pattern URL_PATTERN =
            Pattern.compile(
                    "https?://\\S+|www\\.\\S+",
                    Pattern.CASE_INSENSITIVE
            );

    // 1. Analyze text messages
    public AnalysisResult analyzeText(String message) {

        List<String> indicators = new ArrayList<>();

        // Analyze message text
        indicators.addAll(textAnalyzer.analyze(message));

        // Extract and analyze URLs from the message
        if (message != null) {

            Matcher matcher = URL_PATTERN.matcher(message);

            while (matcher.find()) {

                String url = matcher.group();

                indicators.addAll(urlAnalyzer.analyze(url));
            }
        }

        // Classify risk
        String riskLevel = riskClassifier.classify(indicators);

        // Generate recommendation
        String recommendation =
                recommendationService.generateRecommendation(
                        riskLevel, indicators
                );

        // Return complete analysis result
        return new AnalysisResult(
                riskLevel,
                indicators,
                recommendation
        );
    }

    // 2. Analyze URLs directly
    public AnalysisResult analyzeUrl(String url) {

        List<String> indicators = new ArrayList<>();

        // Analyze the URL
        indicators.addAll(urlAnalyzer.analyze(url));

        // Classify risk
        String riskLevel = riskClassifier.classify(indicators);

        // Generate recommendation
        String recommendation =
                recommendationService.generateRecommendation(
                        riskLevel, indicators
                );

        // Return complete analysis result
        return new AnalysisResult(
                riskLevel,
                indicators,
                recommendation
        );
    }
}