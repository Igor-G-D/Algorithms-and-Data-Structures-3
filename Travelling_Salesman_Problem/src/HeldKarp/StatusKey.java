package HeldKarp;

public class StatusKey {
    private int pos;
    private int bitmask;

    public StatusKey(int pos, int bitmask) {
        this.pos = pos;
        this.bitmask = bitmask;
    }

    public boolean equals(StatusKey key) {
        if (pos == key.getPos() && bitmask == key.getBitmask()) {
            return true;
        }
        return false;
    }

    public int getPos() {
        return pos;
    }

    public int getBitmask() {
        return bitmask;
    }
}
