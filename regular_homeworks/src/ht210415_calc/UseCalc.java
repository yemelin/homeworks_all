package ht210415_calc;

/* Command-line command-based calculator upgrade
 * Uses command-line arguments or interactive keyboard input (called with no args)
 * Since it has no stack memory, I assume it acts like a primitive calculator,
 * where all commands are applied to the current "total"
 * Added to original Calculator: some commands and _cleared state field
 * Valid command-line: 
 * 10 plus 20 div 10 sqr sqr clear 10
 * Output: 10 30 3 9 81 0 10
*/
import java.util.Scanner;

public class UseCalc {
	private final static int OPMISS = 1;
	private final static int DIVZERO = 2;
	private final static int UNOP = 3;
	private final static String[] ERRORS = {"Operand missing",
			"Division by zero", "Syntax error or unsupported operation"};

	private int _pos;
	private int _error;
	private Calculator calc;// = new Calculator();
	private String [] tokens;

//	acquire next command string
	public void setTokens (String[] args) {
		tokens = args;
		_pos = 0;
		_error=0;
	}
	
	private boolean isBinOp (String s) {
		return (s.equals("plus") ||
				s.equals("minus") ||
				s.equals("mult") ||
				s.equals("div"));
	}
	private Calculator.Command getBinOp(String s, int n) {
		Calculator.Command c = null;
		if (s.equals("plus"))
			c = calc.new Plus(n);
		else if (s.equals("minus"))
			c = calc.new Minus(n);
		else if (s.equals("mult"))
			c = calc.new Mult(n);
		else if (s.equals("div")) {
			if (n==0)
				_error=DIVZERO;
			else
				c = calc.new Div(n);
		}
		return c;
	}

	private Calculator.Command getOp (String s) {
		Calculator.Command c = null;
		if (s.equals("get"))
			c = calc.new Get();
		else if (s.equals("clear"))
			c = calc.new Clear();
		else if (s.equals("sqr"))
			c = calc.new Sqr();
		return c;
	}

//  read next binary op (+, -...) with its operand or other op (sqr, clear...).
	private Calculator.Command readNextCommand() {
		Calculator.Command c = null; //will be returned on failure
		int operand;
		_error=0;
		if (isBinOp(tokens[_pos])) {
				_pos++;
				operand = readInt();
				if (_error==0)
					c = getBinOp(tokens[_pos-2], operand);
		}
		else {
			c = getOp(tokens[_pos++]);
			if (c==null)
				_error = UNOP;
		}
		return c;
	}

//	try to read current token as int. Set error marker in case of failure.
	private int readInt() {
		int ret=0;
		_error=0;
		if (_pos>=tokens.length) {
			_error = OPMISS;
		}
		else {
			try {
				ret = Integer.parseInt(tokens[_pos]);
			}
			catch (NumberFormatException nfe){
				_error=OPMISS;
			}
			_pos++;
		}
		return ret;
	}
	
	public void printArgs() {
		for (int i = 0; i < tokens.length; i++) {
			System.out.print(tokens[i]+" ");
		}
		System.out.println();
	}
	
//	TODO: reorganize logic to get rid of duplicated code around readInts
	public void execString () {
		Calculator.Command cmd = null;
		do {
			int pos = _pos; //save for possible failure to read command
			cmd = readNextCommand();
			if (cmd==null) { //couldn't read a command
				if (calc.isCleared()) { //try to read an int and set it
				_pos = pos;
				int operand = readInt();
				if (_error==0)
					cmd = calc.new Set(operand);
				}
			}
			if (cmd!=null)
				cmd.execute();
		} while (cmd!=null && _pos<tokens.length);
		if (_error>0)
			System.out.println("Error at position "+(_pos-1)+": "+ERRORS[_error-1]);
	}
	
	public static void main(String[] args) {
		UseCalc us = new UseCalc();
		us.calc = new Calculator();
		if (args.length>0) {
			us.setTokens(args);
			us.printArgs();
			us.execString();
		}
		else {
			Scanner sc = new Scanner(System.in);
			while (sc.hasNextLine()) {
				us.setTokens (sc.nextLine().split("\\s"));
				us.execString();
			}
		}
	}
}
