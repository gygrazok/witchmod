package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;

public class StrangeBrew extends AbstractWitchCard {
    public static final String ID = "StrangeBrew";
    public static final String NAME = "Strange Brew";
    public static final String IMG = "cards/strangebrew.png";
    public static final String DESCRIPTION = "Apply on yourself the effect of all your drinkable potions, without consuming them. Exhaust";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;
    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;


    public StrangeBrew() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPotion potion : p.potions) {
            if (potion.isThrown == false && !(isRestricted(potion))) {
                potion.use(p);
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int usable = 0;
        for (AbstractPotion potion : p.potions) {
            if (potion.isThrown == false && !(isRestricted(potion))) {
                usable++;
            }
        }
        if (usable == 0) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    protected boolean isRestricted(AbstractPotion potion) {
        return potion.ID.equals(SmokeBomb.POTION_ID)
                || potion.ID.equals(FairyPotion.POTION_ID);
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
