package pl.first.firstjava;

public class InvalidDatabaseException extends DaoException {
    
    public InvalidDatabaseException() {
        super(); 
    }
    
    public InvalidDatabaseException(String message) { 
        super(message); 
    }

    public InvalidDatabaseException(String message, Throwable cause) { 
        super(message, cause);      
    }

    public InvalidDatabaseException(Throwable cause) { 
        super(cause); 
    }
}
