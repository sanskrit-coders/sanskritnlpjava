# -*- coding: utf-8 -*- 
import unicodecsv
import csv
from itertools import takewhile, izip


# def removeNonAscii(s): return "".join(filter(lambda x: ord(x)<128, s))
def get_common_prefix(strings):
  b = zip(*strings)
  c = [x[0] for x in b if x==(x[0],)*len(x)]
  result = "".join(c)
  # result = ''.join(c[0] for c in takewhile(lambda x: all(x[0] == y for y in x), izip(*strings)))
  return result

data_file = u'data/Chandas छन्दः - अर्धसम.csv'
out_file = 'data/ardhasama_prefix.csv'

# with open(out_file, 'w') as outfile:
with open(data_file, 'r') as csvfile:
  chandas_reader =  csv.reader(csvfile)
  # chandas_reader =  unicodecsv.reader(csvfile, encoding='utf-8')
  # chandas_reader = csvfile.readlines()
  for chandas in chandas_reader:
    # chandas = chandas.decode("utf-8").split(',')
    # chandas = chandas.split(',')
    # print chandas
    # prefix = get_common_prefix([chandas[3].decode("utf-8"), chandas[5].decode("utf-8")])
    prefix = get_common_prefix([chandas[3], chandas[5]])
    print prefix
    # print prefix.encode("utf-8")
    # outfile.write(prefix.encode("utf-8") + '\n')
    # print prefix.encode("utf-8")
    # print unicode(prefix, errors='ignore')
