package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock = null;

    @BeforeEach
    public void setUp() {
        doorLock = new SmartDoorLockImpl();
    }

    @Test
    public void testLockLock() {
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void setPin() {
        doorLock.setPin(1234);
        doorLock.lock();
        doorLock.unlock(1234);
        assertFalse(doorLock.isLocked());
    }
}
