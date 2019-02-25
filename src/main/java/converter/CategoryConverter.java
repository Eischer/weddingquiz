package converter;


import model.Category;
import view.CreateQuestionsView;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String categoryId) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{createQuestionsView}", CreateQuestionsView.class);
        CreateQuestionsView createQuestionsView = (CreateQuestionsView) vex.getValue(facesContext.getELContext());
        return !categoryId.isEmpty() ? createQuestionsView.getCategoryById(Long.valueOf(categoryId)) : null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object category) {
        return category instanceof Category ? Long.toString(((Category) category).getCategoryId()) : null;
    }
}
