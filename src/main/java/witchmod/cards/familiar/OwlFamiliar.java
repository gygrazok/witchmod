package witchmod.cards.familiar;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.cards.AbstractWitchCard;

public class OwlFamiliar extends AbstractWitchCard{
	public static final String ID = "OwlFamiliar";
	public static final	String NAME = "Owl Familiar";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Draw !M! cards. NL Exhaust. NL Ethereal.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int UPGRADE_BONUS = 1;

	public OwlFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));

	}

	public AbstractCard makeCopy() {
		return new OwlFamiliar();
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
