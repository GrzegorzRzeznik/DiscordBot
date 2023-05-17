package rzeznik.grzegorz.purger;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;

public class MessagePurger extends ListenerAdapter {

    /* removes previous 1-100 messages from the channel with !purge command */
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (!e.getAuthor().isBot()
                && e.getMember().getPermissions(e.getChannel()).contains(Permission.ADMINISTRATOR)
                && e.getMessage().getContentRaw().startsWith(("!purge"))) {
            if (e.getMessage().getContentRaw().equals("!purge all")) {
                purge(e, 100);
            } else {
                purgeCustomNumber(e);
            }
        }
    }

    private void purgeCustomNumber(GuildMessageReceivedEvent e) {
        String[] input = e.getMessage().getContentRaw().split(" ");
        try {
            int noOfMessages = Integer.parseInt(input[1]) + 1;
            purge(e, noOfMessages);
        } catch (Exception exception) {
            e.getChannel().sendMessage("Incorrect format. (type !purge 1-99 or !purge all)").queue();
        }
    }


    public void purge(GuildMessageReceivedEvent e, int messages) {
        TextChannel channel = e.getChannel();
        List<Message> messageList = channel.getHistory().retrievePast(messages).complete();
        channel.deleteMessages(messageList).queue();

    }
}
