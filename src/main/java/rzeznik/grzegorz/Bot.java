package rzeznik.grzegorz;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import rzeznik.grzegorz.info.InfoProvider;
import rzeznik.grzegorz.info.InfoUpdater;
import rzeznik.grzegorz.purger.MessagePurger;
import rzeznik.grzegorz.recruitment.Recruiter;
import rzeznik.grzegorz.reminders.ScrollReminder;

import java.util.Timer;

public class Bot {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault(args[0]).build();
        ScrollReminder reminder = new ScrollReminder(jda, Long.parseLong(args[1]));
        Timer timer = new Timer();
        timer.schedule(reminder, 1000L, 86400000L);
        Recruiter recruiter = new Recruiter(args[2]);
        MessagePurger purger = new MessagePurger();
        InfoProvider infoProvider = new InfoProvider();
        jda.addEventListener(recruiter);
        jda.addEventListener(purger);
        jda.addEventListener(infoProvider);
        jda.addEventListener(new InfoUpdater());

    }

}
