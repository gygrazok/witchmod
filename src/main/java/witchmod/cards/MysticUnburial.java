package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.MysticUnburialAction;

public class MysticUnburial extends AbstractWitchCard {
	public static final String ID = "MysticUnburial";
	public static final	String NAME = "Mystic Unburial";
	public static final	String IMG = "cards/mysticunburial.png";
	public static final	String DESCRIPTION = "Choose and play a card from your discard pile, paying its play cost.";
	public static final	String DESCRIPTION_UPGRADED = "Choose and play a card from your discard pile, ignoring its play cost.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = 0;


	public MysticUnburial() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new MysticUnburialAction(AbstractDungeon.getRandomMonster(),upgraded));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		//if there is at least 1 card that can be played, you can use this card
		for (AbstractCard card : p.discardPile.group) {
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if (card.canUse(p, monster)) {
					return true;
				}
			}
		}
		cantUseMessage = "I don't have any playable card in my discard pile.";
		return false;
	}

	public AbstractCard makeCopy() {
		return new MysticUnburial();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
