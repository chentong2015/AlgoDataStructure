package interview;

import java.util.HashMap;

public class DesignQuestion {

    HashMap<String, Command> commandHashMap = new HashMap<>();

    void registerCommand(String commandName, Command command) {
        if (commandHashMap.containsKey(commandName)) {
            return;
        }
        commandHashMap.put(commandName, command);
    }

    String exec(String commandName, String text) {
        if (this.commandHashMap.containsKey(commandName)) {
            return this.commandHashMap.get(commandName).exec(text);
        } else {
            throw new IllegalArgumentException(commandName);
        }
    }

    public static void main(String[] args) {
        DesignQuestion designQuestion = new DesignQuestion();
        // designQuestion.registerCommand("count", new WordCountCommand());
        String result1 = designQuestion.exec("count", "this is line");
        String result2 = designQuestion.exec("check", "this is check");
    }

    // 将缘有的逻辑提取到抽象类型中，对实际的代码进行解耦
    abstract class Command {
        abstract String exec(String text);
    }

    class WordCountCommand extends Command {
        @Override
        String exec(String text) {
            if (!text.contains(" ")) {
                return "1";
            }
            int countWords = text.split(" ").length;
            return String.valueOf(countWords);
        }
    }
}
