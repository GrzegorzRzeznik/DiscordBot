package rzeznik.grzegorz.recruitment;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rzeznik.grzegorz.persistence.user.CharacterClass;
import rzeznik.grzegorz.persistence.user.UserController;
import rzeznik.grzegorz.persistence.user.UserDTO;
import java.util.Objects;

public class Recruiter extends ListenerAdapter {

    private static final UserController userController = new UserController();
    private final String channelID;
    private UserDTO userDTO = new UserDTO();
    private Member currentUser;
    private int surveyStep = 0;

    public Recruiter(String channelID) {
        this.channelID = channelID;
    }

    /*Initializing and resetting survey*/
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String messageContent = e.getMessage().getContentRaw();

        if (e.getChannel().getId().equals(channelID)
                && !e.getAuthor().isBot()) {
            if (messageContent.equals("!reset")) {
                e.getChannel().sendMessage("Recruitment process reset.").queue();
                reset();
                return;
            }
            if (currentUser == null && messageContent.equals("!start")
                    && e.getMember().getRoles().stream().anyMatch(r -> r.getName().equals("Trial"))){
                currentUser = e.getMember();
            }
            if (isUserValid(e) && !messageContent.startsWith("!purge")) {
                survey(e);
            }
        }
    }

    /* user survey and saving to database on successful survey*/
    private void survey(GuildMessageReceivedEvent e) {
        switch (surveyStep) {
            case 0:
                e.getChannel().sendMessage(String.format("Hi %s, what is your family name?", currentUser.getUser().getName())).queue();
                surveyStep++;
                break;
            case 1:
                userDTO.setFamilyName(e.getMessage().getContentRaw());
                e.getChannel().sendMessage("What is your character name?").queue();
                surveyStep++;
                break;
            case 2:
                userDTO.setCharacterName(e.getMessage().getContentRaw());
                e.getChannel().sendMessage("What is your class?").queue();
                surveyStep++;
                break;
            case 3:
                String className = e.getMessage().getContentRaw().toUpperCase();
                try{
                userDTO.setCharacterClass(CharacterClass.valueOf(className));
                }catch (IllegalArgumentException exception){
                    e.getChannel().sendMessage("Class name incorrect.").queue();
                    break;
                }
                e.getChannel().sendMessage("What is your level?").queue();
                surveyStep++;
                break;
            case 4:
                userDTO.setLevel(Integer.parseInt(e.getMessage().getContentRaw()));
                e.getChannel().sendMessage("What is your ap?").queue();
                surveyStep++;
                break;
            case 5:
                userDTO.setAp(Integer.parseInt(e.getMessage().getContentRaw()));
                e.getChannel().sendMessage("What is your aap?").queue();
                surveyStep++;
                break;
            case 6:
                userDTO.setAap(Integer.parseInt(e.getMessage().getContentRaw()));
                e.getChannel().sendMessage("What is your dp?").queue();
                surveyStep++;
                break;
            case 7:
                userDTO.setDp(Integer.parseInt(e.getMessage().getContentRaw()));
                e.getChannel().sendMessage("Please post url to your gear screenshot").queue();
                surveyStep++;
                break;
            case 8:
                userDTO.setGearScreenshotURL((e.getMessage().getContentRaw()));
                e.getChannel().sendMessage("Thank you!").queue();
                userDTO.setUserId(e.getAuthor().getId());
                userController.save(userDTO);
                System.out.println(userDTO);
                reset();
        }

    }

    private boolean isUserValid(GuildMessageReceivedEvent e) {
        return Objects.equals(e.getMember(), currentUser)
                && !e.getMessage().getAuthor().isBot()
                && e.getMember().getRoles().stream().anyMatch(r -> r.getName().equals("Trial"));
    }

    private void reset() {
        surveyStep = 0;
        currentUser = null;
        userDTO = new UserDTO();
    }


}



