

--INSERT INTO users (user_id, user_email, user_first_name, user_last_name, user_names, user_password, username) values (1, 'miki@gmail.com', 'Duenas', 'Guevara', 'Miguel', '$2a$10$da1RJcMzdheVuAB16sBAwu9gqjRGW5MQaAQlXcYZShkN.DD3nta0m', 'admin');
--INSERT INTO users (user_id, user_email, user_first_name, user_last_name, user_names, user_password, username) values (2, 'jose@gmail.com', 'Montoya', 'Reyes', 'Jose', '$2a$10$Q2.SD5D5arG.6G.kNkxmpOrhW5FvOxRl5vRrlZpFi/T5gkdnT3YJe', 'user');
--
--INSERT INTO role (role_id, authority, user_id) values (1, 'ROLE_ADMIN', 1);
--INSERT INTO role (role_id, authority, user_id) values (2, 'ROLE_USER', 2);

INSERT INTO booking_status (bookingstatus_id, bookingstatus_name) values (1, 'PENDIENTE DE PAGO');
INSERT INTO booking_status (bookingstatus_id, bookingstatus_name) values (2, 'PAGADO');