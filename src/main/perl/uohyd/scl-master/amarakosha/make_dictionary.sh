./makeDb.pl > output/dict_wx.tsv
cat output/dict_wx.tsv| ../converters/wx2utf8.sh >output/dict_deva.tsv
cp output/dict_deva.tsv ~/sanskritnlp/src/main/stardict-sanskrit/sa-head/amara-onto/amara-onto.tsv
