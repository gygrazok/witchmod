package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.actions.ForesightAction;

public class Foresight extends AbstractWitchCard {
    public static final String ID = "Foresight";
    public static final String NAME = "Foresight";
    public static final String IMG = "cards/foresight.png";
    public static final String DESCRIPTION = "Draw a card. If it's a Curse or a Status, exhaust it. Otherwise draw a copy of it. Exhaust.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;
    private static final int COST = 1;
    private static final int COST_UPGRADED = 0;


    public Foresight() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ForesightAction());
    }

    public AbstractCard makeCopy() {
        return new Foresight();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADED);
            initializeDescription();
        }
    }
}
