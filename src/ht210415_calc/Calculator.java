package ht210415_calc;

public class Calculator {

	// TODO
	//  java Calculator 10 plus 20 plus 100 minus 30 get
	private boolean _cleared = true;
	
	abstract class Command {
		public void execute() {
			realExecute();
			System.out.println(total);
		}

		abstract void realExecute();
	}
	
	public class Plus extends Command {
		
		int _value;

		Plus(final int value) {
			_value = value;
		}
		
		@Override
		void realExecute() {
			total += _value;
		}
	}
	
	public class Minus extends Plus {
		Minus(final int value) {
			super(-value);
		}
	}
	
	public class Mult extends Plus {
		Mult(final int value) {
			super(value);
		}

		@Override
		void realExecute() {
			total*=_value;			
		}
	}
//	integer division
	public class Div extends Plus {
		Div(final int value) {
			super(value);
		}

		@Override
		void realExecute() {
			total/=_value;			
		}		
	}
//	n^2
	public class Sqr extends Command {
		@Override
		void realExecute() {
			total*=total;			
		}		
	}
	
	public class Clear extends Command {

		@Override
		void realExecute() {
			total = 0;
			_cleared = true;
		}
		
	}
	
	public class Get extends Command {

		@Override
		void realExecute() {
			System.out.println("from get");
			// do nothing
		}

	}
	
	
	public class Set extends Plus {
		Set(final int value) {
			super(value);
			_cleared = false;
		}
	}
	
	public final boolean isCleared() {
		return _cleared;
	}
	
	int total = 0;
		

}
