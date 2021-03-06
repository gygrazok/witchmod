package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class BalefulWardPower extends AbstractWitchPower {
	private static final String POWER_ID = "BalefulWardPower";
	//public static final String NAME = "Baleful Ward";
	//public static final String[] DESCRIPTIONS = new String[]{ "If Block is broken add #b", " Baleful Ward to your hand."};
	public static final String IMG = "powers/balefulward.png";
	private AbstractCard card;
	public BalefulWardPower(AbstractCard cardToCopy) {
		super(POWER_ID);
		this.owner = AbstractDungeon.player;
		this.card = cardToCopy;
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
		this.isTurnBased = true;
		this.amount = 1;
		this.updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
	}
	
	@Override
	public void onBlockBreak() {
		flash();
		AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card.makeStatEquivalentCopy(),this.amount));
		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
	}

	@Override
	public void atEndOfRound() {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
	}



}

