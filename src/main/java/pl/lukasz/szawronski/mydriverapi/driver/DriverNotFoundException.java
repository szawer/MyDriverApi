package pl.lukasz.szawronski.mydriverapi.driver;

public class DriverNotFoundException extends Throwable {
    public DriverNotFoundException(String message) {
        super(message);
    }
}
