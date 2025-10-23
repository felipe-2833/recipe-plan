package br.com.fiap.recipeplan.views;

import br.com.fiap.recipeplan.model.Recipe;
import br.com.fiap.recipeplan.service.ChatService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    private final VerticalLayout responsePanel = new VerticalLayout();
    private final ChatService chatService;

    public HomeView(ChatService chatService) {
        var input = new MessageInput();
        input.setWidthFull();

        this.chatService = chatService;

        add(new H1("Planejador de Receitas"));
        add(new Paragraph("O que você gostaria de cozinhar hoje?"));
        add(input);
        input.addSubmitListener(e -> {
            showRecipe(chatService.getRecipe(e.getValue()));
        });
    }

    private void showRecipe(Recipe recipe) {
        responsePanel.removeAll();
        responsePanel.add(new RecipeView(recipe));
        add(responsePanel);
    }

}
