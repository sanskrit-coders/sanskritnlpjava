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


tie(%LEX,GDBM_File,$ARGV[0],GDBM_WRCREAT,0666);

#Fields: word(0), kANda(1), lifga(2), varga(3), arWa(4), jAwi(5), upAXi(6), guNaH(7),

while(<STDIN>) {
  chomp;
  @flds = split(/,/,$_);
 # print "input = ",$_,"\n";
  if(($flds[4] !~ /^%/) && (($flds[13] ne "") || ($flds[14] ne ""))) {

     $str  =  $flds[13]."#".$flds[14];
     if($LEX{$flds[4]} eq "") {
        $LEX{$flds[4]}  =  $str;
     } elsif (($LEX{$flds[4]} !~ /^$str$/) && ($LEX{$flds[4]} !~ /^$str::/) && ($LEX{$flds[4]} !~ /::$str$/) && ($LEX{$flds[4]} !~ /::$str::/)){
#        print "str = $str\n";
#        print "LEX = $LEX{$flds[4]}\n";
        $LEX{$flds[4]}  .= "::". $str;
     }
  }
}
untie(%LEX);
