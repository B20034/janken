CREATE TABLE users (
    id IDENTITY,
    userName VARCHAR NOT NULL,
);

CREATE TABLE matches(
    id IDENTITY,
    user1 INTEGER NOT NULL,
    user2 INTEGER NOT NULL,
    user1Hand VARCHAR NOT NULL,
    user2Hand VARCHAR NOT NULL,
)
