package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.AthameAction;

public class Athame extends AbstractWitchCard{
	public static final String ID = "Athame";
	public static final	String NAME = "Athame";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. NL If this kills a non-minion enemy, double the chance of receiving rare cards as monster rewards. Exhaust.";
	public static final	String DESCRIPTION_UPGRADED = "Deal !D! damage. NL If this kills a non-minion enemy, triple the chance of receiving rare cards as monster rewards. Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int POWER_UPGRADED_BONUS = 4;
	private static final int MAGIC_NUMBER = 2;
	private static final int MAGIC_NUMBER_UPGRADED_BONUS = 1;

	public Athame() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new AthameAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Athame();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(POWER_UPGRADED_BONUS);
			upgradeMagicNumber(MAGIC_NUMBER_UPGRADED_BONUS);
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
