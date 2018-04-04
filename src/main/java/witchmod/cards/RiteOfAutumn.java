package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RiteOfAutumnAction;

public class RiteOfAutumn extends AbstractWitchCard {
	public static final String ID = "RiteOfAutumn";
	public static final	String NAME = "Rite of Autumn";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Exhaust any number of cards in your hand and draw that many cards. NL Recurrent.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;


	
	public RiteOfAutumn() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		reshuffleOnUse = true;
		AbstractDungeon.actionManager.addToBottom(new RiteOfAutumnAction());
	}
	
	public AbstractCard makeCopy() {
		return new RiteOfAutumn();
	}
	
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
