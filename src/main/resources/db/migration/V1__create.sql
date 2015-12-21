create table unique_number (
  id BIGINT IDENTITY,
  number VARCHAR(5),
  qty INTEGER NOT NULL,

  UNIQUE (number),
  CHECK (CHARACTER_LENGTH(number) = 5)

);
