#!/usr/bin/perl


#  Copyright (C) 2006-2012 Sivaja Nair and Amba Kulkarni (ambapradeep@gmail.com)
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


use GDBM_File;

my($LEX);
tie(%LEX,GDBM_File,"/home/vvasuki/scl/amarakosha/DBM/stem2head.gdbm",GDBM_READER,0666) || die "can't open DBM/stem2head.gdbm";

$pid = $ARGV[0];
$word = $ARGV[1];

&shwMorph($pid,$word);

untie(%LEX);
		
sub shwMorph{
my($pid,$word) = @_;
$word =~ s/[^a-zA-Z]//g;
system("echo $word | /usr/bin//lt-proc -c /home/vvasuki/scl/SHMT/MT/prog/morph/dix_and_bin/skt_morf.bin > /tmp/in_mo$pid");

open(MORPH," < /tmp/in_mo$pid")||die "/tmp/in_mo$pid not found";
$result=<MORPH>;
close(MORPH);

$result=~ s/\^|\$//g;
$result=~ s/\//#/g;
@flds=split(/#/,$result);
    foreach $fld (@flds) {
       $fld =~ s/<.*//;
       if($LEX{$fld} ne "") { print $fld,"\n";}
    }
}
1;
