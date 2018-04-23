package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.KillMonsterAction;

public class LivingBomb extends AbstractWitchCard{
	public static final String ID = "LivingBomb";
	public static final	String NAME = "Livin Bomb";
	public static final	String IMG = "cards/livingbomb.png";
	public static final	String DESCRIPTION = "Kill an enemy if it has !M! HP or less. If successful, other enemies are dealt !D! damage.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 3;
	private static final int POWER = 15;
	private static final int UPGRADED_BONUS = 5;
	private static final int DAMAGE = 12;
	private static final int UPGRADED_BONUS_DAMAGE = 4;


	public LivingBomb() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.baseDamage = DAMAGE;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m.currentHealth > magicNumber) {
			//should never happen
			return;
		}
		AbstractDungeon.actionManager.addToTop(new KillMonsterAction(m));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25f));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, DamageType.THORNS, AttackEffect.FIRE));
	}

	public AbstractCard makeCopy() {
		return new LivingBomb();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if (m != null && m.currentHealth > magicNumber) {
			cantUseMessage = "The enemy must have #y"+magicNumber+" health or less.";
			return false;
		}
		return super.canUse(p, m);
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
			upgradeDamage(UPGRADED_BONUS_DAMAGE);
		}
	}
}
