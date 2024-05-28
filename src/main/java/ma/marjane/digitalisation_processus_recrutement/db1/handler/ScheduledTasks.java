package ma.marjane.digitalisation_processus_recrutement.db1.handler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.UtilisateurServiceImp;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {


    private final UtilisateurServiceImp utilisateurServiceImp;

    @Scheduled(cron = "0 0 6 * * ?")
    public void performScheduledTask() {
        utilisateurServiceImp.saveAllUsers();
    }
}
