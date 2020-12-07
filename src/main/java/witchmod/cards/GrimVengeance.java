package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.powers.GrimVengeancePower;

public class GrimVengeance extends AbstractWitchCard {
    public static final String ID = "GrimVengeance";
    public static final String NAME = "Grim Vengeance";
    public static final String IMG = "cards/grimvengeance.png";
    public static final String DESCRIPTION = "When you are attacked apply !M! Decrepit to the attacker.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 1;

    private static final int POWER = 1;
    private static final int UPGRADE_BONUS = 1;


    public GrimVengeance() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GrimVengeancePower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new GrimVengeance();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
