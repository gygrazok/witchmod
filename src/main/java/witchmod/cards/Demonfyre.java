package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.effects.IgniteEffect;
import witchmod.powers.DemonfyrePower;

public class Demonfyre extends AbstractWitchCard{
	public static final String ID = "Demonfyre";
	public static final	String NAME = "Demonfyre";
	public static final	String IMG = "cards/demonfyre.png";
	public static final	String DESCRIPTION = "Deal !D! damage. Increases the damage of the next Demonfyre by !M!. NL Recurrent.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 5;
	private static final int MAGIC = 1;
	private static final int UPGRADE_BONUS = 1;

	public Demonfyre() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		reshuffleOnUse = true;
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.FIRE));
		AbstractDungeon.effectsQueue.add(new IgniteEffect(m.hb.cX, m.hb.cY, Color.PURPLE, 35));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DemonfyrePower(p, magicNumber), magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Demonfyre();
	}

	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
		int bonus = AbstractDungeon.player.getPower(DemonfyrePower.POWER_ID) == null?0:AbstractDungeon.player.getPower(DemonfyrePower.POWER_ID).amount;
		return tmp + bonus;
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
