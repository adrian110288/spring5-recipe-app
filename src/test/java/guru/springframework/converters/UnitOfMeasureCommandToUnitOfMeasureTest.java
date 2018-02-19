package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest extends BaseConverterTest<UnitOfMeasureCommandToUnitOfMeasure, UnitOfMeasureCommand> {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    @Test
    public void convert() {

        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = target.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

    @Override
    protected UnitOfMeasureCommandToUnitOfMeasure provideConverter() {
        return new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Override
    protected UnitOfMeasureCommand provideEmpty() {
        return new UnitOfMeasureCommand();
    }
}