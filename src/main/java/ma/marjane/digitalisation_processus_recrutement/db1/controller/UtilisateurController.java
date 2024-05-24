package ma.marjane.digitalisation_processus_recrutement.db1.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.UtilisateurServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurServiceImp utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable String id) {
        return utilisateurService.findById(id)
                .map(utilisateurDto -> ResponseEntity.status(HttpStatus.OK).body(utilisateurDto))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUtilisateurDto = utilisateurService.save(utilisateurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateurDto);
    }

//    @PutMapping
//    public ResponseEntity<UtilisateurDto> updateUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
//        UtilisateurDto updatedUtilisateurDto = utilisateurService.update(utilisateurDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedUtilisateurDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable String id) {
        utilisateurService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
