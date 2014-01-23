package training.tele;

public class Plan {

	protected static final int RATE_PER_FAMILY_DISCOUNT_LINE = 5;
	protected static final int FAMILY_DISCOUNT_LIMIT = 3;
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
		return rateOfAddtionalLines() + basicMonthlyRate;
	}

	protected double rateOfAddtionalLines() {
		if (numberOfLines > FAMILY_DISCOUNT_LIMIT)
			return (numberOfLines - FAMILY_DISCOUNT_LIMIT)
					* RATE_PER_FAMILY_DISCOUNT_LINE + 2 * ratePerAdditionalLine;
		return (numberOfLines - 1) * ratePerAdditionalLine;
	}

	protected double feeOfExceedMinute() {
		double feeOfExceedMinute = 0;
		if (usedMinutes > includedMinutes)
			feeOfExceedMinute = (usedMinutes - includedMinutes)
					* ratePerExceedMinute;
		return feeOfExceedMinute;
	}
}