package exercise.fizzBuzz;

public class FizzBuzz {

	protected Handler firstHandler;

	public FizzBuzz(Handler firstHandler) {
		this.firstHandler = firstHandler;
	}

	public String[] play(int max) {
		String results[] = new String[100];
		for (int i = 0; i < max; i++) {
			results[i] = firstHandler.handleRequest(i + 1);
		}
		return results;
	}
}

abstract class Handler {

	public Handler successor;

	public abstract String handleRequest(int SN);

	public Handler addSuccessor(Handler successor) {
		this.successor = successor;
		return this;
	}
}

class HandlerForMultiplesOfFifteen extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (SN % 15 == 0) {
			return "FizzBuzz";
		}

		return this.successor.handleRequest(SN);
	}
}

class HandlerForMultiplesOfThree extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (SN % 3 == 0) {
			return "Fizz";
		}

		return this.successor.handleRequest(SN);
	}
}

class HandlerForMultiplesOfFive extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (SN % 5 == 0) {
			return "Buzz";
		}
		return this.successor.handleRequest(SN);

	}
}

class HandlerForMultiplesOfSeven extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (SN % 7 == 0) {
			return "Whizz";
		}
		return this.successor.handleRequest(SN);

	}
}

class HandlerForMultiplesOfThirtyFive extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (SN % 35 == 0) {
			return "FizzBuzzWhizz";
		}
		return this.successor.handleRequest(SN);

	}
}

class HandlerForNumberThree extends Handler {
	@Override
	public String handleRequest(int SN) {
		if (String.valueOf(SN).contains("3")) {
			return "Fizz";
		}
		return this.successor.handleRequest(SN);

	}
}

class HandlerForOthers extends Handler {
	@Override
	public String handleRequest(int SN) {
		return "" + SN;
	}
}
