package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.AutumnalRiteAction;

public class AutumnalRite extends AbstractWitchCard {
	public static final String ID = "AutumnalRite";
	public static final	String NAME = "Autumnal Rite";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Exhaust any number of cards in your hand and draw that many cards.";
	public static final	String DESCRIPTION_UPGRADED = "Exhaust any number of cards in your hand and draw that many cards. NL Recurrent.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 1;


	
	public AutumnalRite() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new AutumnalRiteAction());
	}
	
	public AbstractCard makeCopy() {
		return new AutumnalRite();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			reshuffleOnUse = true;
			this.rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
