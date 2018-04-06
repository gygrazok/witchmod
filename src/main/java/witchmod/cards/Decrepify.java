package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import witchmod.powers.DecrepitPower;

public class Decrepify extends AbstractWitchCard {
	public static final String ID = "Decrepify";
	public static final	String NAME = "Decrepify";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Apply 1 Decrepit to a random enemy X + !M! times.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = -1;

	private static final int POWER = 2;
	private static final int UPGRADED_BONUS = 2;


	public Decrepify() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int energy = EnergyPanel.getCurrentEnergy();
		int counter = magicNumber + energy;
		while (counter > 0) {
			AbstractMonster monster = AbstractDungeon.getRandomMonster();
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new DecrepitPower(monster, 1, false),1, true));
			counter--;
		}
		p.energy.use(energy);
	}

	public AbstractCard makeCopy() {
		return new Decrepify();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
		}
	}
}
