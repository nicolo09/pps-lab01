package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private boolean isLocked = false;
    private int pin;

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            isLocked = false;
        } else {
            throw new IllegalArgumentException("Invalid PIN");
        }
    }

    @Override
    public void lock() {
        isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
