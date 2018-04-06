package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.effects.FastShockWaveEffect;

public class DireShriek extends AbstractWitchCard{
	public static final String ID = "DireShriek";
	public static final	String NAME = "Dire Shriek";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "When drawn deal !D! damage to all enemies. NL Deal !D! damage to all enemies.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 2;
	private static final int POWER = 7;
	private static final int UPGRADE_BONUS = 2;


	public DireShriek() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new FastShockWaveEffect(p.hb.cX, p.hb.cY, Color.YELLOW, FastShockWaveEffect.ShockWaveType.NORMAL), 0.35f));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));

	}

	public AbstractCard makeCopy() {
		return new DireShriek();
	}

	@Override
	public void triggerWhenDrawn() {
		super.triggerWhenDrawn();
		flash();
		applyPowers();
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
