package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GenericSmokeEffect;

import witchmod.actions.ReduceBlockAction;

public class RustWallCurse extends AbstractWitchCard{
	public static final String ID = "RustWallCurse";
	public static final	String NAME = "Curse of Rust";
	public static final	String IMG = "cards/rustwallcurse.png";
	public static final	String DESCRIPTION = "Unplayable. At the end of your turn, lose half of your Block and exhaust if it has consumed at least !M! Block in total.";

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.CURSE;

	private static final int POOL = 2;
	private static final int COST = -2;

	private static final int POWER = 10;

	private int blockConsumed = 0;
	public RustWallCurse() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			useBlueCandle(p);
		} else {
			int blockToConsume = Math.floorDiv(p.currentBlock,2);
			blockConsumed += blockToConsume;
			for (int i = 0; i < 10; i++) {
				float dx = (float) (Math.random()*30 - 15);
				float dy = (float) (Math.random()*30 - 15);
				AbstractDungeon.effectsQueue.add(new GenericSmokeEffect(p.hb.cX + dx, p.hb.cY + dy));
			}
			AbstractDungeon.actionManager.addToTop(new ReduceBlockAction(p, p, blockToConsume));
			AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
			if (blockConsumed >= magicNumber) {
				AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
			}
		}
	}

	@Override
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
	}

	@Override
	public void triggerOnEndOfTurnForPlayingCard() {
		dontTriggerOnUseCard = true;
		AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem((AbstractCard)this, true));
	}


	public AbstractCard makeCopy() {
		return new RustWallCurse();
	}


	public void upgrade() {

	}
}
