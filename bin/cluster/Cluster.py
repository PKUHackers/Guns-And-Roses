#########################################################################
# Author: h.yihong.cs
# Created Time: 2013/5/18 18:33:44
# File Name: parseXML.py
# Description: cluster and summary 
#########################################################################

import os
import json
import re
import string
import math
import sys

global topics
global news

def parseNewsJson(input_file):
    global news
    news = {}
    fin = open(input_file)
    r = fin.read()
    docs = json.loads(r)
    for doc in docs:
	newsInfo = {}
	newsInfo['title'] = doc['title']
	newsInfo['pubdate'] = doc['pubdate']
	newsInfo['keywords'] = doc['keywords']
	news[doc['url']] = newsInfo
    fin.close()

def parsetopicXML(input_file):
    global news
    global topics

    fin = open(input_file)
    topics = {}
    topicID = ""
    topicInfos = {}
    for line in fin.readlines():
	if '</Topic' in line:
	    topics[topicID] = topicInfos
	else:
	    if '<Topic' in line:
		topicInfos = {}
		topicID = ""
	    else:
		temp = line.replace('>','<')
		domains = temp.split('<')
		if domains[1]=='id':
		    topicID = domains[2]
		else:
		    if domains[1]=='postingList':
			urls = domains[2].split(';')
			postingList = {}
			for url in urls:
			    postingList[url] = news[url]
			topicInfos[domains[1]] = postingList
		    else:
			topicInfos[domains[1]] = domains[2]
    fin.close()
#    print topics
#    topics_json = json.dumps(topics)
    #print topics_json

def getTFIDF(keyword):
    terms = {}
    words = keyword.split(';')
    l = 0.0
    try:
	for word in words:
	    w = word.split(':')
	    #terms[w[0]] = string.atof(w[1]) * string.atof(w[2])
	    terms[w[0]] = string.atof(w[2])
	    l = l + terms[w[0]] * terms[w[0]]
    except:
	print keyword
    return terms, math.sqrt(l)

def calcSimility(k1, k2):
    t1, l1 = getTFIDF(k1)
    t2, l2 = getTFIDF(k2)
    sim = 0.0
    for (k,v) in t1.items():
	if k in t2:
	    sim = sim + v * t2[k]
    if (l1!=0 and l2!=0):
	return sim/(l1*l2)
    else:
	return 0

def generateTopic(topicID, topN, N):
    global topics
    docs = topics[topicID]['postingList'].items()
  #  print len(docs)
  #  print topics[topicID]['name']

    sortdocs = sorted(docs, key=lambda x:x[1]['pubdate'])
  #  print sortdocs
    similities = []
    for i in range(1,len(sortdocs)):
	similities.append(calcSimility(sortdocs[i-1][1]['keywords'],sortdocs[i][1]['keywords']))
#	print similities[i-1]
    diff = []
    for i in range(1,len(similities)):
	diff_tmp = []
	diff_tmp.append(i-1)
	diff_tmp.append(abs(similities[i] - similities[i-1]))
	diff.append(diff_tmp)
    sortdiff = sorted(diff, key=lambda x:-x[1])
 #   print sortdiff
    
    flag = []
    for i in range(0,len(sortdocs)):
	flag.append(1)
    
    for i in range(0,topN):
	flag[sortdiff[i][0]] = 0;

##generate results
    
    res = {}
    res['title'] = topics[topicID]['name']
    res['color'] = 'white'
    res['initial_zoom'] = '50'
    res['focus_date'] = '2012-10-01 00:00'
    event = []

    cluster = {}
    startflag = True
    postingList = []
    n = 0
    for i in range(0,len(sortdocs)):
	if startflag:
	    n = n+1
	   # print n
	    cluster = {}
	    cluster['startdate'] = sortdocs[i][1]['pubdate'][0:len(sortdocs[i][1]['pubdate'])-4]
	    startflag = False
	postingList.append(sortdocs[i][0])
#	print sortdocs[i][1]['pubdate'].encode('utf-8'), "^_^", sortdocs[i][0].encode('utf-8'), "=.=", sortdocs[i][1]['title'].encode('utf-8')
	if flag[i]==0 or i == len(sortdocs)-1:
	    cluster['title'] = news[sortdocs[i][0]]['title']
	    #cluster['title'] = ""
	    cluster['description'] = "<a target=_blank href=\"/NRS/time/details.jsp?method=ShowDetail&topicId="+str(N)+"&nodeId="+str(n)+"\">read more</a>"
	    cluster['importance'] = '25'
	    cluster['low_threshold'] = '5'
	  #  print n
	    cluster['id'] = str(n)
	  #  print cluster['id']
	    cluster['ypix'] = '0'
	    cluster['event_type'] = 'event'
	    cluster['high_threshold'] = '75'
	    cluster['slug'] = ""
	    cluster['icon'] = "triangle_green.png"
	    cluster['enddate'] = sortdocs[i][1]['pubdate'][0:len(sortdocs[i][1]['pubdate'])-4]
	   # cluster['N'] = str(N)
	   # cluster['n'] = str(n)
	   # cluster['postingList'] = postingList
	    event.append(cluster)
	    postingList = []
	    startflag = True
#	    print '======================================================================='
    res['events'] = event
    res['id'] = res['title']
    res['size_importance'] = 'true'

    fout = open("display20_"+str(N)+'.json','w')
    out = []
    out.append(res)
    fout.write(json.dumps(out))
    fout.close()
    return res

def generateUrlsForCrawl():
    global topics
    urlForParse = []
    n = 0
    for topic in topics:
	if len(topics[topic]['postingList']) >100:
	    n = n + 1	    
	    for k,v in topics[topic]['postingList'].items():
		dic = {}
		dic['url'] = k
		dic['title'] = v['title']
		urlForParse.append(dic)
    print json.dumps(urlForParse)
    print n

def generateAllTopics(topN):
    global topics

    n = 0
    output = []
    for topic in topics:
	if len(topics[topic]['postingList']) >100:	    
	    n = n + 1
	    output.append(generateTopic(topic, topN, n))
    print json.dumps(output)


if __name__=='__main__':
    
    parseNewsJson("piece1.json")
    parsetopicXML("topic.xml")
    generateAllTopics(string.atoi(sys.argv[1]))
