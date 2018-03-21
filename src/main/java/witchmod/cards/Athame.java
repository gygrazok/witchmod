package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Athame extends AbstractWitchCard{
	public static final String ID = "Athame";
	public static final	String NAME = "Athame";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. NL If this kills a non-minion enemy, double the chance of receiving rare cards as monster rewards. Exhaust.";
	public static final	String DESCRIPTION_UPGRADED = "Deal !D! damage. NL If this kills a non-minion enemy, triple the chance of receiving rare cards as monster rewards. Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int UPGRADE_BONUS = 4;

	public Athame() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
	}

	public AbstractCard makeCopy() {
		return new Athame();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
