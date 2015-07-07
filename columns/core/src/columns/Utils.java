package columns;

public class Utils {

	public static void Delay(long t) {
	    try {
	        Thread.sleep(t);
	    }
	    catch (InterruptedException e) {
	    	e.printStackTrace();
	    };
	}

}
