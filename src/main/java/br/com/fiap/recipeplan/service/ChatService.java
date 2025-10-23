package br.com.fiap.recipeplan.service;

import br.com.fiap.recipeplan.model.Recipe;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    private final ChatClient chatClient;

    private final PromptTemplate generatePromptTemplate = new PromptTemplate("""
            Crie uma receita de {name}.
            Para a receita forneça o nome da receita, lista de ingredientes, intruções.
            Para os ingredientes, forneça o nome do ingrediente, quantidade deste ingredientes para a receita
            e unidade de medida.
            """);

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Recipe getRecipe(String name) {
        return chatClient
                .prompt(generatePromptTemplate.create(Map.of("name", name)))
                .call()
                .entity(new ParameterizedTypeReference<Recipe>() {});
    }
}
