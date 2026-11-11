package com.detection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyze")
@CrossOrigin(origins = "*")
public class DetectionController {

    private final DetectionService detectionService =
            new DetectionService();

    // 1. Analyze text messages
    @PostMapping("/text")
    public AnalysisResult analyzeText(
            @RequestBody String message) {

        return detectionService.analyzeText(message);
    }

    // 2. Analyze URLs directly
    @PostMapping("/url")
    public AnalysisResult analyzeUrl(
            @RequestBody String url) {

        return detectionService.analyzeUrl(url);
    }
}