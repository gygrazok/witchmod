package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.ReduceBlockAction;

public class Raze extends AbstractWitchCard {
	public static final String ID = "Raze";
	public static final	String NAME = "Raze";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Steal up to !M! block from an enemy. NL Persistent.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 10;
	private static final int UPGRADE_BONUS = 5;


	
	public Raze() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.retain = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int stealableBlock = Math.min(m.currentBlock,magicNumber);
		if (stealableBlock > 0) {
			AbstractDungeon.actionManager.addToBottom(new ReduceBlockAction(m, p, stealableBlock));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, stealableBlock));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Raze();
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if (m != null && m.currentBlock == 0) {
			cantUseMessage = "That enemy doesn't have any Block to steal.";
			return false;
		}
		return super.canUse(p, m);
	}
	
	@Override
	public void atTurnStart(){
		retain = true;
	}
	
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
