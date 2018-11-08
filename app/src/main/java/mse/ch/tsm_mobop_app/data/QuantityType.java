package mse.ch.tsm_mobop_app.data;

/**
 * Different types of quantities which an article can have.
 */
public enum QuantityType {
    PER_PIECE (""),
    PER_KILO ("kg"),
    PER_GRAM ("g");

    private String desc;

    QuantityType(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}
