USE watch_store_db;

-- Очищення перед повторним наповненням
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE watches;
TRUNCATE TABLE manufacturers;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Виробники (Швейцарія, Японія, Німеччина, США)
INSERT INTO manufacturers (manufacturer_id, name, country) VALUES
   (1, 'Tissot', 'Switzerland'),
   (2, 'Casio', 'Japan'),
   (3, 'Seiko', 'Japan'),
   (4, 'Rolex', 'Switzerland'),
   (5, 'Omega', 'Switzerland'),
   (6, 'Citizen', 'Japan'),
   (7, 'Sinn', 'Germany'),
   (8, 'Timex', 'USA');

-- 2. Годинники (з різними типами, цінами та залишками на складі)
INSERT INTO watches (brand, type, price, quantity, manufacturer_id) VALUES
    -- Tissot (id: 1, Швейцарія)
    ('Tissot PRX Powermatic', 'mechanical', 750.00, 5, 1),
    ('Tissot Classic Dream', 'quartz', 280.00, 10, 1),
    ('Tissot Gentleman', 'mechanical', 820.00, 4, 1),

    -- Casio (id: 2, Японія)
    ('Casio G-Shock GA-2100', 'quartz', 120.00, 15, 2),
    ('Casio Edifice', 'quartz', 180.00, 8, 2),
    ('Casio Vintage A168', 'quartz', 45.00, 25, 2),

    -- Seiko (id: 3, Японія)
    ('Seiko 5 Sports', 'mechanical', 320.00, 6, 3),
    ('Seiko Prospex Diver', 'mechanical', 950.00, 2, 3),
    ('Seiko Essentials', 'quartz', 190.00, 7, 3),

    -- Rolex (id: 4, Швейцарія)
    ('Rolex Submariner', 'mechanical', 11500.00, 1, 4),
    ('Rolex Datejust', 'mechanical', 9800.00, 2, 4),

    -- Omega (id: 5, Швейцарія)
    ('Omega Speedmaster Moonwatch', 'mechanical', 7200.00, 2, 5),
    ('Omega Seamaster Aqua Terra', 'mechanical', 5600.00, 3, 5),

    -- Citizen (id: 6, Японія)
    ('Citizen Promaster Eco-Drive', 'quartz', 310.00, 9, 6),
    ('Citizen Tsuyosa', 'mechanical', 380.00, 8, 6),

    -- Sinn (id: 7, Німеччина)
    ('Sinn 104 St Sa', 'mechanical', 1650.00, 3, 7),
    ('Sinn 556 I', 'mechanical', 1400.00, 4, 7),

    -- Timex (id: 8, США)
    ('Timex Weekender', 'quartz', 60.00, 20, 8),
    ('Timex Marlin Automatic', 'mechanical', 250.00, 5, 8),
    ('Timex Expedition', 'quartz', 85.00, 12, 8);