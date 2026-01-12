import pyodbc
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits import mplot3d

# Update connection string information
server = 'mysqlserveres.database.windows.net'
database = 'ExercisesDatabase'
username = 'examiner'
password = 'p3180272_p3190160'
driver = '{ODBC Driver 17 for SQL Server}'

# Establish a connection to the database
connection_string = f"DRIVER={driver};SERVER={server};DATABASE={database};UID={username};PWD={password}"
conn = pyodbc.connect(connection_string)
print("Connection established")


cursor = conn.cursor()

# Find and make a plot for number of movies per year for movies with a total of budget greater than 1,000,000

cursor.execute("""SELECT YEAR(release_date) AS year, COUNT(*) AS movies_per_year
                  FROM movie
                  WHERE budget > 1000000
                  GROUP BY YEAR(release_date)
                  ORDER BY YEAR(release_date);""")
rows = cursor.fetchall()

f=[]
s=[]
for row in rows:
    f.append(row[0])
    s.append(row[1])



x = np.array(f)
y = np.array(s)

plt.bar(x,y)
plt.title("Number of movies per year")
plt.xlabel("Release Date")
plt.ylabel("Number of movies")
plt.show()

# Find and make a plot for number of movies per genre for movies 
#that have a total budget of more than 1,000,000 or a duration of more than 2 hours

cursor.execute("""SELECT g.name, COUNT(*) AS movies_per_genre
                  FROM movie m
                  JOIN hasGenre hg ON m.id = hg.movie_id
                  JOIN genre g ON hg.genre_id = g.id
                  WHERE m.budget > 1000000 OR m.runtime > 120
                  GROUP BY g.name;""")
rows = cursor.fetchall()

f=[]
s=[]
for row in rows:
    f.append(row[0])
    s.append(row[1])
    

x = np.array(f)
y = np.array(s)

plt.bar(x,y,width = 0.6)
plt.title("Number of movies per genre")
plt.xlabel("Genre")
plt.ylabel("Number of movies")
plt.xticks(rotation=30)
plt.show()

# Find and make a plot for number of movies per year and genre

cursor.execute("""SELECT g.name, YEAR(m.release_date) AS year, COUNT(*) AS movies_per_gy
                  FROM movie m
                  JOIN hasGenre hg ON m.id = hg.movie_id
                  JOIN genre g ON hg.genre_id = g.id
                  WHERE m.budget > 1000000 OR m.runtime > 120
                  GROUP BY g.name, YEAR(m.release_date)
                  ORDER BY g.name, YEAR(m.release_date);""")
results = cursor.fetchall()


genre_counts = {}
year_counts = {}


for row in results:
    genre, year, count = row
    if genre is not None and year is not None:
        genre_counts.setdefault(genre, []).append(count)
        year_counts.setdefault(year, []).append(count)



years = sorted(year_counts.keys())
genres = sorted(genre_counts.keys())
movies_per_gy = [
    [genre_counts[genre][i] if genre in genre_counts and i < len(genre_counts[genre]) else 0 for genre in genres]
    for i in range(len(years))
]


fig, ax = plt.subplots(figsize=(10, 6))
for i, genre in enumerate(genres):
    ax.bar(years, [movies_per_gy[j][i] for j in range(len(years))], label=genre)


plt.xlabel('Year')
plt.ylabel('Number of Movies')
plt.title('Number of Movies per Genre and Year')
plt.legend()


plt.show()



# Find and make a plot for total revenue for the movies Johnny Depp has been in per year

cursor.execute("""SELECT YEAR(m.release_date) AS year, SUM(m.revenue) AS revenues_per_year
                  FROM movie m
                  JOIN movie_cast mc ON m.id = mc.movie_id
                  WHERE name = 'Johnny Depp' AND YEAR(m.release_date) IS NOT NULL
                  GROUP BY YEAR(m.release_date)
                  ORDER BY YEAR(m.release_date);""")
rows = cursor.fetchall()

f=[]
s=[]
for row in rows:
    f.append(row[0])
    s.append(row[1])



x = np.array(f)
y = np.array(s)

plt.bar(x,y)
plt.title("Total revenue for the movies Johnny Depp has been in per year")
plt.xlabel("Release Date")
plt.ylabel("Revenue summary")
plt.show()

# Find and make a plot for max budget of movies per year, when this budget is not zero.

cursor.execute("""SELECT YEAR(m.release_date) AS year,MAX(budget) AS max_budget
                  FROM movie m
                  WHERE YEAR(m.release_date) IS NOT NULL AND budget > 0 
                  GROUP BY YEAR(m.release_date)
                  ORDER BY YEAR(m.release_date);""")
rows = cursor.fetchall()

f=[]
s=[]
for row in rows:
    f.append(row[0])
    s.append(row[1])



x = np.array(f)
y = np.array(s)

plt.bar(x,y)
plt.title("Max budget of movies per year")
plt.xlabel("Release Date")
plt.ylabel("Max Budget")
plt.show()

# Find and make a plot for number of user ratings and the average rating

cursor.execute("""SELECT user_id,AVG(rating) AS avg_rating,COUNT(rating) AS rating_count
                  FROM ratings
                  GROUP BY user_id
                  ORDER BY user_id;""")
rows = cursor.fetchall()

ui=[]
ar=[]
cr=[]
for row in rows:
    ui.append(row[0])
    ar.append(row[1])
    cr.append(row[2])


x = np.array(cr)
y = np.array(ar)

plt.scatter(x,y)

# Remove comment to see labels
# for i, txt in enumerate(ui):
    # plt.annotate(txt, (cr[i], ar[i]))
    
plt.title("Number of user ratings and the average rating")
plt.xlabel("Number of Ratings")
plt.ylabel("Average Rating")
plt.show()


# Clean up
conn.commit()
cursor.close()
conn.close()
