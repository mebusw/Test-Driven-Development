package training.tele;

public class GoldPlan extends Plan {

	public GoldPlan(int numberOfLines) {
		super(numberOfLines);
		ratePerAdditionalLine = 14.5;
		basicMonthlyRate = 49.95;
		ratePerExceedMinute = 0.45;
		includedMinutes = 1000;
	}

	public GoldPlan(int numberOfLines, int usedMinutes) {
		this(numberOfLines);
		this.usedMinutes = usedMinutes;
	}



}
