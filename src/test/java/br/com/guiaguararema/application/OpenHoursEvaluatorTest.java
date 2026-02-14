package br.com.guiaguararema.application;

import br.com.guiaguararema.domain.service.OpenHoursEvaluator;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenHoursEvaluatorTest {
    @Test
    void shouldReturnUnknownWhenMissing() {
        var evaluator = new OpenHoursEvaluator();
        String status = evaluator.evaluate(null, ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")), "now");
        assertEquals("HORARIO_NAO_INFORMADO", status);
    }
}
