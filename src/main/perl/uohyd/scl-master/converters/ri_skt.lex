/*
##############################################################################
#
#  Copyright (C) Vinnet Chaitanya 1987-2012 and  Amba Kulkarni 1995-2012
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

##############################################################################
*/
 char map[]="01234567890123456789012345678901234567890123456789012345678901234⁄Àπ¿‚º∂£‹ª¥L¢¡Ê…Q÷’æﬁV√≈YÈ      § ∏ø·∑µÿ€∫≥—Ã∆Â»ﬂœ◊Ω›‘¬ƒÕ° ";
 char tmp;
NUKTA Z
OPERATOR_V V
OPERATOR_Y Y
SPECIAL_CATEGORY [zMH]
VOWEL_A a
VOWEL_Q Q
VOWEL_L L
VOWEL_REMAINING [AiIuUqeEoO]
CONSONANT [kKgGfcCjJFtTdDNwWxXnpPbBmyrlvSRsh]
ROM_WORD [A-Za-z0-9]+
%x CONS 
%%
@{ROM_WORD}				{printf("%s",yytext+1);}

{CONSONANT}				{
					printf("%c",map[yytext[0] ]);BEGIN CONS;
					}

z{NUKTA}				{
					printf("°È");
					}
\.					{
					printf("Í");
					}
\.{NUKTA}				{
					printf("ÍÈ");
					}
{NUKTA}					{
					printf("ÍÈ");
					}
<CONS>I{NUKTA}				{
					printf("‹È");
					BEGIN INITIAL;
					}

<CONS>{NUKTA}				{
					printf("ËÍÈ");
					BEGIN INITIAL;
					}

<CONS>{VOWEL_A}				{BEGIN INITIAL;}

<CONS>{VOWEL_Q}				{
					printf("ﬂÈ");
					BEGIN INITIAL;
					}

<CONS>{VOWEL_L}				{
					printf("€È");
					BEGIN INITIAL;
					}

<CONS>{VOWEL_REMAINING}			{
					printf("%c",map[yytext[0] ]);
					BEGIN INITIAL;
					}

<CONS>{VOWEL_REMAINING}{OPERATOR_V}+ 	{
					tmp = map[yytext[0] ]-yyleng+1;
					/*printf("%c",map[yytext[0] ]-yyleng+1); 
This produces a warning: 
format %c expects type int, but argument 2 has type yy_size_t
Hence everywhere a 'tmp' variable is introduced, and then it is printed.
*/
					printf("%c",tmp);
					BEGIN INITIAL;
					}

<CONS>{VOWEL_REMAINING}{OPERATOR_Y}+ 	{
					tmp = map[yytext[0] ]+yyleng-1;
					/*printf("%c",map[yytext[0] ]+yyleng-1); */
					printf("%c",tmp);
					BEGIN INITIAL;
					}

<CONS>{CONSONANT}			{
					printf("Ë%c",map[yytext[0] ]);
					}

<CONS>{CONSONANT}{OPERATOR_V}+		{
					tmp = map[yytext[0] ]-yyleng+1;
					/*printf("Ë%c",map[yytext[0] ]-yyleng+1);*/
					printf("Ë%c",tmp);
					}

<CONS>{CONSONANT}{OPERATOR_Y}+		{
					tmp = map[yytext[0] ]+yyleng-1;
					/*printf("Ë%c",map[yytext[0] ]+yyleng-1);*/
					printf("Ë%c",tmp);
					}

<CONS>(.|\n)				{
					printf("Ë%c",yytext[0]);
					BEGIN INITIAL;
					}

{VOWEL_REMAINING}			{
					printf("%c",map[yytext[0] ]-53);
					}

{VOWEL_A}				{
					printf("%c",map[yytext[0] ]);
					}

{VOWEL_Q}				{
					printf("™È");
					}

{VOWEL_L}				{
					printf("¶È");
					}
{SPECIAL_CATEGORY}			{
					printf("%c",map[yytext[0] ]);
					}

{CONSONANT}{OPERATOR_V}+		{
					tmp = map[yytext[0] ]-yyleng+1;
				/*	printf("%c",map[yytext[0] ]-yyleng+1); */
					printf("%c",tmp);
					BEGIN CONS;
					}

{CONSONANT}{OPERATOR_Y}+		{
					tmp = map[yytext[0] ]+yyleng-1;
				/*	printf("%c",map[yytext[0] ]+yyleng-1); */
					printf("%c",tmp);
					BEGIN CONS;
					}

{VOWEL_REMAINING}{OPERATOR_V}+		{
					tmp = map[yytext[0] ]-53-yyleng+1;
				/*	printf("%c",map[yytext[0] ]-53-yyleng+1); */
					printf("%c",tmp);
					}

{VOWEL_REMAINING}{OPERATOR_Y}+		{
					tmp = map[yytext[0] ]-53+yyleng-1;
				/*	printf("%c",map[yytext[0] ]-53+yyleng-1); */
					printf("%c",tmp);
					}


{VOWEL_A}{OPERATOR_V}+			{
					tmp = map[yytext[0] ]-yyleng+1;
				/*	printf("%c",map[yytext[0] ]-yyleng+1); */
					printf("%c",tmp);
					}

{VOWEL_A}{OPERATOR_Y}+			{
					tmp = map[yytext[0] ]+yyleng-1;
				/*	printf("%c",map[yytext[0] ]+yyleng-1); */
					printf("%c",tmp);
					}
\.Y					{
					printf("ÍÈ");
					}

