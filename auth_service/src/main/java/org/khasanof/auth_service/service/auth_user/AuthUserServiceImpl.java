package org.khasanof.auth_service.service.auth_user;

import org.khasanof.auth_service.criteria.auth_user.AuthUserBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDetailDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;
import org.khasanof.auth_service.enums.auth_user.AuthUserStatusEnum;
import org.khasanof.auth_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.auth_service.mapper.auth_user.AuthUserMapper;
import org.khasanof.auth_service.predicate.auth_user.AuthUserPredicateExecutor;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_info.AuthInfoService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.validator.auth_user.AuthUserValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class AuthUserServiceImpl extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator> implements AuthUserService {

    private final AuthUserProducerService userProducerService;
    private final MongoTemplate mongoTemplate;
    private final AuthRoleRepository roleRepository;
    private final AuthInfoService authInfoService;
    private final JavaMailSender mailSender;

    public AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, AuthUserProducerService userProducerService, MongoTemplate mongoTemplate, AuthRoleRepository roleRepository, @Lazy AuthInfoService authInfoService, JavaMailSender mailSender) {
        super(repository, mapper, validator);
        this.userProducerService = userProducerService;
        this.mongoTemplate = mongoTemplate;
        this.roleRepository = roleRepository;
        this.authInfoService = authInfoService;
        this.mailSender = mailSender;
    }

    @Override
    public void create(AuthUserCreateDTO createDto) {
        validator.validCreateDTO(createDto);
        AuthUserEntity userEntity = mongoTemplate.findOne(
                Query.query(Criteria.where("email")
                        .is(createDto.getEmail())
                        .orOperator(Criteria.where("username")
                                .is(createDto.getUsername()))),
                AuthUserEntity.class);
        if (Objects.nonNull(userEntity)) {
            short equalsCount = 0;
            if (createDto.getEmail().equals(userEntity.getEmail())) {
                equalsCount += 1;
            }
            if (createDto.getUsername().equals(userEntity.getUsername())) {
                equalsCount += 2;
            }
            if (equalsCount == 1) {
                throw new AlreadyCreatedException("This email already used!");
            } else if (equalsCount == 2) {
                throw new AlreadyCreatedException("This username already used!");
            } else if (equalsCount == 3) {
                throw new AlreadyCreatedException("This email and username already used!");
            }
        }
        AuthUserEntity authUserEntity = mapper.toCreateDTO(createDto);
        authUserEntity.setStatus(AuthUserStatusEnum.NO_ACTIVE);
        authUserEntity.setPassword(BaseUtils.ENCODER.encode(createDto.getPassword()));
        AuthUserEntity entity = repository.insert(authUserEntity);
        roleRepository.insert(new AuthRoleEntity(entity, AuthRoleEnum.USER.getValue()));
        if (Objects.nonNull(createDto.getCategoryIds())) {
            authInfoService.create(new AuthInfoCreateDTO(entity.getId(),
                    createDto.getCategoryIds()));
        } else {
            authInfoService.create(new AuthInfoCreateDTO(entity.getId(),
                    BaseUtils.DEFAULT_CATEGORIES));
        }
        sendMessage();
    }

    @Override
    public void update(AuthUserUpdateDTO updateDto) {
        validator.validUpdateDTO(updateDto);
        AuthUserEntity authUser = repository
                .findById(updateDto.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        BeanUtils.copyProperties(updateDto, authUser, "id");
        repository.save(authUser);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        repository.deleteById(id);
        userProducerService.sendMessage(id);
        authInfoService.delete(id);
    }

    @Override
    public AuthUserGetDTO get(String id) {
        validator.validKey(id);
        return mapper.fromGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("User not found by id: %s".formatted(id));
                        })
        );
    }

    @Override
    public AuthUserDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public List<AuthUserGetDTO> list(AuthUserCriteria criteria) {
        return mapper.fromGetListDTO(
                repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getSort(),
                                criteria.getFieldsEnum().getValue()
                        )
                ).stream().toList());
    }

    @Override
    public boolean exist(String id) {
        validator.validKey(id);
        return repository.existsById(id);
    }

    @Override
    public AuthUserEntity getEntity(String id) {
        validator.validKey(id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<AuthUserGetDTO> listWithSc(AuthUserSearchCriteria searchCriteria) {
        return mapper.fromGetListDTO(
                mongoTemplate.find(
                        new AuthUserPredicateExecutor.SearchPredicate(searchCriteria).searchQuery(),
                        AuthUserEntity.class));
    }

    @Override
    public List<AuthUserGetDTO> listWithBc(AuthUserBetweenCriteria betweenCriteria) {
        return mapper.fromGetListDTO(
                mongoTemplate.find(
                        new AuthUserPredicateExecutor.BetweenPredicate(betweenCriteria).betweenQuery(),
                        AuthUserEntity.class));
    }

    private void sendMessage() {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
        mailMessage.setFrom("khasanof373@gmail.com");
        mailMessage.setTo("noza5037@gmail.com");
        mailMessage.setText("Boom");
        mailMessage.setSubject("Boom Guys!");
        mailSender.send(mailMessage);
    }
}
