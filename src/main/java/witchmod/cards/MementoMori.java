package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.effects.ColoredSliceEffect;

public class MementoMori extends AbstractWitchCard{
	public static final String ID = "MementoMori";
	public static final	String NAME = "Memento Mori";
	public static final	String IMG = "cards/mementomori.png";
	public static final	String DESCRIPTION = "Deal !D! damage, increased by the percentage of target's missing health. NL Exhaust.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 11;
	private static final int UPGRADE_BONUS = 5;

	public MementoMori() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		float dy = 100;
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ColoredSliceEffect(m.hb.cX - 20, m.hb.cY - dy, m.hb.cX - 20, m.hb.cY + dy, Color.RED.cpy()), 0.25f));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ColoredSliceEffect(m.hb.cX, m.hb.cY - dy, m.hb.cX, m.hb.cY + dy, Color.RED.cpy()), 0.25f));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ColoredSliceEffect(m.hb.cX + 20, m.hb.cY - dy, m.hb.cX + 20, m.hb.cY + dy, Color.RED.cpy()), 0.25f));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AttackEffect.POISON));
	}

	public AbstractCard makeCopy() {
		return new MementoMori();
	}


	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
		if (mo == null || mo.maxHealth == 0) {
			return tmp;
		}
		float percent = (float)mo.currentHealth/mo.maxHealth;
		return tmp*(2f - percent);
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
