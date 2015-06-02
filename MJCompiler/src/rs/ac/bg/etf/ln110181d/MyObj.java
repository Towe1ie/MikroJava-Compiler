package rs.ac.bg.etf.ln110181d;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyObj extends Obj
{
	public boolean isClassMember = false;
	public boolean isInherited = false;
	
	public MyObj(int kind, String name, Struct type)
	{
		super(kind, name, type);
		// TODO Auto-generated constructor stub
	}
	
	public MyObj(int kind, String name, Struct type, int adr, int level)
	{
		super(kind, name, type, adr, level);
		// TODO Auto-generated constructor stub
	}
	
	public MyObj(int kind, String name, Struct type, int adr, int level, boolean isClassMember)
	{
		super(kind, name, type, adr, level);
		this.isClassMember = isClassMember;
	}

}
