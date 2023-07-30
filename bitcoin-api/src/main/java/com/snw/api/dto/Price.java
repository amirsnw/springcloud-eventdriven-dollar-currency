package com.snw.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(BigDecimal value, LocalDateTime timestamp) {
}
