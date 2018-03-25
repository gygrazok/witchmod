package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

public class BroomstickSmash extends AbstractWitchCard{
	public static final String ID = "BroomstickSmash";
	public static final	String NAME = "Broomstick Smash";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. If the target is Frail the damage is increased by 25%.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int UPGRADE_BONUS = 3;

	public BroomstickSmash() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage),AttackEffect.BLUNT_HEAVY));      
	}
	
	public AbstractCard makeCopy() {
		return new BroomstickSmash();
	}
	
	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
		if (mo == null) {
			return tmp;
		}
		float bonus = mo.getPower(FrailPower.POWER_ID) == null?1f:1.25f;
		return tmp*bonus;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
