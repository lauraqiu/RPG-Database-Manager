CREATE TABLE Accounts
(
    username   VARCHAR(20) PRIMARY KEY,
    isVerified NUMBER(1, 0),
    password   VARCHAR(20)         NOT NULL,
    email      VARCHAR(320) UNIQUE NOT NULL,
    InvSlots   INT                 NOT NULL
);
-- --
CREATE TABLE Friends
(
    username   VARCHAR(20),
    friend     VARCHAR(20),
    PRIMARY KEY (username, friend),
    FOREIGN KEY (username) REFERENCES Accounts (username) ON DELETE CASCADE,
    FOREIGN KEY (friend) REFERENCES Accounts (username) ON DELETE CASCADE
);

CREATE TABLE Leaderboards
(
    location   VARCHAR(20),
    PRIMARY KEY (location)
);

CREATE TABLE Servers
(
    name     VARCHAR(20),
    location VARCHAR(20),
    capacity INT    NOT NULL,
    PRIMARY KEY (name),
    FOREIGN KEY (location) REFERENCES Leaderboards (location)
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

    PRIMARY KEY (ID, Acc_User),
    FOREIGN KEY (Acc_User) REFERENCES Accounts (username) ON DELETE CASCADE,
    FOREIGN KEY (serverName) REFERENCES Servers (name)
);
--
CREATE TABLE LeaderboardParticipation
(
    CID         VARCHAR(20) ,
    Acc_User    VARCHAR(20) NOT NULL,
    boardLoc    VARCHAR(20) ,
    fame        INT,
    PRIMARY KEY (CID, boardLoc),
    FOREIGN KEY (boardLoc) REFERENCES Leaderboards(location) ON DELETE CASCADE ,
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User) ON DELETE CASCADE
);

CREATE TABLE Items
(
    name        VARCHAR(20) ,
    type        VARCHAR(20) ,
    isKey       NUMBER(1,0) DEFAULT 0,
    description VARCHAR(100),
    PRIMARY KEY (name, type)
);

CREATE TABLE Consumables
(
    itemName    VARCHAR(20) ,
    itemType    VARCHAR(20),
    maxStack    INT,
    PRIMARY KEY (itemName, itemType),
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type) ON DELETE CASCADE
);

CREATE TABLE Equipments
(
    itemName     VARCHAR(20) ,
    itemType     VARCHAR(20) ,
    strength     INT,
    intelligence INT,
    charisma     INT,
    dexterity    INT,
    luck         INT,
    PRIMARY KEY (itemName, itemType),
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type) ON DELETE CASCADE
);

CREATE TABLE Resources
(
    itemName     VARCHAR(20) ,
    itemType     VARCHAR(20) ,
    maxStack     INT,
    PRIMARY KEY (itemName, itemType),
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type) ON DELETE CASCADE
);

CREATE TABLE Equipped
(
    EqName  VARCHAR(20) ,
    CID     VARCHAR(20) ,
    Acc_User VARCHAR(20) ,
    EqType  VARCHAR(20) ,
    PRIMARY KEY (EqName, EqType, CID, Acc_User),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User) ON DELETE CASCADE,
    FOREIGN KEY (EqName, EqType) REFERENCES Equipments (itemName, itemType) ON DELETE CASCADE,
    UNIQUE (CID, EqType)
);

CREATE TABLE Inventory
(
    CID         VARCHAR(20),
    Acc_User    VARCHAR(20),
    slotNum     INT,
    stacks      INT DEFAULT 1,
    itemName    VARCHAR(20),
    itemType    VARCHAR(20),
    PRIMARY KEY (CID, Acc_User, slotNum),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User) ON DELETE CASCADE,
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type),
    UNIQUE (slotNum)
);

