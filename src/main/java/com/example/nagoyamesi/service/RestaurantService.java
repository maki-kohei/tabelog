package com.example.nagoyamesi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.form.RestaurantEditForm;
import com.example.nagoyamesi.form.RestaurantRegisterForm;
import com.example.nagoyamesi.repository.RestaurantRepository;

@Service
public class RestaurantService {	
	private final RestaurantRepository restaurantRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	//登録
	@Transactional
	public void create(RestaurantRegisterForm restaurantRegisterForm) {
		Restaurant restaurant = new Restaurant();
		MultipartFile imageFile = restaurantRegisterForm.getImageFile();
		
		if(!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			restaurant.setImageName(hashedImageName);
		}
		
		restaurant.setName(restaurantRegisterForm.getName()); //店舗名
		restaurant.setOpeningTime(restaurantRegisterForm.getOpeningTime()); //営業開始時
		restaurant.setClosingTime(restaurantRegisterForm.getClosingTime()); //営業終了時間
		// 定休日が設定されていない場合は"年中無休"
        if (restaurantRegisterForm.getRegularHoliday() == null || restaurantRegisterForm.getRegularHoliday().isEmpty()) {
            restaurant.setRegularHoliday("年中無休");
        } else {
            restaurant.setRegularHoliday(String.join(",", restaurantRegisterForm.getRegularHoliday()));
        }
		restaurant.setLowestPrice(restaurantRegisterForm.getLowestPrice()); //最低料金
		restaurant.setHighestPrice(restaurantRegisterForm.getHighestPrice());  //最大料金
		restaurant.setDescription(restaurantRegisterForm.getDescription()); //店舗説明
		restaurant.setPostalCode(restaurantRegisterForm.getPostalCode()); //郵便番号
		restaurant.setAddress(restaurantRegisterForm.getAddress()); //住所
		restaurant.setPhoneNumber(restaurantRegisterForm.getPhoneNumber()); //電話番号
		restaurant.setCategory(restaurantRegisterForm.getCategory()); //カテゴリ

		restaurantRepository.save(restaurant);

	}
	//編集
	@Transactional
	public void update(RestaurantEditForm restaurantEditForm) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantEditForm.getId());
		MultipartFile imageFile = restaurantEditForm.getImageFile();
		
		if(!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			restaurant.setImageName(hashedImageName);
		}
		
		restaurant.setName(restaurantEditForm.getName()); //店舗名
		restaurant.setOpeningTime(restaurantEditForm.getOpeningTime()); //営業開始時
		restaurant.setClosingTime(restaurantEditForm.getClosingTime()); //営業終了時間
		// 定休日が設定されていない場合は"年中無休"
        if (restaurantEditForm.getRegularHoliday() == null || restaurantEditForm.getRegularHoliday().isEmpty()) {
            restaurant.setRegularHoliday("年中無休");
        } else {
            restaurant.setRegularHoliday(String.join(",", restaurantEditForm.getRegularHoliday()));
        }
		restaurant.setLowestPrice(restaurantEditForm.getLowestPrice()); //最低料金
		restaurant.setHighestPrice(restaurantEditForm.getHighestPrice());  //最大料金
		restaurant.setDescription(restaurantEditForm.getDescription()); //店舗説明
		restaurant.setPostalCode(restaurantEditForm.getPostalCode()); //郵便番号
		restaurant.setAddress(restaurantEditForm.getAddress()); //住所
		restaurant.setPhoneNumber(restaurantEditForm.getPhoneNumber()); //電話番号
		restaurant.setCategory(restaurantEditForm.getCategory()); //カテゴリ
		
		restaurantRepository.save(restaurant);
	}
	
	// UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");                
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();            
        }
        String hashedFileName = String.join(".", fileNames);
        return hashedFileName;
    }     
    
    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {           
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }          
    } 

}
