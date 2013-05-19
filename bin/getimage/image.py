import os
import sys
import time
from urllib import FancyURLopener
import urllib2
import json

global gcount

def get_image_url(query, result_size, is_download=False):
    # Replace spaces ' ' in search term for '%20' in order to comply with request
    query = query.replace(' ','%20')

    # Start FancyURLopener with defined version
    class MyOpener(FancyURLopener):
        version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'
    myopener = MyOpener()

    # Set count to 0
    count = 0

    flag = False

    for i in range(0, 2):
        # Notice that the start changes for each iteration in order to request a new set of images for each loop
        url = ('https://ajax.googleapis.com/ajax/services/search/images?' + 'v=2.0&q='+query+'&start='+str(i*4))
        #print url
        request = urllib2.Request(url, None, {'Referer': 'testing'})
        response = urllib2.urlopen(request)

        # Get results using JSON
        results = json.load(response)
        data = results['responseData']
        dataInfo = data['results']

        image_url_list = []
        # Iterate for each result and get unescaped url
        for myUrl in dataInfo:
            item = {}

            item['url'] = myUrl['unescapedUrl']
            print item['url']
            item['title'] = myUrl['titleNoFormatting']
            print item['title']

            image_url_list.append(item)
            if is_download:
                myopener.retrieve(myUrl['unescapedUrl'], str(count)+'.jpg')
            count = count + 1
            if count > result_size:
                flag = True
                break


        if flag:
            raise Exception('fuck')
            break

        # Sleep for one second to prevent IP blocking from Google
        time.sleep(2)

    return image_url_list


def do_image_query(query_file, image_file):

    global gcount

    if not query_file:
        json_data = open('../../data/queried/query.json')
    else:
        json_data = open(query_file)
    data = json.load(json_data)

    #print data

    gcount = 0




    l = []
    for each_it in data:
        it = {}
        for k,v in each_it.items():
            it[k] = get_image_url(v, 2)
            l.append(it)
        gcount = gcount + 1

        if gcount > 0:

            if not image_file:
                image_file_handler = open('../../data/imaged/image_urls'+ str(gcount) +'.json', 'w')
            else:
                image_file_handler = open(image_file, 'w')

            image_file_handler.write(json.dumps(l))
            image_file_handler.close()

            gcount = 0
            time.sleep(10)


if __name__ == '__main__':
    reload(sys)
    sys.setdefaultencoding('utf8')

    do_image_query(None, None)