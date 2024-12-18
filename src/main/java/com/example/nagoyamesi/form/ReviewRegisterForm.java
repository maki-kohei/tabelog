package com.example.nagoyamesi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	private Integer restaurantId;
	

	@NotNull(message = "評価をを選択してください。")
    private Integer rating;   
    
   @NotBlank(message = "コメントを入力してください。")
    private String comment; 

}
