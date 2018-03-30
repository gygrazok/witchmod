package witchmod.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.actions.CleanseAction;

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
			AbstractDungeon.actionManager.addToTop(new CleanseAction(this));
		}
	}
	
	
	public void cleanse() {
		cleansed = true;
	}
}
