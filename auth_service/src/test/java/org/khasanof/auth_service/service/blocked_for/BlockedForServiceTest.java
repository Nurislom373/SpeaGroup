package org.khasanof.auth_service.service.blocked_for;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.criteria.blocked_for.BlockedForCriteria;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForGetDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.exception.exceptions.NotFoundException;
import org.khasanof.auth_service.mapper.blocked_for.BlockedForMapper;
import org.khasanof.auth_service.mapper.blocked_for.BlockedForMapperImpl;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.khasanof.auth_service.validator.blocked_for.BlockedForValidator;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class BlockedForServiceTest {

    private BlockedForService service;

    @Mock
    private BlockedForRepository repository;

    @BeforeEach
    void setUp() {
        BlockedForMapper mapper = new BlockedForMapperImpl();
        BlockedForValidator validator = new BlockedForValidator();
        service = new BlockedForServiceImpl(repository, mapper, validator);
    }

    @Test
    public void serviceIsNotNull() {
        org.assertj.core.api.Assertions.assertThat(service).isNotNull();
    }

    @Test
    public void createMethodAlreadyCreatedExceptionTest() {
        var dto1 = new BlockedForCreateDTO("Boom", "BOOM", 56);

        Assertions.assertThrows(AlreadyCreatedException.class,
                () -> service.create(dto1));
    }

    @Test
    public void getMethodIsOkTest() {
        BlockedForEntity entity = new BlockedForEntity("BOOM", "Boom", 12);
        Mockito.when(repository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(entity));

        BlockedForGetDTO blockedForGetDTO = service.get("63343ac6f2da927b960fb2bd");
        System.out.println("blockedForGetDTO = " + blockedForGetDTO);
        Assertions.assertEquals(entity.getTime(), blockedForGetDTO.getTime());
    }

    @Test
    public void getMethodNotFoundExceptionTest() {
        Mockito.when(repository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,
                () -> service.get("6320c3dbef1ded597035d8a7"));
    }

    @Test
    public void deleteMethodNotFoundExceptionTest() {
        Mockito.when(repository.existsById(ArgumentMatchers.any()))
                .thenReturn(false);

        Assertions.assertThrows(NotFoundException.class,
                () -> service.delete("6320c3dbef1ded597035d8a7"));
    }

    @Test
    public void deleteMethodIsInvalidValidationExceptionTest() {
        Assertions.assertThrows(InvalidValidationException.class,
                () -> service.delete("6320c3dbef1ded597035d8a"));

        Assertions.assertThrows(InvalidValidationException.class,
                () -> service.delete(null));
    }

    @Test
    public void updateMethodIsNotFoundExceptionTest() {
        var dto = new BlockedForUpdateDTO("6320c3dbef1ded597035d8a7", "YFG", "yfg", 15);

        Mockito.when(repository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,
                () -> service.update(dto));
    }

    @Test
    public void updateMethodIsInvalidValidationExceptionTest() {
        var dto = new BlockedForUpdateDTO("6320c3dbef1ded597035d8a",
                "YFG", "yfg", 15);

        Assertions.assertThrows(InvalidValidationException.class,
                () -> service.update(dto));

        Assertions.assertThrows(InvalidValidationException.class,
                () -> service.update(null));
    }

    @Test
    public void getMethodIsInvalidValidationTest() {
        Assertions.assertThrows(InvalidValidationException.class,
                () -> service.get("6320c3dbef1ded597035d8a"));
    }

    @Test
    public void listMethodIsOkTest() {
        var list = List.of(
                new BlockedForEntity("FGHB", "fyudhsb", 34),
                new BlockedForEntity("YUVYUH", "fhbudsig", 356),
                new BlockedForEntity("UIGBUI", "fdiusg", 5)
        );

        PageRequest request = PageRequest.of(0, 5);
        Mockito.when(repository.findAll(request)).thenReturn(new PageImpl<>(list, request, 3));
        List<BlockedForGetDTO> dtoList = service.list(new BlockedForCriteria());
        Assertions.assertEquals(list.size(), dtoList.size());
    }


}
