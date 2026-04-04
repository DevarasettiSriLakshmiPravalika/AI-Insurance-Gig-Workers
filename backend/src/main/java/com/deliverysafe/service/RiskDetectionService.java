package com.deliverysafe.service;

import com.deliverysafe.dto.DecisionResponse;
import com.deliverysafe.dto.WeatherRequest;
import com.deliverysafe.model.Payout;
import com.deliverysafe.model.User;
import com.deliverysafe.repository.PayoutRepository;
import com.deliverysafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RiskDetectionService {

    @Autowired
    private PayoutRepository payoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrustScoreService trustScoreService;

    private static final double TEMP_THRESHOLD = 45.0;
    private static final double RAINFALL_THRESHOLD = 50.0;
    private static final double AQI_THRESHOLD = 300.0;

    public DecisionResponse processWeather(Long userId, WeatherRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String reason = null;
        if (request.getTemperature() != null && request.getTemperature() > TEMP_THRESHOLD) {
            reason = "Extreme Heat (>45°C)";
        } else if (request.getRainfall() != null && request.getRainfall() > RAINFALL_THRESHOLD) {
            reason = "Heavy Rainfall (>50mm)";
        } else if (request.getAqi() != null && request.getAqi() > AQI_THRESHOLD) {
            reason = "High AQI (>300)";
        }

        // Always check trust score for state trace, even if safe. 
        TrustScoreService.TrustEvaluation trustEval = trustScoreService.evaluateTrust(request.getSpeed(), request.getInconsistentActivity());
        
        DecisionResponse response = new DecisionResponse();
        response.setTrustScore(trustEval.getScore());
        response.setRiskLevel(trustEval.getRiskLevel());

        if (reason != null) {
            // DISRUPTED state
            response.setSystemStatus("DISRUPTED");
            response.setPayoutTriggered(true);

            double payoutAmount = user.getWorkingHoursPerDay() * user.getHourlyEarnings();
            Payout payout = new Payout();
            payout.setUserId(userId);
            payout.setAmount(payoutAmount);
            payout.setReason(reason);
            payout.setTimestamp(LocalDateTime.now());
            payout.setTrustScore(trustEval.getScore());
            payout.setRiskLevel(trustEval.getRiskLevel());

            // Decision Matrix logic
            switch (trustEval.getRiskLevel()) {
                case "LOW":
                    payout.setStatus("INSTANT");
                    break;
                case "MEDIUM":
                    payout.setStatus("DELAYED");
                    break;
                case "HIGH":
                default:
                    payout.setStatus("FLAGGED");
                    response.setPayoutTriggered(false); // Can be considered flagged/pending
                    break;
            }

            response.setPayoutDetails(payoutRepository.save(payout));
        } else {
            response.setSystemStatus("SAFE");
            response.setPayoutTriggered(false);
        }

        return response;
    }
}
