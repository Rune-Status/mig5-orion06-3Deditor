package client;

public class ClickRecorder {

	int x,y,time;
	
	public ClickRecorder(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
	public int getTime(){
		return time;
	}
	
}
