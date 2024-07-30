CREATE TABLE Accounts
(
    username   VARCHAR(20) PRIMARY KEY,
    isVerified NUMBER(1, 0),
    password   VARCHAR(20)         NOT NULL,
    email      VARCHAR(320) UNIQUE NOT NULL,
    InvSlots   INT                 NOT NULL
);

CREATE TABLE Friends
(
    username   VARCHAR(20) PRIMARY KEY,
    friend     VARCHAR(20),
    FOREIGN KEY (username) REFERENCES Accounts (username) ON DELETE CASCADE,
    FOREIGN KEY (friend) REFERENCES Accounts (username) ON DELETE CASCADE
);

CREATE TABLE Servers
(
    name     VARCHAR(20),
    location VARCHAR(20),
    capacity INT,
    PRIMARY KEY (name, location)
);

CREATE TABLE Characters
(
    ID                  VARCHAR(20),
    Acc_User            VARCHAR(20),
    name                VARCHAR(20)   NOT NULL,

    class               VARCHAR(20)   NOT NULL,
    age                 INT           NOT NULL,
    height              INT           NOT NULL,
    weight              INT           NOT NULL,
    race                VARCHAR(20)   NOT NULL,

    lvl                 INT DEFAULT 1 NOT NULL,
    money               INT DEFAULT 0 NOT NULL,
    strength            INT DEFAULT 1 NOT NULL,
    intelligence        INT DEFAULT 1 NOT NULL,
    charisma            INT DEFAULT 1 NOT NULL,
    dexterity           INT DEFAULT 1 NOT NULL,
    luck                INT DEFAULT 1 NOT NULL,

    InvSlots            INT DEFAULT 50          NOT NULL,
    serverName          VARCHAR(20),
    serverLocation      VARCHAR(20),

    PRIMARY KEY (ID, Acc_User),
    FOREIGN KEY (Acc_User) REFERENCES Accounts (username) ON DELETE CASCADE,
    FOREIGN KEY (serverName, serverLocation) REFERENCES Servers (name, location)
);

CREATE TABLE GlobalLeaderboard
(
    id        VARCHAR(20),
    Fame      INT,
    CID       VARCHAR(20),
    Acc_User  VARCHAR(20),

    PRIMARY KEY (id),
    FOREIGN KEY (CID, Acc_User) REFERENCES characters (ID, Acc_User) ON DELETE CASCADE
);

CREATE TABLE LocalLeaderboard
(
    id              VARCHAR(20),
    Fame            INT,
    CID             VARCHAR(20),
    Acc_User        VARCHAR(20),
    serverLocation  VARCHAR(20),
    serverName      VARCHAR(20),

    PRIMARY KEY (id),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, ACC_USER) ON DELETE CASCADE,
    FOREIGN KEY (serverLocation,serverName) REFERENCES Servers (location, name) ON DELETE CASCADE
);


CREATE TABLE Items
(
    name        VARCHAR(20) PRIMARY KEY,
    isKey       NUMBER(1,0) DEFAULT 0,
    description VARCHAR(100)
);

CREATE TABLE Consumables
(
    itemName    VARCHAR(20) PRIMARY KEY,
    maxStack    INT,
    FOREIGN KEY (itemName) REFERENCES Items (name) ON DELETE CASCADE
);

CREATE TABLE Equipments
(
    itemName     VARCHAR(20) ,
    type         VARCHAR(20) NOT NULL,
    strength     INT,
    intelligence INT,
    charisma     INT,
    dexterity    INT,
    luck         INT,
    PRIMARY KEY (itemName, type),
    FOREIGN KEY (itemName) REFERENCES Items (name) ON DELETE CASCADE
);

CREATE TABLE Resources
(
    itemName     VARCHAR(20) PRIMARY KEY,
    maxStack     INT,
    FOREIGN KEY (itemName) REFERENCES Items (name) ON DELETE CASCADE
);

