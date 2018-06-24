package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.DecrepitPower;

public class ZombieSpit extends AbstractWitchCard {
	public static final String ID = "ZombieSpit";
	public static final	String NAME = "Zombie Spit";
	public static final	String IMG = "cards/zombiespit.png";
	public static final	String DESCRIPTION = "Deal !D! damage and apply !M! Decrepit.";

	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;
	private static final int COST = 0;

	private static final int POWER = 4;
	private static final int UPGRADED_BONUS = 1;

	private static final int MAGIC = 1;
	private static final int UPGRADED_MAGIC = 1;

	public ZombieSpit() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,damage,damageTypeForTurn), AttackEffect.POISON));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DecrepitPower(m, magicNumber, false), magicNumber, true));
	}

	public AbstractCard makeCopy() {
		return new ZombieSpit();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADED_BONUS);
			upgradeMagicNumber(UPGRADED_MAGIC);
		}
	}
}
