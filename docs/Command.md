# CommandListener
----
## 命令系统帮助  

用法:  
```java
CommandListener cmdListener = new CommandListener("命令前缀");
cmdListener.registerCommand(命令);
cmdListener.registerToChannel(事件频道);
```
新建命令只需要让类继承 `CommandModel` 即可  

----
## 举个例子
  
新建命令  
```java
public class ExampleCommand extends CommandModel{

	public ExampleCommand() {
		super("example");
	}
  
	@Override
	public void onCommand(CommandSender sender, SingleMessage[] args) {
		if(sender instanceof CommandSenderGroup) {
			CommandSenderGroup senderGroup = (CommandSenderGroup) sender;
			Group group = senderGroup.getGroup();
			Member member = senderGroup.getMember();
			QuoteReply quote = new QuoteReply(senderGroup.getMessageSource());
			if(args.length >= 1) {
				if(args.length == 1) {
					if(args[0].contentToString().equalsIgnoreCase("getid")) {
						group.sendMessage(quote.plus("Your QQ UID is " + member.getId() + "."));
					}
					else if (args[0].contentToString().equalsIgnoreCase("gettime")) {
						group.sendMessage(quote.plus("Now time is " + this.getTime() + "."));
					}
				}
				if(args[0].contentToString().equalsIgnoreCase("at")) {
					if(args.length == 1) {
						group.sendMessage(quote.plus("Using: /example at <@somebody>."));
					}
					if(args[1] instanceof At) {
						At at = (At) args[1];
						group.sendMessage(quote.plus(
							"Target UID: " + at.getTarget() + "\n" +
							"Display: " + at.getDisplay(group)));
					}
					else {
						group.sendMessage(quote.plus("args[1] is not instance of At."));
					}
				}
			}
		}
	}
	
	private String getTime() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
```
初始化命令监听器，注册命令并注册监听器到全局事件频道:
```java
CommandListener cmdListener = new CommandListener("/");
cmdListener.registerCommand(new ExampleCommand());
cmdListener.registerToChannel(GlobalEventChannel.INSTANCE);
// 上面那句也可以改成 GlobalEventChannel.INSTANCE.registerListenerHost(cmdListener);
```
示例命令用法如下:  
在机器人所在的群里  
* 发送 `/example getid` 来获取自己的qq号
* 发送 `/example gettime` 来获取机器人所在服务器的时间
* 发送 `/example at @某人` 来获取被@的人的QQ号和在该群被@时显示的文本
  
  
总之就是很方便
