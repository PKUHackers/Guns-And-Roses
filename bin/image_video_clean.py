import os

path = '../data/'

os.remove(path + 'queried/query.json')
os.remove(path + 'imaged/image_urls.json')
os.remove(path + 'videoed/video_urls.json')

print "cleaned"
