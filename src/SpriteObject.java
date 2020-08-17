public class SpriteObject {

    private boolean hasBeenSeen = false;
    private int type;


    public SpriteObject(int type) {
        this.type = type;
    }

    public boolean isHasBeenSeen() {
        return hasBeenSeen;
    }

    public int intType() {
        return type;
    }

}
