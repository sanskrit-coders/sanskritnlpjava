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

use GDBM_File;



tie(%LEX,GDBM_File,$ARGV[0],GDBM_WRCREAT,0666);

#Fields: Word(0), Reference(1), Gender(2), Varga(3), Head_word(4), is_a_part_of(5), is_a_kind_of(6), janya_janaka(7), pawi_pawnI(8), svasvAmI(9), vESiRtya(10), saMbanXiwa(11), vqwwi(12), English name(13),

while(<STDIN>) {
  chomp;
  @flds = split(/,/,$_);
  if($flds[0] !~ /^%/)  { 

     if($LEX{$flds[4]} eq "") {
        $LEX{$flds[4]}  =  $flds[0]."#".$flds[1]."#".$flds[2]."#".$flds[3];
     }else {
        $LEX{$flds[4]}  .= "::". $flds[0]."#".$flds[1]."#".$flds[2]."#".$flds[3];
     }

  }
}
untie(%LEX);
