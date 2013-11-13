#!/usr/bin/perl -w
use strict;
use warnings;
use Shell;
use CGI;
use CGI qw( :standard );
use LWP::Simple qw/!head/;
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




print CGI::header('-type'=>'text/html', '-expires'=>60*60*24, '-charset' => 'UTF-8');

my $word=param('word');
my $relation=param('relation');
my $encoding=param('encoding');
my $out_encoding=param('out_encoding');

my $pid = $$;

system("/home/vvasuki/scl/amarakosha/callrel.pl $word $relation $encoding $out_encoding $pid");

print "<center><font size=5 color=\"white\">&nbsp;</font></center>";
print "</td></tr></table>";
