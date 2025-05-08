import org.junit.Test;
import static org.junit.Assert.*;

public class DN09Test01 {

    @Test
    public void testPostaja() {
        Postaja p = new Postaja(3, "Tivoli", 10, 25, 12);
        assertEquals(3, p.getID());
        assertEquals("Tivoli", p.getIme());
        assertEquals(10, p.getX());
        assertEquals(25, p.getY());
        assertEquals(12, p.getCakajoci());
    }

    @Test
    public void testPostajaToString() {
        Postaja p = new Postaja(3, "Tivoli", 10, 25, 12);
        assertEquals("3 Tivoli [10,25] čakajoči: 12", p.toString());
    }

    @Test
    public void testAvtobus() {
        Postaja p = new Postaja(2, "Glavna Postaja", 20, 30, 5);
        Avtobus a = new Avtobus(201, 9);
        assertEquals(201, a.getID());
        assertEquals(9, a.getSteviloPotnikov());
        assertNull(a.getTrenutnaPostaja());
        a.setTrenutnaPostaja(p);
        assertEquals(p, a.getTrenutnaPostaja());
    }

    @Test
    public void testAvtobusToString() {
        Postaja p = new Postaja(2, "Glavna Postaja", 20, 30, 5);
        Avtobus a = new Avtobus(201, 9);
        a.setTrenutnaPostaja(p);
        assertEquals("201 (9) - Glavna Postaja", a.toString());
    }

    @Test
    public void testLinija() {
        Linija l = new Linija(6);
        l.setBarva("#FFD700");
        assertEquals(6, l.getID());
        assertEquals("#FFD700", l.getBarva());
        assertTrue(l.getPostaje().isEmpty());
        assertTrue(l.getAvtobusi().isEmpty());
    }

    @Test
    public void testLinijaDodajPostajoInAvtobus() {
        Linija l = new Linija(6);
        Postaja p1 = new Postaja(3, "Tivoli", 10, 25, 12);
        Postaja p2 = new Postaja(2, "Glavna Postaja", 20, 30, 5);
        Avtobus a = new Avtobus(201, 9);

        assertTrue(l.dodajPostajo(p1));
        assertTrue(l.dodajPostajo(p2));
        assertEquals(2, l.getPostaje().size());
        assertEquals(p1, l.getPostaje().get(0));
        assertEquals(p2, l.getPostaje().get(1));

        assertTrue(l.dodajAvtobus(a));
        assertEquals(1, l.getAvtobusi().size());
        assertEquals(a, l.getAvtobusi().get(0));
    }

    @Test
    public void testLinijaToString() {
        Linija l = new Linija(6);
        Postaja p1 = new Postaja(3, "Tivoli", 10, 25, 12);
        Postaja p2 = new Postaja(2, "Glavna Postaja", 20, 30, 5);
        Avtobus a = new Avtobus(201, 9);
        a.setTrenutnaPostaja(p2);

        l.dodajPostajo(p1);
        l.dodajPostajo(p2);
        l.dodajAvtobus(a);

        assertEquals("Linija 6 - Tivoli -> Glavna Postaja (bus)", l.toString());
    }
}
