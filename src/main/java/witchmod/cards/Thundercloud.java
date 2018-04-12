package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class Thundercloud extends AbstractWitchCard {
	public static final String ID = "Thundercloud";
	public static final	String NAME = "Thundercloud";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "When drawn apply !M! Weak to a random enemy. NL Deal !D! damage to all Weak enemies.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 2;
	private static final int POWER = 10;
	private static final int UPGRADED_BONUS = 3;

	private static final int MAGIC = 1;

	public Thundercloud() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!monster.isDeadOrEscaped() && monster.hasPower(WeakPower.POWER_ID)) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(monster.drawX, monster.drawY), 0.05f));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, damage, damageTypeForTurn),AttackEffect.BLUNT_LIGHT,true));
			}
		}
	}

	public AbstractCard makeCopy() {
		return new Thundercloud();
	}

	@Override
	public void triggerWhenDrawn() {
		AbstractMonster monster = AbstractDungeon.getRandomMonster();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new WeakPower(monster, magicNumber, false), magicNumber));
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADED_BONUS);
		}
	}
}
