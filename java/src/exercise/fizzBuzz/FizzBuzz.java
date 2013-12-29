package exercise.fizzBuzz;

public class FizzBuzz {

	protected Handler firstHandler;

	public FizzBuzz(Handler firstHandler) {
		this.firstHandler = firstHandler;
	}

	public String[] play(int max) {
		String results[] = new String[100];
		for (int i = 0; i < max; i++) {
			results[i] = firstHandler.handleRequest("", i);
		}
		return results;
	}
}

class Handler {
	public static Handler EMPTY_HANDLER = new Handler() {
		@Override
		public String handleRequest(String result, int SN) {
			return result;
		}
	};
	public Handler successor = EMPTY_HANDLER;

	public String handleRequest(String result, int SN) {
		return "";
	}

	public Handler addSuccessor(Handler successor) {
		this.successor = successor;
		return this;
	}
}

class HandlerForMultiplesOfThree extends Handler {
	@Override
	public String handleRequest(String result, int SN) {
		if ((SN + 1) % 3 == 0) {
			result += "Fizz";
		}

		return this.successor.handleRequest(result, SN);
	}
}

class HandlerForMultiplesOfFive extends Handler {
	@Override
	public String handleRequest(String result, int SN) {
		if ((SN + 1) % 5 == 0) {
			result += "Buzz";
		}
		return this.successor.handleRequest(result, SN);

	}
}

class HandlerForMultiplesOfSeven extends Handler {
	@Override
	public String handleRequest(String result, int SN) {
		if ((SN + 1) % 7 == 0) {
			result += "Whizz";
		}
		return this.successor.handleRequest(result, SN);

	}
}

class HandlerForNumberThree extends Handler {
	@Override
	public String handleRequest(String result, int SN) {
		if (String.valueOf(SN + 1).contains("3") && !result.contains("Fizz")) {
			result = "Fizz" + result;
		}
		return this.successor.handleRequest(result, SN);

	}
}

class HandlerForOthers extends Handler {
	@Override
	public String handleRequest(String result, int SN) {
		if (result.equals("")) {
			result = "" + (SN + 1);
		}
		return this.successor.handleRequest(result, SN);
	}
}
