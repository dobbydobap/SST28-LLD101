public class FileStoreRepo implements InvoiceRepo {

    private final FileStore store;

    public FileStoreRepo(FileStore store) {
        this.store = store;
    }

    public void save(String id, String text) {
        store.save(id, text);
    }

    public int countLines(String id) {
        return store.countLines(id);
    }
}