package pl.first.firstjava;

public class InvalidFileException extends DaoException {
    
    public InvalidFileException() {
        super(); 
    }
    
    public InvalidFileException(String message) { 
        super(message); 
    }

    public InvalidFileException(String message, Throwable cause) { 
        super(message, cause);      
    }

    public InvalidFileException(Throwable cause) { 
        super(cause); 
    }
    
}
