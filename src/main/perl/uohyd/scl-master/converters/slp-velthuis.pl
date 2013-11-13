#! /usr/bin/perl  
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




while($in=<STDIN>) {
$in=~s{Y}{~n}g;
$in=~s{A}{aa}g;
$in=~s{w}{.t}g;
$in=~s{W}{.th}g;
$in=~s{I}{ii}g;
$in=~s{q}{.d}g;
$in=~s{Q}{.dh}g;
$in=~s{U}{uu}g;
$in=~s{R}{.n}g;
$in=~s{f}{.r}g;
$in=~s{F}{.rr}g;
$in=~s{T}{th}g;
$in=~s{x}{.l}g;
$in=~s{D}{dh}g;
$in=~s{E}{ai}g;
$in=~s{O}{au}g;
$in=~s{P}{ph}g;
$in=~s{M}{.m}g;
$in=~s{~}{"m}g;
$in=~s{B}{bh}g;
$in=~s{H}{.h}g;
$in=~s{K}{kh}g;
$in=~s{G}{gh}g;
$in=~s{N}{f}g;
$in=~s{S}{z}g;
$in=~s{z}{.s}g;
$in=~s{C}{ch}g;
$in=~s{J}{jh}g;
}
