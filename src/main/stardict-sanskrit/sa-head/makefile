STARDICT_TOOLS_DIR=~/stardict/tools/src/
TABFILE=$(STARDICT_TOOLS_DIR)/tabfile

all:
	$(TABFILE) */*.tsv
	dictunzip */*.dz
	bash ./make_tarballs.sh

