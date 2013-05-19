import json, sys

def query_gen(entitiy_file, query_file):
    if not entitiy_file:
        json_data = open('../../data/entitied/test.json')
    else:
        json_data = open(entitiy_file)
    data = json.load(json_data)
    #print data

    count = 0

    query_item = {}
    query = []
    for article in data:
        query_item[article['url']] = article['title']
        query.append(query_item)
        count = count + 1

    if not query_file:
        query_file_handler = open('../../data/queried/query.json', 'w')
    else:
        query_file_handler = open(query_file, 'w')
    query_file_handler.write(json.dumps(query))
    print count

if __name__ == "__main__":
    if len(sys.argv) > 1:
        query_gen(sys.argv[1], None)
    else:
        query_gen('../../data/entitied/urls.json', '../../data/queried/query2.json')

