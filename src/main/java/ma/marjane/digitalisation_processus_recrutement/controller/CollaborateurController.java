package ma.marjane.digitalisation_processus_recrutement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.service.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collaborateur")
@RequiredArgsConstructor
public class CollaborateurController {

    private final CollaborateurService collaborateurService;

    @GetMapping("/request")
    public String request() {
        return "Hello World";
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> save(@RequestBody @Valid CollaborateurDto collaborateurDto){
        collaborateurService.save(collaborateurDto);
        return ResponseEntity.accepted().build();
    }
}
