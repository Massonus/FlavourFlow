INSERT INTO public.kitchen_categories(id, title)
VALUES (1, 'CAFFE'),
       (2, 'CLUB'),
       (3, 'RESTAURANT'),
       (4, 'FAST FOOD');

ALTER SEQUENCE category_seq RESTART WITH 5;

INSERT INTO public.company_country(id, title)
VALUES (1, 'ITALIAN'),
       (2, 'UKRAINIAN'),
       (3, 'AMERICAN'),
       (4, 'SPANISH');

ALTER SEQUENCE country_seq RESTART WITH 5;

INSERT INTO public.company(id, title, category_id, country_id, image_link)
VALUES (1, 'PNS', 4, 4, 'https://img.freepik.com/free-photo/cocktail-wooden-table-restaurant_23-2147936121.jpg?t=st=1709304734~exp=1709308334~hmac=d4a8d61fe99303ee2f6b1a8caefe0b15d1a628e0680861ce1ae68f095dcedbae&w=1380'),
       (2, 'The Minty Pantry', 3, 1, 'https://img.freepik.com/free-photo/wide-restaurant-hall-with-wooden-table-chairs-6-persons_140725-8910.jpg?t=st=1709304730~exp=1709308330~hmac=2a9a09cd42399f5381d28e35595079083b7bb7af695a8b0c73363e729d475024&w=1380'),
       (3, 'The Bamboo Word', 3, 3, 'https://i.pinimg.com/564x/74/39/56/7439562aba5c13cacc7429df288dcf6b.jpg'),
       (4, 'The Ukrainian Cloud', 1, 2, 'https://i.pinimg.com/564x/64/f1/26/64f1265f2667a3617a4a04c5f905476a.jpg'),
       (5, 'The Autumn Room', 3, 3, 'https://i.pinimg.com/564x/aa/23/24/aa2324d993b70ad283baa2ba761f44ad.jpg'),
       (6, 'The Lunar Dragon', 3, 2, 'https://i.pinimg.com/564x/ef/a7/78/efa77888bc356a201691eaef37e91431.jpg'),
       (7, 'The Saffron Laguna', 2, 1, 'https://i.pinimg.com/564x/7d/45/90/7d4590d08eb0f7c787344bb8967d5469.jpg'),
       (8, 'The Sapphire Nights', 2, 2, 'https://i.pinimg.com/564x/c5/a5/2a/c5a52a35f7556b5e17563f7c5ca97a4c.jpg'),
       (9, 'The Square', 2, 3, 'https://i.pinimg.com/564x/22/e5/11/22e511fe8a5bc8ae6bb56ea4ac5b2836.jpg');

ALTER SEQUENCE company_seq RESTART WITH 10;

