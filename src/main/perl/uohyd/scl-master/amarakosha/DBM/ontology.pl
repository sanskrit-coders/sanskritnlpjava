#!/usr/bin/perl

#  Copyright (C) 2008-2012 Sivaja Nair and Amba Kulkarni (ambapradeep@gmail.com)
#
#  This program is free software; you can redistribute it and/or
#  modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either
#  version 2 of the License, or (at your option) any later
#  version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program; if not, write to the Free Software
#  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

require "get_single_synonym.pl";

use GDBM_File;


tie(%LEX,GDBM_File,"amar_onto_new.dbm",GDBM_READER,0666) || die "can't open amar_onto_new.dbm";
tie(%LEX1,GDBM_File,"rule_onto.dbm",GDBM_READER,0666) || die "can't open rule_onto.dbm";

$word =$ARGV[0];
print "<\@center>";
print "<\@table \@border=\"0\" \@align=\"\@middle\">";
print "<\@tr><\@td>";
 @onto=split(/::/,$LEX{$word});
 foreach $onto (@onto){
($hd,$jAw,$upA,$guN) = split(/#/,$onto);
print &get_single_synonym($hd);
if ($jAw ne ""){
print "<\@font \@color=\"\@magenta\" \@align=\"\@middle\">jAwiH</\@font><\@br>";
	print "=> $jAw<\@br>";
        while($LEX1{$jAw}){
                print "=> $LEX1{$jAw}<\@br>";
                $jAw = $LEX1{$jAw};
        }
print "_____________________";
print "<\@br><\@br>";
}
if ($upA ne ""){
print "<\@font \@color=\"\@magenta\" \@align=\"\@middle\">upAXiH</\@font><\@br>";
	print "=> $upA<\@br>";
        while($LEX1{$upA}){
                print "=> $LEX1{$upA}<\@br>";
                $upA = $LEX1{$upA};
        }
print "._____________________.";
print "<\@br><\@br>";
}
}
print "</\@td></\@tr>";
print "</\@table>";
untie(%LEX);
untie(%LEX1);

