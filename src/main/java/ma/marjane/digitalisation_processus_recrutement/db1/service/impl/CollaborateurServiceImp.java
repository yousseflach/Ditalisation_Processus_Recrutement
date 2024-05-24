package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.CollaborateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CollaborateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Collaborateur;
import ma.marjane.digitalisation_processus_recrutement.db1.service.CollaborateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollaborateurServiceImp implements CollaborateurService {


    private final CollaborateurRepository collaborateurRepository;
    private final CollaborateurMapperImpl collaborateurMapper;

    public List<CollaborateurDto> findAll() {

        return collaborateurRepository.findAll().stream().map(collaborateurMapper::convertToDto).toList();
    }

    public Optional<CollaborateurDto> findById(UUID id) {
        Optional<Collaborateur> collaborateurOptional = collaborateurRepository.findById(id);
        return collaborateurOptional.map(collaborateurMapper::convertToDto);
    }

    public CollaborateurDto save(CollaborateurDto collaborateurDto) {
        collaborateurRepository.save(collaborateurMapper.convertToEntity(collaborateurDto));
        return collaborateurDto;
    }

//    public CollaborateurDto update(CollaborateurDto collaborateurDto) {
//        Optional<CollaborateurDto> optionalCollaborateurDto = this.findById(collaborateurDto.getId());
//        if (optionalCollaborateurDto.isPresent()) {
//            collaborateurRepository.save(collaborateurMapper.convertToEntity(collaborateurDto));
//            return collaborateurDto;
//        }else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Demande Collaborateur with id " + collaborateurDto.getId() + " not found");
//        }
//    }

    public void deleteById(UUID id) {
        collaborateurRepository.deleteById(id);
    }
}
