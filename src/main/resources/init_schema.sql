---------- Create SCHEMA
CREATE SCHEMA "api";

---------- Create TABLE
CREATE TABLE IF NOT EXISTS "api"."solutions"(
    "id" SERIAL NOT NULL,
    "a" SMALLINT NOT NULL DEFAULT 0,
    "b" SMALLINT NOT NULL DEFAULT 0,
    "c" SMALLINT NOT NULL DEFAULT 0,
    "d" SMALLINT NOT NULL DEFAULT 0,
    "e" SMALLINT NOT NULL DEFAULT 0,
    "f" SMALLINT NOT NULL DEFAULT 0,
    "g" SMALLINT NOT NULL DEFAULT 0,
    "h" SMALLINT NOT NULL DEFAULT 0,
    "i" SMALLINT NOT NULL DEFAULT 0,
    "is_valid" BOOLEAN NOT NULL,
    "creation_date" TIMESTAMPTZ DEFAULT NOW(),
    "modification_date" TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT pk__solutions__id PRIMARY KEY (id),
    CONSTRAINT unique__solutions__solution UNIQUE (a, b, c, d, e, f, g, h, i)
);