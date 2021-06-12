package io.github.oguzhancevik.stockmanagement.base;

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

}
