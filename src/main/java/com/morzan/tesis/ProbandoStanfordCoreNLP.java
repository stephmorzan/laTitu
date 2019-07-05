package com.morzan.tesis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.morzan.beans.POSWord;
import com.morzan.ctes.RutasCte;
import com.morzan.ctes.TagCte;
import com.morzan.lexicon.LexiconAutoritas;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ProbandoStanfordCoreNLP {

	LexiconAutoritas lexiconAutoritas = LexiconAutoritas.getSingletonInstance();
	static ProbandoStanfordCoreNLP test = new ProbandoStanfordCoreNLP();
	static String estadoAnimo = "";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
/*
		ProbandoStanfordCoreNLP test = new ProbandoStanfordCoreNLP();
		test.getProps();
*/
		
		// Se hace el POS Tagger con Stanford CoreNLP
		MaxentTagger maxentTagger = new MaxentTagger(RutasCte.PATH_POSMODEL);
		String tag = maxentTagger.tagString(
//				"Voronina Tuve abundantes ganas de correr. Aburrida. Recién mañana me dan mis resultados y no sé qué puedo avanzar. Ni siquiera puedo salir con el chico que me gusta porque quiere avanzar su tesis, y Solo porque Yo comencé con esto. Me molesta un poco, pero ya qué más da. Al menos mi código anterior funciona, y eso me alegra un poco.");
				"Tuve un par de pruebas rápidas que hacer antes y luego del almuerzo tuve que"
				+ "repetir mis pruebas. Al parecer, no era un error lo que había detectado, sino"
				+ " un comportamiento del sistema que decidieron dejarlo tal y como está. "
				+ "Mañana comienzo a aprender cómo probar en una interfaz nueva; me parece"
				+ " tan raro y emocionante que me hayan promovido. Todo el día he tenido unos"
				+ " cólicos insoportables que no me dejaban caminar, mucho menos quedarme de"
				+ " pie. Lo que sí tuve el día anterior fue una tarde loca de sexo sin"
				+ " penetración pero de tantas ganas de saciarlas. Me encantaría volver"
				+ " a repetirlo, y excitándonos sin parar hasta terminar rendidos por el"
				+ " cansancio y llegar a dormir uno a lado del otro, bien abrigados y juntos;"
				+ " claro está que debo tener mucho cuidado, pues no deseo exponerme al peligro de terminar "
				+ "embarazada y estancar mi vida en estos momentos.");
////		String tag = maxentTagger.tagString("aguijonearíamos alcahuetearíamos aislaríamos");
		tag = tag.toLowerCase();
		List<String> eachTag = Arrays.asList((tag.split("\\s+")));
		List<POSWord> onlyWords = new ArrayList<>();
		System.out.println("Word " + "Stanford tag");
//		System.out.println("--------------------");
		String word, pos = "";
//
////		for (int i=0; i<eachTag.size(); i++) {
////			word = eachTag.get(i).split("_")[0];
////			pos = eachTag.get(i).split("_")[1];
////			System.out.println(word+ " " + pos);
////		}
//
//		// Se agregan a la lista solo palabras. Se retiran signos de puntuación y
//		// conjuciones
		for (int i = 0; i < eachTag.size(); i++) {
			word = eachTag.get(i).split("_")[0];
			String temppos = eachTag.get(i).split("_")[1];
			pos = temppos.replaceAll("[^\\D{1,4}]", "");
			if (!pos.startsWith("f") && !pos.startsWith("c")) {
				onlyWords.add(new POSWord(word, pos));
				System.out.println(word + " " + pos);
			}

		}

//		// Se hace POSTagging con OpenNLP Spanish Model Maxent

		System.out.println("Tiempo de inicio de todoLemmas: " + System.currentTimeMillis());
		for (POSWord posWord : onlyWords) {
			String lemma = null;
			String token = posWord.getWord();
			String temp2 = posWord.getPosTag();
			String temp1 = temp2.charAt(0) + "";

			System.out.println("Tiempo de inicio de este lemmas: " + System.currentTimeMillis());
//			lemma = test.hallarLemma(token, TagCte.ALL_LEMMAS);
			
			switch (temp1) {
			case "a":
				lemma = test.hallarLemma(token, TagCte.ADJETIVOS);
				System.out.println("encontré un adjetivo");
				break;

			case "d":
				lemma = test.hallarLemma(token, TagCte.DETERMINANTES);
				break;

			case "i":
				lemma = test.hallarLemma(token, TagCte.INTERJECCIONES);
				break;
			case "n":
				switch (temp2) {
				case "nc":
					lemma = test.hallarLemma(token, TagCte.SUST_OTROS);
					System.out.println(temp2);
					break;
				case "ncn":
					lemma = test.hallarLemma(token, TagCte.SUST_OTROS);
					break;
				case "ncp":
					lemma = test.hallarLemma(token, TagCte.SUST_COM_PLU);
					System.out.println(temp2);
					break;
				case "ncs":
					lemma = test.hallarLemma(token, TagCte.SUST_COM_SING);
					break;
				case "np":
					lemma = test.hallarLemma(token, TagCte.SUST_PROPIO);
					break;
				}
				break;

			case "p":
				lemma = test.hallarLemma(token, TagCte.PRONOMBRES);
				break;

			case "r":
				lemma = test.hallarLemma(token, TagCte.ADVERBIOS);
				break;
			case "s":
				lemma = test.hallarLemma(token, TagCte.PREPOSICIONES);
				break;
			case "v":
				String temp0 = temp2.substring(0, 2);
				switch (temp0) {
				case "va":
					lemma = test.hallarLemma(token, TagCte.V_HABER);
					System.out.println("Es un verbo auxiliar.");
					break;
				case "vs":
					lemma = test.hallarLemma(token, TagCte.V_SER);
					System.out.println("Es un verbo semiauxiliar.");
					break;
				case "vm":
					switch (temp2) {
					case "vmg":
						lemma = test.hallarLemma(token, TagCte.V_IND_GERU);
						break;
					case "vmic":
						lemma = test.hallarLemma(token, TagCte.V_IND_COND);
						break;
					case "vmif":
						lemma = test.hallarLemma(token, TagCte.V_IND_FUTURO);
						break;
					case "vmii":
						lemma = test.hallarLemma(token, TagCte.V_IND_PRETIMP);
						break;
					case "vmip":
						lemma = test.hallarLemma(token, TagCte.V_IND_PRESENTE);
						break;
					case "vmis":
						lemma = test.hallarLemma(token, TagCte.V_IND_PRETPERFSIMPLE);
						break;
					case "vmm":
						lemma = test.hallarLemma(token, TagCte.V_IND_IMPER);
						break;
					case "vmn":
						lemma = test.hallarLemma(token, TagCte.V_IND_INF);
						break;
					case "vmp":
						lemma = test.hallarLemma(token, TagCte.V_IND_PART);
						break;
					case "vmsi":
						lemma = test.hallarLemma(token, TagCte.V_SUBJ_PRETIMP);
						break;
					case "vmsp":
						lemma = test.hallarLemma(token, TagCte.V_SUBJ_PRESENTE);
						break;
					}
					break;
				}
				break;
			case "w":
				lemma = test.hallarLemma(token, TagCte.FECHAS);
				break;
			case "z":
				lemma = test.hallarLemma(token, TagCte.NUMERALES);
				break;
			case "word":
				lemma = test.hallarLemma(token, TagCte.OTROS);
				break;
			default:
				// Pasan por aca cuando sale un tipo no reconocido
				System.out.println("pasó porque no hay case definido " + temp2);
				lemma = test.leerProperties(token, TagCte.ALL_LEMMAS);
				break;
			}

			posWord.setLemma(lemma);
			System.out.println("Tiempo de fin de este lemmas: " + System.currentTimeMillis());
		}

		System.out.println("Tiempo de fin de todoLemmas: " + System.currentTimeMillis());
//		for (POSWord word1 : onlyWords) {
//			System.out.println(word1.getWord() + " \t" + word1.getPosTag() + " \t" + word1.getLemma());
//		}

		// contar cuántas palabras son positivas o negativas
		int contPosi = 0, contNega = 0;
//		int totPalabras = onlyWords.size();
		// TODO: Buscar si es palabra positiva o negativa
		for (POSWord word2 : onlyWords) {
			String lemma = word2.getLemma();
			String polaridad = "";
			polaridad = test.lexiconAutoritas.searchInLexicon(lemma);
			if (polaridad != null) {
				switch (polaridad) {
				case "p":
					System.out.println("Lemma posi: " + lemma);
					contPosi++;
					break;
				case "n":
					System.out.println("Lemma nega: " + lemma);
					contNega++;
					break;
				}
			}

		}

		System.out.println("Positivos = " + contPosi);
		System.out.println("Negativos = " + contNega);

		int diferencia = contPosi - contNega;
		
		//Categorizar texto
		if(contPosi>contNega) {
			estadoAnimo = ((diferencia >= 5) ? "Bienestar" : "Tensión");
		}else if (contPosi==contNega){
			estadoAnimo = "tranquilidad";
		}else {
			estadoAnimo = ((diferencia >= -5) ? "Apatía" : "Depresión");
		}
		
		String fecha = new Date()+"";
		System.out.println("Este texto su estado de ánimo es " + estadoAnimo);
		System.out.println("Fecha: " + fecha);
	}

	@SuppressWarnings("resource")
	public String getProps() throws IOException {
		InputStream inputStream;
		OutputStream outputStream;

		Properties prop = new Properties();
		Properties prop2 = new Properties();
		String archivo = "todos_lemmas.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(archivo);
		outputStream = new FileOutputStream("results2.properties");

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("prop file " + archivo + " no se encuentra en el classpath");
		}

		MaxentTagger maxentTagger = new MaxentTagger(
				"../tesis/posmodels_resources/stanford-postagger-full-2018-10-16/models/spanish-distsim.tagger");

		for (int i = 1; i < 236684; i++) {
			String value = prop.getProperty(i + "");
			String tag = maxentTagger.tagString(value);
			List<String> eachTag = Arrays.asList((tag.split("\\s+")));

			String word, pos = "";

			for (int j = 0; j < eachTag.size(); j++) {
				word = eachTag.get(j).split("_")[0];
				pos = eachTag.get(j).split("_")[1];
				if (pos.charAt(0) != 'f') {
					System.out.println(word + " " + pos);
					prop2.setProperty(word, pos);
				}

			}
		}

		prop2.store(outputStream, null);

		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}

	public String hallarLemma(String token, String archivo) throws IOException {
		String lemma = null;
		lemma = leerProperties(token, archivo);
		if (lemma == null) {
			lemma = leerProperties(token, TagCte.ALL_LEMMAS);
			if (lemma == null) {
				// editor.agregarLemma(token, TagCte.ALL_LEMMAS);
			}
			// editor.agregarLemma(token, archivo);
		}
		return lemma;
	}

	public String leerProperties(String token, String archivo) throws IOException {
		InputStream inputStream;
		Properties prop = new Properties();
//		String archivo = TagCte.ALL_LEMMAS;

		inputStream = getClass().getClassLoader().getResourceAsStream(archivo);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("prop file " + archivo + " no se encuentra en el classpath");
		}

		String lemma = prop.getProperty(token);
		System.out.println(token + "\t" + lemma);

		return lemma;
	}
}
