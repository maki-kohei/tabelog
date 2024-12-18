package com.example.nagoyamesi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyamesi.entity.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {

    @Value("${stripe.api-key}")
    private String stripeApiKey;
    
    private final UserService userService;
    
    public StripeService(UserService userService) {
        this.userService = userService;
    }  

    public String createStripeSession(HttpServletRequest httpServletRequest, User user) {
        String userId = (user != null && user.getId() != null) ? String.valueOf(user.getId()) : "null";
        Stripe.apiKey = stripeApiKey;
        String requestUrl = new String(httpServletRequest.getRequestURL());

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSuccessUrl(requestUrl.replaceAll("/subscription/register", ""))
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPrice("price_1PdpGA2KNitvjaTCJlAqfUDe")
                        .setQuantity(1L)
                        .build()
                )
                .putMetadata("userId", userId)
                .putMetadata("roleName", "ROLE_PREMIUM")
                .build();

        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void processSessionCompleted(Event event) {
        Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
        optionalStripeObject.ifPresent(stripeObject -> {
            Session session = (Session) stripeObject;

            if (session.getPaymentIntent() != null) {
                SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();

                try {
                    session = Session.retrieve(session.getId(), params, null);
                    Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
                    userService.updateRole(paymentIntentObject);
                } catch (StripeException e) {
                    e.printStackTrace();
                }
            } else {
                Map<String, String> metadata = session.getMetadata();
                userService.updateRole(metadata);
            }
        });
    }
}
