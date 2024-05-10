---------- Create SCHEMA
CREATE SCHEMA "api";

---------- Create TABLE
CREATE TABLE IF NOT EXISTS "api"."solutions"(
    "id" SERIAL NOT NULL,
    "solution" VARCHAR(20) NOT NULL,
    "is_valid" BOOLEAN NOT NULL,
    "creation_date" TIMESTAMPTZ DEFAULT NOW(),
    "modification_date" TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT pk__solutions__id PRIMARY KEY (id),
    CONSTRAINT unique__solutions__solution UNIQUE (solution)
);