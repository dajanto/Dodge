import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
	
	private int countdown;
	private long delay  = 1000L;
	private long period = 1000L;
	
	public Countdown(int countdown, long delay, long period) {
		
		this.countdown = countdown;
		this.delay = delay;
		this.period = period;
	}
	
	public void start(){
		         
		Timer timer = new Timer("Timer");
		
	    TimerTask repeatedTask = new TimerTask() {
	    	
	        public void run() {

	            if (countdown > 0) {
	            	System.out.println(countdown);
		            countdown--;
				} else {
					System.out.println(countdown);
	            	System.exit(0);
				}
	        }
	    };
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}	
}
