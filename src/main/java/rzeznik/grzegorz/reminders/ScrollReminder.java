package rzeznik.grzegorz.reminders;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

public class ScrollReminder extends TimerTask {

    private final JDA jda;
    private final long channelId;

    public ScrollReminder(JDA jda, long channelId) {
        this.jda = jda;
        this.channelId = channelId;
    }

    @Override
    public void run() {
        List<Message> message = jda.getTextChannelById(channelId).getHistory().retrievePast(30).complete();
        if (message.isEmpty()) {
            sendReminder();
        } else {
            LocalDate date = message.get(0).getTimeCreated().toLocalDate();
            boolean reminded = message.stream()
                    .filter(m -> m.getTimeCreated().toLocalDate().getDayOfYear() == LocalDate.now().getDayOfYear())
                    .anyMatch(e -> e.getContentRaw().contains("@everyone Scroll Reminder!!!"));
            if (date.getDayOfYear() != LocalDate.now().getDayOfYear() ||
                    (date.getDayOfYear() == LocalDate.now().getDayOfYear() && !reminded)){
                sendReminder();
            }
        }
    }
    private void sendReminder() {
        jda.getTextChannelById(channelId).sendMessage("@everyone Scroll Reminder!!!").queue();
    }
}