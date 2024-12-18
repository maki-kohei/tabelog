package com.example.nagoyamesi.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "restaurants")
@Data
public class Restaurant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id; // 店舗のID

    @Column(name = "name")
    private String name; // 店舗の名前

    @Column(name = "image_name")
    private String imageName; // 画像のURL

    @Column(name = "opening_time")
    private String openingTime;  // 営業開始時間
    
    @Column(name = "closing_time")
    private String closingTime;  // 営業終了時間
    
    @Column(name = "regular_holiday")
    private String regularHoliday; // 定休日

    @Column(name = "lowest_price")
    private Integer lowestPrice;  // 最低価格帯

    @Column(name = "highest_price")
    private Integer highestPrice;  // 最高価格帯
    
    @Column(name = "description")
    private String description; // 店舗説明

    @Column(name = "postal_code")
    private String postalCode; // 郵便番号

    @Column(name = "address")
    private String address; // 住所

    @Column(name = "phone_number")
    private String phoneNumber; // 電話番号
    

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt; // レコード作成日時

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt; // レコード更新日時
    
    @ManyToOne
	@JoinColumn(name = "category_id")
	private Category category; //カテゴリ
    
    public List<String> getAvailableTimes() {
        List<String> times = new ArrayList<>();

        String[] openingTimeSplit = this.openingTime.split(":");
        String[] closingTimeSplit = this.closingTime.split(":");

        int openingHour = Integer.parseInt(openingTimeSplit[0]);
        int openingMinute = Integer.parseInt(openingTimeSplit[1]);
        int closingHour = Integer.parseInt(closingTimeSplit[0]);
        int closingMinute = Integer.parseInt(closingTimeSplit[1]);

        if (closingMinute < 30) {
            closingHour -= 2;
            closingMinute += 30;
        } else {
            closingHour -= 1;
            closingMinute -= 30;
        }

        for (int hour = openingHour; hour <= closingHour; hour++) {
            if (hour == openingHour && openingMinute == 0) {
                times.add(String.format("%02d:00", hour));
                times.add(String.format("%02d:30", hour));
            } else if (hour == openingHour && openingMinute >= 30) {
                times.add(String.format("%02d:30", hour));
            } else if (hour == closingHour && closingMinute == 0) {
                times.add(String.format("%02d:00", hour));
            } else if (hour == closingHour && closingMinute == 30) {
                times.add(String.format("%02d:00", hour));
                times.add(String.format("%02d:30", hour));
            } else {
                times.add(String.format("%02d:00", hour));
                times.add(String.format("%02d:30", hour));
            }
        }

        return times;
    }
    
}
