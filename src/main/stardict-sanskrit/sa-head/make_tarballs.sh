touch tars/tars.txt
rm tars/tars.txt
touch tars/tars.txt
for dir in */
do
	base=$(basename "$dir")
	cd $dir
	tar -czf "${base}.tar.gz" `ls *.idx *.dict *.ifo *.syn`
	mv "${base}.tar.gz" ../tars/
	cd ..
	if [ ! -f "$dir/${base}.tar.gz" ]; then
	  echo "<https://github.com/vvasuki/sanskritnlp/raw/master/src/main/stardict-sanskrit/sa-head/tars/${base}.tar.gz>" >> tars/tars.txt
	fi
done
