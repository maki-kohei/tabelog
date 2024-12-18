-- restaurantsテーブル --
CREATE TABLE IF NOT EXISTS restaurants (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 店舗の一意のID
    name VARCHAR(50) NOT NULL, -- 店舗名
    image_name VARCHAR(255), -- 画像のファイル名
    description VARCHAR(255) NOT NULL, -- 店舗の説明
    opening_time TIME NOT NULL, -- 営業開始時間
    closing_time TIME NOT NULL, -- 営業終了時間
    lowest_price INT NOT NULL, -- 最低価格帯
    highest_price INT NOT NULL, -- 最高価格帯
    postal_code VARCHAR(50) NOT NULL, -- 郵便番号
    address VARCHAR(255) NOT NULL, -- 住所
    phone_number VARCHAR(50) NOT NULL, -- 電話番号
    regular_holiday VARCHAR(100),       -- 定休日
    category_id INT NOT NULL,    --カテゴリーID
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- レコード更新日時（自動更新）
);

--カテゴリーテーブル--
CREATE TABLE IF NOT EXISTS categorys (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- カテゴリーid
     name VARCHAR(50) NOT NULL, --カテゴリー名
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, --カテゴリー作成日
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP --カテゴリー更新日
  );

-- rolesテーブル --
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- ユーザーテーブル --
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- ユーザーID
    name VARCHAR(50) NOT NULL, -- ユーザー名
    furigana VARCHAR(50) NOT NULL, -- フリガナ
    postal_code VARCHAR(50) NOT NULL, -- 郵便番号
    address VARCHAR(255) NOT NULL, -- 住所
    phone_number VARCHAR(50) NOT NULL, -- 電話番号
    email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス
   password VARCHAR(255) NOT NULL, -- パスワード
    role_id INT NOT NULL, -- 役割ID
    enabled BOOLEAN NOT NULL, -- アカウントの有効状態
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- レコード更新日時
    FOREIGN KEY (role_id) REFERENCES roles (id) -- role_id列に対する外部キー制約、rolesテーブルのid列を参照
);

-- 認証トークンテーブル --
CREATE TABLE IF NOT EXISTS verification_tokens (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- トークンID 
    user_id INT NOT NULL UNIQUE,                -- ユーザーID 
    token VARCHAR(255) NOT NULL,                -- トークン文字列
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新日時
    FOREIGN KEY (user_id) REFERENCES users (id)  -- usersテーブルのIDを参照する外部キー制約
); 

-- レビュー --
CREATE TABLE IF NOT EXISTS reviews (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- レビューID 
    user_id INT NOT NULL,                       -- レビューを行ったユーザーのID
    restaurant_id INT NOT NULL,                 -- レビュー対象のレストランのID
    rating INT NOT NULL,                        -- レビューの評価 (1から5の整数値)
    comment TEXT,                               -- コメント 
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新日時
    FOREIGN KEY (user_id) REFERENCES users (id),          -- usersテーブルのIDを参照する外部キー制約
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)  -- restaurantsテーブルのIDを参照する外部キー制約
);

-- 予約テーブル --
CREATE TABLE IF NOT EXISTS reservations (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- 予約ID、
    restaurant_id INT NOT NULL,  -- 店舗ID
    user_id INT NOT NULL,  -- ユーザーID
    reservation_date DATE NOT NULL,  -- 予約日
    reservation_time TIME NOT NULL,  -- 予約時間
    number_of_people INT NOT NULL,  -- 予約人数
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 作成日時
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新日時
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),  -- restaurantsテーブルのidカラムと外部キー制約
    FOREIGN KEY (user_id) REFERENCES users (id)   -- usersテーブルのidカラムと外部キー制約
);

 -- お気に入りテーブル --
CREATE TABLE IF NOT EXISTS favorites (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- お気に入りID
    restaurant_id INT NOT NULL, -- 店舗ID
    user_id INT NOT NULL, -- ユーザーID
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 作成日
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日
    UNIQUE (restaurant_id, user_id),  
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);