package witchmod;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import witchmod.cards.*;
import witchmod.characters.WitchCharacter;
import witchmod.patches.AbstractCardEnum;
import witchmod.patches.WitchEnum;
import witchmod.powers.AbstractWitchPower;
import witchmod.relics.*;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class WitchMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, PostDrawSubscriber, OnStartBattleSubscriber, PreMonsterTurnSubscriber {

    public static final Logger logger = LogManager.getLogger(WitchMod.class);

    public static final String modID = "witchmod";
    private static final String MODNAME = "WitchMod";
    private static final String AUTHOR = "Gygrazok";
    private static final String DESCRIPTION = "Adds The Witch character";

    private static final Color WITCH_COLOR = CardHelper.getColor(90.0f, 90.0f, 100.0f);
    private static final String ASSETS_FOLDER = "witchmod_images";

    private static final String ATTACK_CARD = "512/bg_attack_witch.png";
    private static final String SKILL_CARD = "512/bg_skill_witch.png";
    private static final String POWER_CARD = "512/bg_power_witch.png";

    private static final String ENERGY_ORB = "512/card_witch_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_witch.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_witch.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_witch.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_witch_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
    public static final String CHAR_SHOULDER_1 = "char/shoulder.png";
    public static final String CHAR_SHOULDER_2 = "char/shoulder2.png";
    public static final String CHAR_CORPSE = "char/corpse.png";
    public static final String CHAR_SKELETON_ATLAS = "char/skeleton.atlas";
    public static final String CHAR_SKELETON_JSON = "char/skeleton.json";

    public static final String BADGE_IMG = "badge.png";

    public static int cardsDrawnTotal = 0;
    public static int cardsDrawnThisTurn = 0;
    public static int cursesDrawnTotal = 0;

    public static final String getResourcePath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    public WitchMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.WITCH,
                WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
                getResourcePath(ENERGY_ORB),
                getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
                getResourcePath(ENERGY_ORB_PORTRAIT));
    }

    public static void initialize() {
        logger.info("Initializing Witch Mod");
        new WitchMod();
    }

    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("No settings", 400.0f, 700.0f, settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }


    public void receiveEditCharacters() {
        BaseMod.addCharacter(new WitchCharacter("The Witch"),
                getResourcePath(CHAR_BUTTON),
                getResourcePath(CHAR_PORTRAIT),
                WitchEnum.WITCH);
    }


    public void receiveEditRelics() {
        RelicLibrary.add(new BlackCat());
        BaseMod.addRelicToCustomPool(new BirdCage(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new WalkingCane(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new Scissors(), AbstractCardEnum.WITCH);
        BaseMod.addRelicToCustomPool(new ToyHorse(), AbstractCardEnum.WITCH);
    }

    public void receiveEditCards() {
        //BASIC
        BaseMod.addCard(new Strike_Witch());
        BaseMod.addCard(new Defend_Witch());
        BaseMod.addCard(new ZombieSpit());
        BaseMod.addCard(new Hexguard());
        //COMMON (21)
        //Attacks (11)
        BaseMod.addCard(new Demonfyre());
        BaseMod.addCard(new BleedOut());
        BaseMod.addCard(new Broomstick());
        BaseMod.addCard(new BlackBolt());
        BaseMod.addCard(new MercuryWand());
        BaseMod.addCard(new WretchedNails());
        BaseMod.addCard(new BoneCarving());
        BaseMod.addCard(new MagicFang());
        BaseMod.addCard(new MementoMori());
        BaseMod.addCard(new SkullFlask());
        BaseMod.addCard(new Thundercloud());
        //Skills (10)
        BaseMod.addCard(new BlackShield());
        BaseMod.addCard(new SaltCircle());
        BaseMod.addCard(new KarmaDrain());
        BaseMod.addCard(new WickedThoughts());
        BaseMod.addCard(new SoulBarrier());
        BaseMod.addCard(new Bewitch());
        BaseMod.addCard(new GnarledBody());
        BaseMod.addCard(new RoilingBarrier());
        BaseMod.addCard(new Decrepify());
        BaseMod.addCard(new Atonement());


        //UNCOMMON (28)
        //Attacks (10)
        BaseMod.addCard(new Athame());
        BaseMod.addCard(new PainBolt());
        BaseMod.addCard(new CursedBlade());
        BaseMod.addCard(new GhoulTouch());
        BaseMod.addCard(new MortusClaw());
        BaseMod.addCard(new LivingBomb());
        BaseMod.addCard(new RiteOfSummer());
        BaseMod.addCard(new Puncture());
        BaseMod.addCard(new Harmlessness());
        BaseMod.addCard(new Malady());
        //Skills (11)
        BaseMod.addCard(new Foresight());
        BaseMod.addCard(new Shrooms());
        BaseMod.addCard(new NighInvulnerability());
        BaseMod.addCard(new Pillage());
        BaseMod.addCard(new RiteOfAutumn());
        BaseMod.addCard(new CrystalResonance());
        BaseMod.addCard(new IllOmen());
        BaseMod.addCard(new RiteOfWinter());
        BaseMod.addCard(new BalefulWard());
        BaseMod.addCard(new CorruptBlood());
        BaseMod.addCard(new PlagueSpreader());
        //Powers (7)
        BaseMod.addCard(new TwistedMind());
        BaseMod.addCard(new Schadenfreude());
        BaseMod.addCard(new SummonOwlFamiliar());
        BaseMod.addCard(new SummonRatFamiliar());
        BaseMod.addCard(new SummonCatFamiliar());
        BaseMod.addCard(new SummonBatFamiliar());
        BaseMod.addCard(new GrimVengeance());


        //RARE (25)
        //Attacks (8)
        BaseMod.addCard(new ImpendingDoom());
        BaseMod.addCard(new Graveburst());
        BaseMod.addCard(new EternalThirst());
        BaseMod.addCard(new DireShriek());
        BaseMod.addCard(new FatalRay());
        BaseMod.addCard(new BloodSabbath());
        BaseMod.addCard(new Boline());
        BaseMod.addCard(new EvilEye());
        //Skills (9)
        BaseMod.addCard(new MysticUnburial());
        BaseMod.addCard(new UnnaturalEnergy());
        BaseMod.addCard(new UnluckySeven());
        BaseMod.addCard(new StrangeBrew());
        BaseMod.addCard(new RiteOfSpring());
        BaseMod.addCard(new Twitch());
        BaseMod.addCard(new RustWall());
        BaseMod.addCard(new WalpurgisNight());
        BaseMod.addCard(new VileEgg());
        //Powers (8)
        BaseMod.addCard(new Intelligence());
        BaseMod.addCard(new SummonToadFamiliar());
        BaseMod.addCard(new SummonRavenFamiliar());
        BaseMod.addCard(new TrollsBlood());
        BaseMod.addCard(new DarkProcession());
        BaseMod.addCard(new IllusionOfStrength());
        BaseMod.addCard(new ChosenOfTheMoon());
        BaseMod.addCard(new DeliriumForm());
    }


    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("witchmod_strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("localization/eng/WitchMod-Card-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/eng/WitchMod-Power-Strings.json");
    }


    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("localization/eng/WitchMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }

    }

    public void receivePostDraw(AbstractCard c) {
        AbstractPlayer player = AbstractDungeon.player;
        //custom callback for card draw on powers
        for (AbstractPower p : player.powers) {
            if (p instanceof AbstractWitchPower) {
                p.onCardDraw(c);
            }
        }
        cardsDrawnThisTurn++;
        cardsDrawnTotal++;
        if (c.type == CardType.CURSE) {
            cursesDrawnTotal++;
        }
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster m) {
        cardsDrawnThisTurn = 0;
        return true;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        cardsDrawnTotal = 0;
        cursesDrawnTotal = 0;
        cardsDrawnThisTurn = 0;
    }


}
