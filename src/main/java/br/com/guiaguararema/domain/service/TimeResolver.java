package br.com.guiaguararema.domain.service;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeResolver {
    public ZonedDateTime resolve(String when) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        if (when == null || when.isBlank() || "now".equalsIgnoreCase(when)) return now;

        String normalized = when.toLowerCase();
        if (normalized.contains("amanha") || normalized.contains("tomorrow")) return now.plusDays(1);
        if (normalized.contains("fim de semana") || normalized.contains("weekend")) {
            int daysToSaturday = DayOfWeek.SATURDAY.getValue() - now.getDayOfWeek().getValue();
            if (daysToSaturday < 0) daysToSaturday += 7;
            return now.plusDays(daysToSaturday).withHour(10).withMinute(0);
        }
        return now;
    }
}
