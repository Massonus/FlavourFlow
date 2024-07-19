INSERT INTO public.kitchen_category(id, title)
VALUES (1, 'CAFFE'),
       (2, 'CLUB'),
       (3, 'RESTAURANT'),
       (4, 'FAST FOOD');

ALTER SEQUENCE kitchen_category_seq RESTART WITH 5;

INSERT INTO public.company_country(id, title)
VALUES (1, 'ITALIAN'),
       (2, 'UKRAINIAN'),
       (3, 'AMERICAN'),
       (4, 'SPANISH');

ALTER SEQUENCE company_country_seq RESTART WITH 5;

INSERT INTO public.company (id, title, description, image_link, rating, category_id, country_id)
VALUES  (1, 'PNS', 'PNS is a popular Spanish fast food restaurant chain known for its vibrant ambiance and flavorful dishes', 'https://dl.dropboxusercontent.com/scl/fi/ljxzpd4ein0r9dgabn6o7/COMPANY1.jpg?rlkey=g3cb62e852wsl2h9p7xw0b83p&dl=0', 4, 4, 4),
        (2, 'The Minty Pantry', 'A cozy Italian eatery offering exquisite pasta, wood-fired pizzas. Authentic flavors await!', 'https://dl.dropboxusercontent.com/scl/fi/flsumetepgj2sp8bhwk3x/COMPANY2.jpg?rlkey=3bamxjp29cvskwbhs3bpz6i6c&dl=0', 5, 3, 1),
        (3, 'The Bamboo Word', 'A welcoming spot with rustic charm, serving up American classics in a laid-back atmosphere', 'https://i.pinimg.com/564x/74/39/56/7439562aba5c13cacc7429df288dcf6b.jpg', null, 3, 3),
        (4, 'The Ukrainian Cloud', 'A taste of tradition with a modern twist. Delight in authentic flavors amidst a cozy, ethereal atmosphere', 'https://i.pinimg.com/564x/64/f1/26/64f1265f2667a3617a4a04c5f905476a.jpg', null, 1, 2),
        (5, 'The Autumn Room', 'Where nostalgia meets contemporary charm. Enjoy American classics in a cozy, seasonal ambiance', 'https://i.pinimg.com/564x/aa/23/24/aa2324d993b70ad283baa2ba761f44ad.jpg', null, 3, 3),
        (6, 'The Lunar Dragon', 'A fusion of Ukrainian flavors and modern ambiance. Delight in cultural richness under the night sky', 'https://i.pinimg.com/564x/ef/a7/78/efa77888bc356a201691eaef37e91431.jpg', null, 3, 2),
        (7, 'The Saffron Laguna', 'An Italian haven blending tradition and elegance. Savour the essence of Italy', 'https://i.pinimg.com/564x/7d/45/90/7d4590d08eb0f7c787344bb8967d5469.jpg', null, 2, 1),
        (8, 'The Sapphire Nights', 'The Sapphire Nights: Ukraine''s premier club for unforgettable nightlife experiences', 'https://i.pinimg.com/564x/c5/a5/2a/c5a52a35f7556b5e17563f7c5ca97a4c.jpg', null, 2, 2),
        (9, 'The Square', 'The Square: Vibrant American club, pulsating with music and energy. Embrace the vibe!', 'https://i.pinimg.com/564x/22/e5/11/22e511fe8a5bc8ae6bb56ea4ac5b2836.jpg', null, 2, 3);

ALTER SEQUENCE company_seq RESTART WITH 10;

