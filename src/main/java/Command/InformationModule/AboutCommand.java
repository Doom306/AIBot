/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command.InformationModule;

import Command.Command;
import Resource.Constants;
import Setting.Prefix;
import java.awt.Color;
import java.time.Instant;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 *
 * @author liaoyilin
 */
public class AboutCommand implements Command{

    public final static  String HELP = "This command is for getting the bot's description.\n"
                                     + "Command Usage: `"+ Prefix.getDefaultPrefix() +"about`\n"
                                     + "Parameter: `-h | null`";
    
    public final static String BOT_DES = "The AIBot that started in the March of 2017 for music and fun commands.\n"
                                    + "**Developer:** Ayy™#3103 *aka* AlienIdeology\n"
                                    + "**Version:** " + Constants.VERSION +"\n"
                                    + "**Annoucement:** Working on Eval, Dump, Shuffle, Repeat and Softban~~\n"
                                    + "**Latest Changes:** LogCommand and Server Join Message\n"
                                    + "**Github Link:** " + Constants.B_GITHUB;
    private final EmbedBuilder embed = new EmbedBuilder();
    private final EmbedBuilder embeddes = new EmbedBuilder();
    

    @Override
    public void help(MessageReceivedEvent e) {
        embed.setColor(Color.red);
        embed.setTitle("Information Module", null);
        embed.addField("About -Help", HELP, true);
        embed.setFooter("Command Help/Usage", Constants.I_HELP);
        embed.setTimestamp(Instant.now());

        MessageEmbed me = embed.build();
            e.getChannel().sendMessage(me).queue();
        embed.clearFields();
    }

    @Override
    public void action(String[] args, MessageReceivedEvent e) {
        if(args.length == 0) 
        {
            embeddes.setColor(Color.red);
            embeddes.setAuthor("What is " + Constants.B_NAME + "?", Constants.B_DISCORD_BOT, Constants.I_INFO);
            embeddes.setThumbnail(Constants.B_AVATAR);
            embeddes.setDescription(BOT_DES);
            embeddes.setFooter("Request by " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), e.getAuthor().getEffectiveAvatarUrl());
            embeddes.setTimestamp(Instant.now());

            MessageEmbed medes = embeddes.build();
            
            e.getChannel().sendMessage(medes).queue();
        }

        else if("-h".equals(args[0])) 
        {
            help(e);
        }
    }

    
}