CREATE TABLE Equipped
(
    EqName  VARCHAR(20),
    CID     VARCHAR(20),
    Acc_User VARCHAR(20),
    EqType  VARCHAR(20),
    PRIMARY KEY (EqName, CID, Acc_User),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User) ON DELETE CASCADE,
    FOREIGN KEY (EqName, EqType) REFERENCES Equipments (itemName, type) ON DELETE CASCADE,
    UNIQUE (CID, EqType)
);

CREATE TABLE Inventory
(
    CID         VARCHAR(20),
    Acc_User    VARCHAR(20),
    slotNum     INT,
    stacks      INT DEFAULT 1,
    itemName    VARCHAR(20),
    PRIMARY KEY (CID, Acc_User, slotNum),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User) ON DELETE CASCADE,
    FOREIGN KEY (itemName) REFERENCES Items (name),
    UNIQUE (slotNum)
);

CREATE TABLE SharedInventory
(
    userID  VARCHAR(20),
    slotNum INT,
    stacks  INT DEFAULT 1,
    item    VARCHAR(20),
    PRIMARY KEY (userID, slotNum),
    FOREIGN KEY (userID) REFERENCES Accounts (userName) ON DELETE CASCADE,
    FOREIGN KEY (item) REFERENCES Items (name),
    UNIQUE (slotNum)
);



INSERT ALL
    INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('admin', 1, 'admin', '123@fake.com', 150)
    INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('test', 1, 'test','11@fake.ca', 150)
    INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend1', 1, 'test','12@fake.ca', 150)
    INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend2', 1, 'test','31@fake.ca', 150)

--     INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
--                     ('TEST1', 'test', 'char1', 'mage', 45, 130, 120, 'human', 50)
--     INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
--                     ('TEST2', 'test', 'char2', 'warrior', 45, 130, 120, 'elf', 50)
--     INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
--                     ('TEST3', 'test', 'char1', 'pirate', 45, 130, 120, 'dwarf', 50)
--     INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
--                     ('TEST4', 'test', 'char1', 'mage', 45, 130, 120, 'human', 50)
--     INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
--                     ('TEST5', 'test', 'char1', 'mage', 45, 130, 120, 'halfling', 50)
--     INTO ITEMS (name, description) VALUES ('health potion (sm)', 'a potion that restores a small amount of health.')
--     INTO CONSUMABLES (itemName, maxStack) VALUES ('health potion (sm)', 99)
--     INTO ITEMS (name, description) VALUES ('stamina potion (sm)', 'a potion that restores a small amount of stamina.')
--     INTO CONSUMABLES (itemName, maxStack) VALUES ('stamina potion (sm)', 99)
--     INTO ITEMS (name, description) VALUES ('mana potion (sm)', 'a potion that restores a small amount of mana.')
--     INTO CONSUMABLES (itemName, maxStack) VALUES ('mana potion(sm)', 99)
--
--     INTO ITEMS (name, description) VALUES ('basic helmet', 'A basic helmet worn by beginner warriors.')
--     INTO EQUIPMENTS (itemName, type, strength, intelligence, charisma, dexterity, luck) VALUES ('basic helmet', 'helmet', 5,0,0,0,0)
--     INTO ITEMS (name, description) VALUES ('apprentices hat', 'The worn hat of a wizards apprentice.')
--     INTO EQUIPMENTS (itemName, type, strength, intelligence, charisma, dexterity, luck) VALUES ('apprentices hat', 'helmet', 0,5,0,0,0)
--
--     INTO ITEMS (name, description) VALUES ('wood', 'a stack of wood, used for crafting.')
--     INTO RESOURCES (itemName, maxStack) VALUES ('wood', 99)
--     INTO ITEMS (name, description) VALUES ('stone', 'a stack of stone material used for crafting.')
--     INTO RESOURCES (itemName, maxStack) VALUES ('stone', 99)
--
--     INTO INVENTORY (CID, Acc_User, slotNum, itemName) VALUES ('TEST1', 'test', 1, 'basic helmet' )
--
 SELECT 1 from DUAL;

