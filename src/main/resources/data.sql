-- restaurantsテーブル
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 1, 'レストランA', 'restaurant1.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '火曜日',1);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 2, 'レストランB', 'restaurant2.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '水曜日',13);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 3, 'レストランC', 'restaurant3.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',1);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 4, 'レストランD', 'restaurant4.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知件渋谷区神南'     , '030-1234-5678', '火曜日',6);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 5, 'レストランE', 'restaurant5.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '水曜日',4);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 6, 'レストランF', 'restaurant6.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',5);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 7, 'レストランG', 'restaurant7.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '火曜日',17);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 8, 'レストランH', 'restaurant8.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県区梅田'  , '060-2345-6789', '水曜日',4);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES ( 9, 'レストランI', 'restaurant9.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',14);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (10, 'レストランJ', 'restaurant10.jpg', '美味しい料理を提供しています。'        , '10:00:00', '22:00:00', 1000, 3000, '123-4567', '愛知県渋谷区神南'     , '030-1234-5678', '火曜日',16);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (11, 'レストランK', 'restaurant11.jpg', '地元の食材を使用した料理が自慢です。'   , '11:00:00', '20:00:00', 1500, 5000, '456-7890', '愛知県北区梅田'  , '060-2345-6789', '水曜日',8);
INSERT IGNORE INTO restaurants (id, name, image_name, description, opening_time, closing_time, lowest_price, highest_price, postal_code, address, phone_number, regular_holiday, category_id) VALUES (12, 'レストランL', 'restaurant12.jpg', 'アットホームな雰囲気でお待ちしています。', '09:00:00', '18:00:00', 800 , 2500, '567-8901', '愛知県中央区天神', '092-3456-7890', '年中無休',12);

-- categoriesテーブル
INSERT IGNORE INTO categorys (id, name) VALUES (1, '居酒屋');
INSERT IGNORE INTO categorys (id, name) VALUES (2, '和食');
INSERT IGNORE INTO categorys (id, name) VALUES (3, '洋食');
INSERT IGNORE INTO categorys (id, name) VALUES (4, 'ラーメン');
INSERT IGNORE INTO categorys (id, name) VALUES (5, '海鮮');
INSERT IGNORE INTO categorys (id, name) VALUES (6, '寿司');
INSERT IGNORE INTO categorys (id, name) VALUES (7, '焼肉');
INSERT IGNORE INTO categorys (id, name) VALUES (8, 'パスタ');
INSERT IGNORE INTO categorys (id, name) VALUES (9, '鍋料理');
INSERT IGNORE INTO categorys (id, name) VALUES (10, '鉄板焼き');
INSERT IGNORE INTO categorys (id, name) VALUES (11, 'うどん');
INSERT IGNORE INTO categorys (id, name) VALUES (12, 'そば');
INSERT IGNORE INTO categorys (id, name) VALUES (13, 'カレー');
INSERT IGNORE INTO categorys (id, name) VALUES (14, 'パン');
INSERT IGNORE INTO categorys (id, name) VALUES (15, 'ハンバーガー');
INSERT IGNORE INTO categorys (id, name) VALUES (16, 'カフェ');
INSERT IGNORE INTO categorys (id, name) VALUES (17, 'スイーツ');
INSERT IGNORE INTO categorys (id, name) VALUES (18, '名古屋名物');

-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_PREMIUM');

--usersテーブル
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '山田 太郎', 'ヤマダ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '山田 花子', 'ヤマダ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '山田 義勝', 'ヤマダ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '山田 幸美', 'ヤマダ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '山田 雅', 'ヤマダ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '山田 正保', 'ヤマダ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '山田 真由美', 'ヤマダ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '山田 安民', 'ヤマダ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '山田 章緒', 'ヤマダ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '山田 祐子', 'ヤマダ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '山田 秋美', 'ヤマダ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '山田 信平', 'ヤマダ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', 'password', 1, false);

-- reviewテーブル 
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (1, 1, 1, 4, 'うまい');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (2, 2, 1, 5, '素晴らしいサービスと設備が整っています。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (3, 3, 1, 3, '周囲がうるさいです。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (4, 4, 1, 4, '居心地が良くてスタッフもフレンドリーです。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (5, 5, 1, 2, '清潔さとサービスにがっかりしました。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (6, 6, 1, 5, '家族旅行に最適です。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (7, 7, 1, 4, '素晴らしいです。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (8, 8, 1, 3, '全体的には普通でした。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (9, 9, 1, 5, '強くお勧めします。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (10, 10, 1, 2, '問題があるためお勧めしません。');
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, rating, comment) VALUES (11, 11, 1, 4, '快適です。');

-- reservations --
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (1, 1, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (2, 2, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (3, 3, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (4, 4, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (5, 5, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (6, 6, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (7, 7, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (8, 8, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (9, 9, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (10, 10, 1, '2024-08-10', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (11, 11, 1, '2024-08-11', '18:00:00', 2);

-- favorites --
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (1, 1, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (2, 2, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (3, 3, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (4, 4, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (5, 5, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (6, 6, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (7, 7, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (8, 8, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (9, 9, 1);
INSERT IGNORE INTO favorites (id, restaurant_id, user_id) VALUES (10, 10, 1);