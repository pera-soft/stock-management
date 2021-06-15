package io.github.oguzhancevik.stockmanagement.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.oguzhancevik.stockmanagement.factory.DTOFactory;
import io.github.oguzhancevik.stockmanagement.factory.EntityFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseUnitTest {

    protected EntityFactory entityFactory;
    protected DTOFactory dtoFactory;

    @BeforeAll
    public void beforeAll() {
        entityFactory = new EntityFactory();
        dtoFactory = new DTOFactory();
    }

    public String toJson(Object object) throws JsonProcessingException {
        var objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

}
