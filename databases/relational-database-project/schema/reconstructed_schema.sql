-- Reconstructed relational schema
-- Based on CSV imports and subsequent ALTER TABLE constraints
-- Originally generated automatically during Azure CSV ingestion

-- =========================
-- Core entities
-- =========================

CREATE TABLE movie (
    movie_id INT PRIMARY KEY,
    title VARCHAR(255),
    release_date DATE,
    runtime INT,
    popularity FLOAT,
    budget BIGINT,
    revenue BIGINT
);

CREATE TABLE genre (
    genre_id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE keyword (
    keyword_id INT PRIMARY KEY,
    name VARCHAR(150)
);

CREATE TABLE collection (
    collection_id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE productioncompany (
    company_id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE ratings (
    user_id INT,
    movie_id INT,
    rating FLOAT,
    rating_timestamp BIGINT,
    PRIMARY KEY (user_id, movie_id)
);

CREATE TABLE movie_cast (
    cast_id INT PRIMARY KEY,
    movie_id INT,
    name VARCHAR(255),
    character_name VARCHAR(255),
    gender INT
);

CREATE TABLE movie_crew (
    crew_id INT PRIMARY KEY,
    movie_id INT,
    name VARCHAR(255),
    department VARCHAR(100),
    job VARCHAR(100),
    gender INT
);

-- =========================
-- Junction tables (N–M)
-- =========================

CREATE TABLE hasGenre (
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id)
);

CREATE TABLE hasKeywords (
    movie_id INT,
    keyword_id INT,
    PRIMARY KEY (movie_id, keyword_id)
);

CREATE TABLE belongsToCollection (
    movie_id INT,
    collection_id INT,
    PRIMARY KEY (movie_id, collection_id)
);

CREATE TABLE hasProductioncompany (
    movie_id INT,
    company_id INT,
    PRIMARY KEY (movie_id, company_id)
);

-- =========================
-- Foreign Keys
-- =========================

ALTER TABLE ratings
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id);

ALTER TABLE movie_cast
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id);

ALTER TABLE movie_crew
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id);

ALTER TABLE hasGenre
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
ADD FOREIGN KEY (genre_id) REFERENCES genre(genre_id);

ALTER TABLE hasKeywords
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
ADD FOREIGN KEY (keyword_id) REFERENCES keyword(keyword_id);

ALTER TABLE belongsToCollection
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
ADD FOREIGN KEY (collection_id) REFERENCES collection(collection_id);

ALTER TABLE hasProductioncompany
ADD FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
ADD FOREIGN KEY (company_id) REFERENCES productioncompany(company_id);
