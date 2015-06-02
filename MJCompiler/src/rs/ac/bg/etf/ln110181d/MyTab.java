package rs.ac.bg.etf.ln110181d;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyTab extends Tab
{
	public static final MyStruct boolType = new MyStruct(Struct.Bool);
	public static final MyStruct stringType = new MyStruct(Struct.Array,
			charType);
	public static int currLevel = -1;

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

		stringType.myObj = stringObj;
		boolType.myObj = boolObj;

		chrObj.setAdr(1);
		ordObj.setAdr(1);
		lenObj.setAdr(1);

		currLevel = -1;
	}

	public static void openScope(Obj currentScopeObj)
	{
		currentScope = new MyScope(currentScope, currentScopeObj);
		currLevel++;
	}

	public static void closeScope()
	{
		currentScope = currentScope.getOuter();
		currLevel--;
	}

	public static MyScope getCurrentScope()
	{
		return (currLevel != -1) ? (MyScope) currentScope : null;
	}

	public static Obj insert(int kind, String name, Struct type,
			boolean classMember)
	{
		// create a new Object node with kind, name, type
		MyObj newObj = new MyObj(kind, name, type, 0, currLevel, classMember);
		boolean insertWithReplace = (getCurrentScope() != null) && (getCurrentScope().isClassScope = true);
		
		if (insertWithReplace)
		{
			MyScope currScope = (MyScope) currentScope;
			currScope.replace(newObj);
			return newObj;
		}
		else
		{
			if (!currentScope.addToLocals(newObj))
			{
				Obj res = currentScope.findSymbol(name);
				return (res != null) ? res : noObj;
			}
			else
				return newObj;
		}

	}

	public static void replace(int kind, String name, Struct type,
			boolean classMember)
	{

	}

	public static Obj getTypeObj(Struct typeStruct)
	{
		if (typeStruct instanceof MyStruct)
			return ((MyStruct) typeStruct).myObj;
		else
		{
			switch (typeStruct.getKind())
			{
				case Struct.Int:
					return MyTab.intObj;
				case Struct.Char:
					return MyTab.charObj;
				case Struct.Bool:
					return MyTab.boolObj;
				default:
					return MyTab.noObj;
			}

		}
	}

	public static String getTypeName(Struct typeStruct)
	{
		if (typeStruct.getKind() == Struct.Array)
			return "Arr of (" + getTypeName(typeStruct.getElemType()) + ")";
		return getTypeObj(typeStruct).getName();
	}

}
