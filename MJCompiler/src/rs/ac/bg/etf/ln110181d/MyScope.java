package rs.ac.bg.etf.ln110181d;

import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyScope extends Scope
{
	public Obj outerScopeObj;
	public boolean semanticError = false;
	public boolean isClassScope = false;

	public MyScope(Scope outer, Obj outerScopeObj)
	{
		super(outer);
		this.outerScopeObj = outerScopeObj;
	}

	public boolean replace(Obj newObj)
	{
		SymbolDataStructure locals = super.getLocals();
		Obj obj = (locals != null) ? locals.searchKey(newObj.getName()) : null;
		if (obj != null)
		{
			locals.deleteKey(newObj.getName());
		}
		return this.addToLocals(newObj);
	}
}
