package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Assert;
import org.junit.Test;

public class CategoryCommandToCategoryTest extends BaseConverterTest<CategoryCommandToCategory, CategoryCommand> {

    private static final Long COMMAND_ID = 1L;
    private static final String DESCRIPTION = "description";

    @Override
    protected CategoryCommandToCategory provideConverter() {
        return new CategoryCommandToCategory();
    }

    @Override
    protected CategoryCommand provideEmpty() {
        return new CategoryCommand();
    }

    @Test
    public void convert() {

        CategoryCommand command = new CategoryCommand();
        command.setId(COMMAND_ID);
        command.setDescription(DESCRIPTION);

        Category category = target.convert(command);
        Assert.assertNotNull(category);
        Assert.assertEquals(COMMAND_ID, category.getId());
        Assert.assertEquals(DESCRIPTION, category.getDescription());
    }

}