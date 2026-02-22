public class FakeDBRepo implements StudentRepository {

    private final FakeDb db;

    public FakeDBRepo(FakeDb db) {
        this.db = db;
    }

    @Override
    public void save(StudentRecord r) {
        db.save(r);
    }

    @Override
    public int count() {
        return db.count();
    }
}