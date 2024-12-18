package com.example.nagoyamesi.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyamesi.entity.Category;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
    private String name;

    private MultipartFile imageFile;
    
    @NotNull(message = "カテゴリを選択してください。")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank(message = "営業開始時間を選択してください。")
    private String openingTime;

    @NotBlank(message = "営業終了時間を選択してください。")
    private String closingTime;

    
    private String regularHoliday;

    @NotNull(message = "最低料金を選択してください。")
    @Min(value = 1, message = "最低料金はは1円以上に設定してください。")
    private Integer lowestPrice;

    @NotNull(message = "最大料金を選択してください。")
    @Min(value = 1, message = "最大料金は1円以上に設定してください。")
    private Integer highestPrice;

    @NotNull(message = "店舗説明を入力してください。")
    private String description;

    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;

    @NotBlank(message = "住所を入力してください。")
    private String address;

    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;


}
