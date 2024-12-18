package com.example.nagoyamesi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ResetPasswordForm;
import com.example.nagoyamesi.form.SignupForm;
import com.example.nagoyamesi.form.UserEditForm;
import com.example.nagoyamesi.repository.FavoriteRepository;
import com.example.nagoyamesi.repository.ReservationRepository;
import com.example.nagoyamesi.repository.ReviewRepository;
import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.repository.VerificationTokenRepository;



@Service
public class UserService {
	 private final UserRepository userRepository;
     private final RoleRepository roleRepository;
     private final PasswordEncoder passwordEncoder;
     private final FavoriteRepository favoriteRepository;
     private final ReservationRepository reservationRepository; 
     private final ReviewRepository reviewRepository;
     private final VerificationTokenRepository verificationTokenRepository;
     
     public UserService(UserRepository userRepository, RoleRepository roleRepository,
    		 			PasswordEncoder passwordEncoder, FavoriteRepository favoriteRepository,
    		 			ReservationRepository reservationRepository,ReviewRepository reviewRepository,
    		 			VerificationTokenRepository verificationTokenRepository) {
         this.userRepository = userRepository;
         this.roleRepository = roleRepository;        
         this.passwordEncoder = passwordEncoder;
         this.favoriteRepository = favoriteRepository;
         this.reservationRepository = reservationRepository;
         this.reviewRepository = reviewRepository;
         this.verificationTokenRepository = verificationTokenRepository;
     }    
     
     @Transactional
     public User create(SignupForm signupForm) {
         User user = new User();
         Role role = roleRepository.findByName("ROLE_GENERAL");
         
         user.setName(signupForm.getName());
         user.setFurigana(signupForm.getFurigana());
         user.setPostalCode(signupForm.getPostalCode());
         user.setAddress(signupForm.getAddress());
         user.setPhoneNumber(signupForm.getPhoneNumber());
         user.setEmail(signupForm.getEmail());
         user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
         user.setRole(role);
         user.setEnabled(false);        
         
         return userRepository.save(user);
     }  
     
     @Transactional
     public void update(UserEditForm userEditForm) {
         User user = userRepository.getReferenceById(userEditForm.getId());
         
         user.setName(userEditForm.getName());
         user.setFurigana(userEditForm.getFurigana());
         user.setPostalCode(userEditForm.getPostalCode());
         user.setAddress(userEditForm.getAddress());
         user.setPhoneNumber(userEditForm.getPhoneNumber());
         user.setEmail(userEditForm.getEmail());      
         
         userRepository.save(user);
     }    
     
     // メールアドレスが登録済みかどうかをチェックする
     public boolean isEmailRegistered(String email) {
         User user = userRepository.findByEmail(email);  
         return user != null;
     }    
     
     // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
     public boolean isSamePassword(String password, String passwordConfirmation) {
         return password.equals(passwordConfirmation);
     }    
     // ユーザーを有効にする
     @Transactional
     public void enableUser(User user) {
         user.setEnabled(true); 
         userRepository.save(user);
     }    
     
  // メールアドレスが変更されたかどうかをチェックする
     public boolean isEmailChanged(UserEditForm userEditForm) {
         User currentUser = userRepository.getReferenceById(userEditForm.getId());
         return !userEditForm.getEmail().equals(currentUser.getEmail());      
     }  
     
     @Transactional
     public void updateRole(Map<String, String> paymentIntentObject) {
         String userId = paymentIntentObject.get("userId");

         Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
         
         if (!userOptional.isPresent()) {
             throw new RuntimeException("指定されたユーザーが見つかりません。");
         }

         User user = userOptional.get();

         String roleName = paymentIntentObject.get("roleName");

         Role role = roleRepository.findByName(roleName);
         if (role == null) {
             throw new IllegalArgumentException("Role not found: " + roleName);
         }

         user.setRole(role);
         userRepository.save(user);

         refreshAuthenticationByRole(roleName);
     }

     public void refreshAuthenticationByRole(String newRole) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         authorities.add(new SimpleGrantedAuthority(newRole));
         Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

         SecurityContextHolder.getContext().setAuthentication(newAuth);
     }
     
     @Transactional
 	public void updatePassword(ResetPasswordForm resetPasswordForm) {
 		User user = userRepository.getReferenceById(resetPasswordForm.getId());

 		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));

 		userRepository.save(user);
 	}
    
     @Transactional
     public void deleteUserAndAssociations(Long userId) {
         // 1. 関連データの削除
         favoriteRepository.deleteByUserId(userId);
         reservationRepository.deleteByUserId(userId);
         reviewRepository.deleteByUserId(userId);
         verificationTokenRepository.deleteByUserId(userId);

         // 2. ユーザーの削除
         userRepository.deleteById(userId);
     }

}
