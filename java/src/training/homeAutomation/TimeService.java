package training.homeAutomation;


public interface TimeService {
    public Time getTime();
    public void setPeriodicAlarmInSeconds(int period, TimeServiceCallback callback);

}
