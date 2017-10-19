package mail;

public interface IPop3CB{
	public void OnStartAuthorization(String msg);
	public void OnAuthorized(String msg);
	
	public void OnStartRETR(int msg);
	public void OnEndRETR(int msg);
	public void OnStartTOP(int msg);
	public void OnEndTOP(int msg);
	public void OnStartSTAT();
	public void Message(String msg);
}