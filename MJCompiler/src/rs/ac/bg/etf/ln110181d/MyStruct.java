package rs.ac.bg.etf.ln110181d;

import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyStruct extends Struct
{
	public Obj myObj = null;
	public Obj superClass = MyTab.noObj;
	
	public MyStruct(int kind, Obj myObj)
	{
		super(kind);
		this.myObj = myObj;
	}

	public MyStruct(int kind, Struct elemType, Obj myObj)
	{
		super(kind, elemType);
		this.myObj = myObj;
	}
	
	public MyStruct(int kind)
	{
		super(kind);
	}

	public MyStruct(int kind, Struct elemType)
	{
		super(kind, elemType);
	}

	public MyStruct(int kind, SymbolDataStructure members, Obj myObj)
	{
		super(kind, members);
		this.myObj = myObj;
	}
}
