package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.actions.WalpurgisNightAction;

public class WalpurgisNight extends AbstractWitchCard {
    public static final String ID = "WalpurgisNight";
    public static final String NAME = "Walpurgis Night";
    public static final String IMG = "cards/walpurgisnight.png";
    public static final String DESCRIPTION = "Shuffle ALL Exhausted non-Etheral Attacks and Skills into your draw pile, then draw !M! of those cards. Exhaust";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int POWER = 1;
    private static final int UPGRADE_BONUS = 2;

    public WalpurgisNight() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = POWER;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new WalpurgisNightAction(magicNumber));
        int cardsToDraw = 0;
        for (AbstractCard c : p.exhaustPile.group) {
            if ((c.type == CardType.ATTACK || c.type == CardType.SKILL) && c.isEthereal == false && !c.cardID.equals(this.cardID)) {
                cardsToDraw++;
                if (cardsToDraw >= magicNumber) {
                    cardsToDraw = magicNumber;
                    break;
                }
            }
        }
        if (cardsToDraw > 0) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, cardsToDraw));
        }
    }

    public AbstractCard makeCopy() {
        return new WalpurgisNight();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.exhaustPile.group) {
            if ((c.type == CardType.ATTACK || c.type == CardType.SKILL) && c.isEthereal == false && !c.cardID.equals(this.cardID)) {
                return super.canUse(p, m);
            }
        }
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }


    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
