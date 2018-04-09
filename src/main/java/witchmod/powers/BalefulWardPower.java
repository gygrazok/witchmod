package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class BalefulWardPower extends AbstractWitchPower {
	public static final String POWER_ID = "BalefulWardPower";
	public static final String NAME = "Baleful Ward";
	public static final String[] DESCRIPTIONS = new String[]{ "If Block is broken add a Baleful Ward to your hand."};
	public static final String IMG = "powers/balefulward.png";
	private AbstractCard card;
	public BalefulWardPower(AbstractCard cardToCopy) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.card = cardToCopy;
		this.description = DESCRIPTIONS[0];
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
		this.isTurnBased = true;
		this.amount = 1;
	}

	@Override
	public void onDamageAbsorbedByBlock(int amount, int actualBlockLost, int currentBlock) {
		if (currentBlock == 0) {
			flash();
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card.makeStatEquivalentCopy()));
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, BalefulWardPower.POWER_ID));
		}
	}

	@Override
	public void atEndOfRound() {
		if (amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, BalefulWardPower.POWER_ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, BalefulWardPower.POWER_ID, 1));
		}
	}



}

