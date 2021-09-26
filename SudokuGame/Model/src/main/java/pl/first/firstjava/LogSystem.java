package pl.first.firstjava;

import java.util.logging.Logger;

public class LogSystem {
    
    private static Logger log;
    
    public static Logger getConfiguration() {
        
        System.setProperty("java.util.logging.config.file",
                "C:/logConfig/logging.properties");

        log = Logger.getLogger(LogSystem.class.getName());

        return log;
    }
}
