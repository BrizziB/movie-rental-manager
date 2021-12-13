package it.unifi.ing.stlab.movierentalmanager.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    private FakeBaseEntity e1;
    private FakeBaseEntity e2;
    private FakeBaseEntity e3;

    @BeforeEach
    public void setup(){
        System.out.println("Eseguo il setup..");
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        e1 = new FakeBaseEntity( uuid1 );
        e2 = new FakeBaseEntity( uuid2 );
        e3 = new FakeBaseEntity( uuid1 );
    }

    @Test
    public void testNullUUID() {
        System.out.println("Eseguo testNullUUID..");
        assertThrows(
                IllegalArgumentException.class, () -> { new FakeBaseEntity(null); }
        );
    }

    @Test
    public void testEquals(){
        System.out.println("Eseguo testEquals..");
        assertEquals(e1, e1); //identità
        assertEquals(e1, e3); //uguaglianza
        assertNotEquals(e1, e2); //(dis)uguaglianza
    }
}