package witchmod.cards.familiar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import witchmod.cards.AbstractWitchCard;

public class BatFamiliar extends AbstractWitchCard{
	public static final String ID = "BatFamiliar";
	public static final	String NAME = "Bat Familiar";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Apply 1 weak. Reduce Strength by !M! for 1 turn. NL Exhaust. NL Ethereal.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int UPGRADE_BONUS = 1;

	public BatFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1,false), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber), -magicNumber));
        if (!m.hasPower("Artifact")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GainStrengthPower(m, -magicNumber), -magicNumber));
        }
	}

	public AbstractCard makeCopy() {
		return new BatFamiliar();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
