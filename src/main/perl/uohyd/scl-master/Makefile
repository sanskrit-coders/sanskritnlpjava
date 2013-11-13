SHELL = /bin/sh

all: amara
amara:
	cd converters; ./configure; make
	cd amarakosha; ./configure; make

install:
	cd amarakosha; make install
	cp -pr ../scl_amara/amarakosha ../scl_amara/converters /home/vvasuki/scl

uninstall:
	rm -r /home/vvasuki/scl/amarakosha /var/www/htdocs/scl/amarakosha /usr/lib/cgi-bin/scl/amarakosha /tmp/AMARA_DOT
