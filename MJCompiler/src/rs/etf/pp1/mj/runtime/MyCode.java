package rs.etf.pp1.mj.runtime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.IntBuffer;

import rs.ac.bg.etf.ln110181d.MyTab;

public class MyCode extends Code
{
	public static int heap[] = new int[8192];
	public static int heapSize = 1, byteCnt = 0;
	public static int noRelop = 6, checked = 7;

	public static void init()
	{
		// len function
		MyCode.put(MyCode.enter);
		MyCode.put(1);
		MyCode.put(1);
		MyCode.put(MyCode.load_n);
		MyCode.put(MyCode.arraylength);
		MyCode.put(MyCode.exit);
		MyCode.put(MyCode.return_);
		MyTab.lenObj.setAdr(0);
		
		int addr = MyCode.pc;
		// ord function
		MyCode.put(MyCode.enter);
		MyCode.put(1);
		MyCode.put(1);
		MyCode.put(MyCode.load_n);
		MyCode.put(MyCode.exit);
		MyCode.put(MyCode.return_);
		MyTab.ordObj.setAdr(addr);
		
		addr = MyCode.pc;
		// chr function
		MyCode.put(MyCode.enter);
		MyCode.put(1);
		MyCode.put(1);
		MyCode.put(MyCode.load_n);
		MyCode.put(MyCode.exit);
		MyCode.put(MyCode.return_);
		MyTab.chrObj.setAdr(addr);
	}

	public static void paddHeapSize()
	{
		if (byteCnt != 0)
		{
			heapSize++;
			byteCnt = 0;
		}
	}

	public static void heapPutWord(int word)
	{
		paddHeapSize();

		heap[heapSize++] = word;
	}

	public static void heapPutByte(byte _byte)
	{
		if (byteCnt == 4)
		{
			heapSize++;
			byteCnt = 0;
		}

		int data = heap[heapSize];
		int mask = 0x000000ff;
		mask <<= (3 - byteCnt) * 8;
		mask = ~mask;
		data &= mask;
		mask = _byte;
		mask <<= (3 - byteCnt) * 8;
		data |= mask;
		heap[heapSize] = data;
		byteCnt++;
		if (byteCnt == 0)
			heapSize++;
	}

	public static void write(OutputStream s)
	{
		int codeSize;
		try
		{
			codeSize = pc;
			put('M');
			put('J');
			put4(codeSize);
			put4(dataSize);
			paddHeapSize();
			put4(heapSize);
			put4(mainPc);
			s.write(buf, codeSize, pc - codeSize); // header
			s.write(buf, 0, codeSize); // code

			for (int i = 0; i < heapSize; ++i)
				for (int j = 3; j >= 0; --j)
					s.write(heap[i] >> 8 * j);
			s.close();
		}
		catch (IOException e)
		{
			error("Greska pri upisu u izlazni fajl");
		}
	}
}