INSERT INTO public.product (id, title, description, composition, image_link, product_category, price, company_id)
VALUES  (1, 'Pizza', 'Classic pepperoni', 'pizza dough, tomato sauce, pepperoni, mozzarella cheese, optional toppings: onions, mushrooms, green peppers, black olives, pineapple, anchovies', 'https://i.pinimg.com/564x/f0/86/c2/f086c2b68c7bc41d5371815fb4e0fc58.jpg', 'MEAL', 10.2, 1),
        (2, 'Nuggets', 'Crispy, tender, satisfying nuggets', 'Chicken breast, breading, vegetable oil, salt, seasonings', 'https://i.pinimg.com/564x/f9/da/1b/f9da1b2af51741c14d8f5f27f16b00fa.jpg', 'MEAL', 8, 1),
        (3, 'Hamburger', 'Iconic burger, carefully crafted for satisfaction', 'Hamburger patty, bun, lettuce, tomato, onion, pickles, cheese, ketchup, mustard', 'https://i.pinimg.com/564x/1e/65/4f/1e654fac595d426d7ffffccd754b5977.jpg', 'MEAL', 5, 1),
        (4, 'French fries', 'Golden, crispy, irresistible potato delicacy', 'Potato, vegetable oil, salt', 'https://i.pinimg.com/564x/57/22/00/57220047fc59da5722f2daf2bf683b67.jpg', 'MEAL', 3, 1),
        (5, 'Onion rings', 'Crunchy, savory rings, deep-fried perfection', 'Onion, batter (flour, cornstarch, spices), vegetable oil', 'https://i.pinimg.com/564x/b0/df/d7/b0dfd757670a26517a7d7753d0708a7f.jpg', 'MEAL', 2, 1),
        (6, 'Pasta Amore', 'Indulgent pasta dish, culinary bliss', 'Pasta, tomato sauce, garlic, olive oil, basil, parmesan cheese', 'https://i.pinimg.com/564x/d6/a6/33/d6a633342d409a0cdd95a366743e8920.jpg', 'MEAL', 15, 2),
        (7, 'Tortellini', 'Italian delicacy, savory stuffed pasta pockets', 'Tortellini, flour, eggs, cheese, meat, salt', 'https://i.pinimg.com/564x/05/1f/34/051f347f2b8a731a8273a43390182188.jpg', 'MEAL', 18, 2),
        (8, 'Pizza Giovanni', 'Giovanni''s signature pizza, culinary masterpiece', 'Dough, tomato sauce, mozzarella cheese, pepperoni, mushrooms, onions', 'https://i.pinimg.com/564x/4f/46/f9/4f46f9a0b7c72669e45b275e7309b7d3.jpg', 'MEAL', 13, 2),
        (9, 'Greek salad', 'Fresh, vibrant Greek salad, Mediterranean flavors', 'Lettuce, tomatoes, cucumbers, red onions, olives, feta cheese, olive oil, oregano', 'https://i.pinimg.com/564x/c0/66/6e/c0666e81294bdbd90a62f2a19084d6ff.jpg', 'MEAL', 10, 2),
        (10, 'Caesar salad', 'Classic Caesar salad, timeless flavor combination', 'Lettuce, croutons, parmesan cheese, caesar dressing', 'https://i.pinimg.com/564x/16/98/3c/16983c21eeeb368245c4dbf04dc36658.jpg', 'MEAL', 12, 2),
        (11, 'Ribeye steak', 'Juicy, tender ribeye steak, grilled perfection', 'Ribeye beef, salt, pepper', 'https://i.pinimg.com/564x/df/c9/e1/dfc9e1e8c8512092bbd8ca6f6c24336b.jpg', 'MEAL', 13, 3),
        (12, 'Grilled chicken', 'Flavorful, succulent grilled chicken, charred to perfection', 'Chicken breast, olive oil, salt, pepper', 'https://i.pinimg.com/564x/3f/ff/41/3fff415f3fc81e5abe177f98d4b90328.jpg', 'MEAL', 20, 3),
        (13, 'Grilled vegetables', 'Fresh, charred grilled vegetables, bursting with flavor', 'Zucchini, bell peppers, mushrooms, onions, olive oil, salt, pepper', 'https://i.pinimg.com/564x/5a/d1/a2/5ad1a2151f1285f27ebf9c3d9af333a6.jpg', 'MEAL', 17, 3),
        (14, 'Caesar with shrimp', 'Classic Caesar salad with succulent shrimp', 'Lettuce, shrimp, croutons, parmesan cheese, caesar dressing', 'https://i.pinimg.com/564x/cb/3c/28/cb3c28c498097d33757d76477f431fe9.jpg', 'MEAL', 13, 3),
        (15, 'Langoustine burger', 'Sumptuous langoustine burger, culinary indulgence', 'Langoustine meat, bun, lettuce, tomato, onion, pickles, sauce', 'https://i.pinimg.com/564x/8d/2f/06/8d2f062d0084ec4375d10732d4400d9a.jpg', 'MEAL', 14, 3),
        (16, 'Olivier with veal tongue', 'Savory Olivier salad with tender veal tongue', 'Potatoes, veal tongue, pickles, peas, carrots, mayonnaise, salt, pepper', 'https://i.pinimg.com/564x/fa/ac/ca/faaccab7080343d74b826a76c75e7a4f.jpg', 'MEAL', 18, 4),
        (17, 'Lard spread', 'Rich, flavorful lard spread, culinary delight', 'Lard, garlic, salt, pepper', 'https://i.pinimg.com/564x/bd/0c/6e/bd0c6e579d77102a1748cb3ece2bb3b7.jpg', 'MEAL', 8, 4),
        (18, 'Meat legumins', 'Hearty meat legume dish, satisfying comfort food', 'Meat, beans, onions, tomatoes, spices', 'https://i.pinimg.com/564x/d7/92/e9/d792e91033dd6ce090ad32b917a57350.jpg', 'MEAL', 14, 4),
        (19, 'Lard carpaccio with Yuzu sauce', 'Delicate lard carpaccio with zesty Yuzu sauce', 'Lard, yuzu sauce, arugula, Parmesan cheese, cracked black pepper', 'https://i.pinimg.com/564x/95/86/b0/9586b0dbe53b4c9e154b1bee55c38c1e.jpg', 'MEAL', 20, 4),
        (20, 'Bograch with pheasant meat', 'Hearty Bograch featuring succulent pheasant meat', 'Pheasant meat, potatoes, onions, carrots, bell peppers, tomatoes, paprika, garlic, salt, pepper', 'https://i.pinimg.com/564x/d3/05/29/d3052906481fb406ca89421494daea83.jpg', 'MEAL', 21, 4),
        (21, 'French toast', 'Classic French toast, timeless breakfast favorite', 'bread, eggs, milk, cinnamon, vanilla extract', 'https://i.pinimg.com/564x/20/1b/82/201b82ca39ad0bc30e03baac8f040c98.jpg', 'MEAL', 28, 5),
        (22, 'Belglan waffle', 'Authentic Belgian waffle, indulgent breakfast treat', 'flour, eggs, milk, butter, sugar, baking powder, vanilla extract', 'https://i.pinimg.com/564x/f1/1f/97/f11f976b0a79142bdd6e05728fa43eba.jpg', 'MEAL', 30, 5),
        (23, 'Eggs Benedict', 'Iconic Eggs Benedict, brunch classic delight', 'English muffin, Canadian bacon, poached egg, hollandaise sauce', 'https://i.pinimg.com/564x/b9/dd/e9/b9dde9e2e59415b2ec7a103537288abb.jpg', 'MEAL', 29, 5),
        (24, 'Philly Omelet', 'Savory Philly omelet, flavorful breakfast sensation', 'eggs, bell peppers, onions, mushrooms, cheese', 'https://i.pinimg.com/564x/90/7e/f7/907ef797cfc8e4b7293e81b73f221ec7.jpg', 'MEAL', 32, 5),
        (25, 'Western Omelet', 'Hearty Western omelet, breakfast comfort food favorite', 'Eggs, ham, bell peppers, onions, cheese', 'https://i.pinimg.com/564x/bc/80/b0/bc80b06780da1cd638ce00ed257a9cfc.jpg', 'MEAL', 31, 5),
        (26, 'Cabbage Rolls', 'Traditional cabbage rolls, savory comfort food', 'Cabbage leaves, ground meat, rice, onions, tomatoes, garlic, spices', 'https://i.pinimg.com/564x/6e/6a/c3/6e6ac35b7bbb1d2175338f269cf163a1.jpg', 'MEAL', 24, 6),
        (27, 'Walnut Strudel', 'Delicious walnut strudel, sweet pastry delight', 'Dough, walnuts, sugar, cinnamon, butter', 'https://i.pinimg.com/564x/be/c8/95/bec89554d981b3e8e4f5f3df984f660f.jpg', 'MEAL', 19, 6),
        (28, 'Walnut Torte', 'Decadent walnut torte, sweet dessert perfection', 'Walnuts, eggs, sugar, flour, butter, vanilla extract', 'https://i.pinimg.com/564x/b3/83/78/b38378092d7dca6e189b688cc1c24671.jpg', 'MEAL', 26, 6),
        (29, 'Beef Stew', 'Hearty beef stew, comforting savory dish', 'Beef, potatoes, carrots, onions, celery, beef broth, tomato paste, herbs', 'https://i.pinimg.com/564x/a8/b0/69/a8b069dc489728f671b7448aa9f58651.jpg', 'MEAL', 13, 6),
        (30, 'Roast Chicken', 'Succulent roast chicken, flavorful main dish', 'Chicken, salt, pepper, olive oil, herbs', 'https://i.pinimg.com/564x/6c/77/d9/6c77d9f49108cfac950aa59e94416b53.jpg', 'MEAL', 19, 6),
        (31, 'Lasagne Al Forno', 'Baked lasagne al forno, Italian comfort classic', 'Lasagna noodles, bolognese sauce, béchamel sauce, mozzarella cheese, Parmesan cheese', 'https://i.pinimg.com/564x/78/d0/91/78d091495ff0430f5510b03ccd4d729f.jpg', 'MEAL', 34, 7),
        (32, 'Steak Frites', 'Tender steak frites, French bistro favorite', 'Steak, fries, salt, pepper', 'https://i.pinimg.com/564x/0f/87/c2/0f87c21e617249ca55331a18746e3b8d.jpg', 'MEAL', 30, 7),
        (33, 'Penne Alla Principessa', 'Princess-style penne pasta, Italian culinary elegance', 'Penne pasta, cream, smoked salmon, vodka, onion, garlic, butter, salt, pepper', 'https://i.pinimg.com/564x/d4/d5/2c/d4d52c98484435e52b88f7bc2f671dbf.jpg', 'MEAL', 27, 7),
        (34, 'Old Fashioned', 'Classic Old Fashioned cocktail, timeless sophistication', 'Whiskey, sugar, bitters, orange peel', 'https://i.pinimg.com/564x/d3/b9/b3/d3b9b3214917d326751230924f09f9b1.jpg', 'MEAL', 35, 7),
        (35, 'Negroni', 'Iconic Negroni cocktail, Italian aperitif tradition', ' Gin, sweet vermouth, Campari', 'https://i.pinimg.com/564x/14/11/a4/1411a4b91395b90db99bcce66547a25b.jpg', 'MEAL', 40, 7),
        (36, 'Chicken Kyiv', 'Tender Chicken Kyiv, flavorful Ukrainian classic', 'Chicken breast, butter, garlic, herbs, breadcrumbs, egg, flour', 'https://i.pinimg.com/564x/64/64/6c/64646c7f58d8d66af254497d182d9ec3.jpg', 'MEAL', 36, 8),
        (37, 'Stuffed Pepper', 'Savory stuffed peppers, comforting culinary delight', 'Bell peppers, ground meat, rice, onions, tomatoes, garlic, spices', 'https://i.pinimg.com/564x/4c/b4/c5/4cb4c585ae257b03e6a4480ac4b2cc70.jpg', 'MEAL', 33, 8),
        (38, 'Cheese Balls', 'Delicious cheese balls, irresistible savory snack', 'Cheese, breadcrumbs, egg, flour, spices', 'https://i.pinimg.com/564x/31/01/2d/31012d1d37ff47a58febd974332f8b2c.jpg', 'MEAL', 22, 8),
        (39, 'Martini', 'Classic martini, timeless cocktail sophistication', ' Gin, vermouth, olives', 'https://i.pinimg.com/564x/aa/72/c1/aa72c103a511dd75a43a7aca75a3894e.jpg', 'MEAL', 43, 8),
        (40, 'Whiskey Sour', 'Refreshing whiskey sour, balanced citrus delight', 'Whiskey, lemon juice, simple syrup, ice', 'https://i.pinimg.com/564x/3f/64/6c/3f646c733818be43ff98ea9ec24914d5.jpg', 'MEAL', 45, 8),
        (41, 'Tater tots', 'Crunchy tater tots, irresistible potato snack', 'Potatoes, flour, salt, oil', 'https://i.pinimg.com/564x/41/9a/0b/419a0bc20b201e03893ff201fb428b3d.jpg', 'MEAL', 39, 9),
        (42, 'Cobb salad', 'Classic Cobb salad, fresh, flavorful favorite', 'Lettuce, tomatoes, bacon, chicken, hard-boiled eggs, avocado, blue cheese dressing', 'https://i.pinimg.com/564x/34/fe/3d/34fe3d4e39c817c37c8ee1e464e12404.jpg', 'MEAL', 35, 9),
        (43, 'Fajitas', 'Sizzling fajitas, flavorful Tex-Mex sensation', 'Beef or chicken strips, bell peppers, onions, tortillas, seasoning', 'https://i.pinimg.com/564x/38/bf/9e/38bf9e3b81c26d989c9291b012cf420f.jpg', 'MEAL', 37, 9),
        (44, 'Manhattan', 'Classic Manhattan cocktail, timeless sophistication', 'Whiskey, sweet vermouth, bitters, cherry', 'https://i.pinimg.com/564x/49/c5/10/49c510dcec7ca7417727ef466e71bfa4.jpg', 'MEAL', 47, 9),
        (45, 'Spritz', 'Refreshing Spritz cocktail, Italian aperitivo tradition', 'Prosecco, Aperol, soda water, orange slice', 'https://i.pinimg.com/564x/06/36/ee/0636eeda6325e36ba1cea59a2bd35f2e.jpg', 'MEAL', 49, 9),
        (46, 'Ricotta Ravioli', 'Delight your taste buds with our Ricotta Ravioli', 'Ricotta cheese, Parmesan cheese, egg, all-purpose flour, salt, nutmeg, olive oil, water', 'https://dl.dropboxusercontent.com/scl/fi/zn7p8at6qpyy88xt9odxi/PRODUCT49.jpg?rlkey=r559uel4a4nqhtnmdo5ckoimc&dl=0', 'MEAL', 20, 2);

