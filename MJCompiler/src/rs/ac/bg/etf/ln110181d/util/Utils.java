package rs.ac.bg.etf.ln110181d.util;

import rs.ac.bg.etf.ln110181d.*;

public class Utils
{
	public static String symToString(int code)
	{
		String s = "Nepoznat simbol!";
		
		switch (code)
		{
			case sym.ADD:
				s = "ADD";
				break;
			case sym.ASSIGN:
				s = "ASSIGN";
				break;
			case sym.BOOL_CONST:
				s = "BOOL_CONST";
				break;
			case sym.BREAK:
				s = "BREAK";
				break;
			case sym.CHAR_CONST:
				s = "CHAR_CONST";
				break;
			case sym.CLASS:
				s = "CLASS";
				break;
			case sym.COMMA:
				s = "COMMA";
				break;
			case sym.CONST:
				s = "CONST";
				break;
			case sym.DEC:
				s = "DEC";
				break;
			case sym.DIV:
				s = "DIV";
				break;	
			case sym.DOT:
				s = "DOT";
				break;
			case sym.ELSE:
				s = "ELSE";
				break;
			case sym.EOF:
				s = "EOF";
				break;
			case sym.error:
				s = "error";
				break;
			case sym.EXTENDS:
				s = "EXTENDS";
				break;
			case sym.GRT:
				s = "GRT";
				break;
			case sym.GRTE:
				s = "GRTE";
				break;
			case sym.IDENT:
				s = "IDENT";
				break;
			case sym.IF:
				s = "IF";
				break;
			case sym.INC:
				s = "INC";
				break;
			case sym.LAND:
				s = "LAND";
				break;
			case sym.LBRACE:
				s = "LBRACE";
				break;
			case sym.LBRACKET:
				s = "LBRACKET";
				break;
			case sym.LESS:
				s = "LESS";
				break;
			case sym.LESSE:
				s = "LESSE";
				break;
			case sym.LOR:
				s = "LOR";
				break;
			case sym.LPAREN:
				s = "LPAREN";
				break;
			case sym.MOD:
				s = "MOD";
				break;
			case sym.MUL:
				s = "MUL";
				break;
			case sym.NEQ:
				s = "NEQ";
				break;
			case sym.NEW:
				s = "NEW";
				break;
			case sym.NUMBER:
				s = "NUMBER";
				break;
			case sym.PRINT:
				s = "PRINT";
				break;
			case sym.PROGRAM:
				s = "PROGRAM";
				break;
			case sym.RBRACE:
				s = "RBRACE";
				break;
			case sym.RBRACKET:
				s = "RBRACKET";
				break;
			case sym.READ:
				s = "READ";
				break;
			case sym.RETURN:
				s = "RETURN";
				break;
			case sym.RPAREN:
				s = "RPAREN";
				break;
			case sym.SEMI:
				s = "SEMI";
				break;
			case sym.STRING_CONST:
				s = "STRING_CONST";
				break;
			case sym.SUB:
				s = "SUB";
				break;
			case sym.VOID:
				s = "VOID";
				break;
			case sym.WHILE:
				s = "WHILE";
				break;		
		}
		
		return s;
	}
}