package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.actions.RiteOfSummerAction;

public class RiteOfSummer extends AbstractWitchCard {
    public static final String ID = "RiteOfSummer";
    public static final String NAME = "Rite of Summer";
    public static final String IMG = "cards/riteofsummer.png";
    public static final String DESCRIPTION = "Discard any number of cards and deal !D! damage to a random enemy for each card discarded.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;
    private static final int COST = 2;

    private static final int POWER = 6;
    private static final int UPGRADE_BONUS = 3;


    public RiteOfSummer() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RiteOfSummerAction(new DamageInfo(p, baseDamage, damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        return new RiteOfSummer();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
