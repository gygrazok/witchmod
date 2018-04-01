package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import witchmod.powers.DecrepitPower;

public class Envy extends AbstractWitchCleansableCurse {
	public static final String ID = "Envy";
	public static final	String NAME = "Envy";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: an enemy should have at least !M! of either Vulnerable, Weak or Decrepit.";
	public static final	String DESCRIPTION_CLEANSED = "Each enemy loses health equal to twice the amount of Vulnerable, Weak or Decrepit it has.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int COST = 0;

	private static final int THRESHOLD = 3;
	public Envy() {
		super(ID,NAME,IMG,DESCRIPTION,RARITY);
		this.exhaust = true;
		this.baseMagicNumber = this.magicNumber = THRESHOLD;
	}

	@Override
	public void cleanse() {
		super.cleanse();
		type = TYPE;
		cost = COST;
		costForTurn = COST;
		isCostModified = false;
		target = TARGET;
		rawDescription = DESCRIPTION_CLEANSED;
		initializeDescription();
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			this.useBlueCandle(p);
		} else {
			for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
				int effect = getStacks(mo, WeakPower.POWER_ID) + getStacks(mo, DecrepitPower.POWER_ID) + getStacks(mo, VulnerablePower.POWER_ID);
				AbstractDungeon.actionManager.addToTop(new DamageAction(mo, new DamageInfo(p, effect*2, DamageType.HP_LOSS), true));
			}
		}
	}

	@Override
	protected boolean cleanseCheck() {
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			if (m.isDeadOrEscaped()) {
				continue;
			}
			if (envyCheck(m, WeakPower.POWER_ID) || 
					envyCheck(m, VulnerablePower.POWER_ID) || 
					envyCheck(m, DecrepitPower.POWER_ID)) {
				return true;
			}
		}
		return false;
	}


	private boolean envyCheck(AbstractMonster m, String power) {
		return m.hasPower(power) && m.getPower(power).amount >= THRESHOLD;
	}

	private int getStacks(AbstractMonster m, String power) {
		if (m.hasPower(power)) {
			return m.getPower(power).amount;
		} else {
			return 0;
		}
	}


	public AbstractCard makeCopy() {
		return new Envy();
	}

	public void upgrade() {

	}
}
