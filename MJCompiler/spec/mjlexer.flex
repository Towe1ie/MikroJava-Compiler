package rs.ac.bg.etf.ln110181d;

import java_cup.runtime.Symbol;

%%

%{
	private Symbol newSymbol(int type)
	{
		return new Symbol(type, yyline + 1, yycolumn);
	}
	
	private Symbol newSymbol(int type, Object value)
	{
		return new Symbol(type, yyline + 1, yycolumn, value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return newSymbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"	{ return newSymbol(sym.PROGRAM, yytext()); }
"break"		{ return newSymbol(sym.BREAK, yytext()); }
"class"		{ return newSymbol(sym.CLASS, yytext()); }
"else"		{ return newSymbol(sym.ELSE, yytext()); }
"const"		{ return newSymbol(sym.CONST, yytext()); }
"if"		{ return newSymbol(sym.IF, yytext()); }
"new"		{ return newSymbol(sym.NEW, yytext()); }
"print"		{ return newSymbol(sym.PRINT, yytext()); }
"read"		{ return newSymbol(sym.READ, yytext()); }
"return"	{ return newSymbol(sym.RETURN, yytext()); }
"void"		{ return newSymbol(sym.VOID, yytext()); }
"while"		{ return newSymbol(sym.WHILE, yytext()); }
"extends"	{ return newSymbol(sym.EXTENDS, yytext()); }

"+" 		{ return newSymbol(sym.ADD, yytext()); }
"-" 		{ return newSymbol(sym.SUB, yytext()); }
"*" 		{ return newSymbol(sym.MUL, yytext()); }
"/" 		{ return newSymbol(sym.DIV, yytext()); }
"%" 		{ return newSymbol(sym.MOD, yytext()); }
"++" 		{ return newSymbol(sym.INC, yytext()); }
"--" 		{ return newSymbol(sym.DEC, yytext()); }
"==" 		{ return newSymbol(sym.EQ, yytext()); }
"!=" 		{ return newSymbol(sym.NEQ, yytext()); }
">" 		{ return newSymbol(sym.GRT, yytext()); }
">=" 		{ return newSymbol(sym.GRTE, yytext()); }
"<" 		{ return newSymbol(sym.LESS, yytext()); }
"<=" 		{ return newSymbol(sym.LESSE, yytext()); }

"&&" 		{ return newSymbol(sym.LAND, yytext()); }
"||" 		{ return newSymbol(sym.LOR, yytext()); }

"=" 		{ return newSymbol(sym.ASSIGN, yytext()); }

";" 		{ return newSymbol(sym.SEMI, yytext()); }
"," 		{ return newSymbol(sym.COMMA, yytext()); }

"(" 		{ return newSymbol(sym.LPAREN, yytext()); }
")" 		{ return newSymbol(sym.RPAREN, yytext()); }
"[" 		{ return newSymbol(sym.LBRACKET, yytext()); }
"]" 		{ return newSymbol(sym.RBRACKET, yytext()); }
"{" 		{ return newSymbol(sym.LBRACE, yytext()); }
"}"			{ return newSymbol(sym.RBRACE, yytext()); }

"//"				{ yybegin(COMMENT); }
<COMMENT> .			{ yybegin(COMMENT); }
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }

[0-9]+							{ return newSymbol(sym.NUMBER, new Integer(yytext())); }
([a-z]|[A-Z]|_)[a-z|A-Z|0-9|_]*	{ return newSymbol(sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska (" + yytext() + ") u liniji " + (yyline + 1) + " i koloni " + yycolumn); }
