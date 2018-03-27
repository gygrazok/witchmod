package witchmod.cards.familiar;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.ReduceRandomDebuffAction;
import witchmod.cards.AbstractWitchCard;

public class ToadFamiliar extends AbstractWitchCard{
	public static final String ID = "ToadFamiliar";
	public static final	String NAME = "Toad";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain !B! block. Remove up to !M! stacks of Vulnerable, Weak or Frail (randomly chosen). NL Exhaust. NL Ethereal.";
	
	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 4;
	private static final int UPGRADE_BONUS = 3;
	private static final int MAGIC = 1;
	private static final int UPGRADE_MAGIC_BONUS = 1;

	public ToadFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.block = POWER;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		AbstractDungeon.actionManager.addToBottom(new ReduceRandomDebuffAction(p, p, magicNumber));
	}

	public AbstractCard makeCopy() {
		return new ToadFamiliar();
	}
	
    @Override
    public void triggerOnEndOfPlayerTurn() {
        AbstractDungeon.actionManager.addToTop(new ExhaustAllEtherealAction());
    }


	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
			upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
		}
	}
}