ALTER SEQUENCE product_seq RESTART WITH 47;


INSERT INTO public.consumer (id, username, email, password, redactor, bonuses, telegram_id)
VALUES  (1, 'admin', 'admin@gmail.com', '$2a$10$pIBE3YU3qoJOLtkpNLVst.FkxfmoRDaLYrmjN2gwd2NikTB2T0Cpq', 'system', 38.4, 661204444),
        (2, 'user', 'user@gmail.com', '$2a$10$iF6G7T2k2iuO1VJ9kk/pcebiRhFStmq908skq.1LosEAaBbLW5UoC', 'system', 50, 0),
        (3, 'bob', 'bob@mail.com', '$2a$10$IwUvnlOMaigp3vVt.uJhz.xAic1qkZsLZq90k54R5vIlDrQZvd4zu', '1', 22, 0),
        (4, 'Daniel', 'Dan1234@mail.com', '$2a$10$jVKHug8EUmzqkYSe3XJM5uSN4w66HxlyurziwlQ4Th0EAsRx/Zsku', 'registration', 50, 0),
        (5, 'Oleg', 'Oleg@mail.com', '$2a$10$dNiepzoSi7SCpNHzsl3nRutdsW6uaahVTM7Lf6KeWXP37nUaTp9yO', 'registration', 0.61, 0),
        (6, 'TpoJIJIb', 'igorgella20020505@gmail.com', '$2a$10$YXRY894bVriHWZZNQrAnPuRb7yrZt2KIbox82IDnyrq1yCBhlpvby', 'registration', 3.51, 0),
        (7, 'asd', 'verapelevina585@gmail.com', '$2a$10$L9Zi3DYULxjmmna4w02Zz.ihvc6MKahTAaK667Lc04YaB/Cl5Ssia', 'registration', 0, 0);

