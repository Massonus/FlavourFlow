INSERT INTO public.company_country(id, title)
VALUES (1, 'ITALIAN'),
       (2, 'UKRAINIAN'),
       (3, 'AMERICAN'),
       (4, 'SPANISH');


INSERT INTO public.kitchen_categories(id, title)
VALUES (1, 'CAFFE'),
       (2, 'CLUB'),
       (3, 'RESTAURANT'),
       (4, 'FAST FOOD');


INSERT INTO public.company(id, title, price_category, category_id, country_id, image_link)
VALUES (1, 'PNS', 'LOW', 4, 4, 'https://img.freepik.com/free-photo/cocktail-wooden-table-restaurant_23-2147936121.jpg?t=st=1709304734~exp=1709308334~hmac=d4a8d61fe99303ee2f6b1a8caefe0b15d1a628e0680861ce1ae68f095dcedbae&w=1380'),
       (2, 'The Minty Pantry', 'MEDIUM', 3, 1, 'https://img.freepik.com/free-photo/wide-restaurant-hall-with-wooden-table-chairs-6-persons_140725-8910.jpg?t=st=1709304730~exp=1709308330~hmac=2a9a09cd42399f5381d28e35595079083b7bb7af695a8b0c73363e729d475024&w=1380'),
       (3, 'The Bamboo Word', 'MEDIUM', 1, 3, 'https://i.pinimg.com/564x/74/39/56/7439562aba5c13cacc7429df288dcf6b.jpg'),
       (4, 'The Indian Cloud', 'MEDIUM', 1, 2, 'https://i.pinimg.com/564x/64/f1/26/64f1265f2667a3617a4a04c5f905476a.jpg'),
       (5, 'The Autumn Room', 'HIGH', 3, 3, 'https://i.pinimg.com/564x/aa/23/24/aa2324d993b70ad283baa2ba761f44ad.jpg'),
       (6, 'The Lunar Dragon', 'HIGH', 3, 2, 'https://i.pinimg.com/564x/ef/a7/78/efa77888bc356a201691eaef37e91431.jpg'),
       (7, 'The Saffron Laguna', 'PREMIUM', 2, 1, 'https://i.pinimg.com/564x/7d/45/90/7d4590d08eb0f7c787344bb8967d5469.jpg'),
       (8, 'The Sapphire Nights', 'PREMIUM', 2, 2, 'https://i.pinimg.com/564x/c5/a5/2a/c5a52a35f7556b5e17563f7c5ca97a4c.jpg'),
       (9, 'The Square', 'PREMIUM', 2, 3, 'https://i.pinimg.com/564x/22/e5/11/22e511fe8a5bc8ae6bb56ea4ac5b2836.jpg');


INSERT INTO public.product(id, title, price, company_id, image_link)
VALUES (1, 'Pizza', 10, 1, 'https://i.pinimg.com/564x/f0/86/c2/f086c2b68c7bc41d5371815fb4e0fc58.jpg'),
       (2, 'Nuggets', 8, 1, 'https://i.pinimg.com/564x/f9/da/1b/f9da1b2af51741c14d8f5f27f16b00fa.jpg'),
       (3, 'Hamburger', 5, 1, 'https://i.pinimg.com/564x/1e/65/4f/1e654fac595d426d7ffffccd754b5977.jpg'),
       (4, 'French fries', 3, 1, 'https://i.pinimg.com/564x/57/22/00/57220047fc59da5722f2daf2bf683b67.jpg'),
       (5, 'Onion rings', 2, 1, 'https://i.pinimg.com/564x/b0/df/d7/b0dfd757670a26517a7d7753d0708a7f.jpg'),


       (6, 'Pasta Amore', 15, 2, 'https://i.pinimg.com/564x/d6/a6/33/d6a633342d409a0cdd95a366743e8920.jpg'),
       (7, 'Tortellini', 18, 2, 'https://i.pinimg.com/564x/05/1f/34/051f347f2b8a731a8273a43390182188.jpg'),
       (8, 'Pizza Giovanni', 13, 2, 'https://i.pinimg.com/564x/4f/46/f9/4f46f9a0b7c72669e45b275e7309b7d3.jpg'),
       (9, 'Greek salad', 10, 2, 'https://i.pinimg.com/564x/c0/66/6e/c0666e81294bdbd90a62f2a19084d6ff.jpg'),
       (10, 'Caesar salad', 12, 2, 'https://i.pinimg.com/564x/16/98/3c/16983c21eeeb368245c4dbf04dc36658.jpg'),


       (11, 'Ribeye steak', 13, 3, 'https://i.pinimg.com/564x/df/c9/e1/dfc9e1e8c8512092bbd8ca6f6c24336b.jpg'),
       (12, 'Grilled chicken', 20, 3, 'https://i.pinimg.com/564x/3f/ff/41/3fff415f3fc81e5abe177f98d4b90328.jpg'),
       (13, 'Grilled vegetables', 17, 3, 'https://i.pinimg.com/564x/5a/d1/a2/5ad1a2151f1285f27ebf9c3d9af333a6.jpg'),
       (14, 'Caesar with shrimp', 13, 3, 'https://i.pinimg.com/564x/cb/3c/28/cb3c28c498097d33757d76477f431fe9.jpg'),
       (15, 'Langoustine burger', 14, 3, 'https://i.pinimg.com/564x/8d/2f/06/8d2f062d0084ec4375d10732d4400d9a.jpg');