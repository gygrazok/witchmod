package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.effects.SkullFlaskEffect;
import witchmod.powers.SkullFlaskPower;

public class SkullFlask extends AbstractWitchCard{
	public static final String ID = "SkullFlask";
	public static final	String NAME = "Skull Flask";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "When drawn gain !M! Strength for 1 turn. NL Deal !D! damage.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 5;

	private static final int MAGIC = 2;
	private static final int MAGIC_UPGRADE_BONUS = 2;

	public SkullFlask() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new SkullFlaskEffect(p.hb.cY, p.hb.cX, m.hb.cX, m.hb.cY), 0.6f));
		AbstractDungeon.actionManager.addToBottom(new SFXAction("BLOOD_SPLAT"));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p,damage,damageTypeForTurn),AttackEffect.BLUNT_LIGHT));
	}

	public AbstractCard makeCopy() {
		return new SkullFlask();
	}

	@Override
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SkullFlaskPower(AbstractDungeon.player, magicNumber), magicNumber));
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
		}
	}
}
