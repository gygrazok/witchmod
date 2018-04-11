package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.PlayWithoutDiscardingAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class IllusionOfStrengthCurse extends AbstractWitchCard {
	public static final String ID = "IllusionOfStrengthCurse";
	public static final	String NAME = "Delusion of Strength";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. At the end of your turn, lose !M! Strength or exhaust this if you have Strength !M! or less.";

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.CURSE;

	private static final int POOL = 2;
	private static final int COST = -2;

	private static final int POWER = 2;


	public IllusionOfStrengthCurse() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			useBlueCandle(p);
		} else {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, -magicNumber), -magicNumber));
			AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
		}
	}

	@Override
	public void triggerOnEndOfTurnForPlayingCard() {
		dontTriggerOnUseCard = true;
		//if the player has strength <= 2 or if she doesn't have any strength
		if ((AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) && AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount <= magicNumber) ||
				!(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID))) {
			AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
		} else {
			AbstractDungeon.actionManager.addToBottom(new PlayWithoutDiscardingAction(this));
		}

	}


	@Override
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
	}




	public AbstractCard makeCopy() {
		return new IllusionOfStrengthCurse();
	}


	public void upgrade() {

	}
}
