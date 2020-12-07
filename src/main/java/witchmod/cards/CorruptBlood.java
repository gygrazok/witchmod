package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.actions.CorruptBloodAction;

public class CorruptBlood extends AbstractWitchCard {
    public static final String ID = "CorruptBlood";
    public static final String NAME = "Corrupt Blood";
    public static final String IMG = "cards/corruptblood.png";
    public static final String DESCRIPTION = "If the enemy is Poisoned turn all Poison into Rot, otherwise apply !M! Poison. Recurrent.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 3;
    private static final int UPGRADE_BONUS = 2;

    public CorruptBlood() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        reshuffleOnUse = true;
        AbstractDungeon.actionManager.addToBottom(new CorruptBloodAction(m, p, magicNumber));
    }


    public AbstractCard makeCopy() {
        return new CorruptBlood();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
