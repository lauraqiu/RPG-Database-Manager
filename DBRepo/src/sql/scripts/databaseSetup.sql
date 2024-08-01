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
    username   VARCHAR(20) PRIMARY KEY,
    friend     VARCHAR(20),
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
    CID         VARCHAR(20),
    Acc_User    VARCHAR(20),
    boardLoc    VARCHAR(20),
    fame        INT,
    PRIMARY KEY (CID, boardLoc),
    FOREIGN KEY (boardLoc) REFERENCES Leaderboards(location),
    FOREIGN KEY (CID, Acc_User) REFERENCES Characters (ID, Acc_User)
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
    EqName  VARCHAR(20),
    CID     VARCHAR(20),
    Acc_User VARCHAR(20),
    EqType  VARCHAR(20),
    PRIMARY KEY (EqName, CID, Acc_User),
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
    userID  VARCHAR(20),
    slotNum INT,
    stacks  INT DEFAULT 1,
    itemName    VARCHAR(20),
    itemType    VARCHAR(20),
    PRIMARY KEY (userID, slotNum),
    FOREIGN KEY (userID) REFERENCES Accounts (userName) ON DELETE CASCADE,
    FOREIGN KEY (itemName, itemType) REFERENCES Items (name, type),
    UNIQUE (slotNum)
);
-- inserts
