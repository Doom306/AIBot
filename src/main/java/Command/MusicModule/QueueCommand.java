/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command.MusicModule;

import Audio.Music;
import Command.Command;
import static Command.Command.embed;
import Resource.Constants;
import Resource.Emoji;
import Setting.Prefix;
import java.awt.Color;
import java.time.Instant;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class QueueCommand implements Command{
    public final static  String HELP = "This command is getting a list of queued songs\n"
                                     + "Command Usage: `"+ Prefix.getDefaultPrefix() +"queue`\n"
                                     + "Parameter: `-h | null`";

    @Override
    public void help(MessageReceivedEvent e) {
        embed.setColor(Color.red);
        embed.setTitle("Music Module", null);
        embed.addField("Queue -Help", HELP, true);
        embed.setFooter("Command Help/Usage", Constants.I_HELP);
        embed.setTimestamp(Instant.now());

        MessageEmbed me = embed.build();
        e.getChannel().sendMessage(me).queue();
        embed.clearFields();
    }

    @Override
    public void action(String[] args, MessageReceivedEvent e) {
        if(args.length == 1 && "-h".equals(args[0])) 
        {
            help(e);
        }
        else
        {
            try {
                int page = 1;
                if(args.length != 0)
                    page = Integer.parseInt(args[0]);
                Music.queueList(e, page);
            } catch (NumberFormatException npe) {
                e.getTextChannel().sendMessage(Emoji.error + " Please enter a valid page number.").queue();
                return;
            }
        }
    }

    
}
