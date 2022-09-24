package mahyco.iqc.nxg.model;

public class ActionTypeModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    int id;
    String value;
    @Override
    public String toString() {
        return value;
    }
}
