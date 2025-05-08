import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DN09Test02 {
    private Postaja[] testPostaje;
    private Linija[] testLinije;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        testPostaje = new Postaja[] {
                new Postaja(1, "A", 0, 0, 10),
                new Postaja(2, "B", 0, 0, 5)
        };

        testLinije = new Linija[] {
                new Linija(1),
                new Linija(2)
        };

        Avtobus bus1 = new Avtobus(101, 15);
        Avtobus bus2 = new Avtobus(102, 35);

        testLinije[0].dodajPostajo(testPostaje[0]);
        testLinije[0].dodajAvtobus(bus1);
        testLinije[0].setBarva("#123456");
        testLinije[1].dodajPostajo(testPostaje[1]);
        testLinije[1].dodajAvtobus(bus2);
        testLinije[1].setBarva("#234567");

        DN09.postaje = testPostaje;
        DN09.linije = testLinije;
    }

    @Test
    public void testNajboljObremenjenaPostaja() {
        DN09.izpisNajboljObremenjenePostaje(40);
        String expected = "Najbolj obremenjena postaja: 2 B\n" +
                "Cakajoci: 5, Stevilo prostih mest: 5, Razmerje: 1,00";
        assertEquals(expected, outContent.toString().replace("\r", ""));
    }

    @Test
    public void testEnakoRazmerje() {
        Postaja p3 = new Postaja(3, "C", 0, 0, 5);
        testPostaje = new Postaja[]{testPostaje[1], p3};
        testLinije[1].dodajPostajo(p3);
        DN09.postaje = testPostaje;

        DN09.izpisNajboljObremenjenePostaje(40);
        assertTrue(outContent.toString().contains("2 B"));
    }

    @Test
    public void testPreobremenjenAvtobus() {
        Avtobus bus3 = new Avtobus(103, 45);
        testLinije[1].dodajAvtobus(bus3);
        DN09.izpisNajboljObremenjenePostaje(40);
        assertTrue(outContent.toString().contains("Stevilo prostih mest: 5"));
    }
}
