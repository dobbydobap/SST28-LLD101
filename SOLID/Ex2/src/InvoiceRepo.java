public interface InvoiceRepo {
    void save(String id, String text);
    int countLines(String id);
}