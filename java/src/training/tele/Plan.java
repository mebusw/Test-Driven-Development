package training.tele;

public class Plan {

	protected int numberOfLines;
	protected double ratePerAdditionalLine = 0;
	protected double basicMonthlyRate = 0;

	public Plan(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	protected double billing() {
		return (numberOfLines - 1) * ratePerAdditionalLine + basicMonthlyRate;
	}

}