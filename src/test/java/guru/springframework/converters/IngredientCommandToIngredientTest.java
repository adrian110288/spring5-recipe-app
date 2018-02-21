package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest extends BaseConverterTest<IngredientCommandToIngredient, IngredientCommand> {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(2.0f);
    private static final Long UNIT_OF_MEASURE_ID = 2L;

    @Test
    public void convert() {

//        given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasure = new UnitOfMeasureCommand();
        unitOfMeasure.setId(UNIT_OF_MEASURE_ID);
        command.setUom(unitOfMeasure);

//        when
        Ingredient ingredient = target.convert(command);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UNIT_OF_MEASURE_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void convertWithNullUnitOfMeasure() {

        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);


        //when
        Ingredient ingredient = target.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

    @Override
    protected IngredientCommandToIngredient provideConverter() {

        UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure =
                new UnitOfMeasureCommandToUnitOfMeasure();

        return new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    }

    @Override
    protected IngredientCommand provideEmpty() {
        return new IngredientCommand();
    }
}