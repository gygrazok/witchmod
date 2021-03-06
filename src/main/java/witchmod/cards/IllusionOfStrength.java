package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class IllusionOfStrength extends AbstractWitchCard {
    public static final String ID = "IllusionOfStrength";
    public static final String NAME = "Illusory Strength";
    public static final String IMG = "cards/illusionofstrength.png";
    public static final String DESCRIPTION = "Gain !M! Strength. Shuffle a Delusion of Strength in your draw pile.";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;

    private static final int POWER = 4;
    private static final int UPGRADE_BONUS = 2;


    public IllusionOfStrength() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = POWER;
        cardPreviewTooltip = new IllusionOfStrengthCurse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new IllusionOfStrengthCurse(), 1, true, false));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new IllusionOfStrength();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
