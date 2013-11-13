#!/usr/bin/perl

require "ont.pl";

open(FILE, "<DBM/all_kANdas");
# @data = <FILE>;
# @data = qw(ganXavaha, hari, shUra);
$|++;
while(<FILE>) {
# 	print($str);
#	system("./ont.pl $str");
	$str = $_;
	getDetails($str);
}
close(FILE);