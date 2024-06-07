INSERT INTO CATEGORY (ID, DESCRIPTION, NAME)
VALUES (1, 'Clothing items including shirts, pants, dresses, etc.', 'Clothing');

INSERT INTO CATEGORY (ID, DESCRIPTION, NAME)
VALUES (2, 'Footwear items including shoes, sandals, etc.', 'Footwear');

INSERT INTO CATEGORY (ID, DESCRIPTION, NAME)
VALUES (3, 'Accessories including bags, belts, hats, etc.', 'Accessories');

INSERT INTO CUSTOMER (ID, EMAIL, NAME, PASSWORD)
VALUES (1, 'joe@gmail.com', 'Joe Dozey', 'xy2az');

INSERT INTO CUSTOMER (ID, EMAIL, NAME, PASSWORD)
VALUES (2, 'jane.smith@gmail.com', 'Jane Smith', 'password123');

INSERT INTO CUSTOMER (ID, EMAIL, NAME, PASSWORD)
VALUES (3, 'alice.jonesey@gmail.com', 'Alice Jones', 'xZW3_34');

INSERT INTO PICTURE (ID, FILE)
VALUES (1, 'shirt.jpg');

INSERT INTO PICTURE (ID, FILE)
VALUES (2, 'dress.jpg');

INSERT INTO PICTURE (ID, FILE)
VALUES (3, 'shoes.jpg');

INSERT INTO PICTURE (ID, FILE)
VALUES (4, 'bag.jpg');

INSERT INTO ITEM (ID, NAME, CATEGORY_ID, CUSTOMER_ID, PICTURE_ID)
VALUES (1, 'Blue Shirt', 1, 1, 1);

INSERT INTO ITEM (ID, NAME, CATEGORY_ID, CUSTOMER_ID, PICTURE_ID)
VALUES (2, 'Red Dress', 1, 2, 2);

INSERT INTO ITEM (ID, NAME, CATEGORY_ID, CUSTOMER_ID, PICTURE_ID)
VALUES (3, 'Running Shoes', 2, 1, 3);

INSERT INTO ITEM (ID, NAME, CATEGORY_ID, CUSTOMER_ID, PICTURE_ID)
VALUES (4, 'Leather Bag', 3, 3, 4);