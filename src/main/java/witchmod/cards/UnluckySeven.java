package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import witchmod.WitchMod;

public class UnluckySeven extends CustomCard {
	public static final String ID = "UnluckySeven";
	public static final	String NAME = "Unlucky Seven";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. Cleanse: have your current health ending with 7.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.CURSE;
	
	private static final int POOL = 1;
	private static final int COST = -2;


	
	public UnluckySeven() {
		super(ID,NAME,WitchMod.getResourcePath(IMG),COST,DESCRIPTION,TYPE,CardColor.CURSE,RARITY,TARGET,POOL);
		this.exhaust = true;
	}
	
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
            this.useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(p, p, new UnluckySevenCleansed(), 1, false, false));
            AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
        }
    }
    
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
    	int lastDigit = (int) (AbstractDungeon.player.currentHealth - Math.floor(AbstractDungeon.player.currentHealth/10)*10);
    	if (lastDigit == 7) {
            this.dontTriggerOnUseCard = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, null));
    	}
    }

	
	public AbstractCard makeCopy() {
		return new UnluckySeven();
	}
	
	public void upgrade() {

	}
}
