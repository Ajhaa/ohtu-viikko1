package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjänJosNegatiivinenTilavuus() {
        varasto = new Varasto(-2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriLuoOikeanTilavuuden() {
        varasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriAsettaaOikeanAlkusaldon() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriLuoTyhjanJosNegatiivinenTilavuus() {
        varasto = new Varasto(-2, 5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonNollaanJosNegatiivinen() {
        varasto = new Varasto(10, -3);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriTayttaaVarastonJosAlkusaldoSuurempiKuinTilavuus() {
        varasto = new Varasto(10, 20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenEiMuutaTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-2);
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenOttaaKaiken() {
        varasto.lisaaVarastoon(4);
        double otettu = varasto.otaVarastosta(8);

        assertEquals(4, otettu, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisääminenEiMuutaTilaa() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);

        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaLisääminenTäyttääTäyteen() {
        varasto.lisaaVarastoon(21);

        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringTulostaaOikein() {
        String tostring = varasto.toString();
        String expected = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(expected, tostring);
    }
}