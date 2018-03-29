package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MementoMori extends AbstractWitchCard{
	public static final String ID = "MementoMori";
	public static final	String NAME = "Memento Mori";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. Damage is increased by the percentage of target's health missing. NL Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 12;
	private static final int UPGRADE_BONUS = 6;

	public MementoMori() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HEAVY));
	}

	public AbstractCard makeCopy() {
		return new MementoMori();
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		super.calculateCardDamage(mo);
		float percent = 0;
		if (mo.maxHealth != 0) {
			percent = 1 - mo.currentHealth/mo.maxHealth;
		}
		damage *= (1+percent);
		
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
