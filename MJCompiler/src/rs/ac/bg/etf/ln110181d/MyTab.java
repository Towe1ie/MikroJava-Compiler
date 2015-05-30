package rs.ac.bg.etf.ln110181d;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyTab extends Tab
{
	public static final Struct boolType = new Struct(Struct.Bool);
	public static final Struct stringType = new Struct(Struct.Array, charType);
	
	public static Obj intObj, charObj, boolObj, stringObj;
	
	public static void init()
	{
		Tab.init();
		
		// currentScope je nakon init-a universe
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		currentScope.addToLocals(new Obj(Obj.Type, "string", stringType));
		
		intObj = currentScope.findSymbol("int");
		charObj = currentScope.findSymbol("char");
		boolObj = currentScope.findSymbol("bool");
		stringObj = currentScope.findSymbol("string");
	}
}
