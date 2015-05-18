import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class ModifiedSimpleFormatter extends SimpleFormatter
{
	public String format(LogRecord record)
	{
		  if(record.getLevel() == Level.INFO)
		  {
		    return record.getMessage() + "\r\n";
		  }
		  else
		  {
		    return super.format(record);
		  }
	}
}

public class MyLogger {
  static private FileHandler fileTxt;
  static private ModifiedSimpleFormatter formatterTxt;
  static private Logger logger = null;

  static public void setup() throws IOException {

    // get the global logger to configure it
    logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // suppress the logging output to the console
    Logger rootLogger = Logger.getLogger("");
    Handler[] handlers = rootLogger.getHandlers();
    if (handlers[0] instanceof ConsoleHandler) {
      rootLogger.removeHandler(handlers[0]);
    }

    logger.setLevel(Level.INFO);
    logger.setUseParentHandlers(true);
    logger.entering("", "");
    fileTxt = new FileHandler("EventLogs.txt");

    // create a TXT formatter
    formatterTxt = new ModifiedSimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);
  }
  
  static public void clean()
  {
	  logger.removeHandler(fileTxt);
	  fileTxt.close();
  }
}
 
