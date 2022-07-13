package rzeznik.grzegorz.info;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rzeznik.grzegorz.persistence.user.CharacterClass;
import rzeznik.grzegorz.persistence.user.UserController;
import rzeznik.grzegorz.persistence.user.UserDTO;

import java.util.Optional;

public class InfoUpdater extends ListenerAdapter {

    private final UserController userController = new UserController();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        String[] messageParts = message.split(" ");
        String userId = e.getAuthor().getId();
        if (message.startsWith("!update")  && !e.getAuthor().isBot() && messageParts.length > 1){
            Optional<UserDTO> userOptional = userController.findByUserId(userId);
            if (userOptional.isPresent()) {
                UserDTO userDTO = userOptional.get();
                switch (messageParts[1].toLowerCase()) {
                    case "fname":
                        userDTO.setFamilyName(messageParts[2]);
                        userController.update(userDTO);
                        break;
                    case "name":
                        userDTO.setCharacterName(messageParts[2]);
                        userController.update(userDTO);
                        break;
                    case "class":
                        try {
                            userDTO.setCharacterClass(CharacterClass.valueOf(messageParts[2].toUpperCase()));
                            userController.update(userDTO);
                        } catch (IllegalArgumentException exception) {
                            e.getChannel().sendMessage("Class name incorrect.").queue();
                        }
                        break;
                    case "level":
                        userDTO.setLevel(Integer.parseInt(messageParts[2]));
                        userController.update(userDTO);
                        break;
                    case "ap":
                        userDTO.setAp(Integer.parseInt(messageParts[2]));
                        userController.update(userDTO);
                        break;
                    case "aap":
                        userDTO.setAap(Integer.parseInt(messageParts[2]));
                        userController.update(userDTO);
                        break;
                    case "dp":
                        userDTO.setDp(Integer.parseInt(messageParts[2]));
                        userController.update(userDTO);
                        break;
                    case "url":
                        userDTO.setGearScreenshotURL(messageParts[2]);
                        userController.update(userDTO);
                        break;
                    default:
                        e.getChannel().sendMessage("Incorrect command").queue();

                }
            }

        }
    }
}
