package top.mrxiaom.miraiutils;

public abstract class CommandSender {
	private final long senderId;
	private final String senderNick;
	private final int sendTime;
	protected CommandSender(long senderId, String senderNick, int sendTime) {
		this.senderId = senderId;
		this.senderNick = senderNick;
		this.sendTime = sendTime;
	}
	
	public long getSenderID() {
		return this.senderId;
	}
	
	public String getSenderNick() {
		return this.senderNick;
	}
	
	public int getSendTime() {
		return this.sendTime;
	}
}
