
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
-- WHERE c.ID = e.CID and c.CLASS = 'mage' ;

-- TODO: AGGREGATION WITH GROUP BY
-- SELECT RACE,AVG (WEIGHT)
-- FROM CHARACTERS
-- group by RACE;

-- TODO: AGGREGATION WITH HAVING   create one meaningful query that requires the use of a having clause
--     query and chosen tables should make sense given the context of the application
--
--     SELECT RACE, AVG(WEIGHT) FROM CHARACTERS
--                              GROUP BY RACE HAVING COUNT(*)>1

-- TODO: NESTED AGGREGATION
--     SELECT RACE, CLASS, AVG(AGE) FROM CHARACTERS
--     GROUP BY RACE, CLASS
--     HAVING ;


-- TODO: DIVISION
-- SELECT NAME
-- FROM CHARACTERS C
-- WHERE NOT EXISTS(
--     ()
--     EXCEPT
--     (SELECT E.EQTYPE
--               FROM EQUIPPED E))
SELECT EQNAME
FROM EQUIPPED E;


-- SELECT sname
-- FROM Student S
-- WHERE NOT EXISTS
--           ((SELECT C.name
--             FROM Class C)
--               EXCEPT
--               (SELECT E.cname
--               FROM Enrolled E
--               WHERE E.snum=S.snum))


-- SELECT * FROM CHARACTERS WHERE HEIGHT >= 130 AND WEIGHT < 90;

-- DELETE FROM ACCOUNTS;

-- SELECT *
-- FROM ITEMS i, EQUIPMENTS e, CONSUMABLES c
-- WHERE i.NAME = e.ITEMNAME OR i.NAME = c.ITEMNAME ;
--
-- SELECT * FROM ITEMS;
