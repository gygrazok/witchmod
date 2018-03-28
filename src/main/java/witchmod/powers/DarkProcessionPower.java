package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class DarkProcessionPower extends AbstractPower {
	public static final String POWER_ID = "DarkProcessionPower";
	public static final String NAME = "Dark Procession";
	public static final String[] DESCRIPTIONS = new String[]{ "At the start of your turn add a copy of the last non-exhausted played card to your hand.", "NL Last played card: " };
	public static final String IMG = "powers/athamesoffering.png";
	private AbstractCard card;
	public DarkProcessionPower() {
		this.name = NAME;
		this.ID = POWER_ID;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;

	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0]+DESCRIPTIONS[1]+card.name;
	}

	@Override
	public void atStartOfTurn() {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			this.flash();
			AbstractCard toCreate = card.makeStatEquivalentCopy();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(toCreate, 1, false));
		}
	}
	
	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		if (!usedCard.exhaust) {
			card = usedCard;
		}
	}

	
}