CREATE TABLE SharedInventory
(
    Acc_User  VARCHAR(20),
    slotNum INT,
    stacks  INT DEFAULT 1,
    itemName    VARCHAR(20),
    itemType    VARCHAR(20),
    PRIMARY KEY (Acc_User, slotNum),
    FOREIGN KEY (Acc_User) REFERENCES Accounts (userName) ON DELETE CASCADE,
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type),
    UNIQUE (slotNum)
);
-- inserts
--
--
-- Accounts
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('admin', 1, 'admin', '123@fake.com', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('test', 1, 'test','11@fake.ca', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend1', 1, 'test','12@fake.ca', 150);
INSERT INTO ACCOUNTS (username, isVerified, password, email, InvSlots) VALUES ('friend2', 0, 'test','31@fake.ca', 150);
INSERT INTO ACCOUNTS (USERNAME, ISVERIFIED, PASSWORD, EMAIL, INVSLOTS) VALUES ('andrew', 1, 'password','andrew@gmail.com',150);

-- Friends
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('test', 'friend1');
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('test', 'friend2');
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('friend1', 'test');
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('friend2', 'test');
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('andrew', 'friend1');
INSERT INTO FRIENDS (USERNAME, FRIEND) VALUES ('friend1', 'andrew');

-- leaderboards
INSERT INTO LEADERBOARDS (LOCATION) VALUES ('US_WEST');
INSERT INTO LEADERBOARDS (LOCATION) VALUES ('US_EAST');
INSERT INTO LEADERBOARDS (LOCATION) VALUES ('KOR');
INSERT INTO LEADERBOARDS (LOCATION) VALUES ('EU');
INSERT INTO LEADERBOARDS (LOCATION) VALUES ('CHN');

-- servers
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('US_W_america_1', 'US_WEST', 10000 );
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('US_W_america_2', 'US_WEST', 10000 );
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('US_E_america_1', 'US_EAST', 10000);
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('EU_europe_1', 'EU', 5000);
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('CHN_china_1', 'CHN', 15000);
INSERT INTO SERVERS (NAME, LOCATION, CAPACITY) VALUES ('KOR_korea_1', 'KOR', 15000);

-- Characters
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('TEST1', 'test', 'US_W_america_1', 50, 'char_mage', 'mage', 20, 185, 90, 'human', 10, 7, 8, 30, 15, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('TEST2', 'test', 'US_E_america_1', 50, 'char_warrior', 'warrior', 200, 230, 60, 'elf', 50, 85, 40, 15, 20, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('TEST3', 'test', 'US_W_america_1', 50, 'char_pirate',  'pirate', 95, 90, 150, 'dwarf', 28, 20, 18, 15, 38, 30);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('TEST4', 'test', 'US_E_america_1', 50, 'char_mage',  'mage', 26, 150, 100, 'human', 89, 10, 15, 232, 54, 80);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('TEST5', 'test', 'US_W_america_1', 50, 'char_thief',  'thief', 26, 150, 100, 'human', 36, 32, 69, 15, 67, 68);

INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ADMIN1', 'admin', 'KOR_korea_1', 50, 'char_mage', 'mage', 26, 170, 85, 'human', 15, 7, 8, 48, 15, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ADMIN2', 'admin', 'KOR_korea_1', 50, 'char_warrior', 'warrior', 94, 223, 62, 'elf', 37, 74, 38, 15, 18, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ADMIN3', 'admin', 'KOR_korea_1', 50, 'char_pirate',  'pirate', 64, 84, 142, 'dwarf', 32, 34, 18, 15, 45, 34);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ADMIN4', 'admin', 'CHN_china_1', 50, 'char_mage',  'mage', 26, 150, 100, 'halfling', 74, 10, 15, 202, 54, 62);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ADMIN5', 'admin', 'CHN_china_1', 50, 'char_warrior',  'warrior', 38, 144, 86, 'human', 38, 82, 48, 15, 46, 57);

INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND11', 'friend1', 'EU_europe_1', 50, 'maggie', 'warrior', 65, 154, 79, 'human', 21, 85, 62, 10, 20, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND12', 'friend1', 'EU_europe_1', 50, 'warren', 'pirate', 200, 230, 60, 'elf', 42, 24, 56, 15, 46, 84);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND13', 'friend1', 'US_E_america_1', 50, 'pete',  'thief', 95, 90, 150, 'dwarf', 28, 20, 18, 15, 38, 75);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND14', 'friend1', 'US_E_america_1', 50, 'miran',  'mage', 26, 150, 100, 'halfling', 91, 10, 15, 248, 54, 80);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND15', 'friend1', 'EU_europe_1', 50, 'thomas',  'thief', 26, 150, 100, 'human', 41, 32, 69, 15, 67, 87);

INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND21', 'friend2', 'CHN_china_1', 50, 'jerry', 'mage', 66, 165, 78, 'human', 15, 7, 8, 32, 15, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND22', 'friend2', 'CHN_china_1', 50, 'morty', 'mage', 154, 240, 55, 'elf', 34, 15, 12, 72, 20, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND23', 'friend2', 'CHN_china_1', 50, 'rick',  'mage', 50, 89, 135, 'dwarf', 56, 20, 25, 124, 67, 30);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND24', 'friend2', 'CHN_china_1', 50, 'summer',  'mage', 22, 130, 75, 'halfling', 72, 10, 12, 268, 54, 80);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('FRIEND25', 'friend2', 'CHN_china_1', 50, 'beth',  'mage', 36, 125, 91, 'human', 105, 32, 42, 342, 102,62);


INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ANDREW1', 'andrew', 'US_W_america_1', 50, 'bob', 'pirate', 18, 192, 88, 'human', 16, 9, 8, 8, 37, 18);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ANDREW2', 'andrew', 'US_W_america_1', 50, 'linda', 'warrior', 134, 222, 62, 'elf', 34, 85, 40, 15, 20, 15);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ANDREW3', 'andrew', 'US_W_america_1', 50, 'louis',  'pirate', 81, 81, 181, 'dwarf', 75, 20, 34, 15, 154, 64);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ANDREW4', 'andrew', 'US_W_america_1', 50, 'gene', 'pirate', 68, 132, 34, 'halfling', 94, 10, 84, 16, 241, 80);
INSERT INTO CHARACTERS (ID, Acc_User, SERVERNAME, InvSlots, name, class, age, height, weight, race, LVL, STRENGTH, DEXTERITY, INTELLIGENCE, CHARISMA, LUCK) VALUES
    ('ANDREW5', 'andrew', 'US_W_america_1', 50, 'tina', 'thief', 22, 162, 94, 'human', 109, 32, 89, 15, 302 ,68);


-- participation
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST1', 'test', 'US_WEST', 2);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST2', 'test', 'US_EAST', 70);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST3', 'test', 'US_WEST', 30);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST4', 'test', 'US_EAST', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST4', 'test', 'US_WEST', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('TEST5', 'test', 'US_WEST', 64);


INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ADMIN1', 'admin', 'KOR', 2);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ADMIN2', 'admin', 'KOR', 70);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ADMIN3', 'admin', 'KOR', 30);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ADMIN4', 'admin', 'CHN', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ADMIN5', 'admin', 'CHN', 64);

INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND11', 'friend1', 'EU', 2);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND12', 'friend1', 'EU', 70);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND13', 'friend1', 'US_EAST', 30);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND14', 'friend1', 'US_EAST', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND15', 'friend1', 'EU', 64);

INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND21', 'friend2', 'CHN', 2);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND22', 'friend2', 'CHN', 70);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND23', 'friend2', 'CHN', 30);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND24', 'friend2', 'CHN', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('FRIEND25', 'friend2', 'CHN', 64);

INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ANDREW1', 'andrew', 'US_WEST', 2);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ANDREW2', 'andrew', 'US_WEST', 70);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ANDREW3', 'andrew', 'US_WEST', 30);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ANDREW4', 'andrew', 'US_WEST', 150);
INSERT INTO LEADERBOARDPARTICIPATION (CID, ACC_USER, BOARDLOC, FAME) VALUES ('ANDREW5', 'andrew', 'US_WEST', 64);

