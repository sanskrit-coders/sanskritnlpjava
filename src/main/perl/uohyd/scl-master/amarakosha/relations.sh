OUTENCODING=$4
if [ $OUTENCODING = "DEV" ] ; then
/home/vvasuki/scl/amarakosha/relations.pl $1 $2 $3 $4 $5 | /home/vvasuki/scl/converters/wxHindi-utf8.sh
fi
if [ $OUTENCODING = "ROMAN" ] ; then
/home/vvasuki/scl/amarakosha/relations.pl $1 $2 $3 $4 $5 | /home/vvasuki/scl/converters/wx2utf8roman.out
fi
