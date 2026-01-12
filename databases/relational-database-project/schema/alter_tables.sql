ALTER TABLE movie
ADD CONSTRAINT movie_pk
PRIMARY KEY (id);

ALTER TABLE genre
ADD CONSTRAINT genre_pk
PRIMARY KEY (id);

ALTER TABLE productioncompany
ADD CONSTRAINT productioncompany_pk
PRIMARY KEY (id);

ALTER TABLE collection
ADD CONSTRAINT collection_pk
PRIMARY KEY (id);

ALTER TABLE movie_cast
ADD CONSTRAINT movie_cast_pk
PRIMARY KEY (movie_id,cid,person_id);

ALTER TABLE movie_crew
ADD CONSTRAINT movie_crew_pk
PRIMARY KEY (movie_id,person_id,job);

ALTER TABLE keyword
ADD CONSTRAINT keyword_pk
PRIMARY KEY (keyword_id);



ALTER TABLE belongsTocollection
 ADD CONSTRAINT FK_movie_id FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE belongsTocollection
 ADD CONSTRAINT FK_collection_id FOREIGN
KEY (collection_id)
 REFERENCES collection(id);


ALTER TABLE hasGenre 
 ADD CONSTRAINT FK_movieid FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE hasGenre 
 ADD CONSTRAINT FK_genre_id FOREIGN
KEY (genre_id)
 REFERENCES genre(id);


ALTER TABLE hasProductioncompany 
 ADD CONSTRAINT FK_movie__id FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE hasProductioncompany 
 ADD CONSTRAINT FK_pc_id FOREIGN
KEY (pc_id)
 REFERENCES productioncompany(id);


ALTER TABLE ratings  
 ADD CONSTRAINT FK__movie_id FOREIGN
KEY (movie_id)
 REFERENCES movie(id);


ALTER TABLE movie_cast  
 ADD CONSTRAINT FK__movie__id FOREIGN
KEY (movie_id)
 REFERENCES movie(id);


ALTER TABLE movie_crew  
 ADD CONSTRAINT FK__movieid FOREIGN
KEY (movie_id)
 REFERENCES movie(id);


ALTER TABLE hasKeywords  
 ADD CONSTRAINT ForeignK_movieid FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE hasKeywords  
 ADD CONSTRAINT FK_keyword_id FOREIGN
KEY (keyword_id)
 REFERENCES keyword(keyword_id);