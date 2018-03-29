package witchmod.cards;

public abstract class AbstractWitchCleansableCurse extends AbstractWitchCard{
	public boolean cleansed = false;
	
	public AbstractWitchCleansableCurse(String id, String name, String img, String rawDescription, CardRarity rarity) {
		super(id, name, img, -2, rawDescription, CardType.CURSE, rarity, CardTarget.NONE, 1);
	}
	
	protected boolean cleanseCheck() {
		return false;
	}

	@Override
	public void triggerWhenDrawn() {
		if (cleansed == false && cleanseCheck()) {
			cleanse();
		}
	}
	
	
	public void cleanse() {
		cleansed = true;
	}
}
