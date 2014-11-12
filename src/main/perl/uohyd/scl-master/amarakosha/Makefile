SHELL = /bin/sh

default:
	cd DBM; make

install:
	mkdir -p /var/www/htdocs/scl/amarakosha; mkdir -p /usr/lib/cgi-bin/scl/amarakosha; cp web_interface/*.html /var/www/htdocs/scl/amarakosha; cp -r web_interface/js /var/www/htdocs/scl/amarakosha;  cp -r web_interface/FAQ /var/www/htdocs/scl/amarakosha/; cp -r web_interface/*.cgi /usr/lib/cgi-bin/scl/amarakosha; cp web_interface/amarakosha_knowledge_structure.pdf /var/www/htdocs/scl/amarakosha; cp web_interface/logouoh.png /var/www/htdocs/scl/amarakosha; cp web_interface/about.html /var/www/htdocs/scl/amarakosha
	mkdir -p /tmp/AMARA_DOT; chown www-data:www-data /tmp/AMARA_DOT; cd /var/www/htdocs/scl/amarakosha; ln -sf /tmp/AMARA_DOT DOT

debian_install:
	mkdir -p /tmp/scl//var/www/htdocs/scl/amarakosha; mkdir -p /tmp//scl/usr/lib/cgi-bin/scl/amarakosha; cp web_interface/*.html /tmp/scl//var/www/htdocs/scl/amarakosha; cp -r web_interface/js /tmp/scl//var/www/htdocs/scl/amarakosha;  cp -r web_interface/FAQ /tmp/scl//var/www/htdocs/scl/amarakosha/; cp -r web_interface/*.cgi /tmp/scl//usr/lib/cgi-bin/scl/amarakosha; cp web_interface/amarakosha_knowledge_structure.pdf /tmp/scl//var/www/htdocs/scl/amarakosha; cp web_interface/logouoh.png /tmp/scl//var/www/htdocs/scl/amarakosha; cp web_interface/about.html /tmp/scl//var/www/htdocs/scl/amarakosha
	mkdir -p /tmp/AMARA_DOT; chown www-data:www-data /tmp/AMARA_DOT; cd /var/www/htdocs/scl/amarakosha; ln -sf /tmp/AMARA_DOT DOT

dict:
	./makeDb.pl > output/dict_wx.tsv
	cat output/dict_wx.tsv| ../converters/wx2utf8.sh >output/dict_deva.tsv
	sed -i 's/||/\\n\\n/g' output/dict_deva.tsv
	cp output/dict_deva.tsv ~/sanskritnlp/src/main/stardict-sanskrit/sa-head/amara-onto/amara-onto.tsv
