import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class DN09Test6 {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Postaja[] testPostaje;
    private Linija[] testLinije;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        testPostaje = new Postaja[] {
                new Postaja(1, "A", 0, 0, 10),
                new Postaja(2, "B", 0, 0, 5),
                new Postaja(3, "C", 0, 0, 5),
                new Postaja(4, "D", 0, 0, 5),
                new Postaja(5, "E", 0, 0, 5),
                new Postaja(6, "F", 0, 0, 5),
                new Postaja(7, "G", 0, 0, 5),
                new Postaja(8, "H", 0, 0, 5)
        };

        testLinije = new Linija[] {
                new Linija(1),
                new Linija(2),
                new Linija(3)
        };

        Avtobus bus1 = new Avtobus(101, 15);
        Avtobus bus2 = new Avtobus(102, 35);
        Avtobus bus3 = new Avtobus(103, 35);

        bus1.setTrenutnaPostaja(testPostaje[2]);
        bus2.setTrenutnaPostaja(testPostaje[5]);
        bus3.setTrenutnaPostaja(testPostaje[7]);

        testLinije[0].dodajPostajo(testPostaje[0]);
        testLinije[0].dodajPostajo(testPostaje[1]);
        testLinije[0].dodajPostajo(testPostaje[2]);
        testLinije[0].dodajPostajo(testPostaje[3]);
        testLinije[0].dodajAvtobus(bus1);
        testLinije[1].dodajPostajo(testPostaje[5]);
        testLinije[1].dodajPostajo(testPostaje[6]);
        testLinije[1].dodajPostajo(testPostaje[2]);
        testLinije[1].dodajAvtobus(bus2);
        testLinije[2].dodajPostajo(testPostaje[7]);
        testLinije[2].dodajPostajo(testPostaje[6]);
        testLinije[2].dodajAvtobus(bus3);

        DN09.postaje = testPostaje;
        DN09.linije = testLinije;
        DN09.avtobusi = new Avtobus[]{bus1, bus2, bus3};
    }
    @Test
    public void stPrestopov() throws FileNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DN09.steviloPrestopov(1, 8);
        assertEquals("Za pot A -> H je potrebno prestopiti 2-krat".strip().replace("\r", ""), outContent.toString().strip().replace("\r", ""));
        System.setOut(originalOut);

    }
    @Test
    public void stPrestopov2() throws FileNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DN09.steviloPrestopov(1, 2);
        assertEquals("Za pot A -> B prestop ni potreben".strip().replace("\r", ""), outContent.toString().strip().replace("\r", ""));
        System.setOut(originalOut);

    }
    @Test
    public void stPrestopov3() throws FileNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DN09.steviloPrestopov(1, 5);
        assertEquals("Za pot A -> E pot ni možna".strip().replace("\r", ""), outContent.toString().strip().replace("\r", ""));
        System.setOut(originalOut);

    }

}