package pl.kkowalewski.recipeapp.converter.tocommand;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.CategoryCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>,
        Command<Category, CategoryCommand> {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryCommand(category.getId(), category.getDescription());
    }
}
