%%
@[a-zA-Z]+	{printf("%s",yytext+1);}
A	{printf("ā");}
I	{printf("ī");}
U	{printf("ū");}

E	{printf("ai");}
O	{printf("au");}

H	{printf("ḥ");}
q	{printf("ṛ");}

K	{printf("kh");}
G	{printf("gh");}
f	{printf("ṅ");}

C	{printf("ch");}
J	{printf("jh");}
F	{printf("ñ");}

t	{printf("ṭ");}
T	{printf("ṭh");}
d	{printf("ḍ");}
D	{printf("ḍh");}
N	{printf("ṇ");}

M	{printf("ṃ");}

w	{printf("t");}
W	{printf("th");}
x	{printf("d");}
X	{printf("dh");}

P	{printf("ph");}
B	{printf("bh");}

S	{printf("ś");}
R	{printf("ṣ");}
%%
/*Q	{printf("\.rr");}
L	{printf("\.l");} */
