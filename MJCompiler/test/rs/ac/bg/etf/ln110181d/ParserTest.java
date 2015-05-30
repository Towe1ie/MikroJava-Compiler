package rs.ac.bg.etf.ln110181d;

import java.io.*;

//import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.ln110181d.util.Log4JUtils;

public class ParserTest
{

	static
	{
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args)
	{
		Logger log = Logger.getLogger(ParserTest.class);
		
		File sourceCodeFile = new File("test/program.mj");

		try (BufferedReader br = new BufferedReader(new FileReader(sourceCodeFile));)
		{
			Yylex lexer = new Yylex(br);
			MJParser parser = new MJParser(lexer);
			parser.parse();
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}

}
