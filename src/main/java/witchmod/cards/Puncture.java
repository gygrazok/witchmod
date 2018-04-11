package witchmod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.effects.PunctureEffect;

public class Puncture extends AbstractWitchCard{
	public static final String ID = "Puncture";
	public static final	String NAME = "Puncture";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage !M! times. NL Exhaust.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 1;
	private static final int MAGIC = 6;
	private static final int UPGRADE_MAGIC_BONUS = 2;

	public Puncture() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new PunctureEffect(m.hb.cX + MathUtils.random(-50.0f, 50.0f) * Settings.scale, m.hb.cY + MathUtils.random(-60.0f, 60.0f) * Settings.scale), 0.0075f));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AttackEffect.NONE));
		}
	}

	public AbstractCard makeCopy() {
		return new Puncture();
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
		}
	}
}
