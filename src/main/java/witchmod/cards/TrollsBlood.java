package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.powers.TrollsBloodPower;

public class TrollsBlood extends AbstractWitchCard {
    public static final String ID = "TrollsBlood";
    public static final String NAME = "Troll's Blood";
    public static final String IMG = "cards/trollsblood.png";
    public static final String DESCRIPTION = "Recover !M! health at the end of each turn in which you played at least an attack.";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final int POOL = 1;

    private static final int POWER = 1;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;


    public TrollsBlood() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = POWER;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TrollsBloodPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() {
        return new TrollsBlood();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
