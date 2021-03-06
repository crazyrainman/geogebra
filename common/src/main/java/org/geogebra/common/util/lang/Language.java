package org.geogebra.common.util.lang;

import java.util.Locale;

import org.geogebra.common.plugin.EuclidianStyleConstants;
import org.geogebra.common.util.debug.Log;

import com.himamis.retex.editor.share.util.Unicode;

/**
 * Collection of which languages are official in which countries (only includes
 * languages supported in GeoGebra)
 * 
 * @author michael@geogebra.org
 *         http://en.wikipedia.org/wiki/List_of_official_languages
 *         http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
 */
@SuppressWarnings("javadoc")
public enum Language {

	// need to be in Alphabetical order so they appear in the menu in the right
	// order
	// Afrikaans(null, false, "af","af", "Afrikaans", Country.SouthAfrica),

	Albanian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"sq", "sq", "Albanian / Gjuha Shqipe"),

	Amharic(
					EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
					"\u134b\u12ed\u120d", false, "am", "am",
			"Amharic / \u0041\u006d\u0061\u0072\u0259\u00f1\u00f1\u0061"),

	Arabic(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			null, null, true, "ar", "ar",
							Unicode.LEFT_TO_RIGHT_MARK + "Arabic"
									+ Unicode.LEFT_TO_RIGHT_MARK + " / "
									+ Unicode.RIGHT_TO_LEFT_MARK
									+ "\u0627\u0644\u0639\u0631\u0628\u064A\u0629"
					+ Unicode.RIGHT_TO_LEFT_MARK),

	Arabic_Morocco(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null,
			true, "arMA", "ar_MA", "ar",
			Unicode.LEFT_TO_RIGHT_MARK + "Arabic (Morocco)"
					+ Unicode.LEFT_TO_RIGHT_MARK + " / " + Unicode.RIGHT_TO_LEFT_MARK
					+ "\u0627\u0644\u0639\u0631\u0628\u064A\u0629 (\u0627\u0644\u0645\u063A\u0631\u0628)"
					+ Unicode.RIGHT_TO_LEFT_MARK),

	Arabic_Tunisia(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null,
			true, "arTN", "ar_TN", "ar",
			Unicode.LEFT_TO_RIGHT_MARK + "Arabic (Tunisia)"
					+ Unicode.LEFT_TO_RIGHT_MARK + " / " + Unicode.RIGHT_TO_LEFT_MARK
					+ "\u0627\u0644\u0639\u0631\u0628\u064A\u0629 (\u062A\u0648\u0646\u0633)"
					+ Unicode.RIGHT_TO_LEFT_MARK),

	Armenian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, "\u0570",
			true, "hy", "hy",
			"Armenian / \u0540\u0561\u0575\u0565\u0580\u0565\u0576"),

	// Azerbaijani(null, null, false, "az", "az", "Azerbaijani",
	// Country.Azerbaijan),

	Basque(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "eu", "eu", "eu",
			"Basque / Euskara"),
	// fudge to get right flag

	Bosnian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "bs", "bs",
			"Bosnian / \u0431\u043E\u0441\u0430\u043D\u0441\u043A\u0438"),

	Bulgarian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "bg", "bg",
			"Bulgarian / \u0431\u044A\u043B\u0433\u0430\u0440\u0441\u043A\u0438 \u0435\u0437\u0438\u043A"),

	Catalan(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "ca", "ca", "ca",
			"Catalan / Catal\u00E0"),
	// fudge to get right flag

	Valencian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "caXV", "ca_XV", "ca",
			"Catalan / Catal\u00E0 (Valenci\u00E0)"),
	// fudge to get right flag

	Chinese_Simplified(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
			"\u984F", true, "zhCN", "zh_CN", "zh",
			"Chinese Simplified / \u7B80\u4F53\u4E2D\u6587"),

	Chinese_Traditional(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
			"\u984F", true, "zhTW", "zh_TW", "zh",
			"Chinese Traditional / \u7E41\u9AD4\u4E2D\u6587"),

	Croatian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "hr", "hr",
			"Croatian / Hrvatska"),

	Czech(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "cs", "cs",
			"Czech / \u010Ce\u0161tina"),

	Danish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"da", "da", "Danish / Dansk"),

	Dutch_Belgium(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "nl", "nl", "nl_BE",
			"Dutch / Nederlands (Belgi\u00eb)"),

	Dutch(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "nlNL", "nl_NL", "nl",
			"Dutch / Nederlands (Nederland)"),

	English_US(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_DOLLAR + "" + "", null, true, "en", "en",
			"English (US)"),

	English_UK(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_POUND + "", null, true, "enGB", "en_GB", "en",
			"English (UK)"),

	English_Australia(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_DOLLAR + "", null, true, "enAU", "en_AU", "en",
			"English (Australia)"),

	// could have esperanto.png for flag (but we don't)
	// Esperanto(null, false, "eo","eo", "Esperanto", "esperanto"),

	Estonian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "et", "et",
			"Estonian / Eesti keel"),

	Filipino(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"tl", "tl", "fil", "Filipino"),

	Finnish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "fi", "fi",
			"Finnish / Suomi"),

	French(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "fr", "fr",
			"French / Fran\u00E7ais"),

	Galician(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "gl", "gl", "gl",
			"Galician / Galego"),
	// fudge to get right flag

	Georgian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, "\u10d8",
			true, "ka", "ka",
			"Georgian / \u10E5\u10D0\u10E0\u10D7\u10E3\u10DA\u10D8 \u10D4\u10DC\u10D0"),

	// German must be before German_Austria
	German(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_DOT,
			Unicode.CURRENCY_EURO + "",
			null, true, "de", "de", "German / Deutsch"),

	German_Austria(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_DOT,
			Unicode.CURRENCY_EURO + "", null, true, "deAT", "de_AT", "de",
			"German / Deutsch (\u00D6sterreich)"),

	Greek(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "el", "el",
			"Greek / \u0395\u03BB\u03BB\u03B7\u03BD\u03B9\u03BA\u03AC"),

	Hebrew(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_SHEKEL + "", "\u05d9", true, "iw", "iw", "he",
			"Hebrew / \u05E2\u05B4\u05D1\u05B0\u05E8\u05B4\u05D9\u05EA"),

	Hindi(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_INDIAN_RUPEE + "", "\u0be7", true, "hi", "hi",
			"Hindi / \u092E\u093E\u0928\u0915 \u0939\u093F\u0928\u094D\u0926\u0940"),

	Hungarian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_DOT,
			Unicode.CURRENCY_EURO + "", null, true, "hu", "hu",
			"Hungarian / Magyar"),

	Icelandic(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null,
			true, "is", "is", "Icelandic / \u00CDslenska"),

	Indonesian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null,
			true, "in", "in", "id", "Indonesian / Bahasa Indonesia"),

	Italian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "it", "it",
			"Italian / Italiano"),

	// Irish(null, false, "ga", "ga", "Irish / Gaeilge", Country.Ireland),

	Japanese(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_YEN + "", "\uff9d", true, "ja", "ja",
			"Japanese / \u65E5\u672C\u8A9E"),

	// Kannada("\u1103", false, "kn","kn", "Kannada", Country.India),

	Kazakh(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"kk", "kk",
			"Kazakh / \u049A\u0430\u0437\u0430\u049B \u0442\u0456\u043B\u0456"),

	Korean(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_WON + "", "\u1103", true, "ko", "ko",
			"Korean / \uD55C\uAD6D\uB9D0"),

	Latvian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "lv", "lv",
			"Latvian / Latvie\u0161u valoda"),

	Lithuanian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "lt", "lt",
			"Lithuanian / Lietuvi\u0173 kalba"),

	Malay(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"ms", "ms", "Malay / Bahasa Malaysia"),

	// Malayalam("\u0D2E", false, "ml","ml",
	// "Malayalam / \u0D2E\u0D32\u0D2F\u0D3E\u0D33\u0D02", Country.India),

	Macedonian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "mk", "mk",
			"Macedonian / \u041C\u0430\u043A\u0435\u0434\u043E\u043D\u0441\u043A\u0438 \u0458\u0430\u0437\u0438\u043A"),

	// Marathi("\u092e", false, "mr","mr",
	// "Marathi / \u092E\u0930\u093E\u0920\u0940", Country.India),

	Mongolian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_TUGHRIK + "", null, true, "mn", "mn",
			"Mongolian / \u041C\u043E\u043D\u0433\u043E\u043B \u0445\u044D\u043B"),

	Nepalese(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_RUPEE + "", "\u0947", true, "ne", "ne",
			"Nepalese / \u0928\u0947\u092A\u093E\u0932\u0940"),

	Norwegian_Bokmal(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
			null, true, "noNO", "no_NB", "nb", "Norwegian / Bokm\u00e5l"),

	Norwegian_Nynorsk(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
			null, true, "noNONY", "no_NN", "nn", "Norwegian / Nynorsk"),

	Persian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"fa", "fa", "Persian / \u0641\u0627\u0631\u0633\u06CC"),

	Polish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_DOT,
			Unicode.CURRENCY_EURO + "",
			null, true, "pl", "pl", "Polish / J\u0119zyk polski"),

	// use Brazilian as the root (ie not ptBR) as there are more speakers
	Portuguese_Brazil(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null,
			null, true, "pt", "pt", "Portuguese / Portugu\u00EAs (Brasil)"),

	Portuguese_Portugal(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "ptPT", "pt_PT", "pt",
			"Portuguese / Portugu\u00EAs (Portugal)"),

	Romanian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "ro", "ro",
			"Romanian /  Rom\u00E2n\u0103"),

	Russian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, "\u0439",
			true, "ru", "ru",
			"Russian / \u0420\u0443\u0441\u0441\u043A\u0438\u0439 \u044F\u0437\u044B\u043A"),

	Sinhala(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_RUPEE + "", "\u0d9a", true, "si", "si",
			"Sinhala / \u0DC3\u0DD2\u0D82\u0DC4\u0DBD"),

	Serbian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "sr", "sr",
			"Serbian / \u0441\u0440\u043F\u0441\u043A\u0438"),

	Slovak(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "sk", "sk",
			"Slovak / Slovensk\u00FD jazyk"),

	Slovenian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "sl", "sl",
			"Slovenian / Sloven\u0161\u010Dina"),

	Spanish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_DOLLAR + "", null, true, "es", "es",
			"Spanish / Espa\u00F1ol"),

	Spanish_UY(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_DOLLAR + "", null, true, "esUY", "es_UY", "es",
			"Spanish / Espa\u00F1ol (Uruguay)"),

	Spanish_ES(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "esES", "es_ES", "es",
			"Spanish / Espa\u00F1ol (Espa\u00F1a)"),

	Swedish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "sv", "sv",
			"Swedish / Svenska"),

	Tamil(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_RUPEE + "", "\u0be7", true, "ta", "ta",
			"Tamil / \u0BA4\u0BAE\u0BBF\u0BB4\u0BCD"),

	// Tajik(null, false, "tg","tg", "Tajik", Country.Tajikistan),

	// Telugu("\u0C24", false, "te","te",
	// "Telugu / \u0C24\u0C46\u0C32\u0C41\u0C17\u0C41", Country.India),

	Thai(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_BAHT + "", null, true, "th", "th",
			"Thai / \u0E20\u0E32\u0E29\u0E32\u0E44\u0E17\u0E22"),

	Turkish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_EURO + "", null, true, "tr", "tr",
			"Turkish / T\u00FCrk\u00E7e"),

	Ukrainian(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null,
			true, "uk", "uk",
			"Ukrainian / \u0423\u043A\u0440\u0430\u0457\u043D\u0441\u044C\u043A\u0430 \u043C\u043E\u0432\u0430"),

	Uyghur(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"ug", "ug", "Uyghur"),

	Vietnamese(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_DONG + "", null, true, "vi", "vi",
			"Vietnamese / Ti\u1EBFng Vi\u1EC7t"),

	Welsh(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"cy", "cy", "cy", "Welsh / Cymraeg"),
	// fudge to get right flag

	Xhosa(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE, null, null, true,
			"xh", "xh", "Xhosa / isiXhosa"),

	Yiddish(EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE,
			Unicode.CURRENCY_SHEKEL + "", "\u05d9\u05b4", true, "ji", "ji",
			"yi",
			"Yiddish / \u05D9\u05D9\u05B4\u05D3\u05D9\u05E9");

	// Interlingua(null, true, "ia", "ia", "Interlingua",
	// Country.UnitedStatesofAmerica);

	final public String localeGWT;
	final public String locale;
	final public String localeISO6391;
	final public String name;
	// official counties which speak that language
	// used to determine whether to put in release versions
	final public boolean fullyTranslated;
	/**
	 * test characters to get a font that can display the correct symbols for
	 * each language Also used to register the fonts so that JLaTeXMath can
	 * display other Unicode blocks
	 */
	final private String testChar;
	final private String currency;

	// https://en.wikipedia.org/wiki/Right_angle
	private int rightAngleStyle = EuclidianStyleConstants.RIGHT_ANGLE_STYLE_SQUARE;

	/**
	 */
	Language(int rightAngleStyle, String currency, String testChar,
			boolean fullyTranslated, String locale, String localeGWT,
			String localeISO6391, String name) {
		this.rightAngleStyle = rightAngleStyle;
		this.currency = currency == null ? "$" : currency;
		this.locale = locale;
		this.localeGWT = localeGWT;
		this.name = name;
		this.localeISO6391 = localeISO6391;
		this.fullyTranslated = fullyTranslated;
		this.testChar = testChar;
	}

	Language(int rightAngleStyle, String currency, String testChar,
			boolean fullyTranslated, String locale, String localeGWT,
			String name) {
		this(rightAngleStyle, currency, testChar, fullyTranslated, locale,
				localeGWT, locale, name);
	}

	/**
	 * @param language
	 *            ISO code
	 * @return closest constant
	 */
	final public static Language getLanguage(String language) {
		for (Language l : Language.values()) {
			// language could be "ca" or "caXV"
			if (l.locale.equals(language)) {
				return l;
			}
		}

		for (Language l : Language.values()) {
			if (l.locale.substring(0, 2).equals(language)) {
				return l;
			}
		}

		Log.error("language not recognized: " + language);
		return null;
	}

	final public static String getDisplayName(String ggbLangCode) {

		// eg change en_GB to enGB
		String shortLangCode = ggbLangCode.replaceAll("_", "");

		for (Language l : Language.values()) {
			if (l.locale.equals(shortLangCode)
					|| l.localeGWT.replaceAll("_", "").equals(shortLangCode)) {
				return l.name;
			}
		}

		Log.error("language not found: " + shortLangCode);

		return null;
	}

	/**
	 * 
	 * @param language
	 *            two letter code
	 * @return
	 */
	final public static String getTestChar(String language) {
		for (Language l : Language.values()) {
			if (l.locale.startsWith(language)) {
				return l.testChar == null ? "a" : l.testChar;
			}
		}

		Log.error("language not found: " + language);
		return "a";
	}

	final public static String getClosestGWTSupportedLanguage(
			String browserLangCode) {
		String normalizedLanguage = (browserLangCode + "")
				.toLowerCase(Locale.US)
				.replace("-", "_");

		if ("he".equals(normalizedLanguage)) {
			normalizedLanguage = "iw";
		} else if ("zh_hans_cn".equals(normalizedLanguage)) {
			normalizedLanguage = "zh_cn";
		} else if ("zh_hant_tw".equals(normalizedLanguage)) {
			normalizedLanguage = "zh_tw";
		}
		// on iOS it's nb_no
		else if (normalizedLanguage.startsWith("nb")) {
			normalizedLanguage = "no_nb";
		} else if ("nn".equals(normalizedLanguage)) {
			normalizedLanguage = "no_nn";
		}

		// browserLangCode example: en-US, en-GB, pt-BR, pt-pt, and de-DE
		for (Language lang : Language.values()) {
			if (lang.localeGWT.equalsIgnoreCase(normalizedLanguage)) {
				return lang.localeGWT;
			}
		}
		// look for mother language in the hierarchy ie. the first two
		// characters
		for (Language lang : Language.values()) {
			if (lang.localeGWT
					.equalsIgnoreCase(normalizedLanguage.substring(0, 2))) {
				return lang.localeGWT;
			}

		}
		return "en";

	}

	/**
	 * @param language
	 * @return the currency belonging to the given language (default Dollar)
	 */
	final public static String getCurrency(String language) {
		for (Language l : Language.values()) {
			if (l.localeGWT.equals(language)) {
				return l.currency;
			}
		}
		return Unicode.CURRENCY_DOLLAR + "";
	}

	final public static int getRightAngleStyle(String language) {
		return getLanguage(language).getRightAngleStyle();
	}

	final public int getRightAngleStyle() {
		return this.rightAngleStyle;
	}

	final public boolean hasTranslatedKeyboard() {
		switch (this) {
			case Chinese_Simplified:
			case Chinese_Traditional:
				return false;
			default:
				return true;
		}
	}
}
