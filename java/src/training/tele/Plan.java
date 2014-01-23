package training.tele;

public class Plan {

	protected static final int familyDiscountLimit = 3;
	protected int numberOfLines;
	protected double ratePerAdditionalLine;
	protected double basicMonthlyRate;
	protected int usedMinutes;
	protected double ratePerExceedMinute;
	protected int includedMinutes;

	public Plan(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public Plan() {
	}

	protected double billing() {
		return feeOfIncludedMinutes() + feeOfExceedMinute();

	}


	protected double feeOfIncludedMinutes() {
		if (numberOfLines > familyDiscountLimit)
			return (numberOfLines - familyDiscountLimit) * 5 + 2
					* ratePerAdditionalLine + basicMonthlyRate;
		return (numberOfLines - 1) * ratePerAdditionalLine + basicMonthlyRate;
	}

	protected double feeOfExceedMinute() {
		double feeOfExceedMinute = 0;
		if (usedMinutes > includedMinutes)
			feeOfExceedMinute = (usedMinutes - includedMinutes)
					* ratePerExceedMinute;
		return feeOfExceedMinute;
	}
}