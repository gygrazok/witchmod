package witchmod.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.actions.CleanseAction;

public abstract class AbstractWitchCleansableCurse extends AbstractWitchCard{

	public boolean cleansed = false;
	public boolean checkAtTurnStart = true;
	public boolean checkAtTurnEnd = true;
	public boolean checkDuringTurn = true;

	public AbstractWitchCleansableCurse(String id, String name, String img, String rawDescription, CardRarity rarity) {
		super(id, name, img, -2, rawDescription, CardType.CURSE, rarity, CardTarget.NONE, 1);
	}

	protected boolean cleanseCheck() {
		return false;
	}

	@Override
	public void atTurnStart() {
		if (checkAtTurnStart && AbstractDungeon.player.hand.contains(this)) {
			doCleanseCheck();
		}
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		if (checkDuringTurn && AbstractDungeon.player.hand.contains(this)) {
			doCleanseCheck();
		}
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		super.triggerOnEndOfPlayerTurn();
		if (checkAtTurnEnd && AbstractDungeon.player.hand.contains(this)) {
			doCleanseCheck();
		}
	}

	private void doCleanseCheck() {
		if (cleansed == false && cleanseCheck()) {
			AbstractDungeon.actionManager.addToTop(new CleanseAction(this));
		}
	}

	public void cleanse() {
		cleansed = true;
		applyPowers();
	}
}
