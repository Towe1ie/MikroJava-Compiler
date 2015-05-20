package rs.ac.bg.etf.ln110181d;

import java.net.URL;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.ln110181d.util.Log4JUtils;

public class Test
{
	static
	{
		Log4JUtils logUtilsInstance = Log4JUtils.instance();
		URL loggConfigFile = logUtilsInstance.findLoggerConfigFile();
		DOMConfigurator.configure(loggConfigFile);
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Test.class);
		logger.info("Zdravo info");

	}

}
