CREATE DATABASE IF NOT EXISTS pomina;

USE pomina;

SET time_zone = 'Asia/Ho_Chi_Minh';

-- Create table sys_user

CREATE TABLE sys_user
(
    user_id       INT PRIMARY KEY,
    username      VARCHAR(255),
    phone_number  VARCHAR(20),
    password      VARCHAR(255),
    ho_va_ten     VARCHAR(255),
    ma_so_thue    VARCHAR(50),
    dia_chi_1     VARCHAR(255),
    dia_chi_2     VARCHAR(255),
    dia_chi_3     VARCHAR(255),
    dia_chi_4     VARCHAR(255),
    dia_chi_5     VARCHAR(255),
    city          VARCHAR(255),
    location_code VARCHAR(255),
    bank_name     VARCHAR(255),
    bank_number   VARCHAR(50),
    description   VARCHAR(255),
    role_id       INT,
    is_active     INT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO sys_user (user_id, username, phone_number, password, ho_va_ten, ma_so_thue, dia_chi_1, dia_chi_2, dia_chi_3,
                      dia_chi_4, dia_chi_5, city, location_code, bank_name, bank_number, description, role_id,
                      is_active)
VALUES (1, 'quocdat', '0964192032', '123', 'Bùi Quốc Đạt', '86527428', 'Tổ 11', 'Ấp 2', 'Xã Thanh Sơn',
        'Huyện Định Quán', 'Tỉnh Đồng Nai', NULL, NULL, NULL, NULL, 'User Test', 1, 1),
       (2, 'xuanngoc', '0332425253', '123', 'Lê Xuân Ngọc', '96227389', 'Tổ 11', 'Eaka', 'Eaka', 'Huyện Eaka',
        'Tỉnh Đắk Lắk', NULL, NULL, NULL, NULL, 'User Test', 1, 1),
       (3, 'minhnam', '0862308110', '123', 'Nguyễn Minh Nam', '96227389', 'Tổ 11', 'Ấp 2', 'Xã Thanh Sơn',
        'Tp Thủ Dầu 1', 'Tình Bình Dương', NULL, NULL, NULL, NULL, 'User Test', 1, 1),
       (4, 'bienngoc', '0918080855', 'bienngoc', 'CÔNG TY TNHH SẮT THÉP NGỌC BIỂN', '96227389', 'Lô Số 22 Song Hành',
        'KCN Tân Tạo', 'Phường Tân Tạo A', 'Quận Bình Tân', 'TP HCM',
        'Lô Số 22 Song Hành, KCN Tân Tạo, Phường Tân Tạo A, Quận Bình Tân, TP HCM', '56', NULL, NULL, NULL, NULL, NULL);

-- Create table sys_role
CREATE TABLE sys_role
(
    id          INT PRIMARY KEY,
    role_name   VARCHAR(255) NOT NULL,
    description VARCHAR(255)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO sys_role (id, role_name, description)
VALUES (1, 'ROLE_ADMIN', 'Toàn quyền hệ thống'),
       (2, 'ROLE_MANAGER', 'Quản lý'),
       (3, 'ROLE_SALE_ADMIN', 'Trưởng nhóm saler'),
       (4, 'ROLE_SALE', 'Saler'),
       (5, 'ROLE_SUPPLIER', 'Nhà Phân Phối'),
       (6, 'ROLE_USER', 'Xưởng Cán');

-- Tạo bảng Distributor
CREATE TABLE distributor
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,

    status     VARCHAR(50) DEFAULT 'ACTIVE',
    note       TEXT,
    created_at DATETIME    DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    is_deleted TINYINT     DEFAULT 0
);

-- Tạo bảng Customer
CREATE TABLE customer
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    address      TEXT,

    status       VARCHAR(50) DEFAULT 'ACTIVE',
    note         TEXT,
    created_at   DATETIME    DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   VARCHAR(100),
    is_deleted   TINYINT     DEFAULT 0
);

-- Tạo bảng Product
CREATE TABLE product
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    coil_code   VARCHAR(50) NOT NULL UNIQUE,
    length_from INT,
    length_to   INT,
    length      INT,
    name        VARCHAR(255),
    coil_type   VARCHAR(50),

    status      VARCHAR(50) DEFAULT 'ACTIVE',
    note        TEXT,
    created_at  DATETIME    DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR(100),
    updated_at  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by  VARCHAR(100),
    is_deleted  TINYINT     DEFAULT 0
);

-- Tạo bảng ActivationCode
CREATE TABLE activation
(
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    code                       VARCHAR(50) NOT NULL UNIQUE,
    activation_date            DATE,
    color_fade_warranty_expiry DATE,
    corrosion_warranty_expiry  DATE,

    customer_id                INT,
    product_id                 INT,
    distributor_id             INT,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (distributor_id) REFERENCES distributor (id),

    status                     VARCHAR(50) DEFAULT 'ACTIVE',
    note                       TEXT,
    created_at                 DATETIME    DEFAULT CURRENT_TIMESTAMP,
    created_by                 VARCHAR(100),
    updated_at                 DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by                 VARCHAR(100),
    is_deleted                 TINYINT     DEFAULT 0
);

INSERT INTO distributor (name, created_by, updated_by)
VALUES
    ('CÔNG TY TNHH SẮT THÉP NGỌC BIỂN', 'admin', 'admin'),
    ('CÔNG TY TNHH TÔN THÉP NAM THUẬN PHÁT', 'admin', 'admin'),
    ('CÔNG TY TNHH MTV TM DV SX CƠ KHÍ HẢI PHƯƠNG ANH', 'admin', 'admin');

