USE `k-pac`;

DELETE FROM k_pac_sets_k_pacs;
DELETE FROM k_pacs;
DELETE FROM k_pac_sets;

INSERT INTO k_pacs (title, description, creation_date)
VALUES('K-PAC 1', 'Knowledge Package number one', CURRENT_TIMESTAMP - INTERVAL 2 DAY);

INSERT INTO k_pacs (title, description, creation_date)
VALUES('K-PAC 2', 'Knowledge Package number two', CURRENT_TIMESTAMP - INTERVAL 1 DAY);

INSERT INTO k_pacs (title, description, creation_date)
VALUES('K-PAC 3', 'Knowledge Package number three', CURRENT_TIMESTAMP);

INSERT INTO k_pac_sets(title) VALUES('Basic K-PAC set');
INSERT INTO k_pac_sets(title) VALUES('Pro K-PAC set');
INSERT INTO k_pac_sets(title) VALUES('Premium K-PAC set');

INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Basic K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 1' LIMIT 1));

INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Pro K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 1' LIMIT 1));
INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Pro K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 2' LIMIT 1));

INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Premium K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 1' LIMIT 1));
INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Premium K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 2' LIMIT 1));
INSERT INTO k_pac_sets_k_pacs (k_pac_set_id, k_pac_id)
VALUES((SELECT id FROM k_pac_sets WHERE title = 'Premium K-PAC set' LIMIT 1),
       (SELECT id FROM k_pacs WHERE title = 'K-PAC 3' LIMIT 1));
