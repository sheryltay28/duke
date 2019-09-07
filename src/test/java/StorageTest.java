import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {
    Storage storage;

    @Test
    public void testGetFilePath() {
        storage = new Storage("/Users/sheryl/CS2103/duke/src/main/java/Storage.java");
        assertEquals("/Users/sheryl/CS2103/duke/src/main/java/Storage.java", storage.getFilePath());
    }

    @Test
    public void testConvertToInt() {
        storage = new Storage("/Users/sheryl/CS2103/duke/src/main/java/Storage.java");
        assertEquals(1, storage.convertToInt("February"));
    }
}































