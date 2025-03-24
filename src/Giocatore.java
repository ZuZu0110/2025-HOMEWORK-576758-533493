
public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	public Borsa borsa;
	public int cfu;
	
	public Giocatore(Borsa borsa) {
		this.cfu = CFU_INIZIALI;
		this.borsa = borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
}
