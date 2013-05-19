from getquery import query
from getimage import image
from getvideo import video
import sys


def run(path):
    if path:
        query.query_gen(path, '../data/queried/query.json')
    else:
        query.query_gen('../data/entitied/test.json', '../data/queried/query.json')

    reload(sys)
    sys.setdefaultencoding('utf8')
    image.do_image_query('../data/queried/query.json', '../data/imaged/image_urls.json')

    # video.do_video_query('../data/queried/query.json', '../data/videoed/video_urls.json')

if __name__ == '__main__':
    if len(sys.argv) > 1:
        run(sys.argv[1])
    else:
        run('../data/entitied/news-backup22pm.json')
