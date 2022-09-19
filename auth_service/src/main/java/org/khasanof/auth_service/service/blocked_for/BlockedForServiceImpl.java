package org.khasanof.auth_service.service.blocked_for;

import org.khasanof.auth_service.criteria.blocked_for.BlockedForCriteria;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForGetDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.mapper.blocked_for.BlockedForMapper;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.blocked_for.BlockedForValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class BlockedForServiceImpl
        extends AbstractService<BlockedForRepository, BlockedForMapper, BlockedForValidator>
        implements BlockedForService {

    public BlockedForServiceImpl(BlockedForRepository repository, BlockedForMapper mapper, BlockedForValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(BlockedForCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (repository.existsByCode(dto.getCode()))
            throw new RuntimeException("Blocked For Already Created Exception!");
        repository.save(mapper.toCreateDTO(dto));
    }

    @Override
    public void update(BlockedForUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        BlockedForEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("BlockedFor not found");
                });
        BeanUtils.copyProperties(dto, entity, "id");
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id))
            throw new NotFoundException("BlockedFor not found");
        else
            repository.deleteById(id);
    }

    @Override
    public BlockedForGetDTO get(String id) {
        return mapper.fromGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("BlockedFor not found");
                        }));
    }

    @Override
    public List<BlockedForGetDTO> list(BlockedForCriteria criteria) {
        return mapper.fromGetListDTO(
                repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream().toList());
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
