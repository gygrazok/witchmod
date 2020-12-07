package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UnluckySeven extends AbstractWitchCleansableCurse {
    public static final String ID = "UnluckySeven";
    public static final String NAME = "Unlucky Seven";
    public static final String NAME_CLEANSED = "Lucky Seven";
    public static final String IMG = "cards/luckyseven.png";
    public static final String DESCRIPTION = "Unplayable. NL Cleanse: have your current health ending with 7.";
    public static final String DESCRIPTION_CLEANSED = "Gain !M! [B]. NL Draw !M! cards. NL Lose !M! health. NL Exhaust.";
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 0;
    private static final int POWER = 7;


    public UnluckySeven(boolean hasCardPreview) {
        super(ID, IMG, RARITY);
        this.baseMagicNumber = this.magicNumber = POWER;
        this.exhaust = true;
        if (hasCardPreview) {
            UnluckySeven tmp = new UnluckySeven(false);
            tmp.cleanse(false);
            cardPreviewTooltip = tmp;
        }
    }

    public UnluckySeven() {
        this(true);
    }

    @Override
    public void cleanse(boolean applyPowers) {
        type = TYPE;
        cost = COST;
        costForTurn = COST;
        isCostModified = false;
        target = TARGET;
        name = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
        super.cleanse(applyPowers);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!dontTriggerOnUseCard && p.hasRelic("Blue Candle") && !cleansed) {
            useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, magicNumber, DamageType.HP_LOSS)));
        }
    }

    @Override
    protected boolean cleanseCheck() {
        int lastDigit = AbstractDungeon.player.currentHealth % 10;
        return lastDigit == 7;
    }


    public AbstractCard makeCopy() {
        return new UnluckySeven();
    }

    public void upgrade() {

    }
}
