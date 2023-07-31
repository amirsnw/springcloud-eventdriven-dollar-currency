package com.snw.client.websocket;

import java.time.Instant;

public record Message(String price, Instant timestamp) {
}
