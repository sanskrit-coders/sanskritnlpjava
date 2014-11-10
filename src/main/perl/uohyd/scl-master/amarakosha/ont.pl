#!/usr/bin/perl

# use strict; use warnings;

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

my $input = $ARGV[0];
my $rel_dbm = $ARGV[1];
#ensure onto is last below.
my @relations = qw(aparAjAwi
onto
);

my @sambanXi_rel = qw(
avayava
pawi
pawnI
svAmI
janaka
sambanXi1
sambanXi2
vESiRtyavaw
janya
sevaka
vqwwivAn
avayavI
vESiRtya
vqwwi
parAjAwi
);

getDetails($input);

sub getDetails {
	my ($input) = @_;
	my @input_words = split(",",$input);
	my $word = $input_words[0];
	my $shloka_sankhyA = $input_words[1];
	my $arWa = $input_words[4];
	my $output_mode = 'dict';
# 	print @input_words, "\n";
	my(%LEX,%LEX1,$head,$vargaH,$synset,$synset_info, @samAnArWaka, @related_words);

	die "can't open file for error log" unless open(TMP1,">>/tmp/error");
	tie(%LEX,GDBM_File,"DBM/stem2head.gdbm",GDBM_READER,0666) || die "can't open DBM/stem2head.gdbm";
	tie(%LEX1,GDBM_File,"DBM/synset_info.gdbm",GDBM_READER,0666) || die "can't open DBM/synsetinfo.gdbm";

	my @head = split(/::/,$LEX{$word});
# 	print join(",", @head), "\n";
	foreach $head (@head) {
# 		print $head, "\n";
# 		print $arWa, "\n";
		next if $head ne $arWa;
		$synset = $LEX1{$head};
# 		print $synset, "\n";
		$synset_info = "";
		if($synset) {
			@samAnArWaka = &synset_info($head,$synset);
		}
		my @fields;
		my $rel_dbm = "samAnArWaka";
# 		push(@fields, join(",", $rel_dbm,  @samAnArWaka));
		if ($output_mode eq 'dict' and scalar(@samAnArWaka)) {
			push(@fields, $rel_dbm . ":" . join(",", @samAnArWaka));
		} else {
			push(@fields, join(", ", @samAnArWaka));
		}
		
		for $rel_dbm(@sambanXi_rel){
			my @related_words = get_related_words($head, $rel_dbm);
			if ($output_mode eq 'dict' and scalar(@related_words)) {
				push(@fields, $rel_dbm . " : " . join(',', @related_words));
			} else {
				push(@fields, join(', ', @related_words));
			}
		}
		
		for $rel_dbm(@relations){
			my @related_words = get_related_words($head, $rel_dbm);
			my $sep = ',';
			$sep = ';' if($rel_dbm eq "onto");
# 			push(@fields, join(",", $rel_dbm,  @related_words));
			if ($output_mode eq 'dict' and scalar(@related_words)) {
				my $field_name = "padArwa-viBAgaH" if($rel_dbm eq "onto");
				push(@fields, $field_name . " : " . join(', ', @related_words));
			} else {
				push(@fields, join($sep, @related_words));
			}
		}

		push(@fields, $shloka_sankhyA);
		push(@fields, get_Sloka($shloka_sankhyA));
		
	#     print $synset, "\n";
		if ($output_mode eq 'dict') {
			my @non_empty_fields = grep(!/^$/, @fields);
			my $head_word = "$word $input_words[2] $input_words[4] $shloka_sankhyA";
			print "$head_word\t" . join(" || ", @input_words[2, 4], @non_empty_fields), "\n";
		} else {
			print $word . ";" . join(";", @input_words[2, 4], @fields), "\n";
		}
	}
	untie(%LEX);
	untie(%LEX1);
	close TMP1;
}
1;

sub get_related_words {
  my($head,$rel_dbm) = @_;
  my @related_words;
  my (%LEX2,%LEX3, $str);
#   print @_, "\n";
	tie(%LEX2,GDBM_File,"DBM/$rel_dbm.gdbm",GDBM_READER,0666) || die "can't open DBM/$rel_dbm.gdbm";
	tie(%LEX3,GDBM_File,"DBM/rule_onto.gdbm",GDBM_READER,0666) || die "can't open DBM/rule_onto.gdbm";
	
	if($rel_dbm eq "onto") {
		my @ont;
	#    print $LEX2{$word};
		my ($jAw,$upA) = split(/#/,$LEX2{$head});
		#print "jAwi = $jAw upAXi = $upA";
		if ($jAw ne ""){
			$str = $jAw;
			unshift(@ont, $str);
			while($LEX3{$str}){
	#                 $relata_info .= "=> $LEX3{$str}<\@br>";
				$str = $LEX3{$str};
				unshift(@ont, $str) if($str ne "paxArWaH");
			}
		}
		my @upAXis;
		$str = $upA;
		push(@upAXis, $str);
		while($LEX3{$str}){
			$str = $LEX3{$str};
			unshift(@upAXis, $str);
		}
		@related_words = (join(",", @upAXis), @ont);
	} elsif(($rel_dbm eq "avayavI") || ($rel_dbm eq "parAjAwi")){
		my $relata, $relata_synset, $prev;
		while($LEX2{$head}){
			$relata = $LEX2{$head};
# 			print $relata, "\n";
			$relata =~ s/::.*//;
			$relata_synset = $LEX1{$relata};
			if($relata_synset) {
				push(@related_words, $relata) ;
			} else {
				last;
			}
# 			To take care of vAdava infinite loop.
			$prev = $head;
			$head = $relata;
			my %rel_words_map = map { $_ => 1 } @related_words;
			last if $rel_words_map{$head};
		}
	} else {
		@related_words = split(/::/,$LEX2{$head});
	}
	untie(%LEX2);
	untie(%LEX3);
# 	print @related_words, "\n";
	@related_words;
}
1;

sub synset_info{
  my($head,$synset) = @_;
  my($synset_info);
  $add_info = "RANGE";  
  $start = ""; # stating sloka number
  $end = ""; # ending sloka number
  @synset = split(/::/,$synset);
#   print join(",", @synset), "\n";
  my @samAnArWaka;
  for ($i=0;$i<=$#synset;$i++) {
  ($wrd,$kANda,$lifgam,$vargaH) = split(/#/,$synset[$i]);
    push(@samAnArWaka, $wrd)
  }
  @samAnArWaka;
}
1;

# Example input: 1.1.7.2.2
sub get_Sloka {
	my ($index) = @_;
	chomp $index;
	$index =~ s/\.\d+.\d+$//;
	my $result = "";
	die "can't open file for reading $!" unless open(TMP,"<amara.wx");
	my $add_to_result = 0;
	# print "Sloka_$index\n";
	while(my $in = <TMP>){
		chomp $in;
		if($in =~ /<Sloka_$index>/) {
			$add_to_result = 1 ;
		} elsif($in =~ /<\/Sloka_$index>/) {
			$add_to_result = 0 ;
			break;
		} elsif ($add_to_result) {
			$result = $result . $in
		}

	}
	$result =~ s/^M/ /g;
	$result =~ s/\s/ /g;
	# print $result, "\n";
	close TMP;
	$result;
}
1;
