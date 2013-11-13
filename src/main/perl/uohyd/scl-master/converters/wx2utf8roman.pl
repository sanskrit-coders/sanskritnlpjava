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
$in=~ s{A}{ā}g;
$in=~ s{I}{ī}g;
$in=~ s{U}{ū}g;
$in=~ s{L}{ḷ}g;

$in=~ s{E}{ai}g;
$in=~ s{O}{au}g;

$in=~ s{H}{ḥ}g;
$in=~ s{q}{ṛ}g;
$in=~ s{Q}{ṝ}g;

$in=~ s{K}{kh}g;
$in=~ s{G}{gh}g;
$in=~ s{f}{ṅ}g;

$in=~ s{C}{ch}g;
$in=~ s{J}{jh}g;
$in=~ s{F}{ñ}g;

$in=~ s{t}{ṭ}g;
$in=~ s{T}{ṭh}g;
$in=~ s{d}{ḍ}g;
$in=~ s{D}{ḍh}g;
$in=~ s{N}{ṇ}g;

$in=~ s{M}{ṃ}g;

$in=~ s{w}{t}g;
$in=~ s{W}{th}g;
$in=~ s{x}{d}g;
$in=~ s{X}{dh}g;

$in=~ s{P}{ph}g;
$in=~ s{B}{bh}g;

$in=~ s{S}{ś}g;
$in=~ s{R}{ṣ}g;

print $in;
}
