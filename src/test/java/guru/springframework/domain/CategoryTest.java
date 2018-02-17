package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long id = 4l;
        category.setId(id);

        assertEquals(id, category.getId());
    }

    @Test
    public void getDescription() {
        String description = "description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }
}