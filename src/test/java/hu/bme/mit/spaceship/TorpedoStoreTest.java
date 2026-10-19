package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_SuccessDecreasesTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoesAtOnce() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoesEmptiesTheStore() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_FailureReturnsFalseAndKeepsTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoesThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeNumberThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_MoreThanAvailableThrowsException() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }

    @Test
    void isEmpty_StoreWithTorpedoesIsNotEmpty() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_StoreWithZeroTorpedoesIsEmpty() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertTrue(store.isEmpty());
    }

    @Test
    void getTorpedoCount_ReturnsInitialCount() {
        TorpedoStore store = new TorpedoStore(7, 0.0);

        assertEquals(7, store.getTorpedoCount());
    }
}