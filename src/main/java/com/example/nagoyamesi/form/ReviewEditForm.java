package com.example.nagoyamesi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Long id;
	
	@NotNull(message = "評価をを選択してください。")
    private Integer rating;   
    
   @NotBlank(message = "コメントを入力してください。")
    private String comment;
}
