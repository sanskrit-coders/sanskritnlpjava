SHELL = /bin/sh

TARGETS = stem2head.gdbm avayava.gdbm avayavI.gdbm aparAjAwi.gdbm parAjAwi.gdbm janaka.gdbm janya.gdbm pawi.gdbm pawnI.gdbm svAmI.gdbm sevaka.gdbm vESiRtya.gdbm vESiRtyavaw.gdbm sambanXi1.gdbm  sambanXi2.gdbm vqwwi.gdbm vqwwivAn.gdbm synset_info.gdbm rule_onto.gdbm onto.gdbm

all: $(TARGETS)

onto.gdbm: create_onto_dbm.pl all_kANdas
	./create_onto_dbm.pl onto.gdbm < all_kANdas

rule_onto.gdbm: create_rule_onto.pl rules_onto
	./create_rule_onto.pl rule_onto.gdbm < rules_onto

synset_info.gdbm: create_dbm.pl all_kANdas
	./create_dbm.pl synset_info.gdbm < all_kANdas

stem2head.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 0 4 stem2head.gdbm < all_kANdas

avayava.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 5 4 avayava.gdbm < all_kANdas

avayavI.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 5 avayavI.gdbm < all_kANdas

aparAjAwi.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 6 4 aparAjAwi.gdbm < all_kANdas

parAjAwi.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 6 parAjAwi.gdbm < all_kANdas

janaka.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 7 janaka.gdbm < all_kANdas

janya.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 7 4 janya.gdbm < all_kANdas

pawnI.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 8 4 pawnI.gdbm < all_kANdas

pawi.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 8 pawi.gdbm < all_kANdas

svAmI.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 9 svAmI.gdbm < all_kANdas

sevaka.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 9 4 sevaka.gdbm < all_kANdas

vESiRtya.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 10 vESiRtya.gdbm < all_kANdas

vESiRtyavaw.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 10 4 vESiRtyavaw.gdbm < all_kANdas

sambanXi1.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 11 sambanXi1.gdbm < all_kANdas

sambanXi2.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 11 4 sambanXi2.gdbm < all_kANdas

vqwwi.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 4 12 vqwwi.gdbm < all_kANdas

vqwwivAn.gdbm: create_dbm_key_value.pl all_kANdas
	 ./create_dbm_key_value.pl 12 4 vqwwivAn.gdbm < all_kANdas

.PHONY: all

clean:
	rm -f $(TARGETS)
