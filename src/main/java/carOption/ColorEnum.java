package carOption;

public enum ColorEnum {
    BLACK(2,"czarny"), WHITE(4,"bialy");

    private Integer colorNumber;
    private String plName; //nazwa koloru

    ColorEnum(Integer colorNumber, String plName) {
        this.colorNumber = colorNumber;
        this.plName = plName;
    }

    public Integer getColorNumber() { //w enumie nie ma setterow
        return colorNumber;
    }

    public String getPlName() {
        return plName;
    }
}
