package top.mrxiaom.miraiutils;

import net.mamoe.mirai.contact.Stranger;
import net.mamoe.mirai.message.data.MessageSource;

public class CommandSenderStranger extends CommandSender{
	private final Stranger stranger;
	private final MessageSource source;
	public CommandSenderStranger(Stranger stranger, MessageSource source, int time) {
		super(stranger.getId(), stranger.getNick(), time);
		this.stranger = stranger;
		this.source = source;
	}

	public Stranger getStranger() {
		return this.stranger;
	}
	
	public MessageSource getMessageSource() {
		return this.source;
	}
}
