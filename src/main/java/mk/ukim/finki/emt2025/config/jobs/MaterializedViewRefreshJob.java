package mk.ukim.finki.emt2025.config.jobs;

import mk.ukim.finki.emt2025.repository.AccommodationsPerHostViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefreshJob {

    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public MaterializedViewRefreshJob(AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void refreshAccommodationsPerHostView() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
    }
}