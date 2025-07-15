import java.util.HashMap;
import java.util.Scanner;

public class aichatbot {

    private static HashMap<String, String> knowledgeBase;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Welcome to CodeAlpha AI ChatBot!");
        System.out.println("Type 'exit' to end the conversation.\n");

        // Initialize FAQ database
        initKnowledgeBase();

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("exit")) {
                System.out.println("Bot: Goodbye! Have a great day 😊");
                break;
            }

            String response = getResponse(input);
            System.out.println("Bot: " + response);
        }
    }

    // Train with FAQ and rules
    private static void initKnowledgeBase() {
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("hello", "Hi there! What can I do for you?");
        knowledgeBase.put("how are you", "I'm just a bunch of code, but I'm running well!");
        knowledgeBase.put("what is your name", "I'm CodeAlpha AI ChatBot 🤖");
        knowledgeBase.put("what is java", "Java is a high-level, object-oriented programming language.");
        knowledgeBase.put("what is ai", "AI stands for Artificial Intelligence. It's the simulation of human intelligence by machines.");
        knowledgeBase.put("who created you", "I was created by a CodeAlpha intern using Java!");
        knowledgeBase.put("thank you", "You're welcome!");
    }

    // NLP-style preprocessing & matching
    private static String getResponse(String input) {
        // Basic normalization
        input = input.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();

        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "Sorry, I don't understand that yet. Can you rephrase?";
    }
}
