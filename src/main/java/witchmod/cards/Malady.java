package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.RotPower;

public class Malady extends AbstractWitchCard{
	public static final String ID = "Malady";
	public static final	String NAME = "Malady";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Deal !D! damage and apply !M! Rot. NL Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int DAMAGE = 9;
	private static final int DAMAGE_BONUS = 3;
	private static final int MAGIC = 1;
	private static final int MAGIC_BONUS = 1;

	public Malady() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new RotPower(m,p, magicNumber), magicNumber));
	}
	

	public AbstractCard makeCopy() {
		return new Malady();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(DAMAGE_BONUS);
			upgradeMagicNumber(MAGIC_BONUS);
		}
	}
}
