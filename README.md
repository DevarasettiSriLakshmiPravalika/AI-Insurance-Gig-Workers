# AI-Powered Parametric Insurance for Delivery Partners

An AI-based platform designed to protect delivery partners from income loss caused by external disruptions like weather, pollution, and curfews through automated claim triggering and instant payouts.

---

## Target User (Persona)

- Food delivery partners (Zomato/Swiggy)
- Work 8–10 hours daily
- Earn ₹300–₹800 per day
- Fully dependent on daily earnings
- Highly affected by weather and environmental conditions

---

## Problem Statement

Delivery partners lose 20–30% of their income due to:
- Heavy rain
- Extreme heat
- High pollution
- Sudden curfews or strikes

Currently, there is no system to protect their income during these disruptions.

---

## Proposed Solution

1. User registers on the platform  
2. AI calculates a weekly premium  
3. System monitors real-time environmental conditions  
4. If disruption occurs → claim is automatically triggered  
5. Instant payout is processed to the user  

---

## 🚀 Adaptive Income Protection Engine (Core Innovation)

At the heart of our platform lies the **Adaptive Income Protection Engine**, an AI-driven system that ensures fair, real-time compensation for delivery partners based on actual income loss.

Unlike traditional insurance models that provide fixed payouts, this engine dynamically calculates the **true financial impact** of external disruptions on each individual worker.

### 🔍 How It Works

1. The system continuously monitors external conditions such as rainfall, AQI, temperature, and curfews  
2. When a disruption crosses a predefined threshold, the system estimates the **duration of work interruption**  
3. AI analyzes the user’s past activity to determine their **average earnings per hour**  
4. The system calculates income loss using:

   👉 Income Loss = Hours Lost × Average Hourly Earnings  

5. A personalized payout is instantly triggered  

### 🎯 Why This is Unique

- Personalized payouts instead of fixed compensation  
- Fair calculation based on actual work patterns  
- Eliminates underpayment and overpayment  
- Fully automated process  
- Directly aligned with income-loss-only insurance  

---

## Weekly Pricing Model

- Users pay a weekly premium (₹20–₹50)
- Premium is calculated based on:
  - Location risk
  - Weather patterns
  - Work frequency

---

## Parametric Triggers

- Rainfall > 50mm → payout  
- AQI > 300 → payout  
- Temperature > 45°C → payout  
- Government-declared curfew → payout  

(All triggers are automatic, no manual claim required)

---

## AI/ML Integration

AI is used for:

- Risk Prediction: Analyzing historical weather and location data  
- Premium Calculation: Dynamic weekly pricing based on risk  
- Fraud Detection: Identifying abnormal claims and suspicious patterns  

---

## System Architecture

- Frontend: React  
- Backend: Spring Boot  
- Database: MySQL  
- APIs: Weather API (mock)  
- Payment: Razorpay (test mode)  

Flow:

User → Backend → AI Model → External APIs → Payment System  

---

## Adversarial Defense & Anti-Spoofing Strategy

To prevent fraud and fake claims:

- GPS Spoof Detection: Compare GPS and network location to detect inconsistencies  
- Fake Account Detection: Identify multiple accounts from same device/IP  
- Fraud Ring Detection: Detect multiple claims from same area or unusual spikes  
- Data Validation: Match claims with real-time weather data  
- Behavioral Analysis: Identify abnormal user activity patterns  

Fairness Mechanism:  
Suspicious claims are flagged for review instead of direct rejection to avoid impacting genuine users.

---

## Tech Stack

- Frontend: React  
- Backend: Spring Boot  
- Database: MySQL  
- APIs: Weather API  
- Payments: Razorpay  

---

## Future Scope

- More accurate AI-based risk prediction  
- Integration with real-time delivery platform APIs  
- Expansion to other gig workers  

---

## Demo Video

(Add your video link here)
