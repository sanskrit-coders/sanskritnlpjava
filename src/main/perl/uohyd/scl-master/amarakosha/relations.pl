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

my $rel_dbm = $ARGV[0];
my $heading = $ARGV[1];
my $word = $ARGV[2];
my $out_encoding = $ARGV[3];
my $pid = $ARGV[4];
die "can't open file for error log" unless open(TMP1,">>/tmp/error");

my(%LEX,%LEX1,%LEX2,%LEX3,$head,$vargaH,$synset,$heading_info,$relata_info,$synset_info);

tie(%LEX,GDBM_File,"/home/vvasuki/scl/amarakosha/DBM/stem2head.gdbm",GDBM_READER,0666) || die "can't open DBM/stem2head.gdbm";
tie(%LEX1,GDBM_File,"/home/vvasuki/scl/amarakosha/DBM/synset_info.gdbm",GDBM_READER,0666) || die "can't open DBM/synsetinfo.gdbm";

if($rel_dbm ne "NULL") {
   tie(%LEX2,GDBM_File,"/home/vvasuki/scl/amarakosha/DBM/$rel_dbm.gdbm",GDBM_READER,0666) || die "can't open DBM/$rel_dbm.gdbm";
}
if($rel_dbm eq "onto") {
   tie(%LEX3,GDBM_File,"/home/vvasuki/scl/amarakosha/DBM/rule_onto.gdbm",GDBM_READER,0666) || die "can't open DBM/rule_onto.gdbm";
}

   $heading_info = "<\@br><\@font \@color=\"\@magenta\">$heading</\@font><\@br>";

  if($rel_dbm eq "NULL") { print "<\@center>$heading_info</\@center>"; }

  if($LEX{$word} eq "") {
     system("/home/vvasuki/scl/amarakosha/shw_stem.pl $pid $word | /usr/bin/sort -u > /tmp/outNotFound$pid.txt");
     if(-s "/tmp/outNotFound$pid.txt") {
        system("/home/vvasuki/scl/amarakosha/showMsg.pl $rel_dbm $out_encoding < /tmp/outNotFound$pid.txt");
     } else {print "\@Please \@Check \@the \@spelling\n";}
  } else {
    @head = split(/::/,$LEX{$word});
    foreach $head (@head) {
     $synset = $LEX1{$head};
     $synset_info = "";
     if($synset) {
        $synset_info = &synset_info($head,$synset);
     }
     if($rel_dbm ne "NULL") {
       if($rel_dbm eq "onto") {
    #    print $LEX2{$word};
         $relata_info = "";
         ($jAw,$upA) = split(/#/,$LEX2{$head});
         #print "jAwi = $jAw upAXi = $upA";
          if ($jAw ne ""){ 
            $str = $jAw;
             $relata_info = "<\@br><\@b>jAwi</\@b><\@br> => $str<\@br>";
            $heading_info = "";
            while($LEX3{$str}){
		$relata_info .= "=> $LEX3{$str}<\@br>";
                $str = $LEX3{$str};
            }
          }
          if ($upA ne ""){ 
            $str = $upA;
            $relata_info .= "<\@br><\@b>upAXi</\@b><\@br> => $str<\@br>";
            $heading_info = "";
            while($LEX3{$str}){
                $relata_info .= "=> $LEX3{$str}<\@br>";
                $str = $LEX3{$str};
            } 
          }
       } elsif(($rel_dbm eq "avayavI") || ($rel_dbm eq "parAjAwi")){
         $relata_info = "";
        while($LEX2{$head}){
           $relata = $LEX2{$head};
           $relata =~ s/::.*//;
           $relata_synset = $LEX1{$relata};
           if($relata_synset) {
              $relata_info .= &synset_info($relata,$relata_synset);
           }
           $head = $relata;
        }
       } else {
        @relata = split(/::/,$LEX2{$head});
        $relata_info = ""; 
        foreach $relata (@relata) { 
           $relata_synset = $LEX1{$relata};
           if($relata_synset) {
              $relata_info .= &synset_info($relata,$relata_synset);
           }
        }
       }
       if($relata_info) {
          print $synset_info,"<\@br>",$heading_info,$relata_info;
      print "<\@br>_____________________<\@br><\@br>";
       }
     } else { print $synset_info; }
   }
 }
untie(%LEX);
untie(%LEX1);
untie(%LEX2);
untie(%LEX3);
close TMP1;
print "<\@script> 
\$(\@function(){
\$(\"#\@tool \@a\").\@click(\@function(){ 	\@alert(\"\@h\");
});
\$(\"#\@tool \@a[\@title]\").\@tooltip();
});
</\@script>";

sub synset_info{
  my($head,$synset) = @_;
  my($synset_info);

   
    $add_info = "RANGE";  
    $start = ""; # stating sloka number
    $end = ""; # ending sloka number

    @synset = split(/::/,$synset);
	$range_info = &kANda_range1(@synset); #finding range of kANda;
	$range_info =~ /^(.)\.*/; $s = $1;
	$range_info = &get_Sloka_info($range_info,$wrd);
	print TMP1 $range_info;
    $synset_info = "<\@br><\@div \@id=\"\@tool\"><\@font \@color=\"\@green\"><\@a \@title=\"$range_info\">arWaH :: $head</a></\@font> | ";
    $synset_info .= "<\@font \@color=\"\@black\">";
      for ($i=0;$i<=$#synset;$i++) {
      ($wrd,$kANda,$lifgam,$vargaH) = split(/#/,$synset[$i]);
#	$range_info1 = &add_span($range_info,$wrd);
        if($i == 0){
           $synset_info .= "<\@font \@color=\"\@red\">vargaH :: $vargaH</\@font> | ";
	   if($kANda =~ /^$s/){
          	 $synset_info .= "<\@a \@title=\"kANda,varga,Sloka,pAxa :: $kANda\,lifga :: $lifgam,</\@br> $range_info\">".$wrd."</\@a>";
	   }
	   else{
          	 $synset_info .= "<\@a \@title=\"kANda,varga,Sloka,pAxa :: $kANda\,lifga :: $lifgam\">".$wrd."</\@a>";
	  }
        } else {
	   if($kANda =~ /^$s/){
           	$synset_info .= ", <\@a \@title=\"kANda,varga,Sloka,pAxa :: $kANda\,lifga :: $lifgam, </\@br> $range_info\">".$wrd."</\@a>";
	   }
	   else{
           	$synset_info .= ", <\@a \@title=\"kANda,varga,Sloka,pAxa :: $kANda\,lifga :: $lifgam\">".$wrd."</\@a>";

	  }
        }
      }
      $synset_info .= "</\@font></\@div>";
$synset_info;
 }
1;


#code for finding range;
sub kANda_range1{
	my @synset = @_;
	my $start=0 , $end = 0;
	 for ($i=0;$i<=$#synset;$i++) {
  		 ($wrd,$kANda,$lifgam,$vargaH) = split(/#/,$synset[$i]);
       		 if($i == 0){ $start = $kANda;}
		 else {  $start =~ /(.)\.*/; $s =$1;
			if($kANda =~ /^$s\./){ $end = $kANda;}
		}
	
	}
  my $result = $start."-".$end;
$result;
}
1;


sub get_Sloka_info{
	my ($range,$wrd) = @_;
	chomp $range; chomp $wrd;
	$result ="";
	my $count = 0;
	my ($s,$e) = split (/-/,$range);
	$s =~ s/\.[0-9]+\.[0-9]+$//;
        $e =~ s/\.[0-9]+\.[0-9]+$//;
	die "can't open file for reading $!" unless open(TMP,"</home/vvasuki/scl/amarakosha/amara.wx");
	while(my $in = <TMP>){
		chomp $in;
		if($in =~ /<Sloka_$s/ and $count ==0){ my $result .= $in; $count =1; }
		elsif($in !~ /<\/Sloka_$e/ and $count == 1){ $result .= $in;}
		elsif($in =~ /<\/Sloka_$e/){ $count =0;}
	}
$result =~ s/<\/Sloka_[0-9]+\.[0-9]+\.[0-9]+>/<\/\@br>/g;
$result =~ s/<Sloka_[0-9]+\.[0-9]+\.[0-9]+>/<\/\@br>/g;
$result =~ s/^M//g;
$result =~ s/ //g;
close TMP;

$result;
}
1;


sub add_span{
my ($res,$wr) = @_;
$wrd =~ /(.)(.*)(.)/;
my $word = $2;
 $res =~ s/$word/<\@word>$word<\/\@word>/g;


$res;
}
1;
