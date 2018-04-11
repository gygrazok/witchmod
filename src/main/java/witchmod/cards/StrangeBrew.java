package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class StrangeBrew extends AbstractWitchCard {
	public static final String ID = "StrangeBrew";
	public static final	String NAME = "Strange Brew";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Apply on yourself the effect of all your drinkable potions, without consuming them. NL Exhaust";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = 3;
	private static final int UPGRADED_COST = 2;



	public StrangeBrew() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractPotion potion : p.potions) {
			if (potion.isThrown == false) {
				potion.use(p);
			}
		}
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		int usable = 0;
		for (AbstractPotion potion : p.potions) {
			if (potion.isThrown == false) {
				usable++;
			}
		}
		if (usable == 0) {
			cantUseMessage = "I don't have any drinkable potion!";
			return false;
		}
		return true;
	}

	public AbstractCard makeCopy() {
		return new StrangeBrew();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(UPGRADED_COST);
		}
	}
}