ALTER SEQUENCE consumer_seq RESTART WITH 8;

INSERT INTO public.user_role (user_id, roles)
VALUES  (1, 'ADMIN'),
        (2, 'USER'),
        (3, 'ADMIN'),
        (4, 'USER'),
        (5, 'USER'),
        (6, 'USER'),
        (7, 'USER');

INSERT INTO public.basket (id, user_id)
VALUES  (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5),
        (6, 6),
        (7, 7);

ALTER SEQUENCE basket_seq RESTART WITH 8;

INSERT INTO public.wishes (id, user_id)
VALUES  (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5),
        (6, 6),
        (7, 7);

ALTER SEQUENCE wishes_seq RESTART WITH 8;

INSERT INTO public.basket_object (id, title, image_link, price, amount, company_id, user_id, basket_id, product_id)
VALUES  (6, 'Tortellini', 'https://i.pinimg.com/564x/05/1f/34/051f347f2b8a731a8273a43390182188.jpg', 18, 1, 2, 1, 1, 7),
        (7, 'Pasta Amore', 'https://i.pinimg.com/564x/d6/a6/33/d6a633342d409a0cdd95a366743e8920.jpg', 15, 1, 2, 1, 1, 6),
        (8, 'Pizza Giovanni', 'https://i.pinimg.com/564x/4f/46/f9/4f46f9a0b7c72669e45b275e7309b7d3.jpg', 13, 1, 2, 1, 1, 8),
        (9, 'Hamburger', 'https://i.pinimg.com/564x/1e/65/4f/1e654fac595d426d7ffffccd754b5977.jpg', 5, 1, 1, 1, 1, 3),
        (10, 'Pizza', 'https://i.pinimg.com/564x/f0/86/c2/f086c2b68c7bc41d5371815fb4e0fc58.jpg', 10.2, 1, 1, 1, 1, 1),
        (11, 'Caesar with shrimp', 'https://i.pinimg.com/564x/cb/3c/28/cb3c28c498097d33757d76477f431fe9.jpg', 13, 1, 3, 1, 1, 14),
        (12, 'Grilled chicken', 'https://i.pinimg.com/564x/3f/ff/41/3fff415f3fc81e5abe177f98d4b90328.jpg', 20, 1, 3, 1, 1, 12),
        (13, 'Ribeye steak', 'https://i.pinimg.com/564x/df/c9/e1/dfc9e1e8c8512092bbd8ca6f6c24336b.jpg', 13, 1, 3, 1, 1, 11);

