package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import witchmod.WitchMod;
import witchmod.patches.AbstractCardEnum;

public class IllusionOfStrengthCurse extends CustomCard {
	public static final String ID = "IllusionOfStrengthCurse";
	public static final	String NAME = "Delusion of Strength";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. At the end of your turn, lose !M! Strength. Exhaust this if you have Strength 0 or less.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.CURSE;
	
	private static final int POOL = 2;
	private static final int COST = -2;

	private static final int POWER = 2;

	
	public IllusionOfStrengthCurse() {
		super(ID,NAME,WitchMod.getResourcePath(IMG),COST,DESCRIPTION,TYPE,AbstractCardEnum.WITCH,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}
	
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
            this.useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber), -magicNumber));
            AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
        }
    }
    
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
    	this.dontTriggerOnUseCard = true;
        //if the player has strength <= 0 or if she doesn't have any strength
        if ((AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) && AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount <= magicNumber) ||
        		!(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID))) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        } else {
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, null));
        }

    }

    
    
	
	public AbstractCard makeCopy() {
		return new IllusionOfStrengthCurse();
	}
	
	
	public void upgrade() {
		
	}
}
