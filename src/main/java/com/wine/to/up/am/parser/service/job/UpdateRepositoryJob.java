package com.wine.to.up.am.parser.service.job;

import com.wine.to.up.am.parser.service.components.AmServiceMetricsCollector;
import com.wine.to.up.am.parser.service.service.UpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Job для периодического обновления данных о винах
 *
 * @author Филимонов Олег
 */

@Component
@Slf4j
public class UpdateRepositoryJob {

    @Resource
    private UpdateService updateService;

    private final AmServiceMetricsCollector metricsCollector;

    public UpdateRepositoryJob(AmServiceMetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    /**
     * Каждый день обновляет список вин
     */
    @Scheduled(cron = "${job.cron.update.repository}")
    public void runJob() {
        ZoneOffset zone = ZoneOffset.of("Z");
        LocalDateTime startDate = LocalDateTime.now();
        long jobStart = System.nanoTime();
        log.info("start ActualizeWineJob run job method at " + startDate);
        updateService.updateDictionary();
        updateService.updateWines();
        LocalDateTime endDate = LocalDateTime.now();
        long jobEnd = System.nanoTime();
        metricsCollector.jobExecutionTime(jobEnd - jobStart);
        log.info("end ActualizeWineJob run job method at " + endDate + " duration = " + (endDate.toEpochSecond(zone) - startDate.toEpochSecond(zone)) + " seconds");
    }

}
