package com.example.nagoyamesi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {

    @NotBlank(message = "予約日を選択してください。")
    private String reservationDate;

    @NotBlank(message = "予約時間を選択してください。")
    private String reservationTime;

    @NotNull(message = "予約人数を選択してください。")
    @Min(value = 1, message = "予約人数は1人以上で選択してください。")
    private Integer numberOfPeople;

    // ゲッターとセッター
    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
