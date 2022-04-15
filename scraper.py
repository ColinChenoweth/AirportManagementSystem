import requests
from bs4 import BeautifulSoup
import csv

r = requests.get('https://gettocenter.com/airports/top-100-airports-in-world')
soup = BeautifulSoup(r.content, 'html.parser')

s = soup.find('table', class_='table')
content = s.findAll('td')

airportName = 1
abbrev = 2
city = 3
a = 0
b = 0

rows = []
curRow = []

for c in content:
    if b == 60:
        a = 0
        b = 0
    else:
        if a == airportName:
            curRow = [c.text]
        if a == abbrev:
            curRow.insert(0, c.text)
        if a == city:
            curRow.append(c.text)
            rows.append(curRow.copy())
        a = (a + 1) % 6
        b = b + 1

with open('airport.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerows(rows)