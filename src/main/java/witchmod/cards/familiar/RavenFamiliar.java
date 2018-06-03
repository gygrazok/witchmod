package witchmod.cards.familiar;

import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RavenAction;
import witchmod.cards.AbstractWitchCard;

public class RavenFamiliar extends AbstractWitchCard{
	public static final String ID = "RavenFamiliar";
	public static final	String NAME = "Raven";
	public static final	String IMG = "cards/raven.png";
	public static final	String DESCRIPTION = "Ethereal. NL Upgrade a card in your hand and reduce its cost by 1 for the rest of the combat. NL Exhaust.";

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 0;

	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public RavenFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new RavenAction());
	}

	public AbstractCard makeCopy() {
		return new RavenFamiliar();
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		AbstractDungeon.actionManager.addToTop(new ExhaustAllEtherealAction());
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
