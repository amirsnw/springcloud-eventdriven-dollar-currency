package com.snw.api.kafka;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceMessage(BigDecimal value, LocalDateTime timestamp) {
}
