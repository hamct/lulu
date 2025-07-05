CREATE TABLE licks (
    user_id TEXT PRIMARY KEY,
    licked_count INT NOT NULL DEFAULT 0
);

CREATE TABLE dabloon_accounts (
   user_id TEXT PRIMARY KEY,
   balance BIGINT NOT NULL DEFAULT 0 CHECK ( balance >= 0 )
);
