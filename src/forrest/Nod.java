package forrest;

import java.util.List;

public class Nod {
    private String value;
    private Nod right;
    private Nod left;

    public Nod(String value, Nod right, Nod left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Nod getRight() {
        return right;
    }

    public void setRight(Nod right) {
        this.right = right;
    }

    public Nod getLeft() {
        return left;
    }

    public void setLeft(Nod left) {
        this.left = left;
    }
}
