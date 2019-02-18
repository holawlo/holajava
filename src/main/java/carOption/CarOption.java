package carOption;

import java.math.BigDecimal;

public class CarOption {

    private String optionName;
    private BigDecimal optionPrice;
    private boolean chosen;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public BigDecimal getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(BigDecimal optionPrice) {
        this.optionPrice = optionPrice;
    }

    public boolean isChoosen() {
        return chosen;
    }

    public void setChoosen(boolean chosen) {
        this.chosen = chosen;
    }

}
