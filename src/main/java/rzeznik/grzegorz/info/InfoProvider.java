package rzeznik.grzegorz.info;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rzeznik.grzegorz.persistence.user.CharacterClass;
import rzeznik.grzegorz.persistence.user.UserController;
import rzeznik.grzegorz.persistence.user.UserDTO;

import java.util.List;
import java.util.Optional;

public class InfoProvider extends ListenerAdapter {
    private final UserController userController = new UserController();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        String[] messageParts = message.split(" ");
        if (!e.getAuthor().isBot()
                && e.getMember().getPermissions(e.getChannel()).contains(Permission.ADMINISTRATOR)
                && message.startsWith("!info")) {
            if (messageParts.length > 1) {
                switch (messageParts[1]) {
                    case "user":
                        findSingleUser(e, messageParts[2]);
                        break;
                    case "all":
                        findAllUsers(e);
                        break;
                    case "class":
                        if (messageParts.length > 2){
                            try {
                                findUsersByClass(e, messageParts[2].toUpperCase());
                            } catch (IllegalArgumentException exception) {
                                e.getChannel().sendMessage("Invalid class name").queue();
                            }
                        }
                        break;
                    case "average":
                        if (messageParts.length == 2) {
                            showAverageOfAll(e);
                        } else {
                            try {
                                getShowAverageOfClass(e, messageParts[2].toUpperCase());
                            } catch (IllegalArgumentException exception) {
                                e.getChannel().sendMessage("Invalid class name").queue();
                            }
                        }
                        break;
                    default:
                        e.getChannel().sendMessage("Invalid command").queue();
                }
            }
        }

    }

    public void findAllUsers(GuildMessageReceivedEvent e) {
        StringBuilder sb = new StringBuilder();
        List<UserDTO> users = userController.findAll();
        for (UserDTO user : users) {
            String data = user.toString();
            sb.append(data).append("\n\n");
        }
        e.getChannel().sendMessage(sb).queue();
    }

    private void findSingleUser(GuildMessageReceivedEvent e, String familyName) {
        Optional<UserDTO> userOptional = userController.findByFamilyName(familyName);
        if (userOptional.isPresent()) {
            UserDTO user = userOptional.get();
            e.getChannel().sendMessage(user.toString()).queue();
        } else {
            e.getChannel().sendMessage("User not found").queue();
        }
    }

    private void findUsersByClass(GuildMessageReceivedEvent e, String className) {
        StringBuilder sb = new StringBuilder();
        List<UserDTO> users = userController.findByClass(CharacterClass.valueOf(className));
        for (UserDTO user : users) {
            String data = user.toString();
            sb.append(data).append("\n\n");
        }
        e.getChannel().sendMessage(sb).queue();
    }

    private void showAverageOfAll(GuildMessageReceivedEvent e) {
        List<UserDTO> users = userController.findAll();
        int averageAP = users.stream().map(UserDTO::getAp).reduce(0, Integer::sum);
        int averageAAP = users.stream().map(UserDTO::getAap).reduce(0, Integer::sum);
        int averageDP = users.stream().map(UserDTO::getDp).reduce(0, Integer::sum);
        e.getChannel().sendMessage("Average AP: " + averageAP + "\nAverage AAP: " + averageAAP + "\nAverage DP: " + averageDP).queue();
    }

    private void getShowAverageOfClass(GuildMessageReceivedEvent e, String className) {
        List<UserDTO> users = userController.findByClass(CharacterClass.valueOf(className));
        int averageAP = users.stream().map(UserDTO::getAp).reduce(0, Integer::sum);
        int averageAAP = users.stream().map(UserDTO::getAap).reduce(0, Integer::sum);
        int averageDP = users.stream().map(UserDTO::getDp).reduce(0, Integer::sum);

        if (users.isEmpty()) {
            e.getChannel().sendMessage("No users of class " + className + " available").queue();
        }else {
            e.getChannel().sendMessage(className + " class: \n Average AP: " + averageAP + "\n Average AAP: " + averageAAP + "\n Average DP: " + averageDP).queue();
        }
    }
}
