-- h2 실행시 테스트용 특강 데이터 삽입


INSERT INTO LECTURE (LECTURE_ID, TITLE, INSTRUCTOR) VALUES (1, '과제발제', '허재');
INSERT INTO LECTURE (LECTURE_ID, TITLE, INSTRUCTOR) VALUES (2, '멘토링', '로이') ;

INSERT INTO LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (1, 1, '2024-09-28', 30);
INSERT INTO LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (2, 1, '2024-10-02', 30);
INSERT INTO LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (3, 2, '2024-10-02', 30);
INSERT INTO LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (4, 2, '2024-10-03', 30);
INSERT INTO LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (5, 2, '2024-10-04', 30);

INSERT INTO LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (1, 1, 1, 30);
INSERT INTO LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (2, 2, 1, 30);
INSERT INTO LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (3, 3, 2, 30);
INSERT INTO LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (4, 4, 2, 30);
INSERT INTO LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (5, 5, 2, 30);

-- INSERT
-- INTO
--     LECTURE (LECTURE_ID, TITLE, INSTRUCTOR)
-- VALUES
--     (1, '과제발제', '허재'),
--     (2, '멘토링', '로이') ;
-- INSERT
-- INTO
--     LECTURE_ITEM (ITEM_ID, LECTURE_ID, LECTURE_DATE, CAPACITY)
-- VALUES
--     (1, 1, '2024-09-28', 30),
--     (2, 1, '2024-10-02', 30),
--     (3, 2, '2024-10-02', 30),
--     (4, 2, '2024-10-03', 30),
--     (5, 2, '2024-10-04', 30);
--
-- INSERT
-- INTO
--     LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT)
-- VALUES
--     (1, 1, 1, 30),
--     (2, 2, 1, 30),
--     (3, 3, 2, 30),
--     (4, 4, 2, 30),
--     (5, 5, 2, 30);