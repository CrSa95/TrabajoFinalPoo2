package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamionTest {

    private Camion camion;
    private static final String PATENTE = "AA 123 AA";

    @BeforeEach
    void setUp() {
        this.camion = new Camion(PATENTE);
    }

    @Test
    void devuelveLaPatenteCorrecta() {
        assertEquals(PATENTE, camion.patente());
    }
}