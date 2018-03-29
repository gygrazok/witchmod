package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import witchmod.WitchMod;
import witchmod.actions.ReduceBlockAction;
import witchmod.patches.AbstractCardEnum;

public class RustWallCurse extends CustomCard{
	public static final String ID = "RustWallCurse";
	public static final	String NAME = "Curse of Rust";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. At the end of your turn, lose half of your block. NL Exhausts after consuming !M! Block.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.CURSE;
	
	private static final int POOL = 2;
	private static final int COST = -2;

	private static final int POWER = 10;

	private int blockConsumed = 0;
	public RustWallCurse() {
		super(ID,NAME,WitchMod.getResourcePath(IMG),COST,DESCRIPTION,TYPE,AbstractCardEnum.WITCH,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}
	
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
            this.useBlueCandle(p);
        } else {
        	int blockToConsume = Math.floorDiv(p.currentBlock,2);
        	blockConsumed += blockToConsume;
            AbstractDungeon.actionManager.addToTop(new ReduceBlockAction(p, p, blockToConsume));
            AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
            if (blockConsumed >= magicNumber) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
            }
        }
    }
    
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
    	this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, null));
    }

	
	public AbstractCard makeCopy() {
		return new RustWallCurse();
	}
	
	
	public void upgrade() {
		
	}
}
