SHELL = /bin/sh

.PHONY: all clean

TARGETS = ir ri ri_skt ir_skt itrans_ra.out logical_itrans.out rm__between_vowels.out kyoto_ra.out wx2utf8roman.out add_vowel_sep.out ra_itrans.out ra_kyoto.out wx2slp.out slp2wx.out velthuis-wx.out wx-velthuis.out

SRC = ir.lex ir_skt.lex iscii2utf8.py itrans-iscii.sh itrans_ra.lex kyoto_ra.lex logical_itrans.lex Makefile ri.lex ri_skt.lex rm__between_vowels.lex slp2wx.pl utf82iscii.pl utf82wx.sh velthuis-wx.pl wx2utf8.sh convert.pl iso2utf8.pl wx-velthuis.pl utf8roman2wx.pl wx2utf8roman.pl iso2utf.sh itrans-iscii.sh utf82wx.sh utf8d2r.sh wx2utf8.sh wxHindi-utf8.sh wx2utf8roman.lex add_vowel_sep.lex ra_itrans.lex w2slp.lex slp2wx.lex velthuis-wx.lex wx-velthuis.lex

LIBPATH = /usr/lib/

all: $(TARGETS)
FLEX = flex
CC = gcc
CFLAGS = -L$(LIBPATH) -g -o

define run_flex
$(FLEX) $^
$(CC) $(CFLAGS) $@ lex.yy.c -lfl
rm lex.yy.c
endef

add_vowel_sep.out: add_vowel_sep.lex
	$(run_flex)

ir: ir.lex
	$(run_flex)

ra_itrans.out: ra_itrans.lex
	$(run_flex)

ri: ri.lex
	$(run_flex)

ri_skt: ri_skt.lex
	$(run_flex)

ir_skt: ir_skt.lex
	$(run_flex)

itrans_ra.out: itrans_ra.lex
	$(run_flex)

logical_itrans.out: logical_itrans.lex
	$(run_flex)

rm__between_vowels.out: rm__between_vowels.lex
	$(run_flex)

kyoto_ra.out: kyoto_ra.lex
	$(run_flex)

ra_kyoto.out: ra_kyoto.lex
	$(run_flex)

wx2utf8roman.out: wx2utf8roman.lex
	$(run_flex)

wx2slp.out: wx2slp.lex
	$(run_flex)

slp2wx.out: slp2wx.lex
	$(run_flex)

velthuis-wx.out: velthuis-wx.lex
	$(run_flex)

wx-velthuis.out: wx-velthuis.lex
	$(run_flex)

distrib:
	cd ../; tar -cvzf /tmp/a.tgz -C converters $(SRC); mkdir /tmp/converters; cd /tmp/converters; tar -xvzf /tmp/a.tgz; cd ../; tar -cvzf converters.tgz converters; rm a.tgz
	cp /tmp/converters.tgz .

clean: 
	rm -f $(TARGETS)

