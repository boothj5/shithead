public class SimpleComputerPlayer extends Player {

	public SimpleComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public String askSwapMore() {
		return "n" ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
}
