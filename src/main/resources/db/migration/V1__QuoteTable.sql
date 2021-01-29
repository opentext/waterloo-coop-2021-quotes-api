CREATE TABLE Quote(
    id UUID NOT NULL PRIMARY KEY,
    text VARCHAR(100) NOT NULL,
    date VARCHAR(100) NOT NULL,
    likes INT NOT NULL,
    dislikes INT NOT NULL,
    reactionId UUID NOT NULL
);
CREATE TABLE Reaction(
    likes BOOLEAN,
    date VARCHAR(100),
    address VARCHAR(100),
    Id UUID NOT NULL
);