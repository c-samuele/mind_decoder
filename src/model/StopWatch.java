package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class StopWatch  {
	
	private IntegerProperty seconds = new SimpleIntegerProperty(0); /* type for Observer */
	private Timeline timeline;
	
	
	public StopWatch() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> seconds.set(seconds.get() + 1 )));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
		public void start() {
			timeline.play(); 
		}
		
	    public void stop() { 
	    	timeline.stop(); 
	    }
	    
	    public void reset() { 
	    	seconds.set(0); 
	    }
	    
	    public IntegerProperty getSecondsProperty() { 
	    	return seconds; 
	    }
	    
	    public int getSecondsInt() { 
	    	return seconds.get(); 
	    }
}
