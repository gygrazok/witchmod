package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.actions.DrawFromDiscardPileAction;

public class MysticUnburial extends AbstractWitchCard {
    public static final String ID = "MysticUnburial";
    public static final String NAME = "Mystic Unburial";
    public static final String IMG = "cards/mysticunburial.png";
    public static final String DESCRIPTION = "Draw a card from your discard pile. NL Exhaust.";
    public static final String DESCRIPTION_UPGRADED = "Draw a card from you discard pile. That card costs 0 for this turn. NL Exhaust.";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;
    private static final int COST = 0;


    public MysticUnburial() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawFromDiscardPileAction(1, false, upgraded));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.discardPile.isEmpty()) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new MysticUnburial();
    }


    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }
}
