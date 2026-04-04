package com.deliverysafe.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
public class TrustScoreService {

    @Data
    public static class TrustEvaluation {
        private Integer score;
        private String riskLevel;
    }

    public TrustEvaluation evaluateTrust(Double speed, Boolean inconsistentActivity) {
        TrustEvaluation evaluation = new TrustEvaluation();
        
        // Default safe baseline
        int baseScore = 95;
        
        if (Boolean.TRUE.equals(inconsistentActivity)) {
            baseScore -= 60; // Huge penalty for spoofing or inconsistency
        }
        
        if (speed != null) {
            if (speed > 120.0) {
                baseScore -= 50; // Impossible speed for delivery
            } else if (speed > 80.0) {
                baseScore -= 30; // Suspiciously fast
            }
        }
        
        // Normalize 0-100
        evaluation.setScore(Math.max(0, Math.min(100, baseScore)));
        
        if (evaluation.getScore() >= 80) {
            evaluation.setRiskLevel("LOW");
        } else if (evaluation.getScore() >= 50) {
            evaluation.setRiskLevel("MEDIUM");
        } else {
            evaluation.setRiskLevel("HIGH");
        }
        
        return evaluation;
    }
}
