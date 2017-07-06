package db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {

	private static final Logger log = LogManager.getLogger(Log4J.class.getName());
	
	public static void main(String[] args) {
        
        log.trace("trace message");
        log.debug("debug message");
        log.warn("warn message");
        log.info("info message");
        log.error("error message");
        log.fatal("fatal message");
 
    }
	
}
