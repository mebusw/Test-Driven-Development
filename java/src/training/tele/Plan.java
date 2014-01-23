package training.tele;

public class Plan {

	protected int numberOfLines;
	protected double ratePerAdditionalLine = 0;
	protected double basicMonthlyRate = 0;
	protected int usedMinutes;
	protected double ratePerExceedMinute;
	protected int includedMinutes;

	public Plan(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public Plan() {
	}

	protected double billing() {
		if (usedMinutes > includedMinutes)
			return monthlyBasicRate() + (usedMinutes - includedMinutes)
					* ratePerExceedMinute;
		return monthlyBasicRate();

	}

	protected double monthlyBasicRate() {
		return (numberOfLines - 1) * ratePerAdditionalLine + basicMonthlyRate;
	}

}