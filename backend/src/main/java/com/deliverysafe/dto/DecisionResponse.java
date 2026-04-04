package com.deliverysafe.dto;

import com.deliverysafe.model.Payout;
import lombok.Data;

@Data
public class DecisionResponse {
    private String systemStatus; // "SAFE" or "DISRUPTED"
    private Integer trustScore;
    private String riskLevel;
    private Boolean payoutTriggered;
    private Payout payoutDetails;
}
