ALTER TABLE books
    ADD COLUMN availability boolean DEFAULT TRUE;
ALTER TABLE books
    ADD COLUMN description text DEFAULT NULL;
ALTER TABLE books
    ADD COLUMN ratings integer DEFAULT 0;
ALTER TABLE books
    ADD COLUMN reviews text[] DEFAULT NULL;