INSERT INTO customer (name, phone_number, address, created_by, updated_by)
VALUES
    ('Nhà Máy Tôn Anh Tú', '0986515242', 'QL44 , LỘC THANH BẢO LÂM , Huyện Bảo Lâm, Lâm Đồng, Việt Nam', 'admin', 'admin'),
    ('Nhà Máy Cán Tôn Năng Lượng 2', '0976774774', 'Thôn 4 Xã Đak Đrông, Huyện Cư Jút, Đắk Nông, Việt Nam', 'admin', 'admin'),
    ('Nhà Máy Tôn Sắt Thép Hải Anh', '0935771107', '217 Nguyễn Tất Thành, TT EA T-LING, Huyện Cư Jút, Đắk Nông, Việt Nam', 'admin', 'admin'),
    ('Công Ty TNHH Nguyễn Danh - 205 Hùng Vương - Tp Huế', '0989947055', '205 Hùng Vương - Tp Huế, Thành phố Huế, Thừa Thiên Huế, Việt Nam', 'admin', 'admin'),
    ('CÔNG TY TNHH TÔN THÉP NAM THUẬN PHÁT', '0977045355', '41 Đường 2, KĐT Vạn Phúc, Phường Hiệp Bình Phước, Thành phố Thủ Đức, Thành phố Hồ Chí Minh, Việt Nam, Thủ Đức, Hồ Chí Minh, Việt Nam', 'admin', 'admin'),
    ('Nhà Máy Cán Tôn Trung Tín', '0979977212', 'Xã Quảng Lập, Huyện Đơn Dương, Lâm Đồng, Việt Nam', 'admin', 'admin'),
    ('CÔNG TY TNHH MTV TMSX TÔN  CƯƠNG LAN', '0933131315', 'E547 Đường Nguyễn Hoàng, Trảng Bom, Đồng Nai, Việt Nam', 'admin', 'admin'),
    ('Nhà máy Tôn Vạn Thắng', '0908748452', '20 Trần  Phú , phường lộc sơn, Thành phố Bảo Lộc, Lâm Đồng, Việt Nam', 'admin', 'admin'),
    ('Anh Trần Văn Huy Tây Ninh', '0915524265', 'Hòa Bình, Châu Thành, Tây Ninh, Việt Nam', 'admin', 'admin');

INSERT INTO product (coil_code, length_from, length_to, length, name, coil_type, created_by, updated_by)
VALUES
    ('12502410008400', 0, 0, 0, 'Tôn lạnh màu Pomina', '1A', 'admin', 'admin'),
    ('12503410000300', 0, 0, 0, 'Tôn lạnh màu Pomina (AZ30)', '1A', 'admin', 'admin'),
    ('12405410012200', 0, 0, 0, 'Tôn lạnh màu Granite (AZ50)', '1A', 'admin', 'admin'),
    ('12502410010200', 0, 0, 0, 'Tôn lạnh màu Pomina', '1A', 'admin', 'admin'),
    ('12403410002500', 0, 0, 0, 'Tôn lạnh Solar (AZ100)', '1A', 'admin', 'admin'),
    ('12406410010400', 0, 0, 0, 'Tôn lạnh màu Pomina', '1A', 'admin', 'admin'),
    ('12503410004300', 0, 0, 0, 'Tôn lạnh màu Pomina (AZ30)', '1A', 'admin', 'admin'),
    ('12504310074400', 0, 1776, 1776, 'Tôn lạnh Pomina (AZ80)', '1B', 'admin', 'admin'),
    ('12503410003300', 0, 1764, 1764, 'Tôn lạnh màu Pomina (AZ30)', '1A', 'admin', 'admin'),
    ('12502410012000', 0, 1192, 1192, 'Tôn lạnh màu Pomina (AZ30)', '1A', 'admin', 'admin'),
    ('12502410012100', 0, 1056, 1056, 'Tôn lạnh màu Pomina (AZ30)', '1A', 'admin', 'admin');

INSERT INTO activation (code, activation_date, color_fade_warranty_expiry, corrosion_warranty_expiry, customer_id, product_id, distributor_id, created_by, updated_by)
VALUES
    ('#99',  '2025-06-17', '2030-06-17', '2035-06-17', 1, 1, 1, 'admin', 'admin'),
    ('#100', '2025-06-17', '2028-06-17', '2035-06-17', 1, 2, 1, 'admin', 'admin'),
    ('#98',  '2025-06-17', '2033-06-17', '2037-06-17', 2, 3, 1, 'admin', 'admin'),
    ('#97',  '2025-06-17', '2030-06-17', '2035-06-17', 3, 4, 1, 'admin', 'admin'),
    ('#96',  '2025-06-13', NULL,         '2040-06-13', 4, 5, 2, 'admin', 'admin'),
    ('#95',  '2025-06-13', '2030-06-13', '2035-06-13', 5, 6, 2, 'admin', 'admin'),
    ('#94',  '2025-06-12', '2028-06-12', '2035-06-12', 6, 7, 1, 'admin', 'admin'),
    ('#88',  '2025-05-29', NULL,         '2030-05-29', 7, 8, 3, 'admin', 'admin'),
    ('#87',  '2025-05-29', '2028-05-29', '2035-05-29', 8, 9, 3, 'admin', 'admin'),
    ('#89',  '2025-05-29', '2028-05-29', '2035-05-29', 9, 10, 3, 'admin', 'admin'),
    ('#90',  '2025-05-29', '2028-05-29', '2035-05-29', 9, 11, 3, 'admin', 'admin');