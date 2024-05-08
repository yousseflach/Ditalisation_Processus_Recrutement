package ma.marjane.digitalisation_processus_recrutement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final InternshipService internshipService;
    private final CollaboratorService collaboratorService;

//    public void saveRequest(RequestReq request) {
//        if () {
//            return internshipService.save((Internship) request);
//        } else if (request instanceof Collaborator) {
//            return collaboratorService.save((Collaborator) request);
//        }
//    }


}
