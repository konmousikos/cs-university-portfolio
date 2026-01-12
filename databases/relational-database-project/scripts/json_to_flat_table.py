
import csv
import ast

with open("hasKeywords.csv", 'w', newline='', encoding='utf-8') as f:
	writer = csv.writer(f)
	file = open("keywords.csv")
	with open('keywords.csv', 'r', newline='', encoding='utf-8') as file:
		csvreader = csv.reader(file)
		header = next(csvreader)
		
		writer.writerow(["movie_id","keyword_id"])
		for row in csvreader:
			jsonString = row[1]  
			data = ast.literal_eval(jsonString) 
			for x in range(len(data)):	
				myrow=[row[0],data[x]["id"]] 
				writer.writerow(myrow)
		file.close()
		f.close()
with open("keyword.csv", 'w', newline='', encoding='utf-8') as f:
	writer = csv.writer(f)
	file = open("keywords.csv")
	with open('keywords.csv', 'r', newline='', encoding='utf-8') as file:
		csvreader = csv.reader(file)
		header = next(csvreader)

		writer.writerow(["keyword_id", "name"])
		for row in csvreader:
			jsonString = row[1]  
			data = ast.literal_eval(jsonString)  
			for x in range(len(data)):  
				myrow = [data[x]["id"], data[x]["name"]]  
				writer.writerow(myrow)
		file.close()
		f.close()