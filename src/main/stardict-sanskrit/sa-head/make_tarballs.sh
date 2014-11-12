for dir in */
do
	base=$(basename "$dir")
	cd $dir
	tar -czf "${base}.tar.gz" `ls *.idx *.dict *.ifo *.syn`
	mv "${base}.tar.gz" ../tars/
	cd ..
done
