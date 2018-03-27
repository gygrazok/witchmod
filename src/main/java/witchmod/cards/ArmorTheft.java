package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.ReduceBlockAction;

public class ArmorTheft extends AbstractWitchCard {
	public static final String ID = "ArmorTheft";
	public static final	String NAME = "Armor Theft";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Remove up to !M! block and gain the same amount.";
	public static final	String DESCRIPTION_UPGRADED = "Remove up to !M! block and gain the same amount. NL Persistent.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 12;


	
	public ArmorTheft() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int stealableBlock = Math.min(m.currentBlock,magicNumber);
		if (stealableBlock > 0) {
			AbstractDungeon.actionManager.addToBottom(new ReduceBlockAction(m, p, stealableBlock));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, stealableBlock));
		}
	}
	
	public AbstractCard makeCopy() {
		return new ArmorTheft();
	}
	
	@Override
	public void atTurnStart(){
		if (upgraded) {
			this.retain = true;
		}
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.retain = true;
		}
	}
}
