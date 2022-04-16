package nl.tomjansen.loopgain.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(){
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

}
