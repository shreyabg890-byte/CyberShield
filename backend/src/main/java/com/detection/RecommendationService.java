package com.detection;

import java.util.List;

public class RecommendationService{
    public String generateRecommendation(String riskLevel,List<String> indicators){
        if(riskLevel == null){
            return "Unable to assess risk level";

        }
        if(riskLevel.equals("HIGH")){
            return "Do not click links, share personal information, or make payments. Verify through official channels.";
        }
        else if(riskLevel.equals("MEDIUM")){
            return "The message shows some suspicious indicators. Exercise caution and verify the source before taking any action.";
        }
        else if(riskLevel.equals("LOW")){
            return "The message appears to be safe, but always remain vigilant and cautious.";
        }
        else{
            return "Unknown risk level";
        }
    }
}