import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DN09Test07 {
    private Postaja[] testPostaje;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        testPostaje = new Postaja[] {
                new Postaja(1, "A", 0, 0, 5),
                new Postaja(2, "B", 3, 4, 3),
                new Postaja(3, "C", 0, 5, 2),
                new Postaja(4, "D", 6, 8, 7)
        };
        DN09.postaje = testPostaje;
    }

    @Test
    public void testNajblizjaPostajaID() {
        DN09.izpisNajblizjePostaje("1");
        String expected = "Najblizja postaja A (ID 1):\n2. B - razdalja: 5.00";
        assertEquals(expected, outContent.toString().replace("\r", ""));
    }

    @Test
    public void testNajblizjaPostajaIme() {
        DN09.izpisNajblizjePostaje("A");
        String expected = "Najblizja postaja A (ID 1):\n2. B - razdalja: 5.00";
        assertEquals(expected, outContent.toString().replace("\r", ""));
    }

    @Test
    public void testEnakaRazdalja() {
        DN09.izpisNajblizjePostaje("1");
        assertTrue(outContent.toString().contains("B - razdalja: 5.00"));
    }

    @Test
    public void testNeveljavenID() {
        DN09.izpisNajblizjePostaje("99");
        assertEquals("Postaja z ID 99 ne obstaja.", outContent.toString().replace("\r", ""));
    }

    @Test
    public void testKNajblizjih() {
        DN09.izpisNajblizjihPostaj("1", 3);
        String expected = "Najblizje postaje A (ID 1):\n" +
                "1. B - razdalja: 5.00\n" +
                "2. C - razdalja: 5.00\n" +
                "3. D - razdalja: 10.00";
        assertEquals(expected, outContent.toString().replace("\r", ""));
    }

    @Test
    public void testKVecKot() {
        DN09.izpisNajblizjihPostaj("1", 10);
        assertTrue(outContent.toString().contains("3. D - razdalja: 10.00"));
    }
}