ALTER SEQUENCE basket_object_seq RESTART WITH 14;

INSERT INTO public.wish_object (id, title, image_link, price, company_id, user_id, wish_id, product_id)
VALUES  (1, 'Pizza', 'https://i.pinimg.com/564x/f0/86/c2/f086c2b68c7bc41d5371815fb4e0fc58.jpg', 10.2, 1, 1, 1, 1),
        (2, 'Pasta Amore', 'https://i.pinimg.com/564x/d6/a6/33/d6a633342d409a0cdd95a366743e8920.jpg', 15, 2, 2, 2, 6),
        (3, 'Tortellini', 'https://i.pinimg.com/564x/05/1f/34/051f347f2b8a731a8273a43390182188.jpg', 18, 2, 2, 2, 7),
        (4, 'Grilled chicken', 'https://i.pinimg.com/564x/3f/ff/41/3fff415f3fc81e5abe177f98d4b90328.jpg', 20, 3, 3, 3, 12),
        (5, 'Caesar with shrimp', 'https://i.pinimg.com/564x/cb/3c/28/cb3c28c498097d33757d76477f431fe9.jpg', 13, 3, 3, 3, 14),
        (6, 'Nuggets', 'https://i.pinimg.com/564x/f9/da/1b/f9da1b2af51741c14d8f5f27f16b00fa.jpg', 8, 1, 1, 1, 2),
        (7, 'Hamburger', 'https://i.pinimg.com/564x/1e/65/4f/1e654fac595d426d7ffffccd754b5977.jpg', 5, 1, 1, 1, 3),
        (8, 'French fries', 'https://i.pinimg.com/564x/57/22/00/57220047fc59da5722f2daf2bf683b67.jpg', 3, 1, 1, 1, 4),
        (9, 'Grilled vegetables', 'https://i.pinimg.com/564x/5a/d1/a2/5ad1a2151f1285f27ebf9c3d9af333a6.jpg', 17, 3, 1, 1, 13);

