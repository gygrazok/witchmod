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
import witchmod.patches.AbstractCardEnum;

public class EternalThirst extends CustomCard {
	public static final String ID = "EternalThirst";
	public static final	String NAME = "Eternal Thirst";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: suffer at least 20 damage in this fight.";
	public static final	String[] EXTENDED_DESCRIPTION = new String[] {" NL You have suffered ", " damage."};
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.CURSE;
	
	private static final int POOL = 2;
	private static final int COST = -2;


	
	public EternalThirst() {
		super(ID,NAME,WitchMod.getResourcePath(IMG),COST,DESCRIPTION,TYPE,AbstractCardEnum.WITCH,RARITY,TARGET,POOL);
		this.exhaust = true;
	}
	
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
            this.useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(p, p, new EternalThirstCleansed(), 1, false, false));
            AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
        }
    }
    
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
    	if (AbstractDungeon.player.damagedThisCombat >= 20) {
            this.dontTriggerOnUseCard = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, null));
    	}
    }
    
    @Override
    public void tookDamage(){
        rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0]+AbstractDungeon.player.damagedThisCombat+EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }
    

	
	public AbstractCard makeCopy() {
		return new EternalThirst();
	}
	
	public void upgrade() {

	}
}
