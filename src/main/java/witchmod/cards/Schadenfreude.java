package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.powers.SchadenfreudePower;


public class Schadenfreude extends AbstractWitchCard {
    public static final String ID = "Schadenfreude";
    public static final String NAME = "Schadenfreude";
    public static final String IMG = "cards/schadenfreude.png";
    public static final String DESCRIPTION = "Whenever you apply a Debuff to an enemy gain !M! block.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 2;
    private static final int UPGRADED_BONUS = 1;

    public Schadenfreude() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SchadenfreudePower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Schadenfreude();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_BONUS);
        }
    }
}