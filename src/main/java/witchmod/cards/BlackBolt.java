package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BlackBolt extends AbstractWitchCard{
	public static final String ID = "BlackBolt";
	public static final	String NAME = "Black Bolt";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. Apply !M! weak if you have at least a Curse in hand.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int UPGRADE_BONUS = 3;
	
	private static final int MAGIC = 1;
	private static final int MAGIC_UPGRADE_BONUS = 1;

	public BlackBolt() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        boolean hasCurse = false;
		for (AbstractCard c : p.hand.group) {
			if (c.type == CardType.CURSE) {
				hasCurse = true;
				break;
			}
		}
		if (hasCurse) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(p, magicNumber, false), magicNumber));
		}
	}

	public AbstractCard makeCopy() {
		return new BlackBolt();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
		}
	}
}
