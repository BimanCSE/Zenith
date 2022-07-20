ALTER TABLE IF EXISTS public.books
    ADD COLUMN availability boolean DEFAULT TRUE;
ALTER TABLE IF EXISTS public.books
    ADD COLUMN description text DEFAULT NULL;
ALTER TABLE IF EXISTS public.books
    ADD COLUMN ratings integer DEFAULT 0;
ALTER TABLE IF EXISTS public.books
    ADD COLUMN reviews text[] DEFAULT NULL;