

import org.freeswitch.esl.client.IEslEventListener;
import org.freeswitch.esl.client.transport.event.EslEvent;
import java.util.logging.*;


public class EslEventListener implements IEslEventListener
{
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public void eventReceived(EslEvent event)
	{
		LOGGER.info(event.getEventDateLocal() + "  EVENT NAME: " + event.getEventName());
		LOGGER.info("EVENT HEADER: " + event.getEventHeaders());
		if (event.hasEventBody())
		{
			LOGGER.info("EVENT BODY :" + event.getEventBodyLines());
		}
		LOGGER.info("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public void backgroundJobResultReceived(EslEvent event){}

}
