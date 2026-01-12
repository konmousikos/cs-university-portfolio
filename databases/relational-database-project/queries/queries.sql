-- ============================================================
-- Relational Database Project
-- Representative SQL Queries
-- ============================================================
-- The following queries demonstrate data retrieval,
-- joins across related entities, and analytical aggregation
-- over the movie dataset.
-- ============================================================


-- ============================================================
-- 1. Keyword-based movie search
-- ============================================================
-- Finds movies that contain keywords starting with 'kill'

SELECT DISTINCT
    m.original_title AS movie_title,
    k.name AS keyword
FROM keyword k
JOIN hasKeywords hk ON k.keyword_id = hk.keyword_id
JOIN movie m ON hk.movie_id = m.id
WHERE k.name LIKE 'kill%'
ORDER BY m.original_title;


-- ============================================================
-- 2. Movies with positive user ratings
-- ============================================================
-- Retrieves movies that have received ratings above average

SELECT DISTINCT
    m.original_title AS movie_title
FROM movie m
JOIN ratings r ON m.id = r.movie_id
WHERE r.rating >= 4.0
ORDER BY m.original_title;


-- ============================================================
-- 3. Movies with high budget or long runtime per genre
-- ============================================================
-- Returns genres that include movies with budget > 1M
-- or runtime greater than 120 minutes

SELECT
    g.name AS genre,
    COUNT(*) AS movie_count
FROM movie m
JOIN hasGenre hg ON m.id = hg.movie_id
JOIN genre g ON hg.genre_id = g.id
WHERE m.budget > 1000000
   OR m.runtime > 120
GROUP BY g.name
ORDER BY movie_count DESC;


-- ============================================================
-- 4. Number of movies per genre and per year
-- ============================================================
-- Aggregates movie production per genre and release year

SELECT
    g.name AS genre,
    YEAR(m.release_date) AS release_year,
    COUNT(*) AS movies_per_year
FROM movie m
JOIN hasGenre hg ON m.id = hg.movie_id
JOIN genre g ON hg.genre_id = g.id
WHERE m.budget > 1000000
   OR m.runtime > 120
GROUP BY g.name, YEAR(m.release_date)
ORDER BY g.name, release_year;


-- ============================================================
-- 5. Average rating per movie
-- ============================================================
-- Computes average user rating for each movie

SELECT
    m.original_title AS movie_title,
    AVG(r.rating) AS average_rating
FROM movie m
JOIN ratings r ON m.id = r.movie_id
GROUP BY m.id, m.original_title
HAVING AVG(r.rating) >= 4.0
ORDER BY average_rating DESC;


-- ============================================================
-- 6. Most frequent keywords across movies
-- ============================================================
-- Identifies the most commonly used keywords

SELECT
    k.name AS keyword,
    COUNT(*) AS usage_count
FROM keyword k
JOIN hasKeywords hk ON k.keyword_id = hk.keyword_id
GROUP BY k.name
ORDER BY usage_count DESC;


-- ============================================================
-- 7. Movie participation per production company
-- ============================================================
-- Counts how many movies each production company has produced

SELECT
    pc.name AS production_company,
    COUNT(*) AS movie_count
FROM productioncompany pc
JOIN hasProductioncompany hp ON pc.company_id = hp.company_id
GROUP BY pc.name
ORDER BY movie_count DESC;


-- ============================================================
-- 8. Cast size per movie
-- ============================================================
-- Calculates the number of cast members per movie

SELECT
    m.original_title AS movie_title,
    COUNT(mc.cast_id) AS cast_size
FROM movie m
JOIN movie_cast mc ON m.id = mc.movie_id
GROUP BY m.id, m.original_title
ORDER BY cast_size DESC;


-- ============================================================
-- 9. Crew departments per movie
-- ============================================================
-- Shows crew participation grouped by department

SELECT
    m.original_title AS movie_title,
    mc.department,
    COUNT(*) AS department_count
FROM movie m
JOIN movie_crew mc ON m.id = mc.movie_id
GROUP BY m.original_title, mc.department
ORDER BY m.original_title, department_count DESC;
