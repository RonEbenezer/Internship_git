package org.example;

import java.math.BigDecimal;

public record OrderLine(String productId, String desc, int quantity, BigDecimal unitPrice) {
    public BigDecimal lineTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
