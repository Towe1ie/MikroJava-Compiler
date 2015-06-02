package rs.ac.bg.etf.ln110181d.util;

import rs.ac.bg.etf.ln110181d.MyStruct;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MySymbolTableVisitor extends SymbolTableVisitor
{

	protected StringBuilder output = new StringBuilder();
	protected final String indent = "   ";
	protected StringBuilder currentIndent = new StringBuilder();

	protected void nextIndentationLevel()
	{
		currentIndent.append(indent);
	}

	protected void previousIndentationLevel()
	{
		if (currentIndent.length() > 0)
			currentIndent.setLength(currentIndent.length() - indent.length());
	}

	@Override
	public void visitObjNode(Obj objToVisit)
	{
		// output.append("[");
		output.append("(Kind) ");
		switch (objToVisit.getKind())
		{
			case Obj.Con:
				output.append("Con ");
				break;
			case Obj.Var:
				output.append("Var ");
				break;
			case Obj.Type:
				output.append("Type ");
				break;
			case Obj.Meth:
				output.append("Meth ");
				break;
			case Obj.Fld:
				output.append("Fld ");
				break;
			case Obj.Prog:
				output.append("Prog ");
				break;
		}

		output.append("(Name) " + objToVisit.getName());
		output.append(": ");

		boolean classKind = false;

		output.append("(Type) ");
		if (objToVisit.getKind() == Obj.Type
				&& objToVisit.getType().getKind() == Struct.Class)
		{
			classKind = true;
			output.append("(Kind) Class");
			output.append(", ");
			output.append("(Addr) " + objToVisit.getAdr());
			output.append(", ");
			output.append("(Level) " + objToVisit.getLevel() + " ");
			nextIndentationLevel();
			for (Obj o : objToVisit.getType().getMembers())
			{
				output.append("\n" + currentIndent.toString());
				o.accept(this);
			}
			previousIndentationLevel();
		}
		else
			objToVisit.getType().accept(this);

		if (!classKind)
		{
			output.append(", ");
			output.append("(Addr) " + objToVisit.getAdr());
			output.append(", ");
			output.append("(Level) " + objToVisit.getLevel() + " ");
		}

		if (objToVisit.getKind() == Obj.Prog
				|| objToVisit.getKind() == Obj.Meth)
		{
			output.append("\n");
			nextIndentationLevel();
		}

		for (Obj o : objToVisit.getLocalSymbols())
		{
			output.append(currentIndent.toString());
			o.accept(this);
			output.append("\n");
		}

		if (objToVisit.getKind() == Obj.Prog
				|| objToVisit.getKind() == Obj.Meth)
			previousIndentationLevel();

		// output.append("]");

	}

	@Override
	public void visitScopeNode(Scope scope)
	{
		for (Obj o : scope.values())
		{
			o.accept(this);
			output.append("\n");
		}
	}

	@Override
	public void visitStructNode(Struct structToVisit)
	{
		output.append("(Kind) ");
		switch (structToVisit.getKind())
		{
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Bool:
				output.append("bool");
				break;
			case Struct.Array:
				output.append("Arr of ");
				structToVisit.getElemType().accept(this);
				// switch (structToVisit.getElemType().getKind())
				// {
				// case Struct.None:
				// output.append("notype");
				// break;
				// case Struct.Int:
				// output.append("int");
				// break;
				// case Struct.Char:
				// output.append("char");
				// break;
				// case Struct.Bool:
				// output.append("bool");
				// break;
				// case Struct.Class:
				// output.append("Class");
				// break;
				// }
				break;
			case Struct.Class:
				if (structToVisit instanceof MyStruct)
				{
					MyStruct struct = (MyStruct) structToVisit;
					output.append("Class [" + struct.myObj.getName() + "]");
				}
				else
				{
					output.append("Class [");
					// nextIndentationLevel();
					for (Obj obj : structToVisit.getMembers())
					{
						// output.append("\n");
						// output.append(currentIndent.toString());
						obj.accept(this);

					}
					// previousIndentationLevel();
					// output.append(currentIndent.toString() + "]");
					output.append("]");
				}
				break;
		}

	}

	public String getOutput()
	{
		return output.toString();
	}

}
