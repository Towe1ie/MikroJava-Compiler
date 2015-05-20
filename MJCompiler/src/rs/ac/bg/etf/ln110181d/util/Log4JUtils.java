package rs.ac.bg.etf.ln110181d.util;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class Log4JUtils
{
	private static Log4JUtils log = new Log4JUtils();
	
	public static Log4JUtils instance()
	{
		return log;
	}
	
	public URL findLoggerConfigFile()
	{
		return Thread.currentThread().getContextClassLoader().getResource("log4j.xml");
	}
	
	public void prepareLogFile(Logger root)
	{
		Appender appender = root.getAppender("file");
		
		if (!(appender instanceof FileAppender))
			return;
		FileAppender fileAppender = (FileAppender)appender;
		
		String logFileName = fileAppender.getFile();
		logFileName = logFileName.substring(0, logFileName.lastIndexOf('.')) + "-test.log";
		
		File logFile = new File(logFileName);
		
		DateFormat dateFormat = new SimpleDateFormat("(dd-MM-HH-mm-ss)");
		Date date = new Date();
		
		File renamedFile = new File(logFile.getAbsolutePath() + "-" + dateFormat.format(date));
		
		if (logFile.exists())
		{
			if (!logFile.renameTo(renamedFile))
				System.err.println("Could not rename log file");
		}
		
		fileAppender.setFile(logFile.getAbsolutePath());
		fileAppender.activateOptions();
	}
}