INSERT INTO public.product(id, title, product_category, price, company_id, image_link)
VALUES (1, 'Pizza', 'MEAL', 10.2, 1, 'https://i.pinimg.com/564x/f0/86/c2/f086c2b68c7bc41d5371815fb4e0fc58.jpg'),
       (2, 'Nuggets', 'MEAL', 8, 1, 'https://i.pinimg.com/564x/f9/da/1b/f9da1b2af51741c14d8f5f27f16b00fa.jpg'),
       (3, 'Hamburger', 'MEAL', 5, 1, 'https://i.pinimg.com/564x/1e/65/4f/1e654fac595d426d7ffffccd754b5977.jpg'),
       (4, 'French fries', 'MEAL', 3, 1, 'https://i.pinimg.com/564x/57/22/00/57220047fc59da5722f2daf2bf683b67.jpg'),
       (5, 'Onion rings', 'MEAL', 2, 1, 'https://i.pinimg.com/564x/b0/df/d7/b0dfd757670a26517a7d7753d0708a7f.jpg'),


       (6, 'Pasta Amore', 'MEAL', 15, 2, 'https://i.pinimg.com/564x/d6/a6/33/d6a633342d409a0cdd95a366743e8920.jpg'),
       (7, 'Tortellini', 'MEAL', 18, 2, 'https://i.pinimg.com/564x/05/1f/34/051f347f2b8a731a8273a43390182188.jpg'),
       (8, 'Pizza Giovanni', 'MEAL', 13, 2, 'https://i.pinimg.com/564x/4f/46/f9/4f46f9a0b7c72669e45b275e7309b7d3.jpg'),
       (9, 'Greek salad', 'MEAL', 10, 2, 'https://i.pinimg.com/564x/c0/66/6e/c0666e81294bdbd90a62f2a19084d6ff.jpg'),
       (10, 'Caesar salad', 'MEAL', 12, 2, 'https://i.pinimg.com/564x/16/98/3c/16983c21eeeb368245c4dbf04dc36658.jpg'),


       (11, 'Ribeye steak', 'MEAL', 13, 3, 'https://i.pinimg.com/564x/df/c9/e1/dfc9e1e8c8512092bbd8ca6f6c24336b.jpg'),
       (12, 'Grilled chicken', 'MEAL', 20, 3, 'https://i.pinimg.com/564x/3f/ff/41/3fff415f3fc81e5abe177f98d4b90328.jpg'),
       (13, 'Grilled vegetables', 'MEAL', 17, 3, 'https://i.pinimg.com/564x/5a/d1/a2/5ad1a2151f1285f27ebf9c3d9af333a6.jpg'),
       (14, 'Caesar with shrimp', 'MEAL', 13, 3, 'https://i.pinimg.com/564x/cb/3c/28/cb3c28c498097d33757d76477f431fe9.jpg'),
       (15, 'Langoustine burger', 'MEAL', 14, 3, 'https://i.pinimg.com/564x/8d/2f/06/8d2f062d0084ec4375d10732d4400d9a.jpg'),


       (16, 'Olivier with veal tongue', 'MEAL', 18, 4, 'https://i.pinimg.com/564x/fa/ac/ca/faaccab7080343d74b826a76c75e7a4f.jpg'),
       (17, 'Lard spread', 'MEAL', 8, 4, 'https://i.pinimg.com/564x/bd/0c/6e/bd0c6e579d77102a1748cb3ece2bb3b7.jpg'),
       (18, 'Meat legumins', 'MEAL', 14, 4, 'https://i.pinimg.com/564x/d7/92/e9/d792e91033dd6ce090ad32b917a57350.jpg'),
       (19, 'Lard carpaccio with Yuzu sauce', 'MEAL', 20, 4, 'https://i.pinimg.com/564x/95/86/b0/9586b0dbe53b4c9e154b1bee55c38c1e.jpg'),
       (20, 'Bograch with pheasant meat', 'MEAL', 21, 4, 'https://i.pinimg.com/564x/d3/05/29/d3052906481fb406ca89421494daea83.jpg'),


       (21, 'French toast', 'MEAL', 28, 5, 'https://i.pinimg.com/564x/20/1b/82/201b82ca39ad0bc30e03baac8f040c98.jpg'),
       (22, 'Belglan waffle', 'MEAL', 30, 5, 'https://i.pinimg.com/564x/f1/1f/97/f11f976b0a79142bdd6e05728fa43eba.jpg'),
       (23, 'Eggs Benedict', 'MEAL', 29, 5, 'https://i.pinimg.com/564x/b9/dd/e9/b9dde9e2e59415b2ec7a103537288abb.jpg'),
       (24, 'Philly Omelet', 'MEAL', 32, 5, 'https://i.pinimg.com/564x/90/7e/f7/907ef797cfc8e4b7293e81b73f221ec7.jpg'),
       (25, 'Western Omelet', 'MEAL', 31, 5, 'https://i.pinimg.com/564x/bc/80/b0/bc80b06780da1cd638ce00ed257a9cfc.jpg'),

       (26, 'Cabbage Rolls', 'MEAL', 24, 6, 'https://i.pinimg.com/564x/6e/6a/c3/6e6ac35b7bbb1d2175338f269cf163a1.jpg'),
       (27, 'Walnut Strudel', 'MEAL', 19, 6, 'https://i.pinimg.com/564x/be/c8/95/bec89554d981b3e8e4f5f3df984f660f.jpg'),
       (28, 'Walnut Torte', 'MEAL', 26, 6, 'https://i.pinimg.com/564x/b3/83/78/b38378092d7dca6e189b688cc1c24671.jpg'),
       (29, 'Beef Stew', 'MEAL', 13, 6, 'https://i.pinimg.com/564x/a8/b0/69/a8b069dc489728f671b7448aa9f58651.jpg'),
       (30, 'Roast Chicken', 'MEAL', 19, 6, 'https://i.pinimg.com/564x/6c/77/d9/6c77d9f49108cfac950aa59e94416b53.jpg'),


       (31, 'Lasagne Al Forno', 'MEAL', 34, 7, 'https://i.pinimg.com/564x/78/d0/91/78d091495ff0430f5510b03ccd4d729f.jpg'),
       (32, 'Steak Frites', 'MEAL', 30, 7, 'https://i.pinimg.com/564x/0f/87/c2/0f87c21e617249ca55331a18746e3b8d.jpg'),
       (33, 'Penne Alla Principessa', 'MEAL', 27, 7, 'https://i.pinimg.com/564x/d4/d5/2c/d4d52c98484435e52b88f7bc2f671dbf.jpg'),
       (34, 'Old Fashioned', 'DRINK', 35, 7, 'https://i.pinimg.com/564x/d3/b9/b3/d3b9b3214917d326751230924f09f9b1.jpg'),
       (35, 'Negroni', 'DRINK', 40, 7, 'https://i.pinimg.com/564x/14/11/a4/1411a4b91395b90db99bcce66547a25b.jpg'),


       (36, 'Chicken Kyiv', 'MEAL', 36, 8, 'https://i.pinimg.com/564x/64/64/6c/64646c7f58d8d66af254497d182d9ec3.jpg'),
       (37, 'Stuffed Pepper', 'MEAL', 33, 8, 'https://i.pinimg.com/564x/4c/b4/c5/4cb4c585ae257b03e6a4480ac4b2cc70.jpg'),
       (38, 'Cheese Balls', 'MEAL', 22, 8, 'https://i.pinimg.com/564x/31/01/2d/31012d1d37ff47a58febd974332f8b2c.jpg'),
       (39, 'Martini', 'DRINK', 43, 8, 'https://i.pinimg.com/564x/aa/72/c1/aa72c103a511dd75a43a7aca75a3894e.jpg'),
       (40, 'Whiskey Sour', 'DRINK', 45, 8, 'https://i.pinimg.com/564x/3f/64/6c/3f646c733818be43ff98ea9ec24914d5.jpg'),


       (41, 'Tater tots', 'MEAL', 39, 9, 'https://i.pinimg.com/564x/41/9a/0b/419a0bc20b201e03893ff201fb428b3d.jpg'),
       (42, 'Cobb salad', 'MEAL', 35, 9, 'https://i.pinimg.com/564x/34/fe/3d/34fe3d4e39c817c37c8ee1e464e12404.jpg'),
       (43, 'Fajitas', 'MEAL', 37, 9, 'https://i.pinimg.com/564x/38/bf/9e/38bf9e3b81c26d989c9291b012cf420f.jpg'),
       (44, 'Manhattan', 'DRINK', 47, 9, 'https://i.pinimg.com/564x/49/c5/10/49c510dcec7ca7417727ef466e71bfa4.jpg'),
       (45, 'Spritz', 'DRINK', 49, 9, 'https://i.pinimg.com/564x/06/36/ee/0636eeda6325e36ba1cea59a2bd35f2e.jpg');


ALTER SEQUENCE product_seq RESTART WITH 46;