package rs.ac.bg.etf.ln110181d;

import java.io.*;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.ln110181d.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.MyCode;

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
		
		File sourceCodeFile = new File("test/test302.mj");
		

		try (BufferedReader srcBr = new BufferedReader(new FileReader(sourceCodeFile));
				BufferedReader libBr = new BufferedReader(new FileReader("libFunctions.mj"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("test/finalSource.mj"));)
		{
			String line = null;
			while ((line = srcBr.readLine()) != null)
			{
				bw.write(line + "\n");
				if (line.contains("{"))
					break;
			}
			
			while ((line = libBr.readLine()) != null)
				bw.write(line + "\n");
			
			while ((line = srcBr.readLine()) != null)
			{
				bw.write(line + "\n");
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage().toString());
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader("test/finalSource.mj"));)
		{
			Yylex lexer = new Yylex(br);
			MJParser parser = new MJParser(lexer);
			parser.parse();
			
	        if (!parser.errorDetected) {
	        	File objFile = new File("test/program.obj");
	        	if (objFile.exists())
	        		objFile.delete();
	        	MyCode.write(new FileOutputStream(objFile));
	        	log.info("Parsiranje uspesno zavrseno!");
	        }
	        else {
	        	log.error("Parsiranje NIJE uspesno zavrseno!");
	        }
		}
		catch (Exception e)
		{
			log.error(e.getMessage().toString());
			//log.error(e.getStackTrace().toString());
		}
	}

}
