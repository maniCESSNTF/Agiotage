package Email;

public class MessagingException extends RuntimeException{
        public MessagingException(String message) {
            super(message);
        }
    
        public MessagingException() {
            super();
        }
    }
    