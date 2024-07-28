CREATE TABLE Accounts
(
    username   VARCHAR(20) PRIMARY KEY,
    isVerified NUMBER(1, 0),
    password   VARCHAR(20)         NOT NULL,
    email      VARCHAR(320) UNIQUE NOT NULL,
    InvSlots   INT                 NOT NULL
);

-- CREATE TABLE Friends
-- (
--     user   VARCHAR(20) PRIMARY KEY,
--     friend VARCHAR(20),
--     FOREIGN KEY (user) REFERENCES Accounts (username) ON DELETE CASCADE,
--     FOREIGN KEY (friend) REFERENCES Accounts (username) ON DELETE CASCADE
-- );
--
CREATE TABLE Characters
(
    ID           VARCHAR(20),
    AccName      VARCHAR(20),
    name         VARCHAR(20)   NOT NULL,
    class        VARCHAR(20)   NOT NULL,
    age          INT           NOT NULL,
    height       INT           NOT NULL,
    weight       INT           NOT NULL,
    race         VARCHAR(20)   NOT NULL,
    lvl        INT DEFAULT 1 NOT NULL,
    money        INT DEFAULT 0 NOT NULL,
    strength     INT DEFAULT 1 NOT NULL,
    intelligence INT DEFAULT 1 NOT NULL,
    charisma     INT DEFAULT 1 NOT NULL,
    dexterity    INT DEFAULT 1 NOT NULL,
    luck         INT DEFAULT 1 NOT NULL,
    InvSlots     INT           NOT NULL,
    PRIMARY KEY (ID, AccName),
    FOREIGN KEY (AccName) REFERENCES Accounts (username) ON DELETE CASCADE
);

-- CREATE TABLE Equipped
-- (
--     EqName  VARCHAR(20),
--     CID     VARCHAR(20),
--     AccName VARCHAR(20),
--     EqType  VARCHAR(20),
--     PRIMARY KEY (EqName, CID, AccName),
--     FOREIGN KEY (CID, AccName) REFERENCES Characters (ID, AccName) ON DELETE CASCADE,
--     FOREIGN KEY (EqName, EqType) REFERENCES Equipments (name, type) ON DELETE CASCADE,
--     UNIQUE (CID, EqType)
-- );
--
-- CREATE TABLE Inventory
-- (
--     CID     VARCHAR(20),
--     AccName VARCHAR(20),
--     slotNum INT(3),
--     stacks  INT(4) DEFAULT 1,
--     item    VARCHAR(20),
--     PRIMARY KEY (CID, AccName, slotNum),
--     FOREIGN KEY (CID, AccName) REFERENCES Characters (ID, AccName) ON DELETE CASCADE,
--     FOREIGN KEY (item) REFERENCES Items (name)
-- );
--
-- CREATE TABLE SharedInventory
-- (
--     userID  VARCHAR(20),
--     slotNum INT(3),
--     stacks  INT(4) DEFAULT 1,
--     item    VARCHAR(20),
--     PRIMARY KEY (userID, slotNum),
--     FOREIGN KEY (userID) REFERENCES Accounts (userName) ON DELETE CASCADE,
--     FOREIGN KEY (item) REFERENCES Items (name)
-- );
--
-- CREATE TABLE Items
-- (
--     name        VARCHAR(20) PRIMARY KEY,
--     isKey       BOOLEAN DEFAULT 0,
--     description VARCHAR(100)
-- );
--
-- CREATE TABLE Consumables
-- (
--     name     VARCHAR(20) PRIMARY KEY,
--     maxStack INT(4),
--     FOREIGN KEY (name) REFERENCES Items (name) ON DELETE CASCADE
-- );
--
-- CREATE TABLE Equipments
-- (
--     name         VARCHAR(20) PRIMARY KEY,
--     type         VARCHAR(20) NOT NULL,
--     strength     INT(5),
--     intelligence INT(5),
--     charisma     INT(5),
--     dexterity    INT(5),
--     luck         INT(5),
--     FOREIGN KEY (name) REFERENCES Items (name) ON DELETE CASCADE
-- );
--
-- CREATE TABLE Resources
-- (
--     name     VARCHAR(20) PRIMARY KEY,
--     maxStack INT(4),
--     FOREIGN KEY (name) REFERENCES Items (name) ON DELETE CASCADE
-- );
--
-- CREATE TABLE GlobalLeaderboard
-- (
--     totalFame INT(15),
--     rank      int(10) UNIQUE,
--     CID       VARCHAR(20),
--     AccName   VARCHAR(20),
--     name      VARCHAR(20),
--     PRIMARY KEY (CID, AccName),
--     FOREIGN KEY (CID, AccName, name) REFERENCES characters (ID, AccName, name) ON DELETE CASCADE
-- );
--
-- CREATE TABLE LocalLeaderboard
-- (
--     rank     INT(10),
--     ID       VARCHAR(20),
--     Fame     INT(15),
--     CID      VARCHAR(20),
--     AccName  VARCHAR(20),
--     location VARCHAR(20),
--     PRIMARY KEY (ID, rank),
--     FOREIGN KEY (CID, AccName) REFERENCES Characters (ID, AccName) ON DELETE CASCADE,
--     FOREIGN KEY (location) REFERENCES Server (location) ON DELETE CASCADE
-- );
--
-- CREATE TABLE Server
-- (
--     name     VARCHAR(20),
--     location VARCHAR(20),
--     capacity INT(6),
--     PRIMARY KEY (name, location)
-- );
