package witchmod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import witchmod.cards.Athame;
import witchmod.cards.Bewitch;
import witchmod.cards.BleedOut;
import witchmod.cards.CursedBlade;
import witchmod.cards.Defend_Witch;
import witchmod.cards.Demonfyre;
import witchmod.cards.DoomBlade;
import witchmod.cards.FamiliarProtection;
import witchmod.cards.Foresight;
import witchmod.cards.Hexguard;
import witchmod.cards.Intelligence;
import witchmod.cards.PainBolt;
import witchmod.cards.RitualOfDecay;
import witchmod.cards.Strike_Witch;
import witchmod.cards.TrollsBlood;
import witchmod.cards.UnluckySeven;
import witchmod.cards.UnnaturalEnergy;
import witchmod.characters.WitchCharacter;
import witchmod.patches.AbstractCardEnum;
import witchmod.patches.WitchEnum;
import witchmod.relics.BlackCat;


@SpireInitializer
public class WitchMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber {

	public static final Logger logger = LogManager.getLogger(WitchMod.class);

	private static final String MODNAME = "WitchMod";
	private static final String AUTHOR = "Gygrazok";
	private static final String DESCRIPTION = "v0.1\n Adds The Witch character";

	private static final Color WITCH_COLOR = CardHelper.getColor(90.0f, 90.0f, 100.0f);
	private static final String ASSETS_FOLDER = "images";

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


	public static final String getResourcePath(String resource) {
		return ASSETS_FOLDER + "/" + resource;
	}

	public WitchMod() {
		BaseMod.subscribeToPostInitialize(this);

		BaseMod.subscribeToEditCharacters(this);

		BaseMod.subscribeToEditRelics(this);

		BaseMod.subscribeToEditCards(this);
		
		BaseMod.subscribeToEditStrings(this);

		BaseMod.subscribeToEditKeywords(this);


		BaseMod.addColor(AbstractCardEnum.WITCH.toString(),
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

	@SuppressWarnings("deprecation")
	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
		ModPanel settingsPanel = new ModPanel();
		settingsPanel.addLabel("No settings", 400.0f, 700.0f, (me) -> {});
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

		Settings.isDailyRun = false;
		Settings.isTrial = false;
		Settings.isDemo = false;
	}


	public void receiveEditCharacters() {
		BaseMod.addCharacter(WitchCharacter.class, "The Witch", "WitchCharacter",
				AbstractCardEnum.WITCH.toString(), "The Witch",
				getResourcePath(CHAR_BUTTON), 
				getResourcePath(CHAR_PORTRAIT),
				WitchEnum.WITCH.toString());
	}


	public void receiveEditRelics() {
		RelicLibrary.add(new BlackCat());
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new Strike_Witch());
		BaseMod.addCard(new Defend_Witch());
		BaseMod.addCard(new Bewitch());
		
		//COMMON
		BaseMod.addCard(new Demonfyre());
		BaseMod.addCard(new FamiliarProtection());
		BaseMod.addCard(new BleedOut());
		BaseMod.addCard(new RitualOfDecay());
		
		//UNCOMMON
		BaseMod.addCard(new Athame());
		BaseMod.addCard(new Foresight());
		BaseMod.addCard(new Hexguard());
		BaseMod.addCard(new PainBolt());
		BaseMod.addCard(new CursedBlade());
		BaseMod.addCard(new TrollsBlood());

		//RARE
		BaseMod.addCard(new UnluckySeven());
		BaseMod.addCard(new UnnaturalEnergy());
		BaseMod.addCard(new DoomBlade());
		BaseMod.addCard(new Intelligence());
	}


	public void receiveEditStrings() {
		String relicStrings = Gdx.files.internal("strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	}


	public void receiveEditKeywords() {
		String[] persistent = {"persistent"};
		BaseMod.addKeyword(persistent, "Doesn't get discarded automatically at the end of the turn.");

		String[] recurrent = {"recurrent"};
		BaseMod.addKeyword(recurrent, "When played gets shuffled back into the deck instead of being discarded.");

	}

}
