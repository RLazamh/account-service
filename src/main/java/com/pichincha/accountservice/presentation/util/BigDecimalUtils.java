package com.pichincha.accountservice.presentation.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {

    private BigDecimalUtils() {}

    public static BigDecimal roundToTwoDecimals(BigDecimal value) {
        return value != null ? value.setScale(2, RoundingMode.HALF_UP) : null;
    }
}
