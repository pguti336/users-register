INSERT INTO usuarios(ID, EMAIL, PASSWORD, ACTIVE) VALUES (UUID 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 'test1@example.com', 'password', true);
INSERT INTO usuarios(ID, EMAIL, PASSWORD, ACTIVE) VALUES (UUID '5a2a392f-f3f7-43d7-b6e5-c91bb9d3f376', 'test2@example.com', 'password', true);
INSERT INTO usuarios(ID, EMAIL, PASSWORD, ACTIVE) VALUES (UUID 'de305d54-75b4-431b-adb2-eb6b9e546014', 'test3@example.com', 'password', true);
INSERT INTO telefonos(ID, NUMBER, CITY_CODE, COUNTRY_CODE, USER_ID) VALUES (1, '87654321', '51', '+56', UUID 'f47ac10b-58cc-4372-a567-0e02b2c3d479');
INSERT INTO telefonos(ID, NUMBER, CITY_CODE, COUNTRY_CODE, USER_ID) VALUES (2, '23456789', '02', '+56', UUID '5a2a392f-f3f7-43d7-b6e5-c91bb9d3f376');
INSERT INTO telefonos(ID, NUMBER, CITY_CODE, COUNTRY_CODE, USER_ID) VALUES (3, '98765432', '11', '+55', UUID 'de305d54-75b4-431b-adb2-eb6b9e546014');