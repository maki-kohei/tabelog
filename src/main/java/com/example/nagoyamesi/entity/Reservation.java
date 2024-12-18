package com.example.nagoyamesi.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;  // 予約ID

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;  // 予約対象のレストラン

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 予約したユーザー

    @Column(name = "reservation_date")
    private LocalDate reservationDate;  // 予約日

    @Column(name = "reservation_time")
    private LocalTime reservationTime;  // 予約時間

    @Column(name = "number_of_people")
    private Integer numberOfPeople;  // 予約の人数

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;  // レコード作成日時

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;  // レコード更新日時
}
