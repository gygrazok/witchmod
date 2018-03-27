package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.RotPower;

public class Malady extends AbstractWitchCard{
	public static final String ID = "Malady";
	public static final	String NAME = "Malady";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Apply !M! Rot.";
	public static final	String DESCRIPTION_UPGRADED = "Apply !M! Contagious Rot (spreads on a random monster on death).";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 1;

	public Malady() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new RotPower(m,p, magicNumber, true), magicNumber));
		} else {
	       	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new RotPower(m,p, magicNumber, false), magicNumber));
		}

	}
	

	public AbstractCard makeCopy() {
		return new Malady();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
