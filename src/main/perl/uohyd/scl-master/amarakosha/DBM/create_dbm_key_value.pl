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

tie(%LEX,GDBM_File,$ARGV[2],GDBM_WRCREAT,0666);

$key = $ARGV[0];
$value = $ARGV[1];

while(<STDIN>) {
  chomp;
  @flds = split(/,/,$_);
  if(($flds[0] !~ /^%/) && ($flds[$key] ne "") && ($flds[$value] ne "")) {
     if($LEX{$flds[$key]} eq "") {
        $LEX{$flds[$key]}  =  $flds[$value];
     }else {
        if (($LEX{$flds[$key]} !~ /::$flds[$value]::/) && 
             ($LEX{$flds[$key]} !~ /^$flds[$value]::/) && 
             ($LEX{$flds[$key]} !~ /::$flds[$value]$/) && 
             ($LEX{$flds[$key]} !~ /^$flds[$value]$/)) {
        $LEX{$flds[$key]}  .= "::". $flds[$value];
        }
     }
  }
}
untie(%LEX);

