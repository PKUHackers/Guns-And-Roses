import os
import json

file_bundle = open("../../data/merged/display20.json")
merged_bundle = open("../../data/merged/merged_news.json")

topics = json.load(file_bundle)
merged = json.load(merged_bundle)

# dir = './set'

# files = os.listdir(dir)
# for f in files:
#     print dir + os.sep + f

count = 0;

for topic in topics:
    for events in topic['events']:
        if events['N'] == '5':
            #for n_iter in range(0, 20):
            if events['n'] == '14':
                print '<<<<<<<<<<<<<<<<' + str(events['n'])
                for i in events['postingList']:
                    flag = False
                    for j in merged:
                        if i == j['url']:
                            count = count + 1
                            print i;

                            print j['title'].encode('utf-8').strip()
                            print j['pubdate']
                            print j['cleanContent'].encode('utf-8').strip()
                            for img in j['imagelist']:
                                print img['url']
                                print img['title'].encode('utf-8').strip()
                            for vid in j['videolist']:
                                print vid['url']
                                print vid['imgurl']
                                print vid['title'].encode('utf-8').strip()
                            flag = True
                            break

                    if not flag:
                        print 'Not Found' + i

                    print ">>>>>>>>>>>>>>>>>>>>>>>>>"

print count
