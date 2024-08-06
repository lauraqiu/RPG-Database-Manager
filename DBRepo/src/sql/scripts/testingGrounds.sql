
-- TODO: INSERT only implemented on one table but needs to affect multiple tables,
--  ie. table must be foreign key to something else
--     satisfied by accounts page
--
-- TODO: UPDATE user should be able to update any number of non-primary key attributes in a relation, relation used
--  for the update operation must have at least 2 non primary key attributes at least 1 non primary key attribute must
--  have either a unique constraint or be a foreign key that references another table, application must present
--  the tuples available so the user can select which to update
--  SATISFIED BY ACCOUNTS PAGE

-- TODO: DELETE implement a cascade on delete situation, user should be able to choose what values to delete
--     delete operation must be implemented for at least one table to get full marks tables can be pre chosen by group
--      SATISFIED BY ACCOUNTS PAGE

-- TODO: SELECTION the user needs to specify the filtering conditions through the where clause, user should be able to
--  search for tuple using any number of ands/or clauses.
--     need to implement in characters table page, select characters where weight > 150, and height is > 150 etc... multiple options
--         satisfied by character height weight

-- TODO: PROJECTION  user is able to choose any number of attributes to choose from any relation in the database,
--  non selected attributes must not appear in the results, one or more tables must contain 4 or more attributes,
--  application must dynamically load the table from the database
--  if we were to insert a new table into the database at the start of the demo,
--  this new table must also show up as an option for the projection
--     SATISFIED BY ADMIN PAGE BY LAURA

-- TODO: JOIN create one query in this category that joins at least 2 tables and performs one useful query user must
--  provide at least one value to qualify in the where clause
--     need to implement this query to satisfy requirement
-- SELECT NAME, CLASS, EQTYPE, EQNAME  FROM EQUIPPED e, CHARACTERS c
-- WHERE c.ID = e.CID and c.CLASS = 'pirate' ;
--                                     ^ variable

-- TODO: AGGREGATION WITH GROUP BY
-- SELECT RACE,AVG (AGE)
-- FROM CHARACTERS
-- group by RACE;

-- TODO: AGGREGATION WITH HAVING   create one meaningful query that requires the use of a having clause
--     query and chosen tables should make sense given the context of the application
--
--     SELECT RACE, CLASS, AVG(LVL), AVG(STRENGTH), AVG(INTELLIGENCE), AVG(DEXTERITY), AVG(CHARISMA), AVG(LUCK) FROM CHARACTERS
--     GROUP BY RACE, CLASS HAVING AVG(STRENGTH) > 15 ;
--                                      ^variable  ^variable
-- TODO: NESTED AGGREGATION
-- STILL NEED TO FIGURE THIS OUT
--  NEED TO PERFORM AN AGGREGATE GROUP BY AND THEN AGGREGATE ON TOP OF THAT.
-- SELECT MAX(AVLVL) FROM
--                         (SELECT CLASS, AVG(LVL) AS AVLVL FROM CHARACTERS)
-- GROUP BY CLASS;

-- SELECT C.class
-- FROM Characters C
-- WHERE AVG(C.height) <= ALL( SELECT AVG(C.height)
--                             FROM Characters C2
--                             GROUP BY C.class);

-- SELECT C.class
-- FROM Characters C
-- Group by C.class
-- HAVING avg(c.height) <= ALL ( SELECT AVG(C2.height) FROM CHARACTERS C2 GROUP BY C2.class)


-- TODO: DIVISION
-- select ALL characters who have equipped all

-- SELECT ID, NAME FROM CHARACTERS C
-- WHERE NOT EXISTS --CHECK IF BELOW EXISTS FOR EACH ID IN CHARACTERS
--         ((SELECT distinct E.ITEMTYPE -- GET ALL EQ TYPES FROM EQUIPMENTS
--             FROM EQUIPMENTS E)
--                 MINUS
--                 (SELECT EQTYPE
--                 FROM EQUIPPED D
--                 WHERE  D.cid = C.ID ));

-- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet',    'TEST2', 'test', 'head');
-- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots',   'TEST2', 'test', 'foot');
-- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic',   'TEST2', 'test', 'chest');
-- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves',  'TEST2', 'test', 'hand');
-- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'TEST3' ,'test', 'foot');

-- SELECT sname
-- FROM Student S
-- WHERE NOT EXISTS -- CHECK IF THE BELOW EXISTS OR NOT FOR EACH NAME IN STUDENTS
--           ((SELECT C.name --GET ALL CLASSNAMES
--             FROM Class C)
--               EXCEPT
--               (SELECT E.cname -- EXCEPT WHERE A STUDENT HAS TAKEN A CLASS
--               FROM Enrolled E
--               WHERE E.snum=S.snum))


-- SELECT * FROM CHARACTERS WHERE HEIGHT >= 130 AND WEIGHT < 90;

-- DELETE FROM ACCOUNTS;

-- SELECT *
-- FROM ITEMS i, EQUIPMENTS e, CONSUMABLES c
-- WHERE i.NAME = e.ITEMNAME OR i.NAME = c.ITEMNAME ;
--
-- SELECT * FROM ITEMS;
