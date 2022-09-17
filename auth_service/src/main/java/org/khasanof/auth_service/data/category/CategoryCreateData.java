package org.khasanof.auth_service.data.category;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.entity.category.CategoryEntity;
import org.khasanof.auth_service.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Order(2)
@Slf4j
public class CategoryCreateData implements CommandLineRunner {

    @Autowired
    private CategoryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        /*ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CategoryEntity>> reference = new TypeReference<>() {
        };

        InputStream inputStream = getClass().getResourceAsStream("/data/category/CATEGORY_MOCK_DATA.json");
        List<CategoryEntity> list = objectMapper.readValue(inputStream, reference);
        repository.saveAll(list);

        log.info(">>>>>>> " + list.size() + " Categories Saved!");*/

        // need to run once!
    }
}
