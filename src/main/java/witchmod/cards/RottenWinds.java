package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;

import witchmod.powers.RotPower;

public class RottenWinds extends AbstractWitchCard{
	public static final String ID = "RottenWinds";
	public static final	String NAME = "Rotten Winds";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Apply !M! Rot and deal !D! damage to ALL enemies 2 times.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 3;
	private static final int UPGRADE_BONUS = 2;
	private static final int MAGIC = 1;

	public RottenWinds() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new DaggerSprayEffect(), 0.0f));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new RotPower(mo,p, magicNumber, false),magicNumber, true));
		}
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new RotPower(mo,p, magicNumber, false),magicNumber, true));
		}
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
	}

	public AbstractCard makeCopy() {
		return new RottenWinds();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
