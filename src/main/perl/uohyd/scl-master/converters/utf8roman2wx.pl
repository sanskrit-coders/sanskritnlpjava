#!/usr/bin/perl
#  Copyright (C) 2002-2012 Amba Kulkarni (ambapradeep@gmail.com)
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




while($in=<STDIN>)
{
$in=~ s{ā}{A}g;
$in=~ s{ī}{I}g;
$in=~ s{ū}{U}g;
$in=~ s{ḷ}{L}g;

$in=~ s{ai}{E}g;
$in=~ s{au}{O}g;

$in=~ s{ḥ}{H}g;
$in=~ s{ṛ}{q}g;
$in=~ s{ṝ}{Q}g;

$in=~ s{kh}{K}g;
$in=~ s{gh}{G}g;
$in=~ s{ṅ}{f}g;

$in=~ s{ch}{C}g;
$in=~ s{jh}{J}g;
$in=~ s{ñ}{F}g;

$in=~ s{ṭh}{T}g;
$in=~ s{ḍh}{D}g;
$in=~ s{ṇ}{N}g;

$in=~ s{ṃ}{M}g;

$in=~ s{th}{W}g;
$in=~ s{t}{w}g;
$in=~ s{dh}{X}g;
$in=~ s{d}{x}g;

# The following two lines are shifted from their place in order to avoid the re-substitution ṭ -> t -> w, ḍ -> d -> x
$in=~ s{ṭ}{t}g;
$in=~ s{ḍ}{d}g;

$in=~ s{ph}{P}g;
$in=~ s{bh}{B}g;

$in=~ s{ś}{S}g;
$in=~ s{ṣ}{R}g;

print $in;
}
