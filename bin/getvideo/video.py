import os
import sys
import time
from urllib import FancyURLopener
import urllib2
import json


def get_video_url(query, result_size):
    # Replace spaces ' ' in search term for '%20' in order to comply with request
    query = query.replace(' ','%20')

    # Start FancyURLopener with defined version
    class MyOpener(FancyURLopener):
        version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'
    myopener = MyOpener()

    # Set count to 1
    count = 1

    flag = False

    for i in range(0, 10):
        # Notice that the start changes for each iteration in order to request a new set of images for each loop
        url = ('https://ajax.googleapis.com/ajax/services/search/video?' + 'v=2.0&q='+query+'&start='+str(i*4))
        #print url
        request = urllib2.Request(url, None, {'Referer': 'testing'})
        response = urllib2.urlopen(request)

        # Get results using JSON
        results = json.load(response)
        data = results['responseData']
        dataInfo = data['results']

        video_url_list = []
        # Iterate for each result and get unescaped url
        for myUrl in dataInfo:
            item = {}
            item['url'] = myUrl['url']
            item['imgurl'] = myUrl['tbUrl']
            item['title'] = myUrl['titleNoFormatting']
            video_url_list.append(item)
            count = count + 1
            if count > result_size:
                flag = True
                break

        if flag:
            break
        # Sleep for one second to prevent IP blocking from Google
        time.sleep(1)

    return video_url_list


def do_video_query(query_file, video_file):

    if not query_file:
        json_data = open('../../data/queried/query.json')
    else:
        json_data = open(query_file)
    data = json.load(json_data)

    #print data

    l = []
    for each_it in data:
        it = {}
        for k,v in each_it.items():
            it[k] = get_video_url(v, 2)
            l.append(it)

    if not video_file:
        image_file_handler = open('../../data/videoed/video_urls.json', 'w')
    else:
        image_file_handler = open(video_file, 'w')

    image_file_handler.write(json.dumps(l))



if __name__ == '__main__':
    reload(sys)
    sys.setdefaultencoding('utf8')
    do_video_query(None, None)