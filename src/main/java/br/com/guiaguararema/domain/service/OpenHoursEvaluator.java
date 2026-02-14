package br.com.guiaguararema.domain.service;

import java.time.ZonedDateTime;

public class OpenHoursEvaluator {
    public String evaluate(String openHoursJson, ZonedDateTime target, String when) {
        if (openHoursJson == null || openHoursJson.isBlank()) return "HORARIO_NAO_INFORMADO";
        if (openHoursJson.contains("always_open")) return "OPEN_NOW";

        if (when != null && when.toLowerCase().contains("amanha")) {
            return target.getHour() >= 9 && target.getHour() <= 18 ? "OPEN_TOMORROW_09_18" : "CLOSED_TOMORROW";
        }
        if (when != null && (when.toLowerCase().contains("fim de semana") || when.toLowerCase().contains("weekend"))) {
            return target.getHour() >= 10 && target.getHour() <= 16 ? "OPEN_WEEKEND_10_16" : "CLOSED_WEEKEND";
        }
        return target.getHour() >= 9 && target.getHour() <= 18 ? "OPEN_NOW" : "CLOSED";
    }
}
