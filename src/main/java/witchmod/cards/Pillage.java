package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.ReduceBlockAction;

public class Pillage extends AbstractWitchCard {
	public static final String ID = "Pillage";
	public static final	String NAME = "Pillage";
	public static final	String IMG = "cards/pillage.png";
	public static final	String DESCRIPTION = "Remove up to !M! Block from an enemy. Gain Block equal to the amount removed plus !B!.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 8;
	private static final int UPGRADE_BONUS = 5;
	private static final int BLOCK = 5;
	private static final int UPGRADE_BLOCK_BONUS = 3;


	public Pillage() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.baseBlock = BLOCK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int stealableBlock = Math.min(m.currentBlock,magicNumber);
		if (stealableBlock > 0) {
			AbstractDungeon.actionManager.addToBottom(new ReduceBlockAction(m, p, stealableBlock));
		}
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, stealableBlock+block));
	}

	public AbstractCard makeCopy() {
		return new Pillage();
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
			upgradeBlock(UPGRADE_BLOCK_BONUS);
		}
	}
}
