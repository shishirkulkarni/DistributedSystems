package edu.sjsu.cs249.five;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChannelRecorder implements Observer{
	private Processor p;
	
	public ChannelRecorder(Processor p) {
		this.p = p;
	}
	
	
	public void startAll() {
		p.getInChannels().stream()
		.forEach(channel -> channel.addObserver(this));
	}
	
	public void stopAll() {
		p.getInChannels().stream()
		.forEach(channel -> channel.deleteObserver(this));
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Channel c = (Channel) arg0;
		Message message = (Message)arg1;
		if(message.getMessageType() == MessageType.MARKER) { //Duplicate Marker
			p.setSeenMarker();
			stopRecordingChannel(c); //Finalize the state of that Channel
		} else {
			System.out.println("Processor " + this.p + " got ALGORITHM from " + message.getFrom() + " on " + c);
			if(p.getChannelState().get(c) != null) {
				p.getChannelState().get(c).add(message);
			} else {
				List<Message> messageList = new ArrayList<Message>();
				messageList.add(message);
				p.getChannelState().put(c, messageList);
			}
		}
	}

	/**
	 * THis method marks the channel as empty
	 * @param c: Channel to mark empty
	 * */
	public void markChannelAsEmpty(Channel c) {
		p.getChannelState().put(c, new ArrayList<>());// Marking channel as empty
	}
	
	public void startRecordingChannel(Channel c) {
		p.getChannelState().put(c, new ArrayList<>());
		c.addObserver(this);
	}
	
	public void stopRecordingChannel(Channel c) {
		c.deleteObserver(this);
	}

}
