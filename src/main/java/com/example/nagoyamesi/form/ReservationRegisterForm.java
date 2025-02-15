package com.example.nagoyamesi.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer restaurantId;

    private Long userId;

    private String reservationDate;

    private String reservationTime;

    private Integer numberOfPeople;
    
    
  
}
