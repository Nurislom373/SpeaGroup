package org.khasanof.auth_service.data.blocked_for;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
@Slf4j
public class BlockedForDataCreate implements CommandLineRunner {

    @Autowired
    private BlockedForRepository blockedForRepository;

    @Override
    public void run(String... args) throws Exception {
        /*List<BlockedForEntity> list = List.of(
                new BlockedForEntity("LOGIN_TRY_COUNT", "Login try count blocked", 20),
                new BlockedForEntity("MANY_REQUESTS", "Block for too many requests", 200),
                new BlockedForEntity("QUERY_REGISTER", "Block query register", 100),
                new BlockedForEntity("ACCESSIBLE_REQUEST", "It sent an api request that was not accessible", 400)
        );

        blockedForRepository.saveAll(list);

        log.info(">>>>>>> " + list.size() + " Blocked For Saved!");*/

        // need to run once!
    }
}
