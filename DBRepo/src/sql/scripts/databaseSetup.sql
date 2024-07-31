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
-- inserts

-- Accounts
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('admin', 1, 'admin', '123@fake.com', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('test', 1, 'test','11@fake.ca', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend1', 1, 'test','12@fake.ca', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend2', 0, 'test','31@fake.ca', 150);
INSERT INTO ACCOUNTS (USERNAME, ISVERIFIED, PASSWORD, EMAIL, INVSLOTS) VALUES ('andrew', 1, 'password','andrew@gmail.com',150);

-- Characters
INSERT INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
    ('TEST1', 'test', 'char1', 'mage', 20, 185, 90, 'human', 50);
INSERT INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
    ('TEST2', 'test', 'char2', 'warrior', 200, 230, 60, 'elf', 50);
INSERT INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
    ('TEST3', 'test', 'char1', 'pirate', 95, 90, 150, 'dwarf', 50);
INSERT INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
    ('TEST4', 'test', 'char1', 'mage', 26, 150, 100, 'human', 50);
INSERT INTO CHARACTERS (ID, Acc_User, name, class, age, height, weight, race, InvSlots) VALUES
    ('char1', 'andrew', 'charlie', 'pirate', 45, 75, 60, 'halfling', 50);

-- DELETE FROM ITEMS;

-- Consumables
INSERT INTO ITEMS (name, description) VALUES ('health potion (sm)', 'a potion that restores a small amount of health.');
INSERT INTO CONSUMABLES (itemName, maxStack) VALUES ('health potion (sm)', 99);
INSERT INTO ITEMS (name, description) VALUES ('stamina potion (sm)', 'a potion that restores a small amount of stamina.');
INSERT INTO CONSUMABLES (itemName, maxStack) VALUES ('stamina potion (sm)', 99);
INSERT INTO ITEMS (name, description) VALUES ('mana potion (sm)', 'a potion that restores a small amount of mana.');
INSERT INTO CONSUMABLES (itemName, maxStack) VALUES ('mana potion (sm)', 99);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('beefy jerky', 'a small piece of beef jerky, restores a small amount of health and stamina');
INSERT INTO CONSUMABLES (ITEMNAME, MAXSTACK) VALUES ('beefy jerky', 50);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('potion of swiftness', 'a magical potion which imbues the user with tremendous speed');
INSERT INTO CONSUMABLES (ITEMNAME, MAXSTACK) VALUES ('potion of swiftness', 10);

-- Resources
INSERT INTO ITEMS (name, description) VALUES ('wood', 'a stack of wood, used for crafting.');
INSERT INTO RESOURCES (itemName, maxStack) VALUES ('wood', 99);
INSERT INTO ITEMS (name, description) VALUES ('stone', 'a stack of stone material used for crafting.');
INSERT INTO RESOURCES (itemName, maxStack) VALUES ('stone', 99);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('iron ore', 'raw pieces of iron excavated from the mountains, can be smelted into iron bars.');
INSERT INTO RESOURCES (ITEMNAME, MAXSTACK) VALUES ('iron ore', 99);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('iron bars', 'ingots made of solid iron, can be used to craft equipment.');
INSERT INTO RESOURCES (ITEMNAME, MAXSTACK) VALUES ('iron bars', 30);
INSERT INTO ITEMS (NAME, DESCRIPTION, ISKEY) VALUES ('string', 'string made from various fibers, useful for crafting clothes.', 1) ;
INSERT INTO RESOURCES (ITEMNAME, MAXSTACK) VALUES ('string', 99);

-- Equipment
INSERT INTO ITEMS (name, description) VALUES ('basic helmet', 'A basic helmet worn by beginner warriors.');
INSERT INTO EQUIPMENTS (itemName, type, strength, intelligence, charisma, dexterity, luck) VALUES ('basic helmet', 'head', 5,0,0,0,0);
INSERT INTO ITEMS (name, description) VALUES ('apprentices hat', 'The worn hat of a wizards apprentice.');
INSERT INTO EQUIPMENTS (itemName, type, strength, intelligence, charisma, dexterity, luck) VALUES ('apprentices hat', 'head', 0,5,0,0,0);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('leather tunic', 'simple leather tunic, worthy of a beginner adventurer!');
INSERT INTO EQUIPMENTS (ITEMNAME, TYPE, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather tunic', 'chest', 1,1,1,1,1);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('leather gloves', 'simple leather gloves, perfect for a beginner adventurer!');
INSERT INTO EQUIPMENTS (ITEMNAME, TYPE, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather gloves', 'hand', 1,1,1,1,1);
INSERT INTO ITEMS (NAME, DESCRIPTION) VALUES ('leather boots', 'simple leather boots, ideal for a beginner adventurer.');
INSERT INTO EQUIPMENTS (ITEMNAME, TYPE, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather boots', 'foot', 1,1,1,1,1);

-- inventory
INSERT INTO INVENTORY (CID, Acc_User, slotNum, itemName) VALUES ('TEST1', 'test', 1, 'basic helmet' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME) VALUES ('TEST1', 'test', 2, 'health potion (sm)');
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME) VALUES ('TEST1', 'test', 3, 'wood' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME) VALUES ('TEST1', 'test', 4, 'leather boots' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME) VALUES ('TEST1', 'test', 5, 'leather boots' );

-- equipped
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet', 'TEST1', 'test', 'head');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'TEST1', 'test', 'foot');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic', 'TEST1', 'test', 'chest');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves', 'TEST1', 'test', 'hand');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'char1' ,'andrew', 'foot');

-- servers

INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('america_1', 'US_WEST', 10000 );
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('america_2', 'US_WEST', 10000 );
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('america_1', 'US_EAST', 10000);
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('europe_1', 'EU', 5000);
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('china_1', 'CHN', 15000);

