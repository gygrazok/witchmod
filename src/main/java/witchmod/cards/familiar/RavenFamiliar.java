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
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Upgrade a card in your hand and reduce its cost by 1 for the rest of the turn. NL Exhaust. NL Ethereal.";
	public static final	String DESCRIPTION_UPGRADED = "Upgrade a card in your hand and reduce its cost by 1 for the rest of the combat. NL Exhaust. NL Ethereal.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int UPGRADE_BONUS = 1;

	public RavenFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RavenAction(false));

	}

	public AbstractCard makeCopy() {
		return new RavenFamiliar();
	}
	
    @Override
    public void triggerOnEndOfPlayerTurn() {
        AbstractDungeon.actionManager.addToTop(new ExhaustAllEtherealAction());
    }


	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
