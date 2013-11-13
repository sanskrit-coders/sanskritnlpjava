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



sub convert{
        my($encoding,$pid,$word) = @_;

	open(TMP,">/tmp/word.$pid") || die "Can't open /tmp/word.$pid for writing";
        print TMP $word,"\n";
        close(TMP);
              
           if($encoding eq "WX")
            {
            system("/bin/cat /tmp/word.$pid > /tmp/word1.$pid");
            }
          
           if($encoding eq "VH")
            {
               system("/home/vvasuki/scl/converters/velthuis-wx.out < /tmp/word.$pid > /tmp/word1.$pid");
            }
 if($encoding eq "KH")
            {
               system("/home/vvasuki/scl/converters/kyoto_ra.out < /tmp/word.$pid > /tmp/word1.$pid");           
             }
            
         if($encoding eq "SLP")
            {
               system("/home/vvasuki/scl/converters/slp2wx.out < /tmp/word.$pid > /tmp/word1.$pid");
            }
            if($encoding eq "Itrans") 
	    {
           system("/home/vvasuki/scl/converters/itrans_ra.out < /tmp/word.$pid > /tmp/word1.$pid");           
               }

            if($encoding eq "Unicode") 
	    {
              system("/home/vvasuki/scl/converters/utf82iscii.pl < /tmp/word.$pid | /home/vvasuki/scl/converters/ir_skt >/tmp/word1.$pid");
           } 
            open(TMP,"</tmp/word1.$pid") || die "Can't open word1.$pid for reading; exiting";
            $word = <TMP>;
            close(TMP);
            system ("rm /tmp/word.$pid /tmp/word1.$pid");
         
return $word; 
}
1;
