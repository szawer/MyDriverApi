package pl.lukasz.szawronski.mydriverapi.VAT;

public class InvoiceNotFoundException extends Throwable {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
