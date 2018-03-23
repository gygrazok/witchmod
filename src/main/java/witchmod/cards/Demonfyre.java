package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.DemonfyrePower;

public class Demonfyre extends AbstractWitchCard{
	public static final String ID = "Demonfyre";
	public static final	String NAME = "Demonfyre";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. Increases the damage of the next Demonfyre by !M!";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 5;
	private static final int MAGIC = 1;
	private static final int UPGRADE_BONUS = 1;

	public Demonfyre() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		//the damage already includes the bonus from the demonfyre power
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DemonfyrePower(p, this.magicNumber), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Demonfyre();
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		int bonus = AbstractDungeon.player.getPower(DemonfyrePower.POWER_ID) == null?0:AbstractDungeon.player.getPower(DemonfyrePower.POWER_ID).amount;
		//ugly hack to include the bonus in the calculations
		this.baseDamage += bonus;
		super.calculateCardDamage(mo);
		this.baseDamage -= bonus;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
