from random import randrange
import json

country_list = [
    {"country": "United States", "averageSentiment": 0.0},
    {"country": "Canada", "averageSentiment": 0.0},
    {"country": "Costa Rica", "averageSentiment": 0.0},
    {"country": "Brazil", "averageSentiment": 0.0},
    {"country": "United Kingdom", "averageSentiment": 0.0}
]

lines = []
with open('country-sentiments.txt', 'w') as f:
    for _ in range(20):
        for country in country_list:
            country["averageSentiment"] = randrange(1, 5)/1
            lines.append(json.dumps(country)+"\n")
    
    f.writelines(lines)
