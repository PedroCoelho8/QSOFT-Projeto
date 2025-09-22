package com.isep.g200.service;

import com.isep.g200.domain.PetType;
import com.isep.g200.repository.PetTypeRepository;
import com.isep.g200.service.dto.PetTypeDTO;
import com.isep.g200.service.mapper.PetTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.isep.g200.domain.PetType}.
 */
@Service
@Transactional
public class PetTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(PetTypeService.class);

    private final PetTypeRepository petTypeRepository;

    private final PetTypeMapper petTypeMapper;

    public PetTypeService(PetTypeRepository petTypeRepository, PetTypeMapper petTypeMapper) {
        this.petTypeRepository = petTypeRepository;
        this.petTypeMapper = petTypeMapper;
    }

    /**
     * Save a petType.
     *
     * @param petTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public PetTypeDTO save(PetTypeDTO petTypeDTO) {
        LOG.debug("Request to save PetType : {}", petTypeDTO);
        PetType petType = petTypeMapper.toEntity(petTypeDTO);
        petType = petTypeRepository.save(petType);
        return petTypeMapper.toDto(petType);
    }

    /**
     * Update a petType.
     *
     * @param petTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public PetTypeDTO update(PetTypeDTO petTypeDTO) {
        LOG.debug("Request to update PetType : {}", petTypeDTO);
        PetType petType = petTypeMapper.toEntity(petTypeDTO);
        petType = petTypeRepository.save(petType);
        return petTypeMapper.toDto(petType);
    }

    /**
     * Partially update a petType.
     *
     * @param petTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PetTypeDTO> partialUpdate(PetTypeDTO petTypeDTO) {
        LOG.debug("Request to partially update PetType : {}", petTypeDTO);

        return petTypeRepository
            .findById(petTypeDTO.getId())
            .map(existingPetType -> {
                petTypeMapper.partialUpdate(existingPetType, petTypeDTO);

                return existingPetType;
            })
            .map(petTypeRepository::save)
            .map(petTypeMapper::toDto);
    }

    /**
     * Get all the petTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PetTypeDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all PetTypes");
        return petTypeRepository.findAll(pageable).map(petTypeMapper::toDto);
    }

    /**
     * Get one petType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PetTypeDTO> findOne(Long id) {
        LOG.debug("Request to get PetType : {}", id);
        return petTypeRepository.findById(id).map(petTypeMapper::toDto);
    }

    /**
     * Delete the petType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete PetType : {}", id);
        petTypeRepository.deleteById(id);
    }
}
