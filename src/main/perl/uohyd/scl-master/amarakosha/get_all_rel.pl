#!/usr/bin/perl

#  Copyright (C) 2006-2012 Shivaja Nair and Amba Kulkarni (ambapradeep@gmail.com)
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

@rel = ("stem2head","avayava","avayavI","aparAjAwi","parAjAwi","janaka","janya","pawi","pawnI","svAmI","sevaka","vESiRtya","vESiRtyavaw","sambanXiwa","vqwwi","vqwivAn");

for $rel (@rel){
$name = "LEX_".$rel;
tie(%{$name},GDBM_File,"DBM/$rel.gdbm",GDBM_READER,0444);
}

$in_word = $ARGV[0];
$out_encoding = $ARGV[1];
@word = split(/::/,$LEX_stem2head{$in_word});

print "digraph G\{\n";

$rels = "";
foreach $word (@word) {
  foreach $rel (@rel) {
   &get_rec($word,$rel,1);
  }
}

open(IN,">/tmp/inputnew$$.txt") || die "Can't open /tmp/inputnew$$.txt for writing";
print IN $nodes,$rels;
close(IN);

if($out_encoding eq "DEV" ) {
system("../converters/wx2utf8.sh < /tmp/inputnew$$.txt > /tmp/input$$.txt");
} elsif($out_encoding eq "ROMAN" ) {
system("../converters/wx2utf8roman.out < /tmp/inputnew$$.txt > /tmp/input$$.txt");
}

open(OUT,"</tmp/input$$.txt");
@word=<OUT>;
close(OUT);

print @word;
#print $nodes,$rels;
print "rankdir=RL\n";
print "\}";

$nodes = "";
sub get_rec{
  my($word,$rel,$level) = @_;
  #print "word rel level = $word $rel $level\n";
  if(($word ne "") && ($level < 4)){
           if($nodes !~ /\@Node$word /){
              $tmp = $word;
              $tmp =~ s/_/_\@/g;
              $tmp =~ s/-/_\@/g;
              $nodes .= "\@Node$tmp \[\@label=\"$word\"]\n";
           }
           $visited{$word} = 1;
           my @sub_node = split(/::/,&get_related($word,$rel));
           #print "sub_node = @sub_node\n";
           foreach $sub_node (@sub_node) {
    #        if($visited{$sub_node} != 1) {
             if($nodes !~ /\@Node$sub_node /){
                $tmp = $sub_node;
                $tmp =~ s/_/_\@/g;
                $tmp =~ s/-/_@/g;
                $nodes .= "\@Node$tmp \[\@label=\"$sub_node\"]\n";
             }
             &draw_dot($word,$rel,$sub_node);
             if($visited{$sub_node} != 1) {
                $visited{$sub_node} = 1;
                $level++;
                foreach $rel (@rel) {
		   #print "word rel subnode: $word $rel $sub_node\n";
                   &get_rec($sub_node,$rel,$level);
                }
             }
    #       }
          }
  }
}

sub get_related{
my($w,$rel) = @_;

$name = "LEX_".$rel;
return ${$name}{$w};
}
1;

sub draw_dot{
my($word1,$rel,$word2) = @_;

if($rel eq "stem2head"){$rel="arWaH";}

            $tmp = $word1;
            $tmp =~ s/_/_\@/g; 
            $tmp =~ s/-/_\@/g;
            	$tmp1 = $word2;
        	$tmp1 =~ s/_/_\@/g; 
	        $tmp1 =~ s/-/_@/g;
  $str="\@Node$tmp -> \@Node$tmp1 \[\@label=\"$rel\",\@fontcolor=\@red, \@fontname=\"\@Lohit \@Hindi\"\]\n";
  #print "str = $str\n";
  if(($rels !~ /\@Node$tmp -> \@Node$tmp1/) && ($rels !~ /\@Node$tmp1 -> \@Node$tmp/) && ($tmp ne $tmp1)) { 
     $rels .= $str;
  }
}
1;
