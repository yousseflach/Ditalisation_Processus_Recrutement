package ma.marjane.digitalisation_processus_recrutement.db1.controller;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.CollaborateurServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collaborateur")
@RequiredArgsConstructor
public class CollaborateurController {

    private final CollaborateurServiceImp collaborateurService;

    @GetMapping("/request")
    public String request() {
        return "Hello World";
    }

    @GetMapping
    public ResponseEntity<List<CollaborateurDto>> getAllCollaborateurs( @RequestParam(required = true) String matricule){
        List<CollaborateurDto> collaborateurs = collaborateurService.findByMatricule(matricule);
        return ResponseEntity.ok(collaborateurs);
    }
    //get all collaborateurs
    @GetMapping("/all")
    public ResponseEntity<List<CollaborateurDto>> getAllCollaborateurs() {
        List<CollaborateurDto> collaborateurs = collaborateurService.findAll();
        return ResponseEntity.ok(collaborateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaborateurDto> getCollaborateurById(@PathVariable String matricule) {
        return collaborateurService.findByMatricule(matricule)
                .stream().map(ResponseEntity::ok)
                .findAny().orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CollaborateurDto> createCollaborateur(@RequestBody CollaborateurDto collaborateurDto) {
        CollaborateurDto createdCollaborateur = collaborateurService.save(collaborateurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCollaborateur);
    }

//    @PutMapping
//    public ResponseEntity<CollaborateurDto> updateTache(@Valid @RequestBody CollaborateurDto collaborateurDto) {
//        CollaborateurDto updatedCollaborateurDto = collaborateurService.update(collaborateurDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedCollaborateurDto);
//    }

    //save collaborateur  if not exist any collaborateur with attribute false if exist any collaborateur with attribute true deleted the old collaborateur and save the new collaborateur




    @RequestMapping(value = "/sauvegarder", method = {RequestMethod.GET, RequestMethod.POST})
    public CollaborateurDto handleRequest(@RequestBody(required = false) CollaborateurDto collaborateur,@RequestParam(required = true) String matricule,HttpServletRequest request){
        String method = request.getMethod();
        if (method.equals("POST")) {

            return collaborateurService.sauvegarderdemander(collaborateur,matricule);
        }else {

            return collaborateurService.saveCollaborateur(collaborateur, matricule);
        }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollaborateur(@PathVariable UUID id) {
        collaborateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
