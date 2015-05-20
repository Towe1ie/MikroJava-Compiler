package rs.ac.bg.etf.ln110181d;

import java.io.*;
import java.net.URL;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.ln110181d.util.Log4JUtils;

public class LexerTest
{

	static
	{
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args)
	{
		Logger log = Logger.getLogger(LexerTest.class);
		
		File sourceCodeFile = new File("test/program.mj");
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCodeFile));)
		{
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			
			while ((currToken = lexer.next_token()).sym != sym.EOF)
			{
				if (currToken != null)
				{
					log.info(currToken.toString() + " " + currToken.value.toString());
				}
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}

}
