package magento;

import java.util.Random;

public class Parameters {

	Random rnd = new Random();

	String[] firstNames = { "Haruki", "Miyuki", "Ryota", "Yuki", "Sakura", "Kaito", "Aya", "Ren", "Hikari", "Kazuki" };
	String[] lastNames = { "Tanaka", "Suzuki", "Yamamoto", "Nakamura", "Ito", "Kobayashi", "Watanabe", "Ishikawa",
			"Kimura", "Saito" };
	String commonPass = "Moca@123";
	
	int rndIndex = rnd.nextInt(10);

	int rndEmailId = rnd.nextInt(9117);
	
	String email = firstNames[rndIndex] + lastNames[rndIndex] + rndEmailId + "@gmail.com";
}
