package se.kth.mlanglet.types;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Message implements Serializable {
	
	/**
	 * A message to be sent over socket
	 */
	private static final long serialVersionUID = 6730974851296321683L;

	public enum MessageType {
		TEXT, HELLO, BYE, MOVE
	}
	
	private String 		messageText;
	private String		messageFrom;
	private String		messageTime;
	private Move 		messageMove;
	private MessageType messageType;
	private boolean		messageBool;
	
	public Message(Message message, String from){		
		messageText = message.getMessage();
		messageTime = getMessageTime();
		messageType = message.getType();
		messageFrom	 = from;
	}
	
	public Message(Message message, MessageType type){
		messageText = message.getMessage();
		messageTime = getMessageTime();
		messageType = type;
		messageFrom	= message.getName();
	}
	
	public Message(String message, MessageType type, String from){
		messageText = message;
		messageTime = getMessageTime();
		messageType = type;
		messageFrom = from;
	}
	
	public Message(String message, MessageType type, String from, boolean yourMove){
		messageText = message;
		messageTime = getMessageTime();
		messageType	= type;
		messageFrom = from;
		messageBool	= yourMove;
	}
	
	public Message(Move move, String from){
		messageMove = move;
		messageTime = getMessageTime();
		messageType = MessageType.MOVE;
		messageFrom = from;
	}
	
	public String getMessage(){
		return messageText;
	}
	
	public String getTime(){
		return messageTime;
	}
	
	public String getName(){
		return messageFrom;
	}
	
	public MessageType getType(){
		return messageType;
	}
	
	public Move getMove(){
		return messageMove;
	}
	
	public boolean getTurn(){
		return messageBool;
	}
	
	private String getMessageTime(){
		Calendar cal = new GregorianCalendar();
		
		cal.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
		
		int hour24 = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		messageTime = "[" + hour24 + ":" + min + ":" + sec + "]";
		
		return messageTime;
	}
}