-- Consumables
INSERT INTO ITEMS (name, type, description) VALUES ('health potion (sm)', 'potion', 'a potion that restores a small amount of health.');
INSERT INTO CONSUMABLES (itemName, itemType, maxStack) VALUES ('health potion (sm)', 'potion', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('stamina potion (sm)', 'potion', 'a potion that restores a small amount of stamina.');
INSERT INTO CONSUMABLES (itemName, itemType ,maxStack) VALUES ('stamina potion (sm)', 'potion', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('mana potion (sm)', 'potion', 'a potion that restores a small amount of mana.');
INSERT INTO CONSUMABLES (itemName, itemType ,maxStack) VALUES ('mana potion (sm)', 'potion', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('beefy jerky', 'food', 'a small piece of beef jerky, restores a small amount of health and stamina');
INSERT INTO CONSUMABLES (itemName, itemType ,maxStack) VALUES ('beefy jerky', 'food', 50);
INSERT INTO ITEMS (name, type, description) VALUES ('potion of swiftness', 'potion', 'a magical potion which imbues the user with tremendous speed');
INSERT INTO CONSUMABLES (itemName, itemType ,maxStack) VALUES ('potion of swiftness', 'potion', 10);
--
-- Resources
INSERT INTO ITEMS (name, type, description) VALUES ('wood', 'raw_material', 'a stack of wood, used for crafting.');
INSERT INTO RESOURCES (itemName, itemType ,maxStack) VALUES ('wood', 'raw_material', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('stone','raw_material', 'a stack of stone material used for crafting.');
INSERT INTO RESOURCES (itemName, itemType ,maxStack) VALUES ('stone', 'raw_material', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('iron ore', 'raw_ore', 'raw pieces of iron excavated from the mountains, can be smelted into iron bars.');
INSERT INTO RESOURCES (itemName, itemType ,maxStack) VALUES ('iron ore', 'raw_ore', 99);
INSERT INTO ITEMS (name, type, description) VALUES ('iron bars', 'raw_metal', 'ingots made of solid iron, can be used to craft equipment.');
INSERT INTO RESOURCES (itemName, itemType ,maxStack) VALUES ('iron bars', 'raw_metal', 30);
INSERT INTO ITEMS (NAME, type , description, isKey) VALUES ('string', 'raw_material', 'string made from various fibers, useful for crafting clothes.', 1) ;
INSERT INTO RESOURCES (itemName, itemType ,maxStack) VALUES ('string', 'raw_material', 99);
--
-- Equipment
INSERT INTO ITEMS (name, type, description) VALUES ('basic helmet', 'head', 'A basic helmet worn by beginner warriors.');
INSERT INTO EQUIPMENTS (itemName, itemType, strength, intelligence, charisma, dexterity, luck) VALUES ('basic helmet', 'head', 5,0,0,0,0);
INSERT INTO ITEMS (name, type, description) VALUES ('apprentices hat', 'head', 'The worn hat of a wizards apprentice.');
INSERT INTO EQUIPMENTS (itemName, itemType, strength, intelligence, charisma, dexterity, luck) VALUES ('apprentices hat', 'head', 0,5,0,0,0);
INSERT INTO ITEMS (name, type, description) VALUES ('leather tunic', 'chest', 'simple leather tunic, worthy of a beginner adventurer!');
INSERT INTO EQUIPMENTS (ITEMNAME, itemType, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather tunic', 'chest', 1,1,1,1,1);
INSERT INTO ITEMS (name, type, description) VALUES ('leather gloves', 'hand', 'simple leather gloves, perfect for a beginner adventurer!');
INSERT INTO EQUIPMENTS (ITEMNAME, itemType, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather gloves', 'hand', 1,1,1,1,1);
INSERT INTO ITEMS (name, type, description) VALUES ('leather boots', 'foot', 'simple leather boots, ideal for a beginner adventurer.');
INSERT INTO EQUIPMENTS (ITEMNAME, itemType, STRENGTH, INTELLIGENCE, CHARISMA, DEXTERITY, LUCK) VALUES ('leather boots', 'foot', 1,1,1,1,1);
--

-- inventory
INSERT INTO INVENTORY (CID, Acc_User, slotNum, itemName, ITEMTYPE) VALUES ('TEST1', 'test', 1, 'basic helmet', 'head' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('TEST1', 'test', 2, 'health potion (sm)', 'potion');
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('TEST1', 'test', 3, 'wood', 'raw_material' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('TEST1', 'test', 4, 'leather boots', 'foot' );
INSERT INTO INVENTORY (CID, ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('TEST1', 'test', 5, 'leather boots', 'foot' );

-- sharedinventory

INSERT INTO SHAREDINVENTORY (Acc_User, slotNum, itemName, ITEMTYPE) VALUES ('test', 1, 'stone', 'raw_material' );
INSERT INTO SHAREDINVENTORY (ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('test', 2, 'health potion (sm)', 'potion');
INSERT INTO SHAREDINVENTORY (ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('test', 3, 'wood', 'raw_material' );
INSERT INTO SHAREDINVENTORY (ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('test', 4, 'iron ore', 'raw_ore' );
INSERT INTO SHAREDINVENTORY (ACC_USER, SLOTNUM, ITEMNAME, ITEMTYPE) VALUES ('test', 5, 'leather boots', 'foot' );
--

-- equipped
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet', 'TEST1', 'test', 'head');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'TEST1', 'test', 'foot');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic', 'TEST1', 'test', 'chest');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves', 'TEST1', 'test', 'hand');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'ANDREW5' ,'andrew', 'foot');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet', 'ANDREW1', 'andrew', 'head');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'ANDREW1', 'andrew', 'foot');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic', 'ANDREW1', 'andrew', 'chest');
INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves', 'ANDREW1', 'andrew', 'hand');

-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet', 'TEST2', 'test', 'head');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'TEST2', 'test', 'foot');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic', 'TEST2', 'test', 'chest');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves', 'TEST2', 'test', 'hand');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'ADMIN1' ,'admin', 'foot');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('basic helmet', 'ADMIN1', 'admin', 'head');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather boots', 'ADMIN2', 'admin', 'foot');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather tunic', 'ADMIN2', 'admin', 'chest');
-- -- INSERT INTO EQUIPPED (EQNAME, CID, ACC_USER, EQTYPE) VALUES ('leather gloves', 'ANDREW1', 'admin', 'hand');
-- -- -- --