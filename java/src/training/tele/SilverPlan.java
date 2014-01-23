package training.tele;

public class SilverPlan extends Plan {

	public SilverPlan(int numberOfLines) {
		super(numberOfLines);
		basicMonthlyRate = 29.95;
		ratePerAdditionalLine = 21.5;
		ratePerExceedMinute = 0.54;
		includedMinutes = 500;
	}

	public SilverPlan(int numberOfLines, int usedMinutes) {
		this(numberOfLines);
		this.usedMinutes = usedMinutes;
	}

	protected double billing() {
		return super.billing();
	}

}