ALTER SEQUENCE wish_object_seq RESTART WITH 10;

INSERT INTO public.orders (id, total, date, time, earned_bonuses, address, user_id, company_id)
VALUES  (1, 18, '2024-05-27', '15:56:00', 1.8, 'Main Street, 123', 4, 2),
        (2, 20.4, '2024-05-31', '15:09:00', 0.61, 'Main Street, 123', 5, 1),
        (3, 117, '2024-06-03', '12:34:00', 3.51, 'Moscow', 6, 2),
        (4, 13, '2024-06-14', '14:56:00', 0.39, 'MSK', 1, 1),
        (5, 33, '2024-06-15', '12:35:00', 0.99, 'Kiev', 1, 2);

ALTER SEQUENCE order_seq RESTART WITH 6;

INSERT INTO public.order_object (id, title, amount, sum, user_id, order_id, product_id, company_id)
VALUES  (1, 'Pizza', 2, 20.4, 5, 2, 1, 1),
        (2, 'Pasta Amore', 4, 60, 6, 3, 6, 2),
        (3, 'Pizza Giovanni', 3, 39, 6, 3, 8, 2),
        (4, 'Tortellini', 1, 18, 6, 3, 7, 2),
        (5, 'Tortellini', 1, 18, 4, 1, 7, 2),
        (6, 'Nuggets', 1, 8, 1, 4, 2, 1),
        (7, 'Hamburger', 1, 5, 1, 4, 3, 1),
        (8, 'Pasta Amore', 1, 15, 1, 5, 6, 2),
        (9, 'Tortellini', 1, 18, 1, 5, 7, 2);

ALTER SEQUENCE order_object_seq RESTART WITH 10;

INSERT INTO public.comment (id, text, comment_time, user_id, item_id)
VALUES  (1, 'Not bad', null, 1, 1),
        (2, 'Perfect', null, 4, 2),
        (3, 'Good', null, 5, 2),
        (4, 'Zaeb', null, 6, 2);

ALTER SEQUENCE comment_seq RESTART WITH 5;

INSERT INTO public.comment_like (comment_id, user_id)
VALUES  (1, 1),
        (2, 1),
        (3, 6);

INSERT INTO public.company_comment (company_id, comment_id)
VALUES  (1, 1),
        (2, 2),
        (2, 3),
        (2, 4);

INSERT INTO public.rating (id, rate, user_id, company_id)
VALUES  (3, 4, 1, 1),
        (4, 5, 4, 2),
        (5, 4, 5, 2);

ALTER SEQUENCE rating_seq RESTART WITH 6;