package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class BloodSabbath extends AbstractWitchCard{
	public static final String ID = "BloodSabbath";
	public static final	String NAME = "Blood Sabbath";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage and apply !M! Vulnerable to ALL enemies.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 3;
	private static final int POWER = 16;
	private static final int UPGRADE_BONUS = 4;
	private static final int MAGIC = 3;
	private static final int UPGRADE_MAGIC_BONUS = 2;

	public BloodSabbath() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
	}

	public AbstractCard makeCopy() {
		return new BloodSabbath();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
			upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
		}
	}
}
