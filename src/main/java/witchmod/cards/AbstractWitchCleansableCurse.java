package witchmod.cards;

public abstract class AbstractWitchCleansableCurse extends AbstractWitchCard{

	public AbstractWitchCleansableCurse(String id, String name, String img, int cost, String rawDescription,CardType type, CardRarity rarity, CardTarget target) {
		super(id, name, img, -2, rawDescription, CardType.CURSE, rarity, CardTarget.NONE, 1);
	}
	
	protected boolean cleanseCheck() {
		return false;
	}

	@Override
	public void triggerWhenDrawn() {
		if (cleanseCheck()) {
			cleanse();
		}
	}
	
	
	public void cleanse() {
		
	}
}
