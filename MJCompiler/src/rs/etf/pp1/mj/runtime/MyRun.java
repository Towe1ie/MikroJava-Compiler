package rs.etf.pp1.mj.runtime;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyRun extends Run
{
	static void load (String name) throws IOException, FormatException {
		int codeSize;
		byte[] sig = new byte[2];
		DataInputStream in = new DataInputStream(new FileInputStream(name));
		in.read(sig, 0, 2);
		if (sig[0] != 'M' || sig[1] != 'J') 
			throw new FormatException("wrong marker");
		codeSize = in.readInt();
		if (codeSize <= 0) throw new FormatException("codeSize <= 0");
		dataSize = in.readInt();
		if (dataSize < 0) throw new FormatException("dataSize < 0");
		
		heap = new int[heapSize]; // fixed sized heap
		free = in.readInt();
		
		startPC = in.readInt();
		if (startPC < 0 || startPC >= codeSize) 
			throw new FormatException("startPC not in code area");
		code = new byte[codeSize];
		in.read(code, 0, codeSize);
		for (int i = 0; i < free; ++i)
			heap[i] = in.readInt();
		
	}
	
	public static void main(String[] arg)
	{
		{
			String fileName = null;
			debug = false;
			for (int i = 0; i < arg.length; i++)
			{
				if (arg[i].equals("-debug"))
					debug = true;
				else
					fileName = arg[i];
			}
			if (fileName == null)
			{
				System.out.println("Syntax: java ssw.mj.Run filename [-debug]");
				return;
			}
			try
			{
				load(fileName);
				
				 // global data as specified in
				data = new int[dataSize];						// classfile
				stack = new int[eStackSize]; // expression stack
				local = new int[mStackSize]; // method stack
				fp = 0;
				sp = 0;
				esp = 0;
				//free = 1; // no block should start at address 0
				long startTime = System.currentTimeMillis();

				interpret();

				System.out.print("\nCompletion took "
						+ (System.currentTimeMillis() - startTime) + " ms");
			}
			catch (FileNotFoundException e)
			{
				System.out.println("-- file " + fileName + " not found");
			}
			catch (IOException e)
			{
				System.out.println("-- error reading file " + fileName);
			}
			catch (FormatException e)
			{
				System.out.println("-- corrupted object file " + fileName
						+ ": " + e.getMessage());
			}

		}
	}
}
