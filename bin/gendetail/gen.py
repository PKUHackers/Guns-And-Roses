import os
import json

file_bundle = open("../../data/merged/display20.json")


dir = './set'

files = os.listdir(dir)
for f in files:
    print dir + os.sep + f